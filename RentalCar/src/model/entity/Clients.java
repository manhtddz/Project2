package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Clients {

    public static String COLLECTION = "clients";
    private ObjectId oId;

    @BsonProperty("tenant_id")
    private int tenantId;

    private Tenants tenant;

    @BsonProperty("name")
    private String name;

    @BsonProperty("phone")
    private String phone;

    @BsonProperty("address")
    private String address;

    @BsonProperty("email")
    private String email;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    @BsonProperty("gender")
    private int gender;

    @BsonProperty("birthday")
    private LocalDate birthDay;

}
