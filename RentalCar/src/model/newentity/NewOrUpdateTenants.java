package model.newentity;

import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import model.entity.Tenants;
import utils.DBUtil;

@Getter
@Setter
public class NewOrUpdateTenants {

    private int id;

    private String name;

    private String phone;

    private String address;

    private String email;

    private LocalTime openHours;

    private LocalTime closeHours;

    private LocalDateTime created;

    private int delFlag;

    public int setId() {
        try (MongoClient client = DBUtil.open()) {
            Tenants tenant = DBUtil.getDatabase(client)
                    .getCollection(Tenants.COLLECTION, Tenants.class)
                    .find()
                    .sort(descending("id"))
                    .first();

            return tenant.getId() + 1;
        }
    }

}
