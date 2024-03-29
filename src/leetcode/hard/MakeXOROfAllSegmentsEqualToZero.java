package leetcode.hard;

import java.util.Arrays;

/**
 * 1787. Make the XOR of All Segments Equal to Zero
 * You are given an array nums and an integer k. The XOR of a segment [left, right] where left <= right is the XOR of all the elements with indices between left and right, inclusive: nums[left] XOR nums[left+1] XOR ... XOR nums[right].
 *
 * Return the minimum number of elements to change in the array such that the XOR of all segments of size k is equal to zero.
 *
 * Example 1:
 *
 * Input: nums = [1,2,0,3,0], k = 1
 * Output: 3
 * Explanation: Modify the array from [1,2,0,3,0] to from [0,0,0,0,0].
 * Example 2:
 *
 * Input: nums = [3,4,5,2,1,7,3,4,7], k = 3
 * Output: 3
 * Explanation: Modify the array from [3,4,5,2,1,7,3,4,7] to [3,4,7,3,4,7,3,4,7].
 * Example 3:
 *
 * Input: nums = [1,2,4,1,2,5,1,2,6], k = 3
 * Output: 3
 * Explanation: Modify the array from [1,2,4,1,2,5,1,2,6] to [1,2,3,1,2,3,1,2,3].
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 2000
 * 0 <= nums[i] < 210
 */
public class MakeXOROfAllSegmentsEqualToZero {

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[][] freq = new int[k][1024];
        for (int i = 0; i < n; i++) {
            freq[i % k][nums[i]]++;
        }
        int[][] dp = new int[k + 1][1024];
        for (int[] row : dp) {
            Arrays.fill(row, n);
        }
        dp[0][0] = 0;
        int bestUpToLast = 0;
        for (int i = 0; i < k; i++) {
            int bestToI = Integer.MAX_VALUE;
            int countOfPos = n / k + (n % k > i ? 1 : 0);
            for (int xorResult = 0; xorResult < 1024; xorResult++) {
                for (int z = i; z < n; z += k) {
                    int target = nums[z];
                    dp[i + 1][xorResult] = Math.min(dp[i + 1][xorResult], dp[i][xorResult ^ target] + countOfPos - freq[i][target]);
                }
                dp[i + 1][xorResult] = Math.min(dp[i + 1][xorResult], bestUpToLast + countOfPos);
                bestToI = Math.min(bestToI, dp[i + 1][xorResult]);
            }
            bestUpToLast = bestToI;
        }
        return dp[k][0];
    }

}
