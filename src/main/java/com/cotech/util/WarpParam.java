package com.cotech.util;

import com.cotech.model.Circle;
import com.cotech.model.Triangle;

final public class WarpParam {

    private WarpParam() {}

    public static String wrapTriangle(String param){
        String[] res = param.split(",");
        Triangle triangle = new Triangle();
        triangle.setSummit0_x(Double.valueOf(res[0]));
        triangle.setSummit0_y(Double.valueOf(res[1]));
        triangle.setSummit1_x(Double.valueOf(res[2]));
        triangle.setSummit1_y(Double.valueOf(res[3]));
        triangle.setSummit2_x(Double.valueOf(res[4]));
        triangle.setSummit2_y(Double.valueOf(res[5]));
        return triangle.toString();
    }

    public static String wrapCircle(String param){
        String res = param;
        String[] ss = res.split(":");
        Circle circle = new Circle();
        circle.setCircle_x(Double.valueOf(ss[1].substring(0,ss[1].indexOf("c"))));
        circle.setCircle_y(Double.valueOf(ss[2].substring(0,ss[2].indexOf("c"))));
        circle.setCircle_r(Double.valueOf(ss[3]));
        return circle.toString();
    }
}
