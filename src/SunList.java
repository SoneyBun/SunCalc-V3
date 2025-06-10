import java.util.ArrayList;

public class SunList {
    // Lists

    private ArrayList<Point> points;
    private static ArrayList<SunList> lists;

    public SunList(ArrayList<Point> p) {
        points = p;
    }

    public Point getAverage() {
        double sumX = 0;
        double sumY = 0;
        for(Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }
        double avgX = sumX / points.size();
        double avgY = sumY / points.size();
        return new Point(avgX, avgY);
    }
}
