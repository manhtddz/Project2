package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.newentity.NewOrUpdateReservations;
import model.entity.Cars;
import model.entity.Options;
import model.entity.PenaltiesPolicy;
import model.entity.Reservations;
import model.entity.Tenants;
import model.searchmodel.ReservationSearch;
import model.searchmodel.Status;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class ReservationDAO {

    public List<Reservations> findAllReservations() {
        List<Reservations> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .find(delFilter)
                    .forEach(results::add);

            Set<Integer> tenantIDs = results.stream()
                    .map(Reservations::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantIDs);
            results.forEach(reserv -> {
                reserv.setTenant(tenantsMap.get(reserv.getTenantId()));
            });

            Set<Integer> carIDs = results.stream()
                    .map(Reservations::getCarId)
                    .collect(Collectors.toSet());
            Map<Integer, Cars> carsMap = new CarDAO().findCarByIds(client, carIDs);
            results.forEach(reserv -> {
                reserv.setCar(carsMap.get(reserv.getCarId()));
            });

            Set<List<Integer>> optionIDs = results.stream()
                    .map(Reservations::getListOptionId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<Options>> optionsMap = new OptionDAO().findOptionsByIds(client, optionIDs);
            results.forEach(reserv -> {
                reserv.setListOption(optionsMap.get(reserv.getListOptionId()));
            });

            Set<List<Integer>> policyIDs = results.stream()
                    .map(Reservations::getListPenaltyPoliciesId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<PenaltiesPolicy>> policiesMap = new PenaltiesPolicyDAO().findPoliciesByIds(client, policyIDs);
            results.forEach(reserv -> {
                reserv.setListPenaltyPolicy(policiesMap.get(reserv.getListPenaltyPoliciesId()));
            });
        }
        return results;
    }

    public List<Reservations> findAllReservationsWithTenant(int tenantId) {
        List<Reservations> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson tenantFilter = Filters.eq("tenant_id", tenantId);
            Bson filter = Filters.and(delFilter, tenantFilter);
            DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .find(filter)
                    .forEach(results::add);

            Set<Integer> tenantIDs = results.stream()
                    .map(Reservations::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantIDs);
            results.forEach(reserv -> {
                reserv.setTenant(tenantsMap.get(reserv.getTenantId()));
            });

            Set<Integer> carIDs = results.stream()
                    .map(Reservations::getCarId)
                    .collect(Collectors.toSet());
            Map<Integer, Cars> carsMap = new CarDAO().findCarByIds(client, carIDs);
            results.forEach(reserv -> {
                reserv.setCar(carsMap.get(reserv.getCarId()));
            });

            Set<List<Integer>> optionIDs = results.stream()
                    .map(Reservations::getListOptionId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<Options>> optionsMap = new OptionDAO().findOptionsByIds(client, optionIDs);
            results.forEach(reserv -> {
                reserv.setListOption(optionsMap.get(reserv.getListOptionId()));
            });

            Set<List<Integer>> policyIDs = results.stream()
                    .map(Reservations::getListPenaltyPoliciesId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<PenaltiesPolicy>> policiesMap = new PenaltiesPolicyDAO().findPoliciesByIds(client, policyIDs);
            results.forEach(reserv -> {
                reserv.setListPenaltyPolicy(policiesMap.get(reserv.getListPenaltyPoliciesId()));
            });
        }
        return results;
    }

    public boolean addNew(NewOrUpdateReservations newReserv) throws DatabaseException {
        Reservations reserv = new Reservations();
        reserv.setReservationNo(newReserv.setId());
        reserv.setCarId(newReserv.getCarId());
        reserv.setTenantId(newReserv.getTenantId());
        reserv.setClientName(newReserv.getClientName());
        reserv.setClientEmail(newReserv.getClientEmail());
        reserv.setClientGender(newReserv.getClientGender());
        reserv.setClientPhone(newReserv.getClientPhone());
        reserv.setClientAddress(newReserv.getClientAddress());
        reserv.setClientBirthDay(newReserv.getClientBirthDay());
        reserv.setLentDate(newReserv.getLentDate());
        reserv.setReturnDate(newReserv.getReturnDate());
        reserv.setRealReturnDate(newReserv.getRealReturnDate());
        reserv.setLateReturnPrice(0F);
        reserv.setRentalPrice(newReserv.rentalPrice());
        reserv.setListOptionId(newReserv.getListOptionId());
        reserv.setOptionTotalPrice(newReserv.optionsPrice());
        reserv.setListPenaltyPoliciesId(newReserv.getListPenaltyPoliciesId());
        reserv.setPenaltiesTotalPrice(newReserv.policiesPrice());
        reserv.setRentalTotalPrice(newReserv.totalPrice());
        reserv.setMemo(newReserv.getMemo());
        reserv.setStatus(0);
        reserv.setDelFlag(0);
        reserv.setCreated(LocalDateTime.now());

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .insertOne(reserv);
        }
        return true;
    }

    public boolean update(int reservationNo, NewOrUpdateReservations newReserv) throws DatabaseException {
        Reservations reserv = new Reservations();
        reserv.setReservationNo(reservationNo);
        reserv.setCarId(newReserv.getCarId());
        reserv.setTenantId(newReserv.getTenantId());
        reserv.setClientName(newReserv.getClientName());
        reserv.setClientEmail(newReserv.getClientEmail());
        reserv.setClientGender(newReserv.getClientGender());
        reserv.setClientPhone(newReserv.getClientPhone());
        reserv.setClientAddress(newReserv.getClientAddress());
        reserv.setClientBirthDay(newReserv.getClientBirthDay());
        reserv.setLentDate(newReserv.getLentDate());
        reserv.setReturnDate(newReserv.getReturnDate());
        reserv.setLateReturnPrice(newReserv.lateReturnPrice());
        reserv.setRealReturnDate(newReserv.getRealReturnDate());
        reserv.setRentalPrice(newReserv.rentalPrice());
        reserv.setListOptionId(newReserv.getListOptionId());
        reserv.setOptionTotalPrice(newReserv.optionsPrice());
        reserv.setListPenaltyPoliciesId(newReserv.getListPenaltyPoliciesId());
        reserv.setPenaltiesTotalPrice(newReserv.policiesPrice());
        reserv.setRentalTotalPrice(newReserv.totalPrice());
        reserv.setMemo(newReserv.getMemo());
        reserv.setStatus(newReserv.getStatus());
        reserv.setDelFlag(0);
        reserv.setCreated(newReserv.getCreated());

        Bson filter = Filters.eq("reservation_no", reservationNo);

        try (MongoClient client = DBUtil.open()) {
            DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .replaceOne(filter, reserv);
        }
        return true;
    }

    public boolean delete(int id) throws DatabaseException {
        Bson idFilter = Filters.eq("reservation_no", id);
        try (MongoClient client = DBUtil.open()) {

            MongoCollection<Reservations> col = DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class);
            MongoCollection<Cars> carCol = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class);

            long count = col.countDocuments(idFilter);
            if (count == 1) {
                col.updateOne(idFilter, Updates.set("del_flag", 1));
                Reservations reserv = col.find(idFilter).first();
                int carId = reserv.getCarId();
                Bson carFilter = Filters.eq("id", carId);
                if (reserv.getStatus() == 0) {
                    carCol.updateOne(carFilter, Updates.set("status", 0));
                    col.updateOne(idFilter, Updates.set("status", 1));
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public List<Reservations> reservationSearch(ReservationSearch reservSearch) {
        List<Reservations> results = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            Tenants tenant = reservSearch.getTenant();
            String clientName = reservSearch.getClientName();
            Status status = reservSearch.getStatus();
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson statusFilter = Filters.eq("status", status.getId());
            Bson tenantIdFilter = Filters.eq("tenant_id", tenant.getId());
            Document clientNameFilter = new Document("client_name", Pattern.compile(clientName, Pattern.CASE_INSENSITIVE));

            List<Bson> bsonList = new ArrayList<>();
            bsonList.add(delFilter);
            if (status.getStatus() != "All status") {
                bsonList.add(statusFilter);
            }
            if (tenant.getName() != "All tenants") {
                bsonList.add(tenantIdFilter);
            }
            if (!StringUtils.isEmpty(clientName)) {
                bsonList.add(clientNameFilter);
            }

            MongoCollection<Reservations> col = DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class);
            if (bsonList.size() != 1) {
                Bson filter = Filters.and(bsonList.toArray(new Bson[bsonList.size()]));
                col.find(filter)
                        .forEach(results::add);
            } else {
                col.find(delFilter)
                        .forEach(results::add);
            }

            Set<Integer> carIDs = results.stream()
                    .map(Reservations::getCarId)
                    .collect(Collectors.toSet());
            Map<Integer, Cars> carsMap = new CarDAO().findCarByIds(client, carIDs);
            results.forEach(reserv -> {
                reserv.setCar(carsMap.get(reserv.getCarId()));
            });

            Set<Integer> tenantIDs = results.stream()
                    .map(Reservations::getTenantId)
                    .collect(Collectors.toSet());
            Map<Integer, Tenants> tenantsMap = new TenantDAO().findTenantByIds(client, tenantIDs);
            results.forEach(reserv -> {
                reserv.setTenant(tenantsMap.get(reserv.getTenantId()));
            });

            Set<List<Integer>> optionIDs = results.stream()
                    .map(Reservations::getListOptionId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<Options>> optionsMap = new OptionDAO().findOptionsByIds(client, optionIDs);
            results.forEach(reserv -> {
                reserv.setListOption(optionsMap.get(reserv.getListOptionId()));
            });

            Set<List<Integer>> policyIDs = results.stream()
                    .map(Reservations::getListPenaltyPoliciesId)
                    .collect(Collectors.toSet());
            Map<List<Integer>, List<PenaltiesPolicy>> policiesMap = new PenaltiesPolicyDAO().findPoliciesByIds(client, policyIDs);
            results.forEach(reserv -> {
                reserv.setListPenaltyPolicy(policiesMap.get(reserv.getListPenaltyPoliciesId()));
            });
        }

        return results;
    }

    public Reservations findReservationById(int id) {
        Reservations result = new Reservations();
        try (MongoClient client = DBUtil.open()) {
            Bson filter = Filters.eq("reservation_no", id);
            result = DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .find(filter)
                    .first();

            Integer tenantID = result.getTenantId();
            Tenants tenant = new TenantDAO().findTenantById(tenantID);
            result.setTenant(tenant);

            Integer carID = result.getCarId();
            Cars car = new CarDAO().findCarById(carID);
            result.setCar(car);

            List<Integer> optionIDs = result.getListOptionId();
            List<Options> options = new OptionDAO().findListOptionByIds(client, optionIDs);
            result.setListOption(options);

            List<Integer> policyIDs = result.getListPenaltyPoliciesId();
            List<PenaltiesPolicy> policies = new PenaltiesPolicyDAO().findListPolicyByIds(client, policyIDs);
            result.setListPenaltyPolicy(policies);

        }
        return result;
    }

}
