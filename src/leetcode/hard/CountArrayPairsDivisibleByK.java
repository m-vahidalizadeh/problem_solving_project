package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 2183. Count Array Pairs Divisible by K
 * Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums[i] * nums[j] is divisible by k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 7
 * Explanation:
 * The 7 pairs of indices whose corresponding products are divisible by 2 are
 * (0, 1), (0, 3), (1, 2), (1, 3), (1, 4), (2, 3), and (3, 4).
 * Their products are 2, 4, 6, 8, 10, 12, and 20 respectively.
 * Other pairs such as (0, 2) and (2, 4) have products 3 and 15 respectively, which are not divisible by 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 0
 * Explanation: There does not exist any pair of indices whose corresponding product is divisible by 5.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 */
public class CountArrayPairsDivisibleByK {

    public long countPairs(int[] nums, int k) {
        long result = 0;
        Map<Integer, Integer> gcdMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int gcd = getGCD(nums[i], k);
            for (int num : gcdMap.keySet()) {
                if ((long) gcd * num % k == 0) result += gcdMap.get(num);
            }
            gcdMap.put(gcd, gcdMap.getOrDefault(gcd, 0) + 1);
        }
        return result;
    }

    private int getGCD(int x, int y) {
        if (x < y) return getGCD(y, x);
        return y == 0 ? x : getGCD(y, x % y);
    }

}
