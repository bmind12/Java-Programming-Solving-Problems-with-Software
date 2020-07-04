package course.StringsThirdAssignments;

import edu.duke.*;

public class Part21 {
    public static void main(String[] args) {
        testCGRatio();
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

//
//    Hint: 9/2 uses integer division because you are dividing an integer by an integer and thus Java thinks you want the result to be an integer. If you want the result to be a decimal number, then make sure you convert one of the integers to a decimal number by changing it to a float. For example, (float) 9/2 is interpreted by Java as 9.0/2 and if one of the numbers is a decimal, then Java assumes you want the result to be a decimal number. Thus (float) 9/2 is 4.5.
//
//    Write a method countCTG that has one String parameter dna, and returns the number of times the codon CTG appears in dna.
//
}
