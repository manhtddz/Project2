package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import model.searchmodel.CarSearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import model.newentity.NewOrUpdateCars;
import model.entity.CarType;
import model.entity.Cars;
import model.entity.Manufacturers;
import model.entity.Tenants;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class CarDAO {

    public List<Cars> findAllCars() {
        List<Cars> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Cars::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(car -> {
                car.setTenant(tenantsMap.get(car.getTenantId()));
            });

            Set<Integer> carTypeIds = results.stream()
                    .map(Cars::getCarTypeId)
                    .collect(Collectors.toSet());
            Map<Integer, CarType> carTypeMap = new CarTypeDAO().findCarTypeByIds(client, carTypeIds);
            results.forEach(car -> {
                car.setCarType(carTypeMap.get(car.getCarTypeId()));
            });

            Set<Integer> manuIds = results.stream()
                    .map(Cars::getManufacturerId)
                    .collect(Collectors.toSet());
            Map<Integer, Manufacturers> manuMap = new ManufacturerDAO().findManuByIds(client, manuIds);
            results.forEach(car -> {
                car.setManufacturer(manuMap.get(car.getManufacturerId()));
            });
        }
        return results;
    }

    public List<Cars> findAllCarsWithTenant(int tenantId) {
        List<Cars> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);
            Bson filter = Filters.and(delFilter, tenantFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Cars::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(car -> {
                car.setTenant(tenantsMap.get(car.getTenantId()));
            });

            Set<Integer> carTypeIds = results.stream()
                    .map(Cars::getCarTypeId)
                    .collect(Collectors.toSet());
            Map<Integer, CarType> carTypeMap = new CarTypeDAO().findCarTypeByIds(client, carTypeIds);
            results.forEach(car -> {
                car.setCarType(carTypeMap.get(car.getCarTypeId()));
            });

            Set<Integer> manuIds = results.stream()
                    .map(Cars::getManufacturerId)
                    .collect(Collectors.toSet());
            Map<Integer, Manufacturers> manuMap = new ManufacturerDAO().findManuByIds(client, manuIds);
            results.forEach(car -> {
                car.setManufacturer(manuMap.get(car.getManufacturerId()));
            });
        }
        return results;
    }

    public boolean addNew(NewOrUpdateCars newCar) throws DatabaseException {
        Cars car = new Cars();
        car.setId(newCar.setId());
        car.setTenantId(newCar.getTenantId());
        car.setName(newCar.getName());
        car.setCarTypeId(newCar.getCarTypeId());
        car.setManufacturerId(newCar.getManufacturerId());
        car.setPriceByDay(newCar.getPriceByDay());
        car.setLateReturnPrice(newCar.getLateReturnPrice());
        car.setDescription(newCar.getDescription());
        car.setStatus(newCar.getStatus());
        car.setDelFlag(newCar.getDelFlag());
        car.setCreated(newCar.getCreated());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .insertOne(car);
        }
        return true;
    }

    public boolean update(Integer id, NewOrUpdateCars newCar) throws DatabaseException {
        Cars car = new Cars();
        car.setId(id);
        car.setTenantId(newCar.getTenantId());
        car.setName(newCar.getName());
        car.setCarTypeId(newCar.getCarTypeId());
        car.setManufacturerId(newCar.getManufacturerId());
        car.setPriceByDay(newCar.getPriceByDay());
        car.setLateReturnPrice(newCar.getLateReturnPrice());
        car.setDescription(newCar.getDescription());
        car.setStatus(newCar.getStatus());
        car.setDelFlag(newCar.getDelFlag());
        car.setCreated(newCar.getCreated());

        Bson filter = Filters.eq("id", id);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .replaceOne(filter, car);
        }
        return true;
    }

    public boolean delete(int id) throws DatabaseException {
        Bson idFilter = Filters.eq("id", id);
        Bson statusFilter = Filters.eq("status", 0);
        Bson delFilter = Filters.eq("del_flag", 0);
        Bson filter = Filters.and(idFilter, statusFilter, delFilter);

        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Cars> col = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class);

            long count = col.countDocuments(filter);
            if (count == 1) {
                col.updateOne(filter, Updates.set("del_flag", 1));
                return true;
            } else {
                return false;
            }
        }
    }

    Map<Integer, Cars> findCarByIds(MongoClient client, Set<Integer> carIDs) {
        Map<Integer, Cars> map = new HashMap<>();

        MongoDatabase db = DBUtil.getDatabase(client);
        MongoCollection<Cars> collection = db.getCollection(Cars.COLLECTION, Cars.class);

        Bson filter = Filters.in("id", carIDs);
        collection.find(filter)
                .forEach(it -> map.put(it.getId(), it));

        return map;
    }

    public Cars findCarById(int carID) {
        Cars results = new Cars();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("id", carID);
            results = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find(filter)
                    .first();

            int tenantsID = results.getTenantId();
            Bson tenantFilter = Filters.eq("id", tenantsID);
            Tenants tenant = DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .find(tenantFilter)
                    .first();
            results.setTenant(tenant);

            int carTypeId = results.getCarTypeId();
            Bson carTypeFilter = Filters.eq("id", carTypeId);
            CarType carType = DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .find(carTypeFilter)
                    .first();
            results.setCarType(carType);

            int manuId = results.getManufacturerId();
            Bson manuFilter = Filters.eq("id", manuId);
            Manufacturers manu = DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .find(manuFilter)
                    .first();
            results.setManufacturer(manu);
        }

        return results;
    }

    public List<Cars> searchCar(CarSearch carSearch) {
        TenantDAO tenantDAO = new TenantDAO();
        CarTypeDAO carTypeDAO = new CarTypeDAO();
        ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
        List<Cars> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Tenants tenant = carSearch.getTenant();
            CarType carType = carSearch.getCarType();
            Manufacturers manufacturer = carSearch.getManufacturer();
            Bson tenantFilter = Filters.eq("tenant_id", tenant.getId());
            Bson carTypeFilter = Filters.eq("car_type_id", carType.getId());
            Bson manufacturerFilter = Filters.eq("manufacturer_id", manufacturer.getId());
            Bson delFilter = Filters.eq("del_flag", 0);
//
            List<Bson> bsonList = new ArrayList<>();
            bsonList.add(delFilter);
            if (tenant.getName() != "All tenants") {
                bsonList.add(tenantFilter);
            }
            if (carType.getName() != "All types") {
                bsonList.add(carTypeFilter);
            }
            if (manufacturer.getName() != "All manufacturers") {
                bsonList.add(manufacturerFilter);
            }

            MongoCollection<Cars> col = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class
                    );
            if (bsonList.size() != 1) {
                Bson carFilter = Filters.and(bsonList.toArray(new Bson[bsonList.size()]));
                col.find(carFilter)
                        .forEach(results::add);
            } else {
                col.find(delFilter)
                        .forEach(results::add);
            }

            Set<Integer> tenantIds = results.stream()
                    .map(Cars::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = tenantDAO.findTenantByIds(client, tenantIds);
            results.forEach(b -> {
                b.setTenant(tenantsMap.get(b.getTenantId()));
            });

            Set<Integer> carTypeIds = results.stream()
                    .map(Cars::getCarTypeId)
                    .collect(Collectors.toSet());
            Map<Integer, CarType> carTypeMap = carTypeDAO.findCarTypeByIds(client, carTypeIds);
            results.forEach(b -> {
                b.setCarType(carTypeMap.get(b.getCarTypeId()));
            });

            Set<Integer> manuIds = results.stream()
                    .map(Cars::getManufacturerId)
                    .collect(Collectors.toSet());
            Map<Integer, Manufacturers> manuMap = manufacturerDAO.findManuByIds(client, manuIds);
            results.forEach(b -> {
                b.setManufacturer(manuMap.get(b.getManufacturerId()));
            });
        }

        return results;
    }

    public List<Cars> findAllNotOrderedCarsWithTenant(int tenantId) {
        List<Cars> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson statusFilter = Filters.eq("status", 0);
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);

            Bson filter = Filters.and(delFilter, statusFilter, tenantFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantsID = results.stream()
                    .map(Cars::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantsID);
            results.forEach(car -> {
                car.setTenant(tenantsMap.get(car.getTenantId()));
            });

            Set<Integer> carTypeIds = results.stream()
                    .map(Cars::getCarTypeId)
                    .collect(Collectors.toSet());
            Map<Integer, CarType> carTypeMap = new CarTypeDAO().findCarTypeByIds(client, carTypeIds);
            results.forEach(car -> {
                car.setCarType(carTypeMap.get(car.getCarTypeId()));
            });

            Set<Integer> manuIds = results.stream()
                    .map(Cars::getManufacturerId)
                    .collect(Collectors.toSet());
            Map<Integer, Manufacturers> manuMap = new ManufacturerDAO().findManuByIds(client, manuIds);
            results.forEach(car -> {
                car.setManufacturer(manuMap.get(car.getManufacturerId()));
            });
        }
        return results;
    }

}
