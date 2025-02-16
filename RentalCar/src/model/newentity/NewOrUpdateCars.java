package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import model.entity.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateCars {

    private int id;

    private String name;

    private String description;

    private LocalDateTime created;

    private int delFlag;

    private int carTypeId;

    private int tenantId;

//    private Tenants tenant;
    private int manufacturerId;

//    private Manufacturers manufacturer;
    private Float priceByDay;

    private Float lateReturnPrice;

    private int status;

//    @BsonProperty("images")
//    private int images;
    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Cars car = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return car.getId() + 1;
        }
    }
}
