package com.cotech.util;

import static com.google.common.base.Preconditions.checkNotNull;

public class ParamCheck {
    private static ParamCheck ourInstance = new ParamCheck();

    public static ParamCheck getInstance() {
        return ourInstance;
    }

    private ParamCheck() {
    }

    public String paramNotEmptyNotNull(String param) throws Exception {
        checkNotNull(param);
        if (param.isEmpty())
            throw new Exception("参数为空");
        return param;
    }

    public Long paramNotZeroNotNull(Long param) throws Exception {
        checkNotNull(param);
        if (param.equals(0l))
            throw new Exception("参数为0");
        return param;
    }
}
