package model.searchmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Gender {

    private int genderId;
    private String gender;

    @Override
    public String toString() {
        return gender;
    }
}
