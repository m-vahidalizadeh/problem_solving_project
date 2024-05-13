package leetcode.companies.facebook;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
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
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class StockMaxProfitIII {

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length <= 1) return 0;
        int[][] trans = new int[k][2];
        for (int i = 0; i < k; i++) trans[i][0] = Integer.MAX_VALUE;
        for (int price : prices) {
            for (int i = 0; i < k; i++) {
                int prevProfit = 0;
                if (i > 0) prevProfit = trans[i - 1][1];
                trans[i][0] = Math.min(trans[i][0], price - prevProfit);
                trans[i][1] = Math.max(trans[i][1], price - trans[i][0]);
            }
        }
        return trans[k - 1][1];
    }

}
