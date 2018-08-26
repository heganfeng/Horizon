package com.gouge.param.main;

import com.gouge.param.PageParam;

import java.util.Date;

/**
 * Created by Godden
 * Datetime : 2018/8/8 13:07.
 */
public class MenuAdvancedParam extends PageParam {
    private String id;

    private String parntId;

    private String menuName;

    private Integer isMenu;

    private String className;

    private Integer isActive;

    private Date crtDate;

    private Date modDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParntId() {
        return parntId;
    }

    public void setParntId(String parntId) {
        this.parntId = parntId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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
