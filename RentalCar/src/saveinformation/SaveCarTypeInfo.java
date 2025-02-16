/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;

/**
 *
 * @author Admin
 */
public class SaveCarTypeInfo {

    private static CarType currentCarType;

    public static CarType getCurrentCarType() {
        return currentCarType;
    }

    public static void saveInfo(CarType carType) {
        currentCarType = carType;
    }
}
