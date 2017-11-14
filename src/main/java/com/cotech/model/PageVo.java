package com.cotech.model;

import java.io.Serializable;

public class PageVo implements Serializable {
    private Long current;
    private Long pageLen;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPageLen() {
        return pageLen;
    }

    public void setPageLen(Long pageLen) {
        this.pageLen = pageLen;
    }
}
