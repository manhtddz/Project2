package model.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Users {

    public static String COLLECTION = "users";
    private ObjectId oId;

    @BsonProperty("id")
    private int id;
    
    @BsonProperty("name")
    private String name;

    @BsonProperty("phone_no")
    private String phone;

    @BsonProperty("email")
    private String email;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    @BsonProperty("password")
    private String password;

    @BsonProperty("role")
    private int role;

    @BsonProperty("tenant_id")
    private int tenantId;

    private Tenants tenant;

}
