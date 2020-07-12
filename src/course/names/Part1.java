package course.names;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part1 {
    public static void main(String[] args) {
        FileResource fr = new FileResource("./assets/04-01-names/example-small.csv");

        System.out.println(getAverageRank("Mason", "M")); // 3.0
        System.out.println(getAverageRank("Jacob", "M")); // 2.66
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

    private static int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("./assets/04-01-names/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser();
        int rank = 1;

        for (CSVRecord record : parser) {
            String nameRecord = record.get(0);
            String genderRecord = record.get(1);

            if (genderRecord.equals(gender)) {
                if (nameRecord.equals(name)) return rank;

                rank++;
            };

        }

        return -1;
    }

    private static String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("./assets/04-01-names/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser();
        int currentRank = 1;

        for (CSVRecord record : parser) {
            String nameRecord = record.get(0);
            String genderRecord = record.get(1);

            if (genderRecord.equals(gender)) {
                if (currentRank == rank) return nameRecord;

                currentRank++;
            };

        }

        return "NO NAME";
    };

    private static void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
    }

    private static int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;

        for (File file : dr.selectedFiles()) {
            int year = Integer.parseInt(file.getName().replaceAll("\\D+",""));
            int rank = getRank(year, name, gender);
            if (rank < highestRank || highestRank == -1) highestRank = rank;
        }

        return highestRank;
    }

    private static double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0;
        double occurances = 0;

        for (File file : dr.selectedFiles()) {
            int year = Integer.parseInt(file.getName().replaceAll("\\D+",""));
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                rankSum += rank;
                occurances++;
            };
        }

        if (rankSum != 0) return rankSum / occurances;

        return -1;
    }
}
