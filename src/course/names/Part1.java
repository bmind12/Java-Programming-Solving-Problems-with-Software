package course.names;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part1 {
    public static void main(String[] args) {
        FileResource fr = new FileResource("/Users/personal/Downloads/us_babynames/us_babynames_by_year/yob1900.csv");

        System.out.println(yearOfHighestRank("Mich", "M"));
    }

    private static void totalBirths(FileResource fr) {
        int girlNamesCount = 0;
        int boysNamesCount = 0;

        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {
            String gender = record.get(1);

            if (gender.equals("F")) {
                girlNamesCount++;
            } else {
                boysNamesCount++;
            };
        }

        System.out.println("Girl names in the file: " + girlNamesCount);
        System.out.println("Boy names in the file: " + boysNamesCount);
        System.out.println("Total names in the file: " + (girlNamesCount + boysNamesCount));
    }

    private static int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("/Users/personal/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
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
        FileResource fr = new FileResource("/Users/personal/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
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
        int yearOfHighestRank = -1;

        for (File file : dr.selectedFiles()) {
            int year = Integer.parseInt(file.getName().replaceAll("\\D+",""));
            int rank = getRank(year, name, gender);
            System.out.println("In year: " + year + " ranked at: " + rank);
            if (rank < highestRank || highestRank == -1 && rank != -1) {
                highestRank = rank;
                yearOfHighestRank = year;
            };
        }

        return yearOfHighestRank;
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

    private static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("/Users/personal/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int totalBirthsNumber = 0;

        for (CSVRecord record : parser) {
            String nameRecord = record.get(0);
            String genderRecord = record.get(1);
            int births = Integer.parseInt(record.get(2));

            if (genderRecord.equals(gender)) {
                if (nameRecord.equals(name)) return totalBirthsNumber;

                totalBirthsNumber += births;
            };

        }

        return totalBirthsNumber;
    }
}
