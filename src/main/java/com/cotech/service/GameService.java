package com.cotech.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String circleGame(String coordinate) {
        String[] params = coordinate.split(",");
        double x = 100.0D;
        double y = 150.0D;
        int r = 50;
        int len = params.length;
        double s = 0.0D;
        double rnew = 0.0D;
        for (int i = 0; i < len - 2; i += 2) {
            rnew = Math.sqrt((Double.valueOf(params[i]).doubleValue() - x) * (Double.valueOf(params[i]).doubleValue() - x) + (Double.valueOf(params[i + 1]).doubleValue() - y) * (Double.valueOf(params[i + 1]).doubleValue() - y));
            s += Math.abs(rnew - (double) r);
        }
        double avg = s / (double) len * 2.0D;
        return String.valueOf(Math.abs(avg / (double) r) * 100.0D).substring(0, 5);
    }
}
