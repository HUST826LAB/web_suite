package com.cotech.model;

import java.io.Serializable;

public class PageVo implements Serializable {
    private Long current;
    private Long pageLen;
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
