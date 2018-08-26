package com.gouge.param.user;

import java.io.Serializable;
import java.util.Date;

public class SwingUser implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        this.levelId = levelId == null ? null : levelId.trim();
    }

    public String getModUserId() {
        return modUserId;
    }

    public void setModUserId(String modUserId) {
        this.modUserId = modUserId == null ? null : modUserId.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }
}