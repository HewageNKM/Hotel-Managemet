package com.kawishika.util;

import lombok.SneakyThrows;

import java.security.MessageDigest;

public class Hashing {
    @SneakyThrows
    public static String getHash(String msg) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
}
