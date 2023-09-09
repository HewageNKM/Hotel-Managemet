package com.kawishika.util;

import java.util.ArrayList;

public class TempMails {
    private static final ArrayList<String> tempMails = new ArrayList<>();
    public static void add(String mail){
        tempMails.add(mail);
    }
    public static boolean check(String mail){
        for (String tempMail : tempMails) {
            if (tempMail.contains(mail)) {
                return true;
            }
        }
        return false;
    }
}
