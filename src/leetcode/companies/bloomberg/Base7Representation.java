package leetcode.companies.bloomberg;

/**
 * 504. Base 7
 * Given an integer num, return a string of its base 7 representation.
 *
 * Example 1:
 *
 * Input: num = 100
 * Output: "202"
 * Example 2:
 *
 * Input: num = -7
 * Output: "-10"
 *
 * Constraints:
 *
 * -107 <= num <= 107
 */
public class Base7Representation {

    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean hadNonZero = false;
        StringBuilder sb = new StringBuilder();
        if (num < 0) sb.append("-");
        num = Math.abs(num);
        for (int i = 9; i >= 0; i--) {
            double currPow = Math.pow(7, i);
            Double div = Math.floor(num / currPow);
            if (div > 0) {
                hadNonZero = true;
                num -= div * currPow;
                sb.append(div.intValue());
            } else if (hadNonZero) sb.append("0");
        }
        return sb.toString();
    }

}
