package leetcode.companies.facebook;

import java.util.*;

/**
 * 269. Alien Dictionary
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 */
public class AlienDict {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int n = words.length;
        Map<Character, Integer> inDegrees = new HashMap<>();
        Map<Character, List<Character>> adj = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegrees.put(c, 0);
                adj.put(c, new ArrayList<>());
            }
        }
        for (int i = 0; i < n - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            boolean found = false;
            for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
                char c1 = first.charAt(j);
                char c2 = second.charAt(j);
                if (c1 != c2) {
                    found = true;
                    inDegrees.put(c2, inDegrees.get(c2) + 1);
                    adj.get(c1).add(c2);
                    break;
                }
            }
            if (!found && second.length() < first.length()) return "";
        }
        StringBuilder res = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : inDegrees.entrySet()) {
            if (e.getValue() == 0) {
                q.add(e.getKey());
                res.append(e.getKey());
            }
        }
        while (!q.isEmpty()) {
            Character curr = q.poll();
            for (char child : adj.get(curr)) {
                int newInDegree = inDegrees.get(child) - 1;
                inDegrees.put(child, newInDegree);
                if (newInDegree == 0) {
                    res.append(child);
                    q.add(child);
                }
            }
        }
        return res.length() == inDegrees.size() ? res.toString() : "";
    }

}
