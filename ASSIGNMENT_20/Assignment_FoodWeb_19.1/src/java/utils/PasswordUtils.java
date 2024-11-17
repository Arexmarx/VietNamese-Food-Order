/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class PasswordUtils {
    public static String hashString(String message) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            byte[] hash = sha256.digest(message.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());

            return null;
        }
    }

    public static boolean checkPass(String password, String hashPass){
        return hashPass.equals(hashString(password));
    }
    
    public static void main(String[] args) {
        String pass= "060905";
        System.out.println(pass);
        System.out.println(hashString(pass));
    }
}
