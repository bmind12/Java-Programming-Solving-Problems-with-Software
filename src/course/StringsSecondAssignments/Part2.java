package course.StringsSecondAssignments;

public class Part2 {
    public static void main(String[] args) {
        testHowMany();
    }

    private static int howMany(String stringA, String stringB) {
        int currIndex = stringB.indexOf(stringA);
        int occurrences = 0;

        while (currIndex != -1) {
            occurrences++;

            currIndex = stringB.indexOf(stringA, currIndex + stringA.length());
        }

        return occurrences;
    }

    private static void testHowMany() {
        int test1 = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("test1: " + test1); // 3
        int test2 = howMany("AA", "ATAAAA");
        System.out.println("test2: " + test2); // 2
        int test3 = howMany("A", "ATAAAA");
        System.out.println("test3: " + test3); // 5
        int test4 = howMany("AAA", "ATAAAA");
        System.out.println("test4: " + test4); // 1
    }
}
