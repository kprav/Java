public class KdTree 
{    
    private Node root;
    
    private int size;
    
    private boolean HOR;
    private boolean VER;
    
    private String left;
    private String right;
    private String bottom;
    private String top;
    
    public KdTree()
    {
        root = null;
        size = 0;
        
        HOR = false;
        VER = true;
        
        left = "LEFT";
        right = "RIGHT";
        bottom = "BOTTOM";
        top = "TOP";
    }
    
    public boolean isEmpty()
    {
        return (root == null && size == 0);        
    }
    
    public int size()
    {
        return size;
    }
    
    public void insert(Point2D p)
    {
        root = insert(null, root, p, VER, null);
    }
    
    public boolean contains(Point2D p)
    {
        return get(p) != null;
    }
    
    public void draw()
    {
        if (root == null)
            return;
        
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        root.rect.draw();
        StdDraw.show();
        draw(root, VER);
        StdDraw.show(0);
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Queue<Point2D> range = new Queue<Point2D>();
        range(root, range, rect);
        return range;
    }
    
    public Point2D nearest(Point2D p)
    {
        if (root == null)
        {
            return null;
        }
        
        Nearest nearest = new Nearest(p);
        return nearest(root, p, nearest);
    }
    
    private class Node
    {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        
        public Node(Point2D p, RectHV rect, Node lb, Node rt)
        {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }
    }
    
    private Node insert(Node parent, Node x, Point2D p, 
                        boolean orientation, String side)
    {
        if (x == null)
        {
            RectHV rect;
            if (parent == null)
            {
                rect = new RectHV(0, 0, 1, 1);
            }
            else
            {
                rect = setupRectHV(parent, x, side);
            }
            size++;
            return new Node(p, rect, null, null);
        }
        else
        {
            Point2D xPoint = x.p;
            
            if (xPoint.equals(p))
            {
                return x;
            }
            else 
            {
                if (orientation == VER)
                {
                    if (p.x() < xPoint.x()) 
                    {
                        x.lb = insert(x, x.lb, p, HOR, left);
                        //x.lb.rect = setupRectHV(x, x.lb, left); 
                    }
                    else
                    {
                        x.rt = insert(x, x.rt, p, HOR, right);
                        //x.rt.rect = setupRectHV(x, x.rt, right);
                    }
                }
                else if (orientation == HOR)
                {
                    if (p.y() < xPoint.y())
                    {
                        x.lb = insert(x, x.lb, p, VER, bottom);
                        //x.lb.rect = setupRectHV(x, x.lb, bottom);
                    }
                    else
                    {
                        x.rt = insert(x, x.rt, p, VER, top);
                        //x.rt.rect = setupRectHV(x, x.rt, top);
                    }
                }
            }
        }
        
        return x;
    }
    
    private RectHV setupRectHV(Node parent, Node x, String side)
    {
        RectHV parentRect = parent.rect;
        
        RectHV rect = null;
        
        double parentRectMinX = parentRect.xmin();
        double parentRectMinY = parentRect.ymin();
        double parentRectMaxX = parentRect.xmax();
        double parentRectMaxY = parentRect.ymax();
        
        if (side.equals(left))
        {
            rect = new RectHV(parentRectMinX, parentRectMinY, 
                              parent.p.x(), parentRectMaxY);
        }
        else if (side.equals(bottom))
        {
            rect = new RectHV(parentRectMinX, parentRectMinY, 
                              parentRectMaxX, parent.p.y());
        }
        else if (side.equals(right))
        {
            rect = new RectHV(parent.p.x(), parentRectMinY,
                              parentRectMaxX, parentRectMaxY);
        }
        else
        {
            rect = new RectHV(parentRectMinX, parent.p.y(),
                              parentRectMaxX, parentRectMaxY);
        }
        
        return rect;
    }
    
    private Point2D get(Point2D p)
    {
        return get(root, p, VER);
    }
    
    private Point2D get(Node x, Point2D p, boolean orientation)
    {
        if (x == null)
        {
            return null;
        }
        
        Point2D xPoint = x.p;
        
        if (xPoint.equals(p))
        {
            return p;
        }
        else 
        {
            if (orientation == VER)
            {
                if (p.x() < xPoint.x()) 
                {
                    return get(x.lb, p, HOR);
                }
                else
                {
                   return get(x.rt, p, HOR);
                }
            }
            else
            {
                if (p.y() < xPoint.y())
                {
                    return get(x.lb, p, VER);
                }
                else
                {
                    return get(x.rt, p, VER);
                }
            }
        }
    }
    
    private void draw(Node x, boolean orientation)
    {
        if (x == null)
            return;
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        
        x.p.draw();
        
        if (orientation == VER)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            Point2D north = new Point2D(x.p.x(), x.rect.ymin());
            Point2D south = new Point2D(x.p.x(), x.rect.ymax());
            south.drawTo(north);
            draw(x.lb, HOR);
            draw(x.rt, HOR);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            Point2D west = new Point2D(x.rect.xmin(), x.p.y());
            Point2D east = new Point2D(x.rect.xmax(), x.p.y());
            west.drawTo(east);
            draw(x.lb, VER);
            draw(x.rt, VER);
        }
    }
    
    private void range(Node x, Queue<Point2D> range, RectHV queryRect)
    {
        if (x == null)
        {
            return;
        }
        
        RectHV xRect = x.rect;
        
        if (!queryRect.intersects(xRect))
        {
            return;
        }
        
        double minX = queryRect.xmin();
        double minY = queryRect.ymin();
        
        double maxX = queryRect.xmax();
        double maxY = queryRect.ymax();
        
        double xCoord = x.p.x();
        double yCoord = x.p.y();
        
        if (xCoord >= minX 
                &&
            xCoord <= maxX 
                &&
            yCoord >= minY 
                &&
            yCoord <= maxY)
        {
            range.enqueue(x.p);
        }
        
        range(x.lb, range, queryRect);
        range(x.rt, range, queryRect);
    }
    
    private class Nearest
    {
        private Point2D p;
        private double d;
        public Nearest(Point2D queryPoint)
        {
            this.p = root.p;
            this.d = queryPoint.distanceSquaredTo(this.p);
        }
    }
        
    private Point2D nearest(Node x, Point2D p, Nearest nearest)
    {
        nearest(x, p, nearest, VER);
        return nearest.p;
    }
    
    private void nearest(Node x, Point2D p, Nearest nearest, boolean orientation)
    {
        if (x == null)
            return;
        
        double d = p.distanceSquaredTo(x.p);
        
        if (d < nearest.d)
        {
            nearest.d = d;
            nearest.p = x.p;
        }
        
        if (orientation == VER)
        {
            if (p.x() < x.p.x())
            {
                if (x.lb != null)
                {
                    if (x.lb.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.lb, p, nearest, HOR);
                    }
                }
                if (x.rt != null)
                {
                    if (x.rt.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.rt, p, nearest, HOR);
                    }
                }
            }
            else
            {
                if (x.rt != null)
                {
                    if (x.rt.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.rt, p, nearest, HOR);
                    }
                }
                if (x.lb != null)
                {
                    if (x.lb.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.lb, p, nearest, HOR);
                    }
                }
            }
        }
        
        if (orientation == HOR)
        {
            if (p.y() < x.p.y())
            {
                if (x.lb != null)
                {
                    if (x.lb.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.lb, p, nearest, VER);
                    }
                }
                if (x.rt != null)
                {
                    if (x.rt.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.rt, p, nearest, VER);
                    }
                }
            }
            else
            {
                if (x.rt != null)
                {
                    if (x.rt.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.rt, p, nearest, VER);
                    }
                }
                if (x.lb != null)
                {
                    if (x.lb.rect.distanceSquaredTo(p) < nearest.d)
                    {
                        nearest(x.lb, p, nearest, VER);
                    }
                }
            }
        }
    }
        
}