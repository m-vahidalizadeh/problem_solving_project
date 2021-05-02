package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubEqualsK {

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cache = new HashMap<>();
        var count = 0;
        var sum = 0;
        for (var i = 0; i < n; i++) {
            sum += nums[i];
            if (sum == k) count++;
            if (cache.containsKey(sum - k)) count += cache.get(sum - k);
            cache.put(sum, cache.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
