package leetcode.companies.random;

import java.util.Arrays;

/**
 * Coin Change
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int c : coins) {
            if (c == amount) return 1;
            if (c < amount) dp[c] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if ((i - c) >= 0 && dp[i - c] != Integer.MAX_VALUE) {
                    int tempMin = dp[i - c] + 1;
                    if (tempMin < dp[i]) dp[i] = tempMin;
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int[] coins1 = {1, 2, 5};
        System.out.println(c.coinChange(coins1, 11));
        int[] coins2 = {2};
        System.out.println(c.coinChange(coins2, 3));
    }

}
