package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.newentity.NewOrUpdateUsers;
import model.entity.Users;
import model.entity.Tenants;
import model.searchmodel.UserSearch;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class UserDAO {

    public List<Users> findAllManagers() {
        List<Users> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson roleFilter = Filters.eq("role", 1);
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson filter = Filters.and(roleFilter, delFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Users::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> map = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(manager -> {
                manager.setTenant(map.get(manager.getTenantId()));
            });
        }
        return results;
    }

    public List<Users> findAllManagersWithTenant(int tenantId) {
        List<Users> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson roleFilter = Filters.eq("role", 1);
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);
            Bson filter = Filters.and(roleFilter, delFilter, tenantFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Users::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> map = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(manager -> {
                manager.setTenant(map.get(manager.getTenantId()));
            });
        }
        return results;
    }

    public List<Users> findAllAdmin() {
        List<Users> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("role", 0);

            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(filter)
                    .forEach(results::add);
        }

        return results;
    }

    public Users findUserByEmail(String email) {
        try (MongoClient client = DBUtil.open()) {
            Bson emailFilter = Filters.eq("email", email);
            Bson filter = Filters.and(emailFilter);
            Users user = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(filter)
                    .first();

            Integer tenantsID = user.getTenantId();
            Tenants tenant = new TenantDAO().findTenantById(tenantsID);
            user.setTenant(tenant);

            return user;
        }
    }
    public Users findUserByEmailForReg(String email) {
        try (MongoClient client = DBUtil.open()) {
            Bson emailFilter = Filters.eq("email", email);
            Bson filter = Filters.and(emailFilter);
            Users user = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(filter)
                    .first();

            return user;
        }
    }

    public boolean delete(int id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Users> col = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean addNewManager(NewOrUpdateUsers newManager) throws DatabaseException {
        Users manager = new Users();
        manager.setId(newManager.setId());
        manager.setTenantId(newManager.getTenantId());
        manager.setName(newManager.getName());
        manager.setPhone(newManager.getPhone());
        manager.setEmail(newManager.getEmail());
        manager.setPassword(newManager.getPassword());
        manager.setRole(1);
        manager.setDelFlag(newManager.getDelFlag());
        manager.setCreated(newManager.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .insertOne(manager);
        }
        return true;
    }

    public boolean addNewAdmin(NewOrUpdateUsers newAdmin) throws DatabaseException {
        Users admin = new Users();
        admin.setName(newAdmin.getName());
        admin.setPhone(newAdmin.getPhone());
        admin.setEmail(newAdmin.getEmail());
        admin.setPassword(newAdmin.getPassword());
        admin.setRole(0);
        admin.setDelFlag(newAdmin.getDelFlag());
        admin.setCreated(newAdmin.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .insertOne(admin);
        }
        return true;
    }

    public boolean updateManagers(Integer id, NewOrUpdateUsers newManager) throws DatabaseException {
        Users manager = new Users();
        manager.setId(id);
        manager.setTenantId(newManager.getTenantId());
        manager.setName(newManager.getName());
        manager.setPassword(newManager.getPassword());
        manager.setPhone(newManager.getPhone());
        manager.setEmail(newManager.getEmail());
        manager.setRole(1);
        manager.setDelFlag(newManager.getDelFlag());
        manager.setCreated(newManager.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .replaceOne(filter, manager);
        }
        return true;
    }

    public boolean updateAdmin(Integer id, NewOrUpdateUsers newAdmin) throws DatabaseException {
        Users admin = new Users();
        admin.setId(id);
        admin.setName(newAdmin.getName());
        admin.setPhone(newAdmin.getPhone());
        admin.setPassword(newAdmin.getPassword());
        admin.setEmail(newAdmin.getEmail());
        admin.setRole(0);
        admin.setDelFlag(newAdmin.getDelFlag());
        admin.setCreated(newAdmin.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .replaceOne(filter, admin);
        }
        return true;
    }

    public List<Users> searchUser(UserSearch userSearch) {
        TenantDAO tenantDAO = new TenantDAO();
        List<Users> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Tenants tenant = userSearch.getTenant();
            String name = userSearch.getName();
            Bson tenantFilter = Filters.eq("tenant_id", tenant.getId());
            Bson roleFilter = Filters.eq("role", 1);
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson activeFilter = Filters.and(roleFilter, delFilter);
            Document nameFilter = new Document("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));

            MongoCollection<Users> col = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class);

            List<Bson> bsonList = new ArrayList<>();
            bsonList.add(roleFilter);
            bsonList.add(delFilter);

            if (tenant.getName() != "All tenants") {
                bsonList.add(tenantFilter);
            }
            if (!StringUtils.isEmpty(name)) {
                bsonList.add(nameFilter);
            }

            if (bsonList.size() != 2) {
                Bson filter = Filters.and(bsonList.toArray(new Bson[bsonList.size()]));
                col.find(filter)
                        .forEach(results::add);
            } else {
                col.find(activeFilter)
                        .forEach(results::add);
            }

            Set<Integer> tenantIds = results.stream()
                    .map(Users::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = tenantDAO.findTenantByIds(client, tenantIds);
            results.forEach(b -> {
                b.setTenant(tenantsMap.get(b.getTenantId()));
            });
        }

        return results;
    }
}
