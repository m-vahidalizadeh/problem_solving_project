package leetcode.companies.facebook;

/**
 * 67. Add Binary
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 10^4
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class SumBinary {

    public String addBinary(String a, String b) {
        int c = 0, ai = a.length() - 1, bi = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (ai >= 0 && bi >= 0) {
            if (c == 1) {
                c = 0;
                if (a.charAt(ai) == '0' && b.charAt(bi) == '0') {
                    sb.insert(0, '1');
                } else if (a.charAt(ai) == '0' && b.charAt(bi) == '1') {
                    sb.insert(0, 0);
                    c = 1;
                } else if (a.charAt(ai) == '1' && b.charAt(bi) == '0') {
                    sb.insert(0, '0');
                    c = 1;
                } else if (a.charAt(ai) == '1' && b.charAt(bi) == '1') {
                    sb.insert(0, '1');
                    c = 1;
                }
            } else {
                if (a.charAt(ai) == '0' && b.charAt(bi) == '0') {
                    sb.insert(0, '0');
                } else if (a.charAt(ai) == '0' && b.charAt(bi) == '1') {
                    sb.insert(0, '1');
                } else if (a.charAt(ai) == '1' && b.charAt(bi) == '0') {
                    sb.insert(0, '1');
                } else if (a.charAt(ai) == '1' && b.charAt(bi) == '1') {
                    sb.insert(0, '0');
                    c = 1;
                }
            }
            ai--;
            bi--;
        }
        while (ai >= 0) {
            if (c == 1) {
                c = 0;
                if (a.charAt(ai) == '0') {
                    sb.insert(0, '1');
                } else {
                    sb.insert(0, '0');
                    c = 1;
                }
            } else {
                if (a.charAt(ai) == '0') sb.insert(0, '0');
                else sb.insert(0, '1');
            }
            ai--;
        }
        while (bi >= 0) {
            if (c == 1) {
                c = 0;
                if (b.charAt(bi) == '0') {
                    sb.insert(0, '1');
                } else {
                    sb.insert(0, '0');
                    c = 1;
                }
            } else {
                if (b.charAt(bi) == '0') sb.insert(0, '0');
                else sb.insert(0, '1');
            }
            bi--;
        }
        if (c == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

}
