package leetcode.companies.google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sentence Similarity II
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
 * <p>
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
 * <p>
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * <p>
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * <p>
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class AreSentencesSimilar2 {

    public class UF {
        Map<String, String> parents;

        public UF() {
            parents = new HashMap<>();
        }

        public void add(String a, String b) {
            a = root(a);
            b = root(b);
            if (!a.equals(b)) {
                parents.put(a, b);
            }
        }

        public String root(String x) {
            while (parents.get(x) != null) x = parents.get(x);
            return x;
        }
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        int n = words1.length;
        int m = words2.length;
        if (n != m) return false;
        UF uf = new UF();
        for (List<String> pair : pairs) {
            uf.add(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < n; i++) {
            if (!uf.root(words1[i]).equals(uf.root(words2[i]))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        AreSentencesSimilar2 a = new AreSentencesSimilar2();
        System.out.println(a.areSentencesSimilarTwo(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"},
                List.of(List.of("great", "good"), List.of("fine", "good"), List.of("acting", "drama"), List.of("skills", "talent"))));
    }

}
