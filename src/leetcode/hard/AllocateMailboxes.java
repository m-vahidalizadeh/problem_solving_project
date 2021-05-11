package leetcode.hard;

import java.util.Arrays;

/**
 * 1478. Allocate Mailboxes
 * Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.
 *
 * Return the minimum total distance between each house and its nearest mailbox.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * Example 1:
 *
 * Input: houses = [1,4,8,10,20], k = 3
 * Output: 5
 * Explanation: Allocate mailboxes in position 3, 9 and 20.
 * Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
 * Example 2:
 *
 * Input: houses = [2,3,5,12,18], k = 2
 * Output: 9
 * Explanation: Allocate mailboxes in position 3 and 14.
 * Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 * Example 3:
 *
 * Input: houses = [7,4,6,1], k = 1
 * Output: 8
 * Example 4:
 *
 * Input: houses = [3,6,14,10], k = 4
 * Output: 0
 *
 * Constraints:
 *
 * n == houses.length
 * 1 <= n <= 100
 * 1 <= houses[i] <= 10^4
 * 1 <= k <= n
 * Array houses contain unique integers.
 */
public class AllocateMailboxes {

    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        int[][] dist = new int[n][n];
        Arrays.sort(houses);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int mid = houses[(i + j) / 2];
                for (int h = i; h <= j; h++) dist[i][j] += Math.abs(mid - houses[h]);
            }
        }
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) dp[i][1] = dist[0][i]; // for 1 mailbox
        for (int kk = 2; kk <= k; kk++) { // for more than one mailbox
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    dp[i][kk] = Math.min(dp[i][kk], dp[j][kk - 1] + dist[j + 1][i]);
                }
            }
        }
        return dp[n - 1][k];
    }

}
