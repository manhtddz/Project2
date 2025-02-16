/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Validation {

    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9]{1,}[@][a-z]{1,}[.][c][o][m]$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(email);
        return ma.find();
    }

    public static boolean validatePhone(String phone) {
        String regex = "^[0-9]{10}$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(phone);
        return ma.find();
    }

    public static boolean validatePassword(String pass) {
        String regex = "^[A-Za-z0-9]{8,}$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(pass);
        return ma.find();
    }

    public static boolean validateName(String name) {
        String regex = "^[A-Za-z0-9]{0,50}$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(name);
        return ma.find();
    }

    public static boolean validatePrice(String price) {
        String regex = "^[0-9]{0,8}$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(price);
        return ma.find();
    }

    public static boolean validateNumber(String number) {
        String regex = "^[0-9]{0,2}$";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(number);
        return ma.find();
    }

    public static boolean validateDate(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 0, 0);
        LocalDateTime end = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), 0, 0);

        Duration duration = Duration.between(start, end);
        return !duration.isNegative();
    }
}
