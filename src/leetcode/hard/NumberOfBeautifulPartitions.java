package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2478. Number of Beautiful Partitions
 * You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.
 *
 * A partition of s is called beautiful if:
 *
 * s is partitioned into k non-intersecting substrings.
 * Each substring has a length of at least minLength.
 * Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and '7', and the rest of the digits are non-prime.
 * Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 *
 * Input: s = "23542185131", k = 3, minLength = 2
 * Output: 3
 * Explanation: There exists three ways to create a beautiful partition:
 * "2354 | 218 | 5131"
 * "2354 | 21851 | 31"
 * "2354218 | 51 | 31"
 * Example 2:
 *
 * Input: s = "23542185131", k = 3, minLength = 3
 * Output: 1
 * Explanation: There exists one way to create a beautiful partition: "2354 | 218 | 5131".
 * Example 3:
 *
 * Input: s = "3312958", k = 3, minLength = 1
 * Output: 1
 * Explanation: There exists one way to create a beautiful partition: "331 | 29 | 58".
 *
 * Constraints:
 *
 * 1 <= k, minLength <= s.length <= 1000
 * s consists of the digits '1' to '9'.
 */
public class NumberOfBeautifulPartitions {

    public int beautifulPartitions(String s, int k, int minL) {
        int n = s.length();
        if (minL * k > n) return 0;
        boolean[] isP = new boolean[10];
        isP[2] = isP[3] = isP[5] = isP[7] = true;
        if (!isP[s.charAt(0) - '0'] || isP[s.charAt(n - 1) - '0']) return 0;
        List<Integer> cuts = new ArrayList<>();
        cuts.add(0);
        for (int i = minL; i < n; i++) {
            if (isP[s.charAt(i) - '0'] && !isP[s.charAt(i - 1) - '0']) cuts.add(i);
        }
        if (cuts.size() < k - 1) return 0;
        int[][] dp = new int[k + 1][cuts.size()];
        for (var _a : dp) Arrays.fill(_a, -1);
        return dfs(cuts, k, 0, n, minL, dp);
    }

    private int dfs(List<Integer> cuts, int k, int cur, int n, int len, int[][] dp) {
        if (k == 1) return dp[k][cur] = cuts.get(cur) + len <= n ? 1 : 0;
        if (dp[k][cur] > -1) return dp[k][cur];
        dp[k][cur] = 0;
        for (int i = cur + 1; i < cuts.size() - k + 2; i++) {
            if (cuts.get(i) - cuts.get(cur) >= len) {
                dp[k][cur] = (dp[k][cur] + dfs(cuts, k - 1, i, n, len, dp)) % 1_000_000_007;
            }
        }
        return dp[k][cur];
    }

}
