package leetcode.companies.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * 97. Interleaving String
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings
 *  respectively, such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * Example 3:
 *
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 * Constraints:
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 */
public class Interleaving {

    String s1, s2, s3;
    Map<String, Boolean> cache;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (s3.length() != s1.length() + s2.length()) return false;
        cache = new HashMap<>();
        return helper(true, 0, 0, 0, 0, 0) || helper(false, 0, 0, 0, 0, 0);
    }

    private boolean helper(boolean isOne, int i1, int i2, int i3, int n, int m) {
        if (i3 == s3.length()) {
            return i1 == s1.length() && i2 == s2.length() && Math.abs(n - m) <= 1;
        }
        if (i1 < s1.length() && i2 < s2.length() && s3.charAt(i3) != s1.charAt(i1) && s3.charAt(i3) != s2.charAt(i2))
            return false;
        String key = isOne + "," + i1 + "," + i2 + "," + i3 + "," + n + "," + m;
        if (cache.containsKey(key)) return cache.get(key);
        if (isOne) {
            if (i1 < s1.length() && s3.charAt(i3) == s1.charAt(i1)) {
                boolean first = helper(isOne, i1 + 1, i2, i3 + 1, n, m);
                boolean second = false;
                if (i2 < s2.length() && s3.charAt(i3) == s2.charAt(i2))
                    second = helper(!isOne, i1, i2 + 1, i3 + 1, n + 1, m);
                boolean res = first || second;
                cache.put(key, res);
                return res;
            } else {
                boolean second = false;
                if (i2 < s2.length() && s3.charAt(i3) == s2.charAt(i2))
                    second = helper(!isOne, i1, i2 + 1, i3 + 1, n + 1, m);
                boolean res = second;
                cache.put(key, res);
                return res;
            }
        } else {
            if (i2 < s2.length() && s3.charAt(i3) == s2.charAt(i2)) {
                boolean first = helper(isOne, i1, i2 + 1, i3 + 1, n, m);
                boolean second = false;
                if (i1 < s1.length() && s3.charAt(i3) == s1.charAt(i1))
                    second = helper(!isOne, i1 + 1, i2, i3 + 1, n, m + 1);
                boolean res = first || second;
                cache.put(key, res);
                return res;
            } else {
                boolean second = false;
                if (i1 < s1.length() && s3.charAt(i3) == s1.charAt(i1))
                    second = helper(!isOne, i1 + 1, i2, i3 + 1, n, m + 1);
                boolean res = second;
                cache.put(key, res);
                return res;
            }
        }
    }

}
