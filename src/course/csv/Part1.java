package course.csv;

import edu.duke.*;
import org.apache.commons.csv.*;

import javax.print.DocFlavor;

public class Part1 {
    public static void main(String[] args) {
        tester();
    }

    private static void tester() {
        FileResource fr = new FileResource("./assets/03-01-csv/exportdata.csv");
        CSVParser parser = fr.getCSVParser();

//        String test1 = countryInfo(parser,"Germany");
//        System.out.println(test1); // Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000

//        parser = fr.getCSVParser(); // reset
//        listExportersTwoProducts(parser, "cotton", "flowers"); // Namibia, South Africa

//        parser = fr.getCSVParser();
//        int test2 = numberOfExporters(parser, "cocoa");
//        System.out.println(test2); // 3

        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
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

    private static void bigExporters(CSVParser parser, String amount) { // $400,000,000
        for (CSVRecord record : parser) {
            String exportValue = record.get("Value (dollars)");
            if (exportValue.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + exportValue);
            }
        }
    }
}
