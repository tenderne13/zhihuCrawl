package com.lxp.util.uuid;

import java.util.UUID;

public class UUIDUtil {
    public static String getHashId(){
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-","");
    }

    public static void main(String arcgs[]){
        String s =getHashId();
        System.out.println(s);
    }
}
