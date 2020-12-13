package leetcode.companies.random;

import java.util.Arrays;

/**
 * 1223. Dice Roll Simulation
 * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
 *
 * Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.
 *
 * Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: n = 2, rollMax = [1,1,2,2,2,3]
 * Output: 34
 * Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
 * Example 2:
 *
 * Input: n = 2, rollMax = [1,1,1,1,1,1]
 * Output: 30
 * Example 3:
 *
 * Input: n = 3, rollMax = [1,1,1,2,2,3]
 * Output: 181
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 */
public class DieSimulator {

    public int dieSimulator(int n, int[] rollMax) {
        int[][] dp = new int[n + 1][7];
        Arrays.fill(dp[0], 1);
        for (int choice = 1; choice <= n; choice++) {
            for (int curr = 0; curr <= 6; curr++) {
                for (int pre = 1; pre <= 6; pre++) {
                    if (pre == curr) continue;
                    for (int count = 1; count <= rollMax[pre - 1] && choice - count >= 0; count++) {
                        dp[choice][curr] = (dp[choice][curr] + dp[choice - count][pre]) % 1000_000_007;
                    }
                }
            }
        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        DieSimulator dieSimulator = new DieSimulator();
        System.out.println(dieSimulator.dieSimulator(2, new int[]{1, 1, 2, 2, 2, 3}));
        System.out.println(dieSimulator.dieSimulator(2, new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println(dieSimulator.dieSimulator(3, new int[]{1, 1, 1, 2, 2, 3}));
    }

}
