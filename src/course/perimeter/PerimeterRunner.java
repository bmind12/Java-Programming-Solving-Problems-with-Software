package course.perimeter;

import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("./assets/perimeter/example2.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + getNumPoints(s));
        System.out.println("avg length = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest X = " + getLargestX(s));
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }

    private static int getNumPoints (Shape s) {
        int pointsCount = 0;

        for (Point point : s.getPoints()) {
            pointsCount++;
        }

        return pointsCount;
    }

    private static double getAverageLength (Shape s) {
        PerimeterRunner pr = new PerimeterRunner();
        int pointsCount = getNumPoints(s);
        double perimeter = pr.getPerimeter(s);

        return perimeter / pointsCount;
    }

    private static double getLargestSide (Shape s) {
        double largestSide = 0;
        Point lastPoint = s.getLastPoint();

        for (Point point : s.getPoints()) {
            double size = point.distance(lastPoint);

            if (size > largestSide) {
                largestSide = size;
            }

            lastPoint = point;
        }

        return largestSide;
    }

    private static double getLargestX (Shape s) {
        double largestX = s.getLastPoint().getX();

        for (Point point : s.getPoints()) {
            double currX = point.getX();

            if (currX > largestX) {
                largestX = currX;
            }
        }

        return largestX;
    }
}
