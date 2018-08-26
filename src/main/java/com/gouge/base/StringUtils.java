package com.gouge.base;

/**
 * Created by Godden
 * Datetime : 2018/8/8 12:17.
 */
public class StringUtils {

    public  static boolean isEmpty(String str){
        if(null == str || str.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isInteger(String str){
        if(null != str && !str.trim().equals("")){
            try {
                Integer.parseInt(str);
            }catch (Exception e){
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
}
