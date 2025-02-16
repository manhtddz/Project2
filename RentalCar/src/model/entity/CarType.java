package model.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class CarType {

    public static String COLLECTION = "car_type";

    private ObjectId oId;

    @BsonProperty("id")
    private int id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("description")
    private String description;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;
    @BsonProperty("capacity")
    private int capacity;

    public CarType() {
    }

    @Override
    public String toString() {
        return name;
    }

    public CarType(String name) {
        this.name = name;
    }
}
