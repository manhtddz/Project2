package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.CarType;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateCarType {

    private int id;

    private String name;

    private String description;

    private int capacity;

    private LocalDateTime created;

    private int delFlag;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            CarType carType = DBUtil.getDatabase(client)
                    .getCollection(CarType.COLLECTION, CarType.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return carType.getId() + 1;
        }
    }

}
