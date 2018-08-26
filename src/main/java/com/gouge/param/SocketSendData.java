package com.gouge.param;

import java.util.List;

/**
 * Created by Godden
 * Datetime : 2018/8/14 18:29.
 */
public class SocketSendData {
    private String userId;
    private String userName;
    /**
     * 0 单人聊天  1 群组聊天  2 服务器推送消息  3 好友通知  99 登陆socket
     */
    private int sendType;

    private String sendUserId;
    private List<String> sendGroupsId;
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public List<String> getSendGroupsId() {
        return sendGroupsId;
    }

    public void setSendGroupsId(List<String> sendGroupsId) {
        this.sendGroupsId = sendGroupsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
