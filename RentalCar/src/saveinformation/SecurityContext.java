/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import model.entity.Users;

/**
 *
 * @author Admin
 */
public class SecurityContext {

    private static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static void saveSercurityContext(Users user) {
        currentUser = user;
    }
}
