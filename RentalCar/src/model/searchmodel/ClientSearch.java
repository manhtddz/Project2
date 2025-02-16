package model.searchmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.entity.Tenants;

@Getter
@Setter
@AllArgsConstructor
public class ClientSearch {

    private Tenants tenant;
    private String name;
}
