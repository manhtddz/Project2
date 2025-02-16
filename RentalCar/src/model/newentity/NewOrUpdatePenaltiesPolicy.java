package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.PenaltiesPolicy;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdatePenaltiesPolicy {

    private int id;

    private String name;

    private Float price;

    private LocalDateTime created;

    private int delFlag;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            PenaltiesPolicy pollicy = DBUtil.getDatabase(client)
                    .getCollection(PenaltiesPolicy.COLLECTION, PenaltiesPolicy.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return pollicy.getId() + 1;
        }
    }

}
