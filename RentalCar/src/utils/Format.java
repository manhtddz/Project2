/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class Format {

    public static String getVNCurrency(Float idx) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getCurrencyInstance(localeVN);
        return vn.format(idx);
    }
}
