package model.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Cars {

    public static String COLLECTION = "cars";
    private ObjectId oId;

    @BsonProperty("id")
    private int id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("description")
    private String description;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    @BsonProperty("car_type_id")
    private int carTypeId;

    private CarType carType;

    @BsonProperty("tenant_id")
    private int tenantId;

    private Tenants tenant;

    @BsonProperty("manufacturer_id")
    private int manufacturerId;

    private Manufacturers manufacturer;

    @BsonProperty("price_by_day")
    private Float priceByDay;

    @BsonProperty("late_return_price")
    private Float lateReturnPrice;

    @BsonProperty("status")
    private int status;

//    @BsonProperty("images")
//    private int images;
    @Override
    public String toString() {
        return name;
    }
}
