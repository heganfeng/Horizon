package com.gouge.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2018/8/7.
 */
public class HttpConnectionHelp<T> {

    public static Object httpPost(String url,Param param,Class clasz){
        JsonResult jr  = HttpUtile.sendHttpPost(url, param == null ? "{}" : JSON.toJSONString(param));
        if (jr.getResult().equals("-1")){
            throw new HttpException(jr.getResult(),jr.getDesc());
        }
        return JSONObject.parseObject(JSON.toJSONString(jr.getData()),clasz);
    }
}
