package com.transaction.util;

import java.util.regex.Pattern;

public class ValidationUtil {

     private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

     private static final Pattern MOBILE = Pattern.compile("^[0-9]{10}$");

     private static final Pattern USERNAME = Pattern.compile("^[A-Za-z0-9_]{4,}$");

     private static final Pattern PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");


     public static boolean isValidEmail(String email) {
         return email != null && EMAIL.matcher(email).matches();
        }

     public static boolean isValidMobile(String mobile) {
         return mobile != null && MOBILE.matcher(mobile).matches();
        }

     public static boolean isValidUsername(String username) {
         return username != null && USERNAME.matcher(username).matches();
        }

     public static boolean isValidPassword(String password) {
         return password != null && PASSWORD.matcher(password).matches();
        }
    }