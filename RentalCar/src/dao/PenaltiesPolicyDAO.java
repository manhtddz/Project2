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
import model.newentity.NewOrUpdatePenaltiesPolicy;
import model.entity.PenaltiesPolicy;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class PenaltiesPolicyDAO {

    public boolean addNew(NewOrUpdatePenaltiesPolicy newPolicy) throws DatabaseException {
        PenaltiesPolicy policy = new PenaltiesPolicy();
        policy.setId(newPolicy.setId());
        policy.setName(newPolicy.getName());
        policy.setPrice(newPolicy.getPrice());
        policy.setDelFlag(newPolicy.getDelFlag());
        policy.setCreated(newPolicy.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                    .insertOne(policy);
        }
        return true;
    }

    public List<PenaltiesPolicy> findAllPolicies() {
        List<PenaltiesPolicy> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                    .find(delFilter)
                    .forEach(results::add);
        }

        return results;
    }

    public boolean update(Integer id, NewOrUpdatePenaltiesPolicy newPolicy) {
        PenaltiesPolicy policy = new PenaltiesPolicy();
        policy.setName(newPolicy.getName());
        policy.setPrice(newPolicy.getPrice());
        policy.setDelFlag(newPolicy.getDelFlag());
        policy.setCreated(newPolicy.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                    .replaceOne(filter, policy);
        }

        return true;
    }

    public boolean delete(Integer id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<PenaltiesPolicy> col = DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }


    Map<List<Integer>, List<PenaltiesPolicy>> findPoliciesByIds(MongoClient client, Set<List<Integer>> policyIDs) {
        Map<List<Integer>, List<PenaltiesPolicy>> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<PenaltiesPolicy> collection = db.getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class);

        for (List<Integer> listPolicyID : policyIDs) {
            List<PenaltiesPolicy> listPolicy = new ArrayList<>();
            for (Integer polcyID : listPolicyID) {
                Bson filter = Filters.in("id", polcyID);
                collection.find(filter)
                        .forEach(it -> listPolicy.add(it));
            }
            map.put(listPolicyID, listPolicy);
        }
        return map;

    }

    public PenaltiesPolicy findPolicyById(int policyID) {
        PenaltiesPolicy results = new PenaltiesPolicy();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("id", policyID);
            results = DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                    .find(filter)
                    .first();
        }
        return results;
    }

    List<PenaltiesPolicy> findListPolicyByIds(MongoClient client, List<Integer> policyIDs) {
        List<PenaltiesPolicy> result = new ArrayList<>();
        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<PenaltiesPolicy> collection = db.getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class);
        for (Integer policyID : policyIDs) {
            Bson filter = Filters.eq("id", policyID);
            PenaltiesPolicy po = collection
                    .find(filter)
                    .first();
            result.add(po);
        }
        return result;
    }
}
