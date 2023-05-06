package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 220. Contains Duplicate III
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 *
 * Find a pair of indices (i, j) such that:
 *
 * i != j,
 * abs(i - j) <= indexDiff.
 * abs(nums[i] - nums[j]) <= valueDiff, and
 * Return true if such pair exists or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 * Output: true
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions:
 * i != j --> 0 != 3
 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 * Example 2:
 *
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 * Output: false
 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 1 <= indexDiff <= nums.length
 * 0 <= valueDiff <= 109
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int min = Arrays.stream(nums).min().getAsInt(); // Find min
        long addition = min < 0 ? -min : 0; // What is the addition to make sure numbers start from zero
        Map<Long, Long> windowMap = new HashMap<>(); // A map that keeps track of elements in our moving window
        for (int i = 0; i < nums.length; i++) {
            long nonNegativeNum = nums[i] + addition;
            long bucket = nonNegativeNum / (valueDiff + 1);
            if (windowMap.containsKey(bucket) || (windowMap.containsKey(bucket - 1) && nonNegativeNum - windowMap.get(bucket - 1) <= valueDiff) || (windowMap.containsKey(bucket + 1) && windowMap.get(bucket + 1) - nonNegativeNum <= valueDiff))
                return true; // If you see a pair (i,j) that satisfies the criteria, return true
            if (windowMap.size() == indexDiff)
                windowMap.remove((nums[i - indexDiff] + addition) / (valueDiff + 1)); // Remove the last bucket if the last index is out of the window
            windowMap.put(bucket, nonNegativeNum);
        }
        return false;
    }

}
