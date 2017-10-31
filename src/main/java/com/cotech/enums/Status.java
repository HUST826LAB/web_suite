package com.cotech.enums;

public enum Status {
    SUCCESS("成功",0),
    Fail("失败",1),
    Error("服务器出现异常",-1),
    ParamError("参数错误",1),
    Logged("已登录状态",2),
    NotCircle("不是圆",3);

    private String msg;
    private int code;

    Status(String msg, int code) {
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
