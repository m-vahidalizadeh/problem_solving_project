package leetcode.companies.random;

import java.util.HashMap;
import java.util.Map;

/**
 * Verifying an Alien Dictionary
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 * <p>
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 * <p>
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class AlienLanguage {

    public boolean isAlienSorted(String[] words, String order) {
        int index = 0;
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) orderMap.put(order.charAt(i), index++);
        for (int i = 0; i < words.length - 1; i++) if (!rightOrder(words[i], words[i + 1], orderMap)) return false;
        return true;
    }

    private boolean rightOrder(String word1, String word2, Map<Character, Integer> orderMap) {
        int i1 = 0, i2 = 0, n1 = word1.length(), n2 = word2.length();
        while (i1 < n1 && i2 < n2) {
            int o1 = orderMap.get(word1.charAt(i1++));
            int o2 = orderMap.get(word2.charAt(i2++));
            if (o1 < o2) return true;
            else if (o1 > o2) return false;
        }
        return n1 <= n2;
    }

    public static void main(String[] args) {
        AlienLanguage a = new AlienLanguage();
        System.out.println(a.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(a.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println(a.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }

}
