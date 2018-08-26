package com.gouge.param.main;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SwingMusicVo implements Serializable {
    private String id;

    private String userId;

    private String musicName;

    private String absolutePath;

    private String accessPath;

    private String artist;

    private BigDecimal seq;

    private String replacePath;

    private String musicType;

    private Date crtDate;

    private Date modDate;

    private Integer index;

    private boolean isPlay;

    public boolean getIsPlay() {
        return isPlay;
    }
    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName == null ? null : musicName.trim();
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath == null ? null : absolutePath.trim();
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath == null ? null : accessPath.trim();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist == null ? null : artist.trim();
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getReplacePath() {
        return replacePath;
    }

    public void setReplacePath(String replacePath) {
        this.replacePath = replacePath == null ? null : replacePath.trim();
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType == null ? null : musicType.trim();
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