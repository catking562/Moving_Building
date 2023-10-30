package taewookim.api;

import java.util.ArrayList;

public class TriangleMath {

    public static ArrayList<Double> cos = new ArrayList<>();
    public static ArrayList<Double> sin = new ArrayList<>();

    static {
        for(int i = 0; i<3600; i++) {
            double d = ((double)i)/10.0D;
            cos.add(Math.cos(Math.toRadians(d)));
            sin.add(Math.sin(Math.toRadians(d)));
        }
    }

}
