package course.csv;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part2 {
    static private int ERROR_TEMP = -9999;

    public static void main(String[] args) {
        testAverageTemperatureInFile();
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

            if (temp < currentLowestTemp && currentLowestTemp != ERROR_TEMP) {
                currentLowest = record;
            }
        }

        return currentLowest;
    }

    private static String fileWithColdestTemperature() {
        String coldestTempFileName = null;
        CSVRecord lowestTempRecord = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = coldestHourInFile(parser);

            if (lowestTempRecord == null) {
                coldestTempFileName = file.getName();
                lowestTempRecord = record;

                continue;
            }

            double lowestTemp = Double.parseDouble(lowestTempRecord.get("TemperatureF"));
            double temp = Double.parseDouble(record.get("TemperatureF"));

            if (temp < lowestTemp) {
                coldestTempFileName = file.getName();
                lowestTempRecord = record;
            }
        }

        return coldestTempFileName;
    }

    private static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;

        for (CSVRecord record : parser) {
            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = record;
                continue;
            }

            String currentHumidityStr = record.get("Humidity");

            if (currentHumidityStr == "N/A") continue;

            int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
            int currentHumidity = Integer.parseInt(currentHumidityStr);


            if (lowestHumidity > currentHumidity) {
                lowestHumidityRecord = record;
            }
        }

        return lowestHumidityRecord;
    }

    private static void lowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = null;

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);

            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = record;
                continue;
            }

            int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
            int currentHumidity = Integer.parseInt(record.get("Humidity"));

            if (lowestHumidity > currentHumidity) {
                lowestHumidityRecord = record;
            }
        }

        String humidity = lowestHumidityRecord.get("Humidity");
        String date = lowestHumidityRecord.get("DateUTC");

        System.out.println("Lowest Humidity was " + humidity + " at " + date);
    }

    private static double averageTemperatureInFile(CSVParser parser) {
        double temperatureSum = 0;
        int countOfRecords = 0;

        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));

            if (temp != ERROR_TEMP) {
                temperatureSum += temp;
                countOfRecords++;
            }
        }

        return temperatureSum / countOfRecords;
    }

    private static void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        CSVRecord record = coldestHourInFile(parser);
        System.out.println();
        System.out.println(record.get("TemperatureF") + " on " + record.get("DateUTC"));
    }

    private static void testFileWithColdestTemperature() {
        String test1 = fileWithColdestTemperature();
        FileResource fr = new FileResource("./assets/03-02-csv/nc_weather/2014/" + test1);
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    private static void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(csv.get("DateUTC"));
    }

    private static void testLowestHumidityInManyFiles() {
        lowestHumidityInManyFiles();
    }

    private static void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        double test1 = averageTemperatureInFile(parser);

        System.out.println("Average temperature in file is " + test1);
    }
}
