package com.gouge.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gouge.param.main.SwingIcoVo;

/**
 * Created by Godden
 * Datetime : 2018/8/9 22:10.
 */
public class SystemVariable {
    public static String key = "22, 57, -46, 53, 12, -16, -101, -99, -54 ,54, 75, 154";

    public  static String gougeIco = null;

    public static String nextMuscIco = null;

    public static String onMuscIco = null;

    public static String upMuscIco = null;

    public static void initVariable(){
        JsonResult jsonResult = HttpUtile.sendHttpPost(Path.httpUrl+"v1/main/getIcos","{}");
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(jsonResult.getData()));
        for (int i =0;i<jsonArray.size();i++){
            SwingIcoVo vo = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)),SwingIcoVo.class);
            if(i == 0){
                gougeIco = vo.getIcoPath();
            }else if(i == 1){
                nextMuscIco = vo.getIcoPath();
            }else if(i == 2){
                onMuscIco = vo.getIcoPath();
            }else if(i == 3){
                upMuscIco = vo.getIcoPath();
            }
        }
    }
}
