package course.tagfinder;

import edu.duke.*;

public class TagFinder4 {
    public static void main(String[] args) {
        testLinkParcer();
    }

    private static void testLinkParcer() {
        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");

        for (String word : urlResource.words()) {
            int youtubeStartsAt = word.toLowerCase().indexOf("youtube");

            if (youtubeStartsAt == -1) continue;

            int linkStartsAt = word.indexOf("\"");
            int linkEndsAt = word.indexOf("\"", youtubeStartsAt);

            System.out.println(word.substring(linkStartsAt + 1, linkEndsAt));
        }
    }
}
