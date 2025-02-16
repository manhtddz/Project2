/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Options;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveOptionInfo {

    private static Options currentOption;

    public static Options getCurrentOption() {
        return currentOption;
    }

    public static void saveInfo(Options option) {
        currentOption = option;
    }
}
