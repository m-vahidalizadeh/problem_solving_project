package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Strobogrammatic Number
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num = "69"
 * Output: true
 * Example 2:
 * <p>
 * Input: num = "88"
 * Output: true
 * Example 3:
 * <p>
 * Input: num = "962"
 * Output: false
 * Example 4:
 * <p>
 * Input: num = "1"
 * Output: true
 */
public class StrobogrammaticNumber {

    Map<Character, Character> map = new HashMap<>() {{
        put('0', '0');
        put('1', '1');
        put('6', '9');
        put('8', '8');
        put('9', '6');
    }};

    public boolean isStrobogrammatic(String num) {
        int n = num.length();
        if (n == 0) return true;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            char lC = num.charAt(l++);
            char rC = num.charAt(r--);
            if (!map.containsKey(lC)) return false;
            if (map.get(lC) != rC) return false;
        }
        if (l == r) {
            char c = num.charAt(l);
            if (!map.containsKey(c)) return false;
            return c == map.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber s = new StrobogrammaticNumber();
        System.out.println(s.isStrobogrammatic("69"));
        System.out.println(s.isStrobogrammatic("88"));
        System.out.println(s.isStrobogrammatic("962"));
        System.out.println(s.isStrobogrammatic("1"));
        System.out.println(s.isStrobogrammatic("2"));
        System.out.println(s.isStrobogrammatic("1990661"));
    }

}
