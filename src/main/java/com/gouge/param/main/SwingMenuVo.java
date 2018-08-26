package com.gouge.param.main;

import java.util.Date;
import java.util.List;

/**
 * Created by Godden
 * Datetime : 2018/8/7 19:11.
 */
public class SwingMenuVo {
    private String id;

    private String parntId;

    private String menuName;

    private Integer isMenu;

    private String className;

    private Integer isActive;

    private Date crtDate;

    private Date modDate;

    private List<SwingMenuVo> menus;

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

    public List<SwingMenuVo> getMenus() {
        return menus;
    }

    public void setMenus(List<SwingMenuVo> menus) {
        this.menus = menus;
    }
}
