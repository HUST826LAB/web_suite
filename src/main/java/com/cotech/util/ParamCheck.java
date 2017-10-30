package com.cotech.util;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ParamCheck {

    private ParamCheck(){}

    public static String paramNotEmptyNotNull(String param) throws Exception {
        checkNotNull(param);
        if (param.isEmpty())
            throw new Exception("参数为空");
        return param;
    }

    public static Long paramNotZeroNotNull(Long param) throws Exception {
        checkNotNull(param);
        if (param.equals(0l))
            throw new Exception("参数为0");
        return param;
    }

    public static Long paramIsZeroNotNull(Long param) throws Exception {
        checkNotNull(param);
        if (!param.equals(0l))
            throw new Exception("参数不为0");
        return param;
    }

    public static Double paramNotNull(Double param) throws Exception{
        checkNotNull(param);
        return param;
    }
}
