package course.names;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public static void main(String[] args) {
        FileResource fr = new FileResource("./assets/04-01-names/example-small.csv");

        totalBirths(fr);
    }

    private static void totalBirths(FileResource fr) {
        int girlNamesCount = 0;
        int boysNamesCount = 0;

        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String gender = record.get(1);
            int count = Integer.parseInt(record.get(2));
            if (gender.equals("F")) {
                girlNamesCount += count;
            } else {
                boysNamesCount += count;
            };
        }

        System.out.println("Girl names in the file: " + girlNamesCount);
        System.out.println("Boy names in the file: " + boysNamesCount);
        System.out.println("Total names in the file: " + (girlNamesCount + boysNamesCount));
    }
}
