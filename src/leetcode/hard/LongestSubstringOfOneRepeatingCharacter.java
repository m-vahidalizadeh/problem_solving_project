package leetcode.hard;

import java.util.TreeMap;

/**
 * 2213. Longest Substring of One Repeating Character
 * You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.
 *
 * The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].
 *
 * Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of only one repeating character after the ith query is performed.
 *
 * Example 1:
 *
 * Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * Output: [3,3,4]
 * Explanation:
 * - 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
 * - 2nd query updates s = "bbbccc".
 *   The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
 * - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
 * Thus, we return [3,3,4].
 * Example 2:
 *
 * Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * Output: [2,3]
 * Explanation:
 * - 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
 * - 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
 * Thus, we return [2,3].
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * k == queryCharacters.length == queryIndices.length
 * 1 <= k <= 105
 * queryCharacters consists of lowercase English letters.
 * 0 <= queryIndices[i] < s.length
 */
public class LongestSubstringOfOneRepeatingCharacter {

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        char[] arr = s.toCharArray();
        int m = arr.length, n = queryIndices.length;
        int[] output = new int[n];
        TreeMap<Integer, Integer> lengths = new TreeMap<>(), spans = new TreeMap<>();
        for (int i = 0, j = 1; j <= m; j++) {
            if (j == m || arr[i] != arr[j]) {
                lengths.put(j - i, lengths.getOrDefault(j - i, 0) + 1);
                spans.put(i, j - 1);
                i = j;
            }
        }
        for (int i = 0; i < queryIndices.length; i++) {
            int j = queryIndices[i];
            if (arr[j] != queryCharacters.charAt(i)) {
                int l = spans.floorKey(j), r = spans.remove(l), length = r - l + 1;
                if (lengths.get(length) == 1) lengths.remove(length);
                else lengths.put(length, lengths.get(length) - 1);
                if (l < j) {
                    spans.put(l, j - 1);
                    lengths.put(j - l, lengths.getOrDefault(j - l, 0) + 1);
                }
                if (j < r) {
                    spans.put(j + 1, r);
                    lengths.put(r - j, lengths.getOrDefault(r - j, 0) + 1);
                }
                arr[j] = queryCharacters.charAt(i);
                l = j;
                r = j;
                if (j > 0 && arr[j] == arr[j - 1]) {
                    l = spans.floorKey(j);
                    length = spans.remove(l) - l + 1;
                    if (lengths.get(length) == 1) lengths.remove(length);
                    else lengths.put(length, lengths.get(length) - 1);
                }
                if (j < m - 1 && arr[j] == arr[j + 1]) {
                    int key = spans.ceilingKey(j);
                    r = spans.remove(key);
                    length = r - key + 1;
                    if (lengths.get(length) == 1) lengths.remove(length);
                    else lengths.put(length, lengths.get(length) - 1);
                }
                spans.put(l, r);
                lengths.put(r - l + 1, lengths.getOrDefault(r - l + 1, 0) + 1);
            }
            output[i] = lengths.lastKey();
        }
        return output;
    }

}
