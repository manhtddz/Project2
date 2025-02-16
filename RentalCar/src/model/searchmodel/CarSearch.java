package model.searchmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.entity.CarType;
import model.entity.Manufacturers;
import model.entity.Tenants;

@Getter
@Setter
@AllArgsConstructor
public class CarSearch {

    private Tenants tenant;
    private CarType carType;
    private Manufacturers manufacturer;

}
