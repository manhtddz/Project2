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
import model.newentity.NewOrUpdateTenants;
import model.entity.Cars;
import model.entity.Tenants;
import model.entity.Users;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class TenantDAO {

    public boolean addNew(NewOrUpdateTenants newTenant) throws DatabaseException {
        Tenants tenant = new Tenants();
        tenant.setId(newTenant.setId());
        tenant.setName(newTenant.getName());
        tenant.setPhone(newTenant.getPhone());
        tenant.setAddress(newTenant.getAddress());
        tenant.setEmail(newTenant.getEmail());
        tenant.setOpenHours(newTenant.getOpenHours());
        tenant.setCloseHours(newTenant.getCloseHours());
        tenant.setDelFlag(newTenant.getDelFlag());
        tenant.setCreated(newTenant.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .insertOne(tenant);
        }
        return true;
    }

    public List<Tenants> findAllTenants() {
        List<Tenants> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .find(delFilter)
                    .forEach(results::add);
        }

        return results;
    }

    public boolean update(Integer id, NewOrUpdateTenants newTenant) {
        Tenants tenant = new Tenants();
        tenant.setId(id);
        tenant.setName(newTenant.getName());
        tenant.setPhone(newTenant.getPhone());
        tenant.setAddress(newTenant.getAddress());
        tenant.setEmail(newTenant.getEmail());
        tenant.setOpenHours(newTenant.getOpenHours());
        tenant.setCloseHours(newTenant.getCloseHours());
        tenant.setDelFlag(newTenant.getDelFlag());
        tenant.setCreated(newTenant.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .replaceOne(filter, tenant);
        }

        return true;
    }

    public boolean delete(int id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);
        Bson tenantFilter = Filters.eq("tenant_id", id);
        Bson delFilter = Filters.eq("del_flag", 0);
        Bson carFilter = Filters.and(tenantFilter, delFilter);
        Bson tenantExisted = Filters.and(idFilter, delFilter);
        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Tenants> col = DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class);

            MongoCollection<Cars> carCol = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class);

            MongoCollection<Users> userCol = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class);
            long carCount = carCol.countDocuments(carFilter);
            if (carCount == 0) {
                col.updateOne(tenantExisted, Updates.set("del_flag", 1));
                userCol.updateOne(tenantFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    Map<Integer, Tenants> findTenantByIds(MongoClient client, Set<Integer> tenantsID) {
        Map<Integer, Tenants> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<Tenants> collection = db.getCollection(Tenants.COLLECTION, Tenants.class);

        Bson filter = Filters.in("id", tenantsID);
        collection.find(filter)
                .forEach(it -> map.put(it.getId(), it));

        return map;
    }

    public Tenants findTenantById(Integer tenantID) {
        Tenants tenant = new Tenants();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("id", tenantID);
            tenant = DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .find(filter)
                    .first();
        }
        return tenant;
    }

    public Tenants findTenantByEmail(String email) {
        Tenants tenant = new Tenants();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("email", email);
            tenant = DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .find(filter)
                    .first();
        }
        return tenant;
    }

}
