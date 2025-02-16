/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.HeadlessException;
import javax.swing.JFileChooser;

/**
 *
 * @author Admin
 */
public class FileChoose {

    public static String fileChooser() throws HeadlessException {
        JFileChooser jc = new JFileChooser();
        int rVal = jc.showSaveDialog(null);
        String filePath = "";
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = jc.getSelectedFile().getName();
            String dir = jc.getCurrentDirectory().toString();
            filePath = dir + "\\" + filename;
        }
        return filePath;
    }
}
