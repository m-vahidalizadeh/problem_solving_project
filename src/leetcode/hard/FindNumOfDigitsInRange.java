package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1067. Digit Count in Range
 * Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.
 *
 * Example 1:
 *
 * Input: d = 1, low = 1, high = 13
 * Output: 6
 * Explanation:
 * The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
 * Example 2:
 *
 * Input: d = 3, low = 100, high = 250
 * Output: 35
 * Explanation:
 * The digit d=3 occurs 35 times in 103,113,123,130,131,...,238,239,243.
 *
 * Note:
 *
 * 0 <= d <= 9
 * 1 <= low <= high <= 2×10^8
 */
public class FindNumOfDigitsInRange {

    public int digitsCount(int d, int low, int high) {
        return helper(d, generateBounds(high), 0, 0, true, true, new Integer[9][9][2][2]) -
                helper(d, generateBounds(low - 1), 0, 0, true, true, new Integer[9][9][2][2]);
    }

    private List<Integer> generateBounds(int n) {
        List<Integer> list = new ArrayList<>();
        String s = String.valueOf(n);
        for (char c : s.toCharArray()) list.add(c - '0');
        return list;
    }

    private int helper(int d, List<Integer> bounds, int i, int count, boolean hadLeadingZero, boolean hasDigitBound, Integer[][][][] dp) {
        if (i == bounds.size()) return count;
        if (dp[i][count][hadLeadingZero ? 1 : 0][hasDigitBound ? 1 : 0] != null)
            return dp[i][count][hadLeadingZero ? 1 : 0][hasDigitBound ? 1 : 0];
        int res = 0;
        int from = 0;
        int to = hasDigitBound ? bounds.get(i) : 9;
        for (int nextD = from; nextD <= to; nextD++) {
            if (hadLeadingZero && nextD == 0) res += helper(d, bounds, i + 1, count, true, false, dp);
            else
                res += helper(d, bounds, i + 1, count + (nextD == d ? 1 : 0), false, (hasDigitBound && nextD == to), dp);
        }
        dp[i][count][hadLeadingZero ? 1 : 0][hasDigitBound ? 1 : 0] = res;
        return res;
    }

}
