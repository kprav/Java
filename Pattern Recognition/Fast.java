import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Fast
{
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        
        boolean drawn = false;
        
        String filename = args[0];
        
        In in = new In(filename);
        
        int N = in.readInt();
        
        Point[] points = new Point[N];
        
        Point[] pointsNaturalOrder = new Point[N];
        
        Map<Point, ArrayList<Point>> drawnMap = 
            new HashMap<Point, ArrayList<Point>>();
        
        List<Point> tempList = new ArrayList<Point>();
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            pointsNaturalOrder[i] = p;
            points[i].draw();
        }
        
        Arrays.sort(pointsNaturalOrder);
        
        for (int i = 0; i < N; i++)
        {
            tempList.clear();
            
            Arrays.sort(points, pointsNaturalOrder[i].SLOPE_ORDER);
            
            double slope = -1;
            
            Point p1 = points[0];
            
            int j = 1;
            
            while (j < N - 2)
            {
                int count = 0;
                
                int k = count;
                
                Point p2 = points[j];
                Point p3 = points[j + 1];
                Point p4 = points[j + 2];
                
                slope = p1.slopeTo(p2);
                
                if ((p1.slopeTo(p3) == slope) && (p1.slopeTo(p4) == slope))
                {
                    tempList.add(p1);
                    tempList.add(p2);
                    tempList.add(p3);
                    tempList.add(p4);
                    count += 4;
                    
                    k = j + count - 1;
                
                    while (k < N)
                    {
                        if (p1.slopeTo(points[k]) == slope)
                        {
                            tempList.add(points[k]);
                            count++;
                        }
                        else
                        {
                            break;
                        }
                        k++;
                    }
                }
                
                if (count >= 4)
                {
                    Collections.sort(tempList);
                    
                    Point first = tempList.get(0);
                    Point last = tempList.get(count - 1);
                    
                    ArrayList<Point> drawnList = drawnMap.get(first);
                    
                    if (drawnList != null)
                    {
                        if (drawnList.contains(last))
                            drawn = true;
                        else
                            drawn = false;
                    }
                    
                    if (!drawn)
                    {
                        first.drawTo(last);
                        
                        if (drawnList == null)
                            drawnList = new ArrayList<Point>();
                        
                        drawnList.add(last);
                        
                        drawnMap.put(first, drawnList);
                        
                        Iterator iter = tempList.iterator();
                        
                        while (iter.hasNext())
                        {
                            StdOut.print(iter.next().toString());
                            if (iter.hasNext())
                                StdOut.print(" -> ");
                        }
                        StdOut.print("\n");
                    }
                }
                
                tempList.clear();
                
                drawn = false;
                
                if (count == 0)
                    j++;
                else
                    j = k - 1;
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);
    }
}

