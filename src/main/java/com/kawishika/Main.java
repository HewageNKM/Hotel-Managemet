package com.kawishika;

import com.kawishika.util.Mail;

public class Main {
    public static void main(String[] args) {
        String subject = "Reserve Successful";
        String message = "Thank You For Choosing Us !"+"\n\n"+
                "Have A Nice Day !"+"\n"+
                "The D24";
        Mail.getInstance().sendMail("kawishikam@gmail.com",subject,message);
    }
}
