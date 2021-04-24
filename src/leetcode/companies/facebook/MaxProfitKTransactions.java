package leetcode.companies.facebook;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * Constraints:
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfitKTransactions {

    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0; // There is no day to gain profit
        if (K >= n / 2) { // n/2 most number of transactions in n days (buy,sell)- so, this means unlimited transactions.
            int profit = 0;
            for (int i = 1; i < n; i++) { // Accumulate the profits piece by piece
                if (prices[i - 1] < prices[i]) profit += prices[i] - prices[i - 1];
            }
            return profit;
        }
        int[][] buy = new int[n][K + 1]; // DP if you buy the stock on day i
        int[][] sell = new int[n][K + 1]; // DP if you sell the stock on day i
        for (int k = 0; k <= K; k++) buy[0][k] = -prices[0];
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                buy[i][k] = Math.max(buy[i - 1][k], sell[i - 1][k - 1] - prices[i]);
                sell[i][k] = Math.max(sell[i - 1][k], buy[i - 1][k] + prices[i]);
            }
        }
        return sell[n - 1][K];
    }

}
