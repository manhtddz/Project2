/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Manufacturers;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveManufacturerInfo {

    private static Manufacturers currentManufacturer;

    public static Manufacturers getCurrentManufacturer() {
        return currentManufacturer;
    }

    public static void saveInfo(Manufacturers manufacturer) {
        currentManufacturer = manufacturer;
    }
}
