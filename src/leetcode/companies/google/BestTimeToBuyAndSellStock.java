package leetcode.companies.google;

/**
 * Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] max = new int[n];
        int[] min = new int[n];
        for (int i = n - 1; i >= 0; i--) max[i] = i == n - 1 ? prices[i] : Math.max(prices[i], max[i + 1]);
        for (int i = 0; i < n; i++) min[i] = i == 0 ? prices[0] : Math.min(prices[i], min[i - 1]);
        int maximum = 0;
        for (int i = 0; i < n; i++) maximum = Math.max(maximum, max[i] - min[i]);
        return maximum;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock b = new BestTimeToBuyAndSellStock();
        System.out.println(b.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
