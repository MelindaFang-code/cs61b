package proj2AB_bearMaps;

import java.util.List;
import java.lang.Math;

public class NaivePointSet implements PointSet {
	private List<Point> points;
	
	public NaivePointSet(List<Point> points) {
		this.points = points;
		
	}
	
	public Point nearest(double x, double y) {
		Point target = new Point(x, y);
		double dis = Point.distance(target, points.get(0));
		int minIndex = 0;
		for(int i = 0; i < points.size(); i++) {
			double d = Point.distance(target, points.get(i));
			if(d < dis) {
				minIndex = i;
			}
		}
		return points.get(minIndex);
		
	}
	
}
