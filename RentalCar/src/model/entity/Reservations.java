package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Reservations {

    public static String COLLECTION = "reservations";

    private ObjectId oId;

    @BsonProperty("reservation_no")
    private int reservationNo;
//    String (officeName + carID?)

    @BsonProperty("tenant_id")
    private int tenantId;

    private Tenants tenant;

    @BsonProperty("client_name")
    private String clientName;

    @BsonProperty("client_email")
    private String clientEmail;
    
    @BsonProperty("client_gender")
    private int clientGender;
    
    @BsonProperty("client_phone")
    private String clientPhone;
    
    @BsonProperty("client_address")
    private String clientAddress;
    
    @BsonProperty("client_birthday")
    private LocalDate clientBirthDay;

    @BsonProperty("car_id")
    private int carId;

    private Cars car;

    @BsonProperty("lent_date")
    private LocalDate lentDate;

    @BsonProperty("return_date")
    private LocalDate returnDate;

    @BsonProperty("real_return_date")
    private LocalDate realReturnDate;

    @BsonProperty("rental_price")
    private Float rentalPrice;

    @BsonProperty("option_id")
    private List<Integer> listOptionId;

    private List<Options> listOption;

    @BsonProperty("option_total_price")
    private Float optionTotalPrice;

    @BsonProperty("late_return_price")
    private Float lateReturnPrice;

    @BsonProperty("penalties_policy_id")
    private List<Integer> listPenaltyPoliciesId;

    private List<PenaltiesPolicy> listPenaltyPolicy;

    @BsonProperty("penalties_total_price")
    private Float penaltiesTotalPrice;

    @BsonProperty("rental_total_price")
    private Float rentalTotalPrice;

    @BsonProperty("memo")
    private String memo;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("status")
    private int status;

    @BsonProperty("del_flag")
    private int delFlag;
}
