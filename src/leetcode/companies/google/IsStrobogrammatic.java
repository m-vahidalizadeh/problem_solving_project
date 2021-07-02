package leetcode.companies.google;

/**
 * 246. Strobogrammatic Number
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
 * Example 4:
 *
 * Input: num = "1"
 * Output: true
 *
 * Constraints:
 *
 * 1 <= num.length <= 50
 * num consists of only digits.
 * num does not contain any leading zeros except for zero itself.
 */
public class IsStrobogrammatic {

    public boolean isStrobogrammatic(String num) {
        int n = num.length();
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            char lC = num.charAt(l++);
            char rC = num.charAt(r--);
            if (!(lC == '0' && rC == '0') && !(lC == '1' && rC == '1') && !(lC == '8' && rC == '8')) {
                if (!(lC == '9' && rC == '6') && !(lC == '6' && rC == '9')) return false;
            }
        }
        return true;
    }

}
