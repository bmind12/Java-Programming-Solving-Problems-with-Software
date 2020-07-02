package course.tagfinder;

public class TagFinder2 {
    public static void main(String[] args) {
        testSimpleGene();
    }

    private static String findSimpleGene(String dna) {
        boolean isUppercase = dna.charAt(0) <= 'Z';
        String startPattern = isUppercase ? "ATG" : "atg";
        String endPattern = isUppercase ? "TAA" : "taa";
        int startCodon = dna.indexOf(startPattern);
        String result = "";

        if (startCodon == -1) return result;

        int stopCodon = dna.indexOf(endPattern, startCodon + 3);

        if (stopCodon == -1) return result;

        String gene = dna.substring(startCodon, stopCodon + 3);

        if (gene.length() % 3 == 0) {
            result = gene;
        };

        return result;
    }

    private static void testSimpleGene() {
        String[] dnas = {
                "AAATGCCCTAACTAGATTAAGAAACC",
        };

        for (String dna : dnas) {
            System.out.println("dna: " + findSimpleGene(dna));
        }
    }
}
