package course.csv;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part2 {
    public static void main(String[] args) {
        testColdestHourInFile();
    }

    private static CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord currentLowest = null;

        for (CSVRecord record : parser) {
            if (currentLowest == null) {
                currentLowest = record;
                continue;
            }

            double currentLowestTemp = Double.parseDouble(currentLowest.get("TemperatureF"));
            double temp = Double.parseDouble(record.get("TemperatureF"));

            if (temp < currentLowestTemp) {
                currentLowest = record;
            }
        }

        return currentLowest;
    }

    private static void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        CSVRecord record = coldestHourInFile(parser);
        System.out.println();
        System.out.println(record.get("TemperatureF") + " on " + record.get("DateUTC"));
    }
}
