package leetcode.companies.google;

import java.util.*;

/**
 * Sentence Similarityto List
 * <p>
 * Share
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 * <p>
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 * <p>
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 * <p>
 * Two sentences are similar if:
 * <p>
 * They have the same length (i.e. the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words a and b are similar and the words b and c are similar, a and c are not necessarily similar.
 * <p>
 * Example 1:
 * <p>
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
 * Example 2:
 * <p>
 * Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
 * Output: true
 * Explanation: A word is similar to itself.
 * Example 3:
 * <p>
 * Input: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
 * Output: false
 * Explanation: As they don't have the same length, we return false.
 * <p>
 * Constraints:
 * <p>
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
 * 0 <= similarPairs.length <= 1000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of lower-case and upper-case English letters.
 * All the pairs (xi, yi) are distinct.
 */
public class AreSentencesSimilar {

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String key = pair.get(0);
            String val = pair.get(1);
            if (map.containsKey(key)) {
                Set<String> existingSet = map.get(key);
                existingSet.add(val);
                map.put(key, existingSet);
            } else {
                Set<String> newSet = new HashSet<>();
                newSet.add(val);
                map.put(key, newSet);
            }
        }
        int n = sentence1.length;
        int m = sentence2.length;
        if (n != m) return false;
        for (int i = 0; i < n; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];
            if (!word1.equals(word2)) {
                if (!(
                        (map.containsKey(word1) && map.get(word1).contains(word2)) ||
                                (map.containsKey(word2) && map.get(word2).contains(word1))
                ))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AreSentencesSimilar a = new AreSentencesSimilar();
        System.out.println(a.areSentencesSimilar(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"},
                List.of(List.of("great", "fine"), List.of("drama", "acting"), List.of("skills", "talent"))));
    }

}
