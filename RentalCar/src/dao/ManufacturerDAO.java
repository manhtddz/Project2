package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.newentity.NewOrUpdateManufacturers;
import model.entity.Manufacturers;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class ManufacturerDAO {

    public boolean addNew(NewOrUpdateManufacturers newManu) throws DatabaseException {
        Manufacturers manu = new Manufacturers();
        manu.setId(newManu.setId());
        manu.setName(newManu.getName());
        manu.setDelFlag(newManu.getDelFlag());
        manu.setCreated(newManu.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .insertOne(manu);
        }
        return true;
    }

    public List<Manufacturers> findAllManufacturers() {
        List<Manufacturers> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .find()
                    .forEach(results::add);
        }

        return results;
    }

    public List<Manufacturers> findNotDeletedManufacturers() {
        List<Manufacturers> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .find(delFilter)
                    .forEach(results::add);
        }

        return results;
    }

    public boolean update(Integer id, NewOrUpdateManufacturers newManu) {
        Manufacturers manu = new Manufacturers();
        manu.setId(id);
        manu.setName(newManu.getName());
        manu.setDelFlag(newManu.getDelFlag());
        manu.setCreated(newManu.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .replaceOne(filter, manu);
        }

        return true;
    }

    public boolean delete(Integer id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Manufacturers> col = DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    Map<Integer, Manufacturers> findManuByIds(MongoClient client, Set<Integer> manuIds) {
        Map<Integer, Manufacturers> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<Manufacturers> collection = db.getCollection(Manufacturers.COLLECTION, Manufacturers.class);

        Bson filter = Filters.in("id", manuIds);
        collection.find(filter)
                .forEach(it -> map.put(it.getId(), it));

        return map;
    }

    public Manufacturers findManufacturerById(int manuID) {
        Manufacturers results = new Manufacturers();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("id", manuID);
            results = DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .find(filter)
                    .first();
        }
        return results;
    }

}
