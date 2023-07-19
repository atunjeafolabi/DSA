package chapter_10;

import chapter_10.hash_maps.ChainHashMap;
import chapter_10.Map.Map;
import chapter_9.priority_queue.Entry;

import java.util.Scanner;

/**
 * Code Fragment 10.2:
 *
 * A program for counting word frequencies in a document, printing the
 * most frequent word. The document is parsed using the Scanner class,
 * for which we change the delimiter for separating tokens from
 * whitespace to any nonletter. We also convert words
 * to lowercase.
 */
public class WordCount {

    public static void main(String[] args) {
        Map<String, Integer> freq = new ChainHashMap<>();   // or any concrete map

        // scan input for words, using all nonletters as delimiters
        Scanner doc = new Scanner(System.in).useDelimiter("[^a-zA-Z]+");

        while (doc.hasNext()) {
            String word = doc.next().toLowerCase();
            Integer count = freq.get(word);     // get the previous count for this word

            if (count == null) {
                count = 0;                      // if not in map, previous count is zero
            }

            freq.put(word, 1 + count);          // (re)assign new count for this word
        }

        int maxCount = 0;
        String maxWord = "no word";

        // ﬁnd max-count word
        for (Entry<String, Integer> ent : freq.entrySet()) {
            if (ent.getValue() > maxCount) {
                maxWord = ent.getKey();
                maxCount = ent.getValue();
            }
        }
        System.out.println("The most frequent word is '" + maxWord);
        System.out.println("' with " + maxCount + "occurrences.");
    }
}
