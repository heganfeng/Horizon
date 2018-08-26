package com.gouge.base;

import java.util.UUID;

/**
 * Created by Administrator on 2018/8/7.
 */
public class SessionId {
    private static String sessionId = null;

    public static String getSessionId(){
        if(sessionId == null) {
            sessionId = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
            return sessionId;
        }
        return sessionId;
    }
}
