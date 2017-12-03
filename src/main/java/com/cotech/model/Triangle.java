package com.cotech.model;

import java.io.Serializable;

public class Triangle implements Serializable{
    private Double summit0_x;
    private Double summit0_y;
    private Double summit1_x;
    private Double summit1_y;
    private Double summit2_x;
    private Double summit2_y;

    public Double getSummit0_x() {
        return summit0_x;
    }

    public void setSummit0_x(Double summit0_x) {
        this.summit0_x = summit0_x;
    }

    public Double getSummit0_y() {
        return summit0_y;
    }

    public void setSummit0_y(Double summit0_y) {
        this.summit0_y = summit0_y;
    }

    public Double getSummit1_x() {
        return summit1_x;
    }

    public void setSummit1_x(Double summit1_x) {
        this.summit1_x = summit1_x;
    }

    public Double getSummit1_y() {
        return summit1_y;
    }

    public void setSummit1_y(Double summit1_y) {
        this.summit1_y = summit1_y;
    }

    public Double getSummit2_x() {
        return summit2_x;
    }

    public void setSummit2_x(Double summit2_x) {
        this.summit2_x = summit2_x;
    }

    public Double getSummit2_y() {
        return summit2_y;
    }

    public void setSummit2_y(Double summit2_y) {
        this.summit2_y = summit2_y;
    }

    @Override
    public String toString(){
        return String.format("{\"summit0_x\":%s,\"summit0_y\":%s,\"summit1_x\":%s,\"summit1_y\":%s,\"summit2_x\":%s,\"summit2_y\":%s}", summit0_x, summit0_y, summit1_x, summit1_y, summit2_x, summit2_y);
    }
}
