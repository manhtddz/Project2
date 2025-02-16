package model.newentity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Sorts.descending;
import java.time.Duration;
import java.time.LocalDate;
import model.entity.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.conversions.Bson;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateReservations {

    private int reservationNo;
//    String (officeName + carID?)

    private int tenantId;

    private String clientName;

    private String clientEmail;

    private int clientGender;

    private String clientPhone;

    private String clientAddress;

    private LocalDate clientBirthDay;

    private int carId;

    private Cars car;

    private LocalDate lentDate;

    private LocalDate returnDate;

    private LocalDate realReturnDate;

    private Float rentalPrice;

    private List<Integer> listOptionId;

    private Float lateReturnPrice;

    private List<Integer> listPenaltyPoliciesId;

    private String memo;

    private LocalDateTime created;

    private int status;

    private int delFlag;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Reservations reserv = DBUtil.getDatabase(client)
                    .getCollection(Reservations.COLLECTION, Reservations.class)
                    .find()
                    .sort(descending("reservation_no"))
                    .first();

            return reserv.getReservationNo() + 1;
        }
    }

    public Float optionsPrice() {
        List<Options> options = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            for (Integer optionId : listOptionId) {
                Bson optionById = Filters.eq("id", optionId);
                DBUtil.getDatabase(client)
                        .getCollection(Options.COLLECTION, Options.class)
                        .find(optionById)
                        .forEach(options::add);
            }
        }
        Float totalOptionsPrice = 0F;
        for (Options option : options) {
            totalOptionsPrice += option.getPrice();
        }
        return totalOptionsPrice;
    }

    public Float policiesPrice() {
        List<PenaltiesPolicy> policies = new ArrayList<>();
        try (MongoClient client = DBUtil.open()) {
            for (Integer policyId : listPenaltyPoliciesId) {
                Bson policyById = Filters.eq("id", policyId);
                DBUtil.getDatabase(client)
                        .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                        .find(policyById)
                        .forEach(policies::add);
            }
        }
        Float totalPoliciesPrice = 0F;
        for (PenaltiesPolicy policy : policies) {
            totalPoliciesPrice += policy.getPrice();
        }
        return totalPoliciesPrice;
    }

    public Float totalPrice() {
        Float totalPrice = 0F;
        totalPrice = optionsPrice() + policiesPrice() + rentalPrice() + lateReturnPrice();
        return totalPrice;
    }

    public Float lateReturnPrice() {
        if (realReturnDate != null) {
            LocalDateTime realReturn = LocalDateTime.of(realReturnDate.getYear(), realReturnDate.getMonth(), realReturnDate.getDayOfMonth(), 0, 0);
            LocalDateTime returnD = LocalDateTime.of(returnDate.getYear(), returnDate.getMonth(), returnDate.getDayOfMonth(), 0, 0);
            Duration duration = Duration.between(returnD, realReturn);
            if (duration.isNegative()) {
                return 0F;
            } else {
                long lateDayQty = duration.toDays();
                return lateDayQty * car.getLateReturnPrice();
            }
        } else {
            return null;
        }
    }

    public Float rentalPrice() {
        LocalDateTime lent = LocalDateTime.of(lentDate.getYear(), lentDate.getMonth(), lentDate.getDayOfMonth(), 0, 0);
        LocalDateTime returnD = LocalDateTime.of(returnDate.getYear(), returnDate.getMonth(), returnDate.getDayOfMonth(), 0, 0);

        Duration duration = Duration.between(lent, returnD);
        if (!duration.isNegative()) {

            long dayQty = duration.toDays() + 1;

            return dayQty * car.getPriceByDay();
        } else {
            return 0F;
        }

    }

}
