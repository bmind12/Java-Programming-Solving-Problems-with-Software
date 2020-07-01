package course.tagfinder;

public class TagFinder3 {
    public static void main(String[] args) {
        testing();
    }

    private static boolean twoOccurrences(String stringA, String stringB) {
        int firstOccurrence = stringB.indexOf(stringA);

        if (firstOccurrence == -1) return false;

        int stringALength = stringA.length();
        int secondOccurrence = stringB.indexOf(stringA, firstOccurrence + stringALength);

        return secondOccurrence != -1;
    }

    private static void testing() {
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
    }
}
