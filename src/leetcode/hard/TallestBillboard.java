package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 956. Tallest Billboard
 * You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
 *
 * You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
 *
 * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
 *
 * Example 1:
 *
 * Input: rods = [1,2,3,6]
 * Output: 6
 * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
 * Example 2:
 *
 * Input: rods = [1,2,3,4,5,6]
 * Output: 10
 * Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
 * Example 3:
 *
 * Input: rods = [1,2]
 * Output: 0
 * Explanation: The billboard cannot be supported, so we return 0.
 *
 * Constraints:
 *
 * 1 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * sum(rods[i]) <= 5000
 *
 * Solution:
 * case 1
 * If put x to tall side
 * ------- y ------|----- d -----|----- x -----|
 * ------- y ------|
 *
 * We update dp[d + x] = max(dp[d + x], y )
 *
 * case 2.1
 * Put x to low side and d >= x:
 * -------y------|----- d -----|
 * -------y------|--- x ---|
 *
 * We update dp[d-x] = max(dp[d - x], y + x)
 *
 * case 2.2
 * Put x to low side and d < x:
 * ------- y ------|----- d -----|
 * ------- y ------|------- x -------|
 * We update dp[x - d] = max(dp[x - d], y + d)
 */
public class TallestBillboard {

    public int tallestBillboard(int[] rods) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int x : rods) {
            Map<Integer, Integer> curr = new HashMap<>(dp);
            for (int d : curr.keySet()) {
                dp.put(d + x, Math.max(curr.get(d), dp.getOrDefault(d + x, 0)));
                if (d >= x) dp.put(d - x, Math.max(curr.get(d) + x, dp.getOrDefault(d - x, 0)));
                else dp.put(x - d, Math.max(curr.get(d) + d, dp.getOrDefault(x - d, 0)));
            }
        }
        return dp.get(0);
    }

}