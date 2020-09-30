package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a set of words D, find the longest word in D that is a subsequence of S.
 * <p>
 * Word W is a subsequence of S if some number of characters, possibly zero, can be deleted from S to form W, without reordering the remaining characters.
 * <p>
 * Note: D can appear in any format (list, hash table, prefix tree, etc.
 * <p>
 * For example, given the input of S = "abppplee" and D = {"able", "ale", "apple", "bale", "kangaroo"} the correct output would be "apple"
 * <p>
 * The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
 * The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
 * The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.
 * Learning objectives
 * <p>
 * This question gives you the chance to practice with algorithms and data structures. It’s also a good example of why
 * careful analysis for Big-O performance is often worthwhile, as is careful exploration of common and worst-case input conditions.
 */
public class RecommendedSeq {

    public String findLongest(String s, String[] d) {
        Map<Character, Integer> parent = getFreq(s);
        String result = "";
        for (String i : d) {
            if (areSeq(parent, getFreq(i)) && i.length() > result.length()) result = i;
        }
        return result;
    }

    private Map<Character, Integer> getFreq(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (char c : s.toCharArray()) result.put(c, result.getOrDefault(c, 0) + 1);
        return result;
    }

    private boolean areSeq(Map<Character, Integer> parent, Map<Character, Integer> child) {
        for (Map.Entry<Character, Integer> e : parent.entrySet()) {
            if (e.getValue() < child.getOrDefault(e.getKey(), 0)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RecommendedSeq r = new RecommendedSeq();
        System.out.println(r.findLongest("abppplee", new String[]{"able", "ale", "apple", "bale", "kangaroo"}));
    }

}
