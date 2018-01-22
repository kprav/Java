public class Solution {
    
    private class Point {
        int x;
        int y;
        boolean possible;
        boolean visited;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.possible = true;
            this.visited = false;
        }
    }
    
    private Point[][] makeBoard(int x, int y) {
        Point[][] board = new Point[x + 1][y + 1];
        for (int i = 0; i < x + 1; i++) {
            for (int j = 0; j < y + 1; j++) {
                Point p = new Point(i, j);
                board[i][j] = p;
            }
        }
        return board;
    }
    
    private double findDistance(Point x, Point y) {
        int x1 = x.x;
        int y1 = x.y;
        int x2 = y.x;
        int y2 = y.y;
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    private boolean isPointWithinCircle(ArrayList<Point> circleCenters, int radius, Point p) {
        for (Point center : circleCenters) {
            if (findDistance(center, p) <= radius) {
                return true;
            }
        }
        return false;
    }
    
    private void markPointsAsPossible(Point[][] board, int x, int y,
                                      ArrayList<Point> circleCenters, int radius) {
        for (int i = 0; i < x + 1; i++) {
            for (int j = 0; j < y + 1; j++) {
                Point p = board[i][j];
                if (isPointWithinCircle(circleCenters, radius, p)) {
                    p.possible = false;
                }
            }
        }
    }
    
    private ArrayList<Point> buildCircleCenters(Point[][] board, ArrayList<Integer> xCoord,
                                                ArrayList<Integer> yCoord) {
        ArrayList<Point> circleCenters = new ArrayList<>();
        for (int i = 0; i < xCoord.size(); i++) {
            Point p = board[xCoord.get(i)][yCoord.get(i)];
            circleCenters.add(p);
        }
        return circleCenters;
    }
    
    private boolean isPointOnBoard(int px, int py, int x, int y) {
        if (px >= 0 && py >= 0 && px <= x && py <= y) {
            return true;
        }
        return false;
    }
    
    private Queue<Point> addPoints(Point[][] board, ArrayList<Point> circleCenters,
                                   Queue<Point> neighbors, Point curr,
                                   int x, int y, int radius) {
        Point p;
        //North
        if (isPointOnBoard(curr.x - 1, curr.y, x, y)) {
            p = board[curr.x - 1][curr.y];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //NorthEast
        if (isPointOnBoard(curr.x - 1, curr.y + 1, x, y)) {
            p = board[curr.x - 1][curr.y + 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //East
        if (isPointOnBoard(curr.x, curr.y + 1, x, y)) {
            p = board[curr.x][curr.y + 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //SouthEast
        if (isPointOnBoard(curr.x + 1, curr.y + 1, x, y)) {
            p = board[curr.x + 1][curr.y + 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //South
        if (isPointOnBoard(curr.x + 1, curr.y, x, y)) {
            p = board[curr.x + 1][curr.y];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //SouthWest
        if (isPointOnBoard(curr.x + 1, curr.y - 1, x, y)) {
            p = board[curr.x + 1][curr.y - 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //West
        if (isPointOnBoard(curr.x, curr.y - 1, x, y)) {
            p = board[curr.x][curr.y - 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        //NorthWest
        if (isPointOnBoard(curr.x - 1, curr.y - 1, x, y)) {
            p = board[curr.x - 1][curr.y - 1];
            if (!p.visited) {
                if (p.possible) {
                    neighbors.add(p);
                }
            }
        }
        return neighbors;
    }
    
    // DFS because of recursion (although queue is used it is not BFS)    
    private boolean solve(Point[][] board, ArrayList<Point> circleCenters,
                          Point p, int x, int y, int radius, boolean sol) {
        if (p == board[x][y]) {
          return true;
        }
        p.visited = true;
        Queue<Point> neighbors = new LinkedList<>();
        addPoints(board, circleCenters, neighbors, p, x, y, radius);
        for (Point neighbor : neighbors) {
            sol = sol || solve(board, circleCenters, neighbor, x, y, radius, sol);
        }
        return sol;
    }
    
    public String solve(int x, int y, int N, int R, 
                        ArrayList<Integer> xCoord,
                        ArrayList<Integer> yCoord) {
        Point[][] board = makeBoard(x, y);
        ArrayList<Point> circleCenters = buildCircleCenters(board, xCoord, yCoord);
        markPointsAsPossible(board, x, y, circleCenters, R);
        Point start = board[0][0];
        if (!start.possible) {
            return "NO";
        }
        return solve(board, circleCenters, start, x, y, R, false) ? "YES" : "NO";
    }
    
}
