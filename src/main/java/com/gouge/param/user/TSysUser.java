package com.gouge.param.user;

import java.io.Serializable;
import java.util.Date;

public class TSysUser implements Serializable {


    private String userid;

    private String usercode;

    private String username;

    private String password;

    private String tel;

    private String email;

    private String createuser;

    private Date createdate;

    private String status;

    private String phonebind;

    private String emailbind;

    private Integer levelint;

    private String levelname;

    private Integer tucoin;

    private String modifyuser;

    private Date modifydate;

    private Date lastlogindate;

    private String remark;

    private String roleid;

    private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPhonebind() {
        return phonebind;
    }

    public void setPhonebind(String phonebind) {
        this.phonebind = phonebind == null ? null : phonebind.trim();
    }

    public String getEmailbind() {
        return emailbind;
    }

    public void setEmailbind(String emailbind) {
        this.emailbind = emailbind == null ? null : emailbind.trim();
    }

    public Integer getLevelint() {
        return levelint;
    }

    public void setLevelint(Integer levelint) {
        this.levelint = levelint;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public Integer getTucoin() {
        return tucoin;
    }

    public void setTucoin(Integer tucoin) {
        this.tucoin = tucoin;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser == null ? null : modifyuser.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}