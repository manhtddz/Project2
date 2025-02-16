package model.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Options {

    public static String COLLECTION = "options";

    private ObjectId oId;

    @BsonProperty("id")
    private int id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("price")
    private Float price;

    @BsonProperty("description")
    private String description;

    @BsonProperty("created")
    private LocalDateTime created;

    @BsonProperty("del_flag")
    private int delFlag;

    public Options(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Options() {
    }

//    @Override
//    public String toString() {
//        return name;
//    }
}
