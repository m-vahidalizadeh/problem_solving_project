package leetcode.companies.facebook;

/**
 * 123. Best Time to Buy and Sell Stock III
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class MaxProfitTwoTransactions {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] maxL = new int[n + 1];
        int[] maxR = new int[n + 1];
        int localMax = 0;
        int down = prices[0];
        for (int i = 0; i < n; i++) {
            localMax = Math.max(localMax, prices[i] - down);
            maxL[i] = localMax;
            down = Math.min(down, prices[i]);
        }
        localMax = 0;
        int up = prices[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            localMax = Math.max(localMax, up - prices[i]);
            maxR[i] = localMax;
            up = Math.max(up, prices[i]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, maxL[i] + maxR[i + 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxProfitTwoTransactions m = new MaxProfitTwoTransactions();
        System.out.println(m.maxProfit(new int[]{2, 1, 4, 5, 2, 9, 7}));
    }

}
