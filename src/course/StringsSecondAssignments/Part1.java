package course.StringsSecondAssignments;

public class Part1 {
    public static void main(String[] args) {
        testFindStopCodon();
    }

    private static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);

        while (currIndex != -1) {
            if (currIndex % 3 == 0) return currIndex;

            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }

        return currIndex;
    }

    private static String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int taaIndex = findStopCodon(dna, startIndex + 3, "TAA");
        int tagIndex = findStopCodon(dna, startIndex + 3, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex + 3, "TGA");
        int minIndex = taaIndex;

        if (minIndex > tagIndex || minIndex == -1) minIndex = tagIndex;
        if (minIndex > tgaIndex || minIndex == -1) minIndex = tgaIndex;

        if (startIndex == -1 || minIndex == -1) return "";

        return dna.substring(startIndex, minIndex + 3);
    }

    private static void testFindStopCodon() {
        int test1 = findStopCodon("ATGTAA", 3, "TAA");
        System.out.println(test1);
        int test2 = findStopCodon("ATGTTAATAA", 0, "TAA");
        System.out.println(test2);
        int test3 = findStopCodon("ATGTAATAA", 2, "TAA");
        System.out.println(test3);
        int test4 = findStopCodon("ATGTAATAA", 5, "TAA");
        System.out.println(test4);
        int test5 = findStopCodon("ATGTAATAA", 7, "TAA");
        System.out.println(test5);
    }
}

