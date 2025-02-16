/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Options;
import model.entity.PenaltiesPolicy;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SavePolicyInfo {

    private static PenaltiesPolicy currentPolicy;

    public static PenaltiesPolicy getCurrentPolicy() {
        return currentPolicy;
    }

    public static void saveInfo(PenaltiesPolicy policy) {
        currentPolicy = policy;
    }
}
