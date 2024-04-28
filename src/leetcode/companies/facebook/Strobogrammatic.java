package leetcode.companies.facebook;

import java.util.Map;

/**
 * 246. Strobogrammatic Number
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Example 1:
 *
 * Input: num = "69"
 * Output: true
 * Example 2:
 *
 * Input: num = "88"
 * Output: true
 * Example 3:
 *
 * Input: num = "962"
 * Output: false
 *
 * Constraints:
 *
 * 1 <= num.length <= 50
 * num consists of only digits.
 * num does not contain any leading zeros except for zero itself.
 */
public class Strobogrammatic {

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = Map.of('0', '0', '1', '1', '2', 'a', '3', 'a', '4', 'a', '5', 'a', '6', '9', '7', 'a', '8', '8', '9', '6');
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(num.length() - 1 - i) != map.get(num.charAt(i))) return false;
        }
        return true;
    }

}
