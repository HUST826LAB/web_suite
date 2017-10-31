package com.cotech.service;

import com.cotech.model.Circle;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GameService {

    public String circleGame(String coordinate, Circle circle) throws Exception {
        String[] params = coordinate.split(",");
        Set<String> quadrant = new HashSet<String>();
        double x = circle.getCircle_x();
        double y = circle.getCircle_y();
        double r = circle.getCircle_r();
        int len = params.length;
        double s = 0.0D;
        double rnew = 0.0D;
        for (int i = 0; i < len - 2; i += 2) {
            double xx = Double.valueOf(params[i])-x;
            double yy = Double.valueOf(params[i + 1])-y;
            rnew = Math.sqrt(xx * xx + yy * yy);
            s += Math.abs(rnew - r);
            if (xx>0&&yy>0)
                quadrant.add("1");
            if (xx>0&&yy<0)
                quadrant.add("2");
            if (xx<0&&yy<0)
                quadrant.add("3");
            if (xx<0&&yy>0)
                quadrant.add("4");
        }
        if (quadrant.size()<4)
            throw new RuntimeException("不是圆");
        double avg = s / (double) len * 2.0D;
        return String.valueOf(avg / r * 100.0D).substring(0, 5);
    }
}
