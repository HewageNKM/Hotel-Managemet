package com.kawishika.util;

public class Regex {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._]{5,35}@gmail\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9+_.-]{3,12}$";
    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    public static boolean validateUserName(String userName) {
        return userName.matches(USERNAME_REGEX);
    }
}
