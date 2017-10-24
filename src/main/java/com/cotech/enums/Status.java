package com.cotech.enums;

public enum Status {
    SUCCESS("成功",0),
    Fail("失败",1),
    Error("服务器出现异常",-1);

    private String msg;
    private int code;

    private Status(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public int getCode() {
        return code;
    }
}
