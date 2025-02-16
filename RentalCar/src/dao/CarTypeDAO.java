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
import model.newentity.NewOrUpdateCarType;
import model.entity.CarType;
import org.bson.conversions.Bson;
import utils.DBUtil;


public class CarTypeDAO {

    public boolean addNew(NewOrUpdateCarType newCarType) throws DatabaseException {
        CarType carType = new CarType();
        carType.setId(newCarType.setId());
        carType.setName(newCarType.getName());
        carType.setDescription(newCarType.getDescription());
        carType.setCapacity(newCarType.getCapacity());
        carType.setDelFlag(newCarType.getDelFlag());
        carType.setCreated(newCarType.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .insertOne(carType);
        }
        return true;
    }

    public List<CarType> findAllCarTypes() {
        List<CarType> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .find()
                    .forEach(results::add);
        }

        return results;
    }

    public List<CarType> findNotDeletedCarTypes() {
        List<CarType> results = new ArrayList<>();
        Bson delFilter = Filters.eq("del_flag", 0);
        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .find(delFilter)
                    .forEach(results::add);
        }

        return results;
    }

    public boolean update(Integer id, NewOrUpdateCarType newCarType) {
        CarType carType = new CarType();
        carType.setId(id);
        carType.setName(newCarType.getName());
        carType.setDescription(newCarType.getDescription());
        carType.setCapacity(newCarType.getCapacity());
        carType.setDelFlag(newCarType.getDelFlag());
        carType.setCreated(newCarType.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .replaceOne(filter, carType);
        }

        return true;
    }

    public boolean delete(int id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<CarType> col = DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    Map<Integer, CarType> findCarTypeByIds(MongoClient client, Set<Integer> carTypeIds) {
        Map<Integer, CarType> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<CarType> collection = db.getCollection(CarType.COLLECTION, CarType.class);

        Bson filter = Filters.in("id", carTypeIds);
        collection.find(filter)
                .forEach(it -> map.put(it.getId(), it));

        return map;
    }

    public CarType findCarTypeById(int id) {
        CarType results = new CarType();
        Bson filter = Filters.eq("id", id);
        try (MongoClient client = DBUtil.open()) {
            results = DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .find(filter)
                    .first();
        }

        return results;
    }
}
