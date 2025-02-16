package model.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter

public class Tenants {

    public static String COLLECTION = "tenants";

    private ObjectId oId;

    @BsonProperty("id")
    private int id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("phone")
    private String phone;

    @BsonProperty("address")
    private String address;

    @BsonProperty("email")
    private String email;

    @BsonProperty("open_hours")
    private LocalTime openHours;

    @BsonProperty("close_hours")
    private LocalTime closeHours;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    @Override
    public String toString() {
        return name;
    }

    public Tenants() {
    }

    public Tenants(String name) {
        this.name = name;
    }
    
}
