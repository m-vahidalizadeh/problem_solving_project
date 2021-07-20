package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 548. Split Array with Equal Sum
 * Given an integer array nums of length n, return true if there is a triplet (i, j, k) which satisfies the following conditions:
 *
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * The sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) is equal.
 * A subarray (l, r) represents a slice of the original array starting from the element indexed l to the element indexed r.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,1,2,1]
 * Output: true
 * Explanation:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 * Example 2:
 *
 * Input: nums = [1,2,1,2,1,2,1,2]
 * Output: false
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 2000
 * -106 <= nums[i] <= 106
 */
public class SplitArr4EqualSums {

    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7) return false;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) sum[i] = sum[i - 1] + nums[i];
        for (int j = 3; j < n - 3; j++) {
            Set<Integer> leftEqualSums = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i]) leftEqualSums.add(sum[i - 1]);
            }
            for (int k = j + 2; k < n - 1; k++) {
                int rightMostDiff = sum[n - 1] - sum[k];
                if (rightMostDiff == sum[k - 1] - sum[j] && leftEqualSums.contains(rightMostDiff)) return true;
            }
        }
        return false;
    }

}
