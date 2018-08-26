package com.gouge.param.main;


import com.gouge.param.PageParam;

import java.util.Date;

/**
 * Created by Godden
 * Datetime : 2018/8/8 13:19.
 */
public class UserRelationAdvancedParam extends PageParam {

    private String id;

    private String userId;

    private String friendId;

    private String friendCode;

    private String friendName;

    private Integer familiarity;

    private Date crtDate;

    private Date modDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendCode() {
        return friendCode;
    }

    public void setFriendCode(String friendCode) {
        this.friendCode = friendCode;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Integer getFamiliarity() {
        return familiarity;
    }

    public void setFamiliarity(Integer familiarity) {
        this.familiarity = familiarity;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
