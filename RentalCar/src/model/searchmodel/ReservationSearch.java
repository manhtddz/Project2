package model.searchmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.entity.Tenants;

@Getter
@Setter
@AllArgsConstructor
public class ReservationSearch {

    private String clientName;
    private Tenants tenant;
    private Status status;
}
