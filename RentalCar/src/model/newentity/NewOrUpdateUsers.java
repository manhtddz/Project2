package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.Users;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateUsers {

    private int id;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime created;

    private int delFlag;

    private String password;

    private int role;

    private int tenantId;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Users user = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return user.getId() + 1;
        }
    }

}
