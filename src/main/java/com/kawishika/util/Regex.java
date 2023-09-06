package com.kawishika.util;

public class Regex {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._]{5,35}@gmail\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9+_.-]{3,12}$";
    private static final String PHONE_REGEX = "^07\\d{8}$";
    private static final String NAME_REGEX = "^[A-Za-z][A-Za-z\\s]{10,48}[A-Za-z]$";
    private static final String STUDENT_ID_REGEX = "^(stm|stf)\\d{7}$";
    private static final String DOUBLE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String ROOM_NUMBER_REGEX = "^R\\d{3}$";
    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    public static boolean validateUserName(String userName) {
        return userName.matches(USERNAME_REGEX);
    }
    public static boolean validatePhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }
    public static boolean validateName(String name) {
        return name.matches(NAME_REGEX);
    }
    public static boolean validateStudentId(String id) {
        return id.matches(STUDENT_ID_REGEX);
    }
    public static boolean validateDoubleValue(String cost) {
        return cost.matches(DOUBLE_REGEX);
    }
    public static boolean validateRoomNumber(String roomNumber) {
        return roomNumber.matches(ROOM_NUMBER_REGEX);
    }
}
