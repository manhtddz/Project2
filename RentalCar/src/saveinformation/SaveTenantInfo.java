/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveTenantInfo {

    private static Tenants currentTenant;

    public static Tenants getCurrentTenant() {
        return currentTenant;
    }

    public static void saveInfo(Tenants tenant) {
        currentTenant = tenant;
    }
}
