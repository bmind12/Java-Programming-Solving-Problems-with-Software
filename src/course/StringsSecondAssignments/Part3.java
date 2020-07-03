package course.StringsSecondAssignments;

public class Part3 {
    public static void main(String[] args) {
        testCountGenes();
    }

    private static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) return currIndex;

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

        if (minIndex == -1 || (minIndex > tagIndex && tagIndex != -1)) minIndex = tagIndex;
        if (minIndex == -1 || (minIndex > tgaIndex && tgaIndex != -1)) minIndex = tgaIndex;

        if (startIndex == -1 || minIndex == -1) return "";

        return dna.substring(startIndex, minIndex + 3);
    }

    private static void printAllGenes(String dna) {
        int startIndex = 0;

        while (true) {
            String currGene = findGene(dna.substring(startIndex));

            if (currGene.equals("")) break;

            System.out.println(currGene);

            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }

    private static int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;

        while (true) {
            String currGene = findGene(dna.substring(startIndex));

            if (currGene.equals("")) break;

            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            count++;
        }

        return count;
    }

    private static void testCountGenes() {
        int test1 = countGenes("ATGTAAGATGCCCTAGT");
        System.out.println("test1: " + test1);
        int test2 = countGenes("ATGTAG");
        System.out.println("test2: " + test2);
        int test3 = countGenes("ATGTTTTAGAATAG");
        System.out.println("test3: " + test3);
        int test4 = countGenes("ATGTTAATAG");
        System.out.println("test4: " + test4);
        int test5 = countGenes("TAATAGATGTTTTGATAATAG");
        System.out.println("test5: " + test5);
        int test6 = countGenes("ATTTAA");
        System.out.println("test6: " + test6);
        int test7 = countGenes("ATGTGAAAATTTAAATGTAGATGTTTTAATAGTAATAGATGTTTTGATAATAGTAGATGTTTTTTTTTAAATGTGA");
        System.out.println("test7: " + test7);
        int test8 = countGenes("ATGTTAG");
        System.out.println("test8: " + test8);
    }
}
