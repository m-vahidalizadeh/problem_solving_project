package leetcode.hard;

import java.util.Arrays;

/**
 * 899. Orderly Queue
 * A string s of lowercase letters is given.  Then, we may make any number of moves.
 *
 * In each move, we choose one of the first k letters (starting from the left), remove it, and place it at the end of the string.
 *
 * Return the lexicographically smallest string we could have after any number of moves.
 *
 * Example 1:
 *
 * Input: s = "cba", k = 1
 * Output: "acb"
 * Explanation:
 * In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
 * In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
 * Example 2:
 *
 * Input: s = "baaca", k = 3
 * Output: "aaabc"
 * Explanation:
 * In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
 * In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 *
 * Note:
 *
 * 1 <= k <= s.length <= 1000
 * s consists of lowercase letters only.
 */
public class OrderlyQueue {

    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] sChars = s.toCharArray();
            Arrays.sort(sChars);
            return new String(sChars);
        }
        String res = s;
        for (int i = 1; i < s.length(); i++) {
            String temp = s.substring(i) + s.substring(0, i);
            if (res.compareTo(temp) > 0) res = temp;
        }
        return res;
    }

}
