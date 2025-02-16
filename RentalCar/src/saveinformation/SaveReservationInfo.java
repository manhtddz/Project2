/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.CarType;
import model.entity.Cars;
import model.entity.Options;
import model.entity.Reservations;
import model.entity.Tenants;

/**
 *
 * @author Admin
 */
public class SaveReservationInfo {

    private static Reservations currentReservation;

    public static Reservations getCurrentReservation() {
        return currentReservation;
    }

    public static void saveInfo(Reservations reservation) {
        currentReservation = reservation;
    }
}
