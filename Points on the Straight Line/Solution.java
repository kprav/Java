public class Solution {
    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() != b.size()) {
            // throw exception
        } else if (a.size() == 0) {
            return 0;
        } else if (a.size() == 1) {
            return 1;
        }
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int maxPointsInLine = 0;
        int currentMax = 0;
        int verticalPoints = 0;
        int overlapPoints = 0;
        for (int i = 0; i < a.size(); i++) {
            currentMax = 0;
            verticalPoints = 0;
            overlapPoints = 0;
            int referencePointX = a.get(i);
            int referencePointY = b.get(i);
            for (int j = i + 1; j < a.size(); j++) {
                int currentPointX = a.get(j);
                int currentPointY = b.get(j);
                if (currentPointX == referencePointX && currentPointY == referencePointY) {
                    overlapPoints++;
                } else if (currentPointX == referencePointX) {
                    verticalPoints++;
                } else {
                    int xDiff = currentPointX - referencePointX;
                    int yDiff = currentPointY - referencePointY;
                    double slope = (double) yDiff / (double) xDiff;
                    if (slope == -0.0) {
                        slope = 0.0;
                    }
                    int pointsInLine = map.getOrDefault(slope, 0);
                    pointsInLine++;
                    map.put(slope, pointsInLine);
                    currentMax = Math.max(currentMax, pointsInLine);
                }
                currentMax = Math.max(currentMax, verticalPoints);
            }
            maxPointsInLine = Math.max(maxPointsInLine, currentMax + overlapPoints + 1);
            map.clear();
        }
        return maxPointsInLine;
    }
}
