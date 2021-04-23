package leetcode.hard;

import java.util.List;

/**
 * 632. Smallest Range Covering Elements from K Lists
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 *
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * Example 3:
 *
 * Input: nums = [[10,10],[11,11]]
 * Output: [10,11]
 * Example 4:
 *
 * Input: nums = [[10],[11]]
 * Output: [10,11]
 * Example 5:
 *
 * Input: nums = [[1],[2],[3],[4],[5],[6],[7]]
 * Output: [1,7]
 *
 * Constraints:
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] is sorted in non-decreasing order.
 */
public class SmallestRange {

    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int[] next = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean hasNext = true;
        while (hasNext) {
            int localMin = Integer.MAX_VALUE;
            int localMax = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                localMax = Math.max(localMax, nums.get(i).get(next[i]));
                localMin = Math.min(localMin, nums.get(i).get(next[i]));
            }
            if ((min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) || (localMax - localMin < max - min)) {
                min = localMin;
                max = localMax;
            }
            for (int i = 0; i < n; i++) {
                if (localMin == nums.get(i).get(next[i])) next[i]++;
                if (next[i] == nums.get(i).size()) hasNext = false;
            }
        }
        return new int[]{min, max};
    }

}
