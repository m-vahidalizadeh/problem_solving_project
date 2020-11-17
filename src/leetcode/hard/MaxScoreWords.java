package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 1255. Maximum Score Words Formed by Letters
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 *
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
 *
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 *
 * Example 1:
 *
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 * Example 2:
 *
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * Word "xxxz" only get a score of 25.
 * Example 3:
 *
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 *
 * Constraints:
 *
 * 1 <= words.length <= 14
 * 1 <= words[i].length <= 15
 * 1 <= letters.length <= 100
 * letters[i].length == 1
 * score.length == 26
 * 0 <= score[i] <= 10
 * words[i], letters[i] contains only lower case English letters.
 */
public class MaxScoreWords {

    Map<Character, Integer> letterFreq;
    Map<String, Map<Character, Integer>> wordFreq;
    Map<String, Integer> wordScore;
    int[] score;
    int n;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        letterFreq = new HashMap<>(); // Build letter freq
        for (char letter : letters) letterFreq.put(letter, letterFreq.getOrDefault(letter, 0) + 1);
        wordFreq = new HashMap<>(); // Build word freq
        for (String word : words) wordFreq.put(word, getFreq(word));
        this.score = score;
        this.n = words.length;
        wordScore = new HashMap<>(); // Build word scores map
        for (String word : words) wordScore.put(word, getScore(word));
        return rec(words, 0, 0); // Backtracking recursive
    }

    private int rec(String[] words, int i, int currScore) {
        if (i == n) return currScore;
        int max = 0;
        if (canSatisfy(words[i])) { // Select the current word
            satisfy(words[i]);
            max = rec(words, i + 1, currScore + wordScore.get(words[i]));
            deSatisfy(words[i]);
        }
        max = Math.max(max, rec(words, i + 1, currScore)); // don't select the current word
        return max;
    }

    private int getScore(String word) {
        int sum = 0;
        for (char c : word.toCharArray()) sum += score[c - 'a'];
        return sum;
    }

    private void satisfy(String word) {
        Map<Character, Integer> wf = wordFreq.get(word);
        for (Map.Entry<Character, Integer> e : wf.entrySet()) {
            letterFreq.put(e.getKey(), letterFreq.get(e.getKey()) - e.getValue());
        }
    }

    private void deSatisfy(String word) {
        Map<Character, Integer> wf = wordFreq.get(word);
        for (Map.Entry<Character, Integer> e : wf.entrySet()) {
            letterFreq.put(e.getKey(), letterFreq.get(e.getKey()) + e.getValue());
        }
    }

    private boolean canSatisfy(String word) {
        Map<Character, Integer> wf = wordFreq.get(word);
        for (Map.Entry<Character, Integer> e : wf.entrySet()) {
            if (e.getValue() > letterFreq.getOrDefault(e.getKey(), 0)) return false;
        }
        return true;
    }

    private Map<Character, Integer> getFreq(String word) {
        Map<Character, Integer> result = new HashMap<>();
        for (char c : word.toCharArray()) result.put(c, result.getOrDefault(c, 0) + 1);
        return result;
    }

    public static void main(String[] args) {
        MaxScoreWords m = new MaxScoreWords();
        System.out.println(m.maxScoreWords(
                new String[]{"dog", "cat", "dad", "good"},
                new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'},
                new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        ));
        System.out.println(m.maxScoreWords(
                new String[]{"xxxz", "ax", "bx", "cx"},
                new char[]{'z', 'a', 'b', 'c', 'x', 'x', 'x'},
                new int[]{4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10}
        ));
        System.out.println(m.maxScoreWords(
                new String[]{"leetcode"},
                new char[]{'l', 'e', 't', 'c', 'o', 'd'},
                new int[]{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        ));
    }

}
