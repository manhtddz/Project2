/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Cars;
import model.entity.Options;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveCarInfo {

    private static Cars currentCar;

    public static Cars getCurrentCar() {
        return currentCar;
    }

    public static void saveInfo(Cars car) {
        currentCar = car;
    }
}
