package com.gouge.param;

/**
 * Created by Godden
 * Datetime : 2018/8/8 14:49.
 */
public class PageInfo {
    private Integer PageCount;
    private Integer PageSize;
    private Integer PageIndex;
    private Integer RecordCount;

    public Integer getPageCount() {
        return PageCount;
    }

    public void setPageCount(Integer pageCount) {
        PageCount = pageCount;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(Integer recordCount) {
        RecordCount = recordCount;
    }
}
