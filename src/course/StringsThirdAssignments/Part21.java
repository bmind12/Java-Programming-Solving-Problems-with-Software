package course.StringsThirdAssignments;

import edu.duke.*;

public class Part21 {
    public static void main(String[] args) {
        testProcessGenes();
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

    private static StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource resource = new StorageResource();

        while (true) {
            String currGene = findGene(dna.substring(startIndex));

            if (currGene.equals("")) break;

            resource.add(currGene);

            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }

        return resource;
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

    private static void testFindGene() {
        String test1 = findGene("AATGCTAACTAGCTGACTAAT");
        System.out.println(test1);
    }

    private static void testPrintAllGenes() {
        printAllGenes("ATGTGAAAATTTAAATGTAGATGTTTTAATAGTAATAGATGTTTTGATAATAGTAGATGTTTTTTTTTAAATGTGA");
    }

    private static void testGetAllGenes() {
        StorageResource data = getAllGenes("ATGTGAAAATTTAAATGTAGATGTTTTAATAGTAATAGATGTTTTGATAATAGTAGATGTTTTTTTTTAAATGTGA");
        System.out.println(data.data());
    }

    private static void testCGRatio() {
        float test1 = cgRatio("ATGCCATAG");
        System.out.println(test1); // .4444444
    }

    private static float cgRatio(String dna) {
        float cgCount = 0;

        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') cgCount++;
        }

        return cgCount / dna.length();
    }

    private static int countCTG(String dna) {
        int countCTG = 0;
        int currIndex = 0;

        while (true) {
            currIndex = dna.indexOf("CTG", currIndex);

            if (currIndex == -1) break;

            countCTG++;
            currIndex += 3;
        }

        return countCTG;
    }

    private static void processGenes(StorageResource sr) {
        int longerThan9StringsCount = 0;
        int cdRatioHigherThan35 = 0;
        int longestGeneLength = 0;

        for (String str : sr.data()) {
            int length = str.length();

            if (length > 9) {
                System.out.println(str);
                longerThan9StringsCount++;
            }

            if (cgRatio(str) > 0.35) {
                System.out.println(str);
                cdRatioHigherThan35++;
            }

            if (length > longestGeneLength) {
                longestGeneLength = length;
            }
        }

        System.out.println(longerThan9StringsCount);
        System.out.println(cdRatioHigherThan35);
        System.out.println(longestGeneLength);
    }

    private static void testProcessGenes() {
        StorageResource sr1 = new StorageResource();
        sr1.add("ATGTTTTTTTAA"); // Longer than 9
        processGenes(sr1);
        StorageResource sr2 = new StorageResource();
        sr2.add("ATGTAA"); // Smaller than 9
        processGenes(sr2);
        StorageResource sr3 = new StorageResource();
        sr3.add("ATGGGGGGGTAA"); // Higher CG ratio
        processGenes(sr3);
        StorageResource sr4 = new StorageResource();
        sr4.add("ATGTTTTAA"); // Lower CG ratio
        processGenes(sr4);
    }
}
