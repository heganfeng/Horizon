package com.gouge.param;

import com.gouge.base.Param;

/**
 * Created by grandfen on 17/8/5.
 */
public class PageParam extends Param{
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
