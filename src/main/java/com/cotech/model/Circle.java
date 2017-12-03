package com.cotech.model;

import java.io.Serializable;

public class Circle implements Serializable{
    private Double circle_x;
    private Double circle_y;
    private Double circle_r;

    public Double getCircle_x() {
        return circle_x;
    }

    public void setCircle_x(Double circle_x) {
        this.circle_x = circle_x;
    }

    public Double getCircle_y() {
        return circle_y;
    }

    public void setCircle_y(Double circle_y) {
        this.circle_y = circle_y;
    }

    public Double getCircle_r() {
        return circle_r;
    }

    public void setCircle_r(Double circle_r) {
        this.circle_r = circle_r;
    }

    @Override
    public String toString(){
        return "{\"circle_x\":"+circle_x+",\"circle_y\":"+circle_y+",\"circle_r\":"+circle_r+"}";
    }
}
