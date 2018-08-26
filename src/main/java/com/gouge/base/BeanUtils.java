package com.gouge.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Godden
 * Datetime : 2018/8/8 13:49.
 */
public class BeanUtils {

    public static Map<String,Object> transformationMap(Object obj){
        Map<String,Object> map = new HashMap<String,Object>();
        if(obj == null)
            return map;
        Class clasz = obj.getClass();
        Field [] fields = clasz.getDeclaredFields();
        try{
            for (Field field : fields){
                field.setAccessible(true);
                map.put(field.getName(),field.get(obj));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
