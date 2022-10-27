package leetcode.hard;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 2448. Minimum Cost to Make Array Equal
 * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
 *
 * You can do the following operation any number of times:
 *
 * Increase or decrease any element of the array nums by 1.
 * The cost of doing one operation on the ith element is cost[i].
 *
 * Return the minimum total cost such that all the elements of the array nums become equal.
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,2], cost = [2,3,1,14]
 * Output: 8
 * Explanation: We can make all the elements equal to 2 in the following way:
 * - Increase the 0th element one time. The cost is 2.
 * - Decrease the 1st element one time. The cost is 3.
 * - Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
 * The total cost is 2 + 3 + 3 = 8.
 * It can be shown that we cannot make the array equal with a smaller cost.
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
 * Output: 0
 * Explanation: All the elements are already equal, so no operations are needed.
 *
 * Constraints:
 *
 * n == nums.length == cost.length
 * 1 <= n <= 105
 * 1 <= nums[i], cost[i] <= 106
 */
public class MinCostToMakeArrayEqual {

    public long minCost(int[] nums, int[] cost) {
        long left = Arrays.stream(nums).asLongStream().map(n -> Math.min(1L, n)).min().getAsLong(); // Find min
        long right = Arrays.stream(nums).asLongStream().map(n -> Math.max(n, 1_000_000L)).max().getAsLong(); // Fix max
        long ans = findCost(nums, cost, 1);
        while (left < right) {
            long mid = (left + right) / 2;
            long costMid = findCost(nums, cost, mid);
            long costMidPlusOne = findCost(nums, cost, mid + 1);
            ans = Math.min(costMid, costMidPlusOne);
            if (costMid < costMidPlusOne) right = mid; // Search left side
            else left = mid + 1; // Search right side
        }
        return ans; // Return the min cost
    }

    private long findCost(int[] nums, int[] cost, long x) { // Find cost to convert all elements to x
        return IntStream.range(0, nums.length).mapToLong(i -> Math.abs(nums[i] - x) * cost[i]).sum();
    }

}
