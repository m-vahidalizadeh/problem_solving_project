package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1703. Minimum Adjacent Swaps for K Consecutive Ones
 * You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.
 *
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 *
 * Example 1:
 *
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: 1
 * Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
 * Example 2:
 *
 * Input: nums = [1,0,0,0,0,0,1,1], k = 3
 * Output: 5
 * Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,0,1], k = 2
 * Output: 0
 * Explanation: nums already has 2 consecutive 1's.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is 0 or 1.
 * 1 <= k <= sum(nums)
 */
public class MinAdjacentSwapsForKConsecutiveOnes {

    public int minMoves(int[] nums, int k) {
        List<Long> A = new ArrayList<>();
        List<Long> B = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) A.add((long) i);
        }
        B.add(0L);
        for (int i = 0; i < A.size(); i++) {
            B.add(B.get(i) + A.get(i));
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < A.size() - k + 1; i++) {
            res = Math.min(res, B.get(i + k) + B.get(i) - B.get(i + k / 2) - B.get(i + (k + 1) / 2));
        }
        res -= (k / 2) * ((k + 1) / 2);
        return (int) res;
    }

}
