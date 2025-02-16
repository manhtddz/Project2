package model.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Manufacturers {

    public static String COLLECTION = "manufacturers";

    private ObjectId oId;

    @BsonProperty("id")
    private int id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    @Override
    public String toString() {
        return name;
    }

    public Manufacturers(String name) {
        this.name = name;
    }

    public Manufacturers() {
    }
}
