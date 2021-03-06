package course.perimeter;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
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

    public int getNumPoints (Shape s) {
        int pointsCount = 0;

        for (Point point : s.getPoints()) {
            pointsCount++;
        }

        return pointsCount;
    }

    public double getAverageLength(Shape s) {
        PerimeterRunner pr = new PerimeterRunner();
        int pointsCount = getNumPoints(s);
        double perimeter = pr.getPerimeter(s);

        return perimeter / pointsCount;
    }

    public double getLargestSide(Shape s) {
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

    public double getLargestX(Shape s) {
        // Put code here
        return 0.0;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            largestPerimeter = getPerimeter(s);
        }

        return largestPerimeter;
    }

    public File getFileWithLargestPerimeter() {
        double largestPerimeter = 0;
        File largestPerimeterFile = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);

            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
                largestPerimeterFile = f;
            }
        }

        return largestPerimeterFile;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("avg side = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest perimeter from selected files = " + getLargestPerimeterMultipleFiles());
        System.out.println("largest perimeter file = " + getFileWithLargestPerimeter());
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
