package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.Options;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateOptions {

    private int id;

    private String name;

    private Float price;

    private String description;

    private LocalDateTime created;

    private int delFlag;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Options option = DBUtil.getDatabase(client)
                    .getCollection(Options.COLLECTION, Options.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return option.getId() + 1;
        }
    }

}
