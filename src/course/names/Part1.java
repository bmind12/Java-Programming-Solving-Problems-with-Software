package course.names;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public static void main(String[] args) {
        FileResource fr = new FileResource("./assets/04-01-names/example-small.csv");

        int rank1 = getRank(2012,"Mason", "M"); // 2
        int rank2 = getRank(2012,"Mason", "F"); // -1
        System.out.println(rank1);
        System.out.println(rank2);
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
}
