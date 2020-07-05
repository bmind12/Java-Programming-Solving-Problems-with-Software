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
        int startIndex = dna.toLowerCase().indexOf("atg");
        int taaIndex = findStopCodon(dna.toLowerCase(), startIndex + 3, "taa");
        int tagIndex = findStopCodon(dna.toLowerCase(), startIndex + 3, "tag");
        int tgaIndex = findStopCodon(dna.toLowerCase(), startIndex + 3, "tga");
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
        FileResource fr = new FileResource("./assets/02-03-dna/GRch38dnapart.fa");
        String dna = fr.asString();

        StorageResource data = getAllGenes(dna);
        System.out.println(data.size());
    }

    private static void testCGRatio() {
        float test1 = cgRatio("ATGCCATAG");
        System.out.println(test1); // .4444444
    }

    private static float cgRatio(String dna) {
        int cgCount = 0;

        for (char c : dna.toLowerCase().toCharArray()) {
            if (c == 'c' || c == 'g') cgCount++;
        }

        return ((float) cgCount) / dna.length();
    }

    private static int countCTG(String dna) {
        int countCTG = 0;
        int currIndex = 0;

        while (true) {
            currIndex = dna.toLowerCase().indexOf("ctg", currIndex);

            if (currIndex == -1) break;

            countCTG++;
            currIndex += 3;
        }

        return countCTG;
    }

    private static void processGenes(StorageResource sr) {
        int longerThanThresholdStringsCount = 0;
        int cdRatioHigherThan35 = 0;
        int longestGeneLength = 0;
        int threshold = 60;

        for (String str : sr.data()) {
            int length = str.length();
            System.out.println(str);
            if (length > threshold) {
                System.out.println(str);
                longerThanThresholdStringsCount++;
            }

            if (cgRatio(str) > 0.35) {
                System.out.println(str);
                cdRatioHigherThan35++;
            }

            if (length > longestGeneLength) {
                longestGeneLength = length;
            }
        }

        System.out.println("longerThanThresholdStringsCount: " + longerThanThresholdStringsCount);
        System.out.println("cdRatioHigherThan35: " + cdRatioHigherThan35);
        System.out.println("longestGeneLength: " + longestGeneLength);
    }

    private static void testProcessGenes() {
        FileResource fr = new FileResource("./assets/02-03-dna/GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr1 = new StorageResource();
        sr1.add(dna); // Longer than 9
        processGenes(getAllGenes(dna));
//        System.out.println("countCTG(sr1.toString()): " + countCTG(dna));
    }
}