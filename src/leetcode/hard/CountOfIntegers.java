package leetcode.hard;

/**
 * 2719. Count of Integers
 * You are given two numeric strings num1 and num2 and two integers max_sum and min_sum. We denote an integer x to be good if:
 *
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * Return the number of good integers. Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that digit_sum(x) denotes the sum of the digits of x.
 *
 * Example 1:
 *
 * Input: num1 = "1", num2 = "12", min_sum = 1, max_sum = 8
 * Output: 11
 * Explanation: There are 11 integers whose sum of digits lies between 1 and 8 are 1,2,3,4,5,6,7,8,10,11, and 12. Thus, we return 11.
 * Example 2:
 *
 * Input: num1 = "1", num2 = "5", min_sum = 1, max_sum = 5
 * Output: 5
 * Explanation: The 5 integers whose sum of digits lies between 1 and 5 are 1,2,3,4, and 5. Thus, we return 5.
 *
 * Constraints:
 *
 * 1 <= num1 <= num2 <= 1022
 * 1 <= min_sum <= max_sum <= 400
 */
public class CountOfIntegers {

    private static final int MOD = (int) 1e9 + 7;
    private Integer[][][][] dp;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        int minLength = num1.length(), maxLength = num2.length(), leadingZeros = maxLength - minLength;
        String num1extended = "0".repeat(leadingZeros) + num1;
        dp = new Integer[maxLength][2][2][401];
        int total = countStrings(0, true, true, max_sum, num1extended, num2);
        int unnecessary = countStrings(0, true, true, min_sum - 1, num1extended, num2);
        int ans = (total - unnecessary) % MOD;
        return ans < 0 ? ans + MOD : ans;
    }

    private int countStrings(int i, boolean lowerBound, boolean upperBound, int sum, String num1, String num2) {
        if (sum < 0) return 0;
        if (i == num2.length()) return 1;
        if (dp[i][lowerBound ? 1 : 0][upperBound ? 1 : 0][sum] != null)
            return dp[i][lowerBound ? 1 : 0][upperBound ? 1 : 0][sum];
        int lo = lowerBound ? num1.charAt(i) - '0' : 0, hi = upperBound ? num2.charAt(i) - '0' : 9, count = 0;
        for (int digit = lo; digit <= hi; digit++)
            count = (count % MOD + countStrings(i + 1, lowerBound & (digit == lo), upperBound & (digit == hi), sum - digit, num1, num2)) % MOD;
        return dp[i][lowerBound ? 1 : 0][upperBound ? 1 : 0][sum] = count;
    }

}
