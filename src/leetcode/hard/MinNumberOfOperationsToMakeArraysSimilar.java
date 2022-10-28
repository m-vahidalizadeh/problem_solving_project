package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2449. Minimum Number of Operations to Make Arrays Similar
 * You are given two positive integer arrays nums and target, of the same length.
 *
 * In one operation, you can choose any two distinct indices i and j where 0 <= i, j < nums.length and:
 *
 * set nums[i] = nums[i] + 2 and
 * set nums[j] = nums[j] - 2.
 * Two arrays are considered to be similar if the frequency of each element is the same.
 *
 * Return the minimum number of operations required to make nums similar to target. The test cases are generated such that nums can always be similar to target.
 *
 * Example 1:
 *
 * Input: nums = [8,12,6], target = [2,14,10]
 * Output: 2
 * Explanation: It is possible to make nums similar to target in two operations:
 * - Choose i = 0 and j = 2, nums = [10,12,4].
 * - Choose i = 1 and j = 2, nums = [10,14,2].
 * It can be shown that 2 is the minimum number of operations needed.
 * Example 2:
 *
 * Input: nums = [1,2,5], target = [4,1,3]
 * Output: 1
 * Explanation: We can make nums similar to target in one operation:
 * - Choose i = 1 and j = 2, nums = [1,4,3].
 * Example 3:
 *
 * Input: nums = [1,1,1,1,1], target = [1,1,1,1,1]
 * Output: 0
 * Explanation: The array nums is already similiar to target.
 *
 * Constraints:
 *
 * n == nums.length == target.length
 * 1 <= n <= 105
 * 1 <= nums[i], target[i] <= 106
 * It is possible to make nums similar to target.
 */
public class MinNumberOfOperationsToMakeArraysSimilar {

    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> oNums = new ArrayList<>(), eNums = new ArrayList<>(), oTarget = new ArrayList<>(), eTarget = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) oNums.add(nums[i]);
            else eNums.add(nums[i]);
            if (target[i] % 2 == 1) oTarget.add(target[i]);
            else eTarget.add(target[i]);
        }
        long ans = 0;
        for (int i = 0; i < oNums.size(); i++)
            ans += Math.abs(oNums.get(i) - oTarget.get(i)); // If they are equal 0 operation otherwise the diff
        for (int i = 0; i < eNums.size(); i++)
            ans += Math.abs(eNums.get(i) - eTarget.get(i)); // // If they are equal 0 operation otherwise the diff
        return ans / 2 / 2; // First divide by two because our increments are 2, the second one is since a pair of +2 and -2 are being considered as 1 operation.
    }

}
