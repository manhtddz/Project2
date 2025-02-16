package model.searchmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Status {

    private int id;
    private String status;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
