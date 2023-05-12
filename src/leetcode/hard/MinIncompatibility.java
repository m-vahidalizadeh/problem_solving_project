package leetcode.hard;

import java.util.*;

/**
 * 1681. Minimum Incompatibility
 * You are given an integer array nums and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.
 *
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 *
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.
 *
 * A subset is a group integers that appear in the array with no particular order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,4], k = 2
 * Output: 4
 * Explanation: The optimal distribution of subsets is [1,2] and [1,4].
 * The incompatibility is (2-1) + (4-1) = 4.
 * Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
 * Example 2:
 *
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4
 * Output: 6
 * Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
 * The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 * Example 3:
 *
 * Input: nums = [5,3,3,6,3,3], k = 3
 * Output: -1
 * Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 16
 * nums.length is divisible by k
 * 1 <= nums[i] <= nums.length
 */
public class MinIncompatibility {

    private int N, k;
    private Map<String, Integer> memo;

    public int minimumIncompatibility(int[] nums, int k) {
        Arrays.sort(nums);
        memo = new HashMap<>();
        this.k = k;
        int[] count = new int[17];
        for (int num : nums) {
            if (++count[num] > k) return -1;
        }
        N = nums.length / k;
        return dfs(new HashSet<>(), count);
    }

    private int dfs(Set<Integer> level, int[] count) {
        if (level.size() == N) return end(count) ? findDiff(level) : findDiff(level) + dfs(new HashSet<>(), count);
        String key = Arrays.hashCode(count) + " " + level;
        if (memo.containsKey(key)) return memo.get(key);
        int res = 1000;
        for (int i = 1; i <= 16; i++) {
            if (count[i] <= 0) continue;
            if (!level.add(i)) continue;
            count[i]--;
            res = Math.min(res, dfs(level, count));
            count[i]++;
            level.remove(i);
            if (level.size() == 0) break;
        }
        memo.put(key, res);
        return res;
    }

    private int findDiff(Set<Integer> level) {
        return Collections.max(level) - Collections.min(level);
    }

    private boolean end(int[] count) {
        for (int c : count) if (c != 0) return false;
        return true;
    }

}
