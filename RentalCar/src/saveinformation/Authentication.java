/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saveinformation;

import dao.TenantDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.entity.Users;
import model.entity.Tenants;
import org.bson.conversions.Bson;
import saveinformation.SecurityContext;
import utils.DBUtil;

/**
 *
 * @author Admin
 */
public class Authentication {

    public Users Login(String email, String password) {
        try (MongoClient client = DBUtil.open()) {
            Bson emailFilter = Filters.eq("email", email);
            Bson passwordFilter = Filters.eq("password", getMd5Password(password));
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson managerLogin = Filters.and(emailFilter, passwordFilter, delFilter);
            Users user = DBUtil.getDatabase(client)
                    .getCollection(Users.COLLECTION, Users.class)
                    .find(managerLogin)
                    .first();
            return user;

        }
    }

    public void setTenant(Users user) {
        Integer tenantsID = user.getTenantId();
        Tenants tenant = new TenantDAO().findTenantById(tenantsID);
        user.setTenant(tenant);
        SecurityContext.saveSercurityContext(user);
    }

    public static String getMd5Password(String input) {
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
