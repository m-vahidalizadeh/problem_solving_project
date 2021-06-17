package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 247. Strobogrammatic Number II
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["11","69","88","96"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["0","1","8"]
 *
 * Constraints:
 *
 * 1 <= n <= 14
 */
public class StroboGrammaticNumII {

    List<String> res;
    int n;
    Map<Character, Character> equals;

    public List<String> findStrobogrammatic(int n) {
        if (n == 1) return List.of("0", "1", "8");
        this.res = new ArrayList<>();
        this.n = n;
        equals = Map.of('0', '0', '1', '1', '6', '9', '9', '6', '8', '8');
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append('#');
        rec(0, n - 1, sb);
        return res;
    }

    private void rec(int l, int r, StringBuilder sb) {
        if (r < l) res.add(sb.toString());
        else {
            for (Map.Entry<Character, Character> e : equals.entrySet()) {
                if (e.getKey() == '0' && l == 0) continue;
                if (l == r) {
                    if (e.getKey() == '9' || e.getKey() == '6') continue;
                }
                char tempL = sb.charAt(l);
                char tempR = sb.charAt(r);
                sb.setCharAt(l, e.getKey());
                sb.setCharAt(r, e.getValue());
                rec(l + 1, r - 1, sb);
                sb.setCharAt(l, tempL);
                sb.setCharAt(r, tempR);
            }
        }
    }

}
