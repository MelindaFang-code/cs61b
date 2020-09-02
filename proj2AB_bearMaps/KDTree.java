package proj2AB_bearMaps;

import java.util.HashMap;
import java.util.List;



public class KDTree {
	private Node root;
	
	
	private class Node {
		private Point point;
		private Node left;
		private Node right;
		private boolean x_based;
		
		private Node(Point p, boolean x) {
			this.point = p;
			x_based = x;			
		}	
		private double x() {
			return this.point.getX();
		}
		private double y() {
			return this.point.getY();
		}
	}
	
	
	public KDTree(List<Point> points) {
		for(Point p : points) {
			put(p);
		}
	}
	
	public void put(Point p) {
    	root = put(p, root, true);
    }
    
	/* x_b is used to make sure that at last the node knows if it is x_based or not */
    private Node put(Point p, Node x, boolean x_b) {
    	if (x == null) {    		
    		return new Node(p, x_b);    		
    	}
    	if (x.x_based) { 
    		if(Double.compare(x.x(), p.getX()) > 0) {
    			x.left = put(p, x.left, false); 
    		}else {
    			x.right = put(p, x.right, false);
    		}
    	}else {
    		if(Double.compare(x.y(), p.getY()) > 0) {
    			x.left = put(p, x.left, true); 
    		}else {
    			x.right = put(p, x.right, true);
    		}    	
    	} 
    	return x;    	
    }
	
    
	public Point nearest(double x, double y) {
    	Node nearest = nearestHelper(root, new Point(x,y), root);
    	return nearest.point;
		
	}
	
	private Node nearestHelper(Node n, Point goal, Node best) {
		if(n == null) {
			return best;
		}
		
		if(Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
			best = n;
		}
		Node good, bad;
        if (n.x_based) {
            if (Double.compare(n.x(), goal.getX()) >= 0) {
                good = n.left;
                bad = n.right;
            } else {
                good = n.right;
                bad = n.left;
            }
        } else {
            if (Double.compare(n.y(), goal.getY()) >= 0) {
                good = n.left;
                bad = n.right;
            } else {
                good = n.right;
                bad = n.left;
            }
        }
        best = nearestHelper(good, goal, best);   
        double b = Point.distance(best.point, goal);
        if(n.x_based && Double.compare(Point.distance(new Point(n.x(), goal.getY()), goal), b) < 0) {
        	best = nearestHelper(bad, goal, best);
        }else if(!n.x_based && Double.compare(Point.distance(new Point(n.y(), goal.getX()), goal), b) < 0) {
        	best = nearestHelper(bad, goal, best);
        }
        return best;
	}

}
