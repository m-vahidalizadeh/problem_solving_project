package leetcode.hard;

/**
 * 1363. Largest Multiple of Three
 * Hard
 *
 * 383
 *
 * 54
 *
 * Add to List
 *
 * Share
 * Given an array of digits digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order. If there is no answer return an empty string.
 *
 * Since the answer may not fit in an integer data type, return the answer as a string. Note that the returning answer must not contain unnecessary leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [8,1,9]
 * Output: "981"
 * Example 2:
 *
 * Input: digits = [8,6,7,1,0]
 * Output: "8760"
 * Example 3:
 *
 * Input: digits = [1]
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= digits.length <= 104
 * 0 <= digits[i] <= 9
 */
public class LargestMultipleOfThree {

    public String largestMultipleOfThree(int[] digits) {
        int[] m1 = {1, 4, 7, 2, 5, 8}, m2 = {2, 5, 8, 1, 4, 7}, ds = new int[10];
        int sum = 0;
        for (int digit : digits) {
            ds[digit]++;
            sum += digit;
        }
        while (sum % 3 != 0) {
            for (int i : sum % 3 == 1 ? m1 : m2) {
                if (ds[i] > 0) {
                    ds[i]--;
                    sum -= i;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) sb.append(Character.toString('0' + i).repeat(ds[i]));
        return sb.length() > 0 && sb.charAt(0) == '0' ? "0" : sb.toString();
    }

}
