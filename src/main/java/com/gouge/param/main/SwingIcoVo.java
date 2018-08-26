package com.gouge.param.main;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SwingIcoVo implements Serializable {
    private String id;

    private String icoPath;

    private String icoName;

    private Integer icoType;

    private Date crtDate;

    private BigDecimal seq;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIcoPath() {
        return icoPath;
    }

    public void setIcoPath(String icoPath) {
        this.icoPath = icoPath == null ? null : icoPath.trim();
    }

    public String getIcoName() {
        return icoName;
    }

    public void setIcoName(String icoName) {
        this.icoName = icoName == null ? null : icoName.trim();
    }

    public Integer getIcoType() {
        return icoType;
    }

    public void setIcoType(Integer icoType) {
        this.icoType = icoType;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }
}