package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.Manufacturers;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateManufacturers {

    private int id;

    private String name;

    private LocalDateTime created;

    private int delFlag;
    
public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Manufacturers manu = DBUtil.getDatabase(client)
                    .getCollection(Manufacturers.COLLECTION, Manufacturers.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return manu.getId() + 1;
        }
    }
}
