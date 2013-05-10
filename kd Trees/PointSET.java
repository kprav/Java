import java.util.Set;
import java.util.TreeSet;

public class PointSET 
{
    private Set<Point2D> pointSet;
    
    public PointSET()
    {
        pointSet = new TreeSet<Point2D>();
    }
    
    public boolean isEmpty()
    {
        return pointSet.isEmpty();
    }
    
    public int size()
    {
        return pointSet.size();
    }
    
    public void insert(Point2D p)
    {
        pointSet.add(p);
    }
    
    public boolean contains(Point2D p)
    {
        return pointSet.contains(p);
    }
    
    public void draw()
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        
        for (Point2D point : pointSet)
        {
            point.draw();
        }
        
        StdDraw.show(0);
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Queue<Point2D> range = new Queue<Point2D>();
        
        double minX = rect.xmin();
        double minY = rect.ymin();
        
        double maxX = rect.xmax();
        double maxY = rect.ymax();
        
        for (Point2D p : pointSet)
        {
            double x = p.x();
            double y = p.y();
            
            if (x >= minX 
                    &&
                x <= maxX 
                    &&
                y >= minY 
                    &&
                y <= maxY)
            {
                range.enqueue(p);
            }
        }
        
        return range;
    }
    
    public Point2D nearest(Point2D p)
    {
        Point2D nearest = null;
        
        double distance = Double.POSITIVE_INFINITY;
        
        for (Point2D current : pointSet)
        {   
            if (p.distanceSquaredTo(current) < distance)
            {
                distance = p.distanceSquaredTo(current);
                nearest = current;
            }            
            
        }
        
        return nearest;
    }
}