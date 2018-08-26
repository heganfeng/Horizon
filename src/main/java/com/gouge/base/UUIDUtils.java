package com.gouge.base;

import java.util.UUID;

/**
 * Created by Godden
 * Datetime : 2018/8/8 12:45.
 */
public class UUIDUtils {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
}
