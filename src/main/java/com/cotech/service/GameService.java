package com.cotech.service;

import com.cotech.model.Circle;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String circleGame(String coordinate, Circle circle) throws Exception {
        String[] params = coordinate.split(",");
        if (params.length<100)
            throw new Exception("点数不够");
        double x = circle.getCircle_x();
        double y = circle.getCircle_y();
        double r = circle.getCircle_r();
        int len = params.length;
        double s = 0.0D;
        double rnew = 0.0D;
        for (int i = 0; i < len - 2; i += 2) {
            rnew = Math.sqrt((Double.valueOf(params[i]) - x) * (Double.valueOf(params[i]) - x) + (Double.valueOf(params[i + 1]) - y) * (Double.valueOf(params[i + 1]) - y));
            s += Math.abs(rnew - r);
        }
        double avg = s / (double) len * 2.0D;
        return String.valueOf(avg / r * 100.0D).substring(0, 5);
    }
}
