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

        String test1 = countryInfo(parser,"Germany"); // Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
        System.out.println(test1);

//        parser = fr.getCSVParser(); // reset
    }

    private static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            System.out.println(record);

            if (record.get("Country").contains(country)) {
                String exports = record.get("Exports");
                String exportValue = record.get("Value (dollars)");

                return country + ": " + exports + ": " + exportValue;
            };
        }

        return "NOT FOUND";
    }
}
