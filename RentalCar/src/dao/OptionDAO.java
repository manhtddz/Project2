
package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Sorts.ascending;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.newentity.NewOrUpdateOptions;
import model.entity.Options;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class OptionDAO {

    public boolean addNew(NewOrUpdateOptions newOption) throws DatabaseException {
        Options option = new Options();
        option.setId(newOption.setId());
        option.setName(newOption.getName());
        option.setDescription(newOption.getDescription());
        option.setPrice(newOption.getPrice());
        option.setDelFlag(newOption.getDelFlag());
        option.setCreated(newOption.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class)
                    .insertOne(option);
        }
        return true;
    }

    public List<Options> findAllOptions() {
        List<Options> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class)
                    .find(delFilter)
                    .sort(ascending("id"))
                    .forEach(results::add);
        }

        return results;
    }

    public boolean update(Integer id, NewOrUpdateOptions newOption) {
        Options option = new Options();
        option.setName(newOption.getName());
        option.setDescription(newOption.getDescription());
        option.setPrice(newOption.getPrice());
        option.setDelFlag(newOption.getDelFlag());
        option.setCreated(newOption.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class)
                    .replaceOne(filter, option);
        }

        return true;
    }

    public boolean delete(Integer id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Options> col = DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    Map<List<Integer>, List<Options>> findOptionsByIds(MongoClient client, Set<List<Integer>> optionIDs) {
        Map<List<Integer>, List<Options>> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<Options> collection = db.getCollection(Options.COLLECTION, Options.class);

        for (List<Integer> listOptionID : optionIDs) {
            List<Options> listOption = new ArrayList<>();
            for (Integer optionID : listOptionID) {
                Bson filter = Filters.in("id", optionID);
                collection.find(filter)
                        .forEach(it -> listOption.add(it));
            }
            map.put(listOptionID, listOption);
        }

        return map;
    }

    public Options findOptionById(int optionID) {
        Options results = new Options();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("id", optionID);
            results = DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class)
                    .find(filter)
                    .first();
        }
        return results;
    }


    List<Options> findListOptionByIds(MongoClient client, List<Integer> optionIDs) {
        List<Options> result = new ArrayList<>();
        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<Options> collection = db.getCollection(Options.COLLECTION, Options.class);
        for (Integer optionId : optionIDs) {
            Bson filter = Filters.eq("id", optionId);
            Options op = collection
                    .find(filter)
                    .first();
            result.add(op);
        }
        return result;
    }
}
