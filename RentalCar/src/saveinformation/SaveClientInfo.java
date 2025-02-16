/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Cars;
import model.entity.Clients;
import model.entity.Options;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveClientInfo {

    private static Clients currentClient;

    public static Clients getCurrentClient() {
        return currentClient;
    }

    public static void saveInfo(Clients client) {
        currentClient = client;
    }
}
