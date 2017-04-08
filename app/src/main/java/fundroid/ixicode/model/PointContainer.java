package fundroid.ixicode.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class PointContainer implements Serializable {
    private String name;
    private ArrayList<Point> points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
}
