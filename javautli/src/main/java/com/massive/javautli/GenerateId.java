package com.massive.javautli;


public class GenerateId {
    public static String idGenerator(String s) {
        String id = "";
        if (!s.isEmpty()) {
            int len = s.length();
            if (len >= 3) {
                id = len + s.substring(0, 3);
            } else {
                id = len + s;
            }
            if (len >= 6) {
                id = id + s.substring(len-3,len);
            }
            return id;
        } else {
            return "";
        }
    }
}
