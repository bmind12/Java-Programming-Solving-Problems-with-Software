package course.csv;

import edu.duke.*;
import org.apache.commons.csv.*;

import javax.print.DocFlavor;

public class Part1 {
    public static void main(String[] args) {
        tester();
    }

    private static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        String test1 = countryInfo(parser,"Germany");
        System.out.println(test1); // Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000

        parser = fr.getCSVParser(); // reset
        listExportersTwoProducts(parser, "gold", "diamonds"); // Namibia, South Africa

        parser = fr.getCSVParser(); // reset
        int test2 = numberOfExporters(parser, "gold");
        System.out.println(test2); // 3
    }

    private static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                String exports = record.get("Exports");
                String exportValue = record.get("Value (dollars)");

                return country + ": " + exports + ": " + exportValue;
            };
        }

        return "NOT FOUND";
    }

    private static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    private static int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;

        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }

        return count;
    }
}
