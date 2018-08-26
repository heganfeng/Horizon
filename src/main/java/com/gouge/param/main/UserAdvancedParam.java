package com.gouge.param.main;


import com.gouge.param.PageParam;

import java.util.Date;

/**
 * Created by Godden
 * Datetime : 2018/8/8 13:19.
 */
public class UserAdvancedParam extends PageParam {

    private String id;

    private String userCode;

    private String nickName;

    private String password;

    private String tel;

    private String email;

    private Date crtDate;

    private Integer isActive;

    private String levelId;

    private String modUserId;

    private Date modDate;

    private Integer isLine;

    private Date lastLineDate;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getModUserId() {
        return modUserId;
    }

    public void setModUserId(String modUserId) {
        this.modUserId = modUserId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Integer getIsLine() {
        return isLine;
    }

    public void setIsLine(Integer isLine) {
        this.isLine = isLine;
    }

    public Date getLastLineDate() {
        return lastLineDate;
    }

    public void setLastLineDate(Date lastLineDate) {
        this.lastLineDate = lastLineDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
