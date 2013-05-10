/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        
        /* YOUR CODE HERE */
        
        double a = 1.0;
        
        double positiveZero = (a - a) / a;
        
        if ((this.x == that.x) && (this.y == that.y)) // Degenerate line segment
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)         // Vertical line segment
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y)              // Horizontal line segment
            return positiveZero;
        return ((double) (that.y - this.y) / (that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        
        /* YOUR CODE HERE */
        
        if ((this.y < that.y)  || (this.y == that.y && this.x < that.x))
            return -1;
        else if ((this.x == that.x) && (this.y == that.y))
            return 0;
        else
            return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class SlopeOrder implements Comparator<Point>
    {
       public int compare(Point p1, Point p2)
        {
            if (slopeTo(p1) < slopeTo(p2))
                return -1;
            else if (slopeTo(p1) > slopeTo(p2))
               return 1;
            else
               return 0;
        }
    };
    
        // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
