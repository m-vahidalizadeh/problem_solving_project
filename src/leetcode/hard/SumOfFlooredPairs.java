package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 1862. Sum of Floored Pairs
 * Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices 0 <= i, j < nums.length in the array. Since the answer may be too large, return it modulo 109 + 7.
 *
 * The floor() function returns the integer part of the division.
 *
 * Example 1:
 *
 * Input: nums = [2,5,9]
 * Output: 10
 * Explanation:
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * We calculate the floor of the division for every pair of indices in the array then sum them up.
 * Example 2:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 49
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class SumOfFlooredPairs {

    public int sumOfFlooredPairs(int[] nums) {
        int mod = 1_000_000_007;
        Map<Integer, Long> counter = new HashMap<>();
        int max = nums[0];
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0L) + 1);
            max = Math.max(max, num);
        }
        long[] quotients = new long[max + 1];
        for (Map.Entry<Integer, Long> item : counter.entrySet()) {
            int num = item.getKey();
            long count = item.getValue();
            for (int multiple = num; multiple <= max; multiple += num) {
                quotients[multiple] += count;
            }
        }
        for (int i = 1; i < quotients.length; i++) {
            quotients[i] = (quotients[i - 1] + quotients[i]) % mod;
        }
        long res = 0;
        for (int num : nums) {
            res = (res + quotients[num]) % mod;
        }
        return (int) (res) % mod;
    }

}
