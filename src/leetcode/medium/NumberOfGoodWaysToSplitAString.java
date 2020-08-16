package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Number of Good Ways to Split a String
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 * <p>
 * Return the number of good splits you can make in s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 * Example 3:
 * <p>
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 * Example 4:
 * <p>
 * Input: s = "acbadbaada"
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * s contains only lowercase English letters.
 * 1 <= s.length <= 10^5
 */
public class NumberOfGoodWaysToSplitAString {

    public int numSplits(String s) {
        int n = s.length();
        if (n < 2) return 0;
        int[] left = new int[n];
        Set<Character> lSet = new HashSet<>();
        int[] right = new int[n];
        Set<Character> rSet = new HashSet<>();
        for (int i = 0; i <= n - 1; i++) {
            // Update left
            lSet.add(s.charAt(i));
            left[i] = lSet.size();
            // Update right
            rSet.add(s.charAt(n - 1 - i));
            right[n - 1 - i] = rSet.size();
        }
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (left[i - 1] == right[i]) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        NumberOfGoodWaysToSplitAString n = new NumberOfGoodWaysToSplitAString();
        System.out.println(n.numSplits("aacaba"));
        System.out.println(n.numSplits("abcd"));
        System.out.println(n.numSplits("aaaaa"));
        System.out.println(n.numSplits("acbadbaada"));
    }

}
