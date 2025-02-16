package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Sorts.ascending;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.newentity.NewOrUpdateClients;
import model.entity.Clients;
import model.entity.Tenants;
import model.searchmodel.ClientSearch;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class ClientDAO {

    public List<Clients> findAllClients() {
        List<Clients> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class)
                    .find(delFilter)
                    .sort(ascending("tenant_id"))
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Clients::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> map = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(cli -> {
                cli.setTenant(map.get(cli.getTenantId()));
            });
        }

        return results;
    }

    public List<Clients> findAllClientsWithTenant(int tenantId) {
        List<Clients> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);
            Bson filter = Filters.and(delFilter, tenantFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Clients::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> map = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(cli -> {
                cli.setTenant(map.get(cli.getTenantId()));
            });
        }

        return results;
    }

    public boolean addNew(NewOrUpdateClients newClient) throws DatabaseException {
        Clients clients = new Clients();
        clients.setName(newClient.getName());
        clients.setTenantId(newClient.getTenantId());
        clients.setGender(newClient.getGender());
        clients.setPhone(newClient.getPhone());
        clients.setAddress(newClient.getAddress());
        clients.setEmail(newClient.getEmail());
        clients.setBirthDay(newClient.getBirthDay());
        clients.setDelFlag(newClient.getDelFlag());
        clients.setCreated(LocalDateTime.now());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class)
                    .insertOne(clients);
        }
        return true;
    }

    public boolean update(String email, int tenantId, NewOrUpdateClients newClient) throws DatabaseException {
        Clients clients = new Clients();
        clients.setName(newClient.getName());
        clients.setTenantId(tenantId);
        clients.setGender(newClient.getGender());
        clients.setPhone(newClient.getPhone());
        clients.setAddress(newClient.getAddress());
        clients.setEmail(email);
        clients.setBirthDay(newClient.getBirthDay());
        clients.setDelFlag(newClient.getDelFlag());
        clients.setCreated(newClient.getCreated());

        Bson filterEmail = Filters.eq("email", email);
        Bson filterTenantId = Filters.eq("tenant_id", tenantId);
        Bson filter = Filters.and(filterEmail, filterTenantId);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class)
                    .replaceOne(filter, clients);
        }
        return true;
    }

    public boolean delete(int tenantId, String email) throws DatabaseException {
        Bson tenantFilter = Filters.eq("tenant_id", tenantId);
        Bson emailFilter = Filters.eq("email", email);
        Bson filter = Filters.and(tenantFilter, emailFilter);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Clients> col = DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class);

            long count = col.countDocuments(filter);
            if (count == 1) {
                col.updateOne(filter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    public List<Clients> searchClient(ClientSearch clientSearch) {
        TenantDAO tenantDAO = new TenantDAO();
        List<Clients> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Tenants tenant = clientSearch.getTenant();
            String name = clientSearch.getName();
            Bson tenantFilter = Filters.eq("tenant_id", tenant.getId());
            Bson delFilter = Filters.eq("del_flag", 0);
            Document clientNameFilter = new Document("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));

            MongoCollection<Clients> col = DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class);

            List<Bson> bsonList = new ArrayList<>();
            bsonList.add(delFilter);
            if (tenant.getName() != "All tenants") {
                bsonList.add(tenantFilter);
            }
            if (!StringUtils.isEmpty(name)) {
                bsonList.add(clientNameFilter);
            }

            if (bsonList.size() != 1) {
                Bson filter = Filters.and(bsonList.toArray(new Bson[bsonList.size()]));
                col.find(filter)
                        .sort(ascending("tenant_id"))
                        .forEach(results::add);
            } else {
                col.find(delFilter)
                        .sort(ascending("tenant_id"))
                        .forEach(results::add);
            }
            Set<Integer> tenantIds = results.stream()
                    .map(Clients::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = tenantDAO.findTenantByIds(client, tenantIds);
            results.forEach(b -> {
                b.setTenant(tenantsMap.get(b.getTenantId()));
            });
        }

        return results;
    }

    public Clients findClientByEmailAndTenantId(String email, int tenantId) {
        Clients result = new Clients();
        try (MongoClient client = DBUtil.open()) {
            Bson emailFilter = Filters.eq("email", email);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);
            Bson filter = Filters.and(emailFilter, tenantFilter);
            result = DBUtil.getDatabase(client)
                    .getCollection(Clients.COLLECTION, Clients.class)
                    .find(filter)
                    .first();

            Tenants tenant = new TenantDAO().findTenantById(tenantId);
            result.setTenant(tenant);
        }

        return result;
    }
}
