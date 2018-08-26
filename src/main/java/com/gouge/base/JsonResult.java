package com.gouge.base;


import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 *
 * @author grandfen
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //处理成功
    public static final String OK = "000";
    //未知错误
    public static final String ERR = "-1";
    //超时或未登录

    public static final JsonResult SUCESS = new JsonResult(OK, "操作成功！");

    //返回结果定义,默认返回"-1",请求失败
    private String result = "-1";

    private String desc = "";

    private T data;

    private Object page;


    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static JsonResult getFailResult(String message) {
        return new JsonResult(ERR, message);
    }

    /**
     * 成功
     *
     * @param message
     * @return
     */
    public static JsonResult getSuccessResult(String message) {
        return new JsonResult(OK, message);
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @return
     */
    public static JsonResult getSuccessResult(Object data, String message) {
        return new JsonResult(OK, message, data);
    }

    public JsonResult() {
    }

    public JsonResult(String code, String message) {
        this.result = code;
        this.desc = message;
    }

    public JsonResult(String code, String message, T result) {
        this.result = code;
        this.desc = message;
        this.data = result;
    }

    public JsonResult(T result) {
        if(result instanceof Page){
            this.result = OK;
            this.desc = "操作成功";
            this.data = result;
            this.page = generatePageMap((Page<?>) result);
        }else {
            this.result = OK;
            this.desc = "操作成功";
            this.data = result;
        }
    }


    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;

        final JsonResult ret = (JsonResult) obj;
        return result == ret.getResult();
    }

    public String getDesc() {
        return desc;
    }

    public JsonResult setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getResult() {
        return result;
    }

    public JsonResult setResult(String result) {
        this.result = result;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(T data) {
        this.data = data;
        return this;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    private Map<String,Object> generatePageMap(Page<?> page){
        Map<String,Object> map =  new HashMap<String, Object>();
        map.put("PageCount", page.getPages());
        map.put("PageIndex", page.getPageNum());
        map.put("PageSize", page.getPageSize());
        map.put("RecordCount", page.getTotal());
        return map;
    }
}
