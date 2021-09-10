package leetcode.hard;

import java.util.*;

/**
 * 164. Maximum Gap
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
public class MaximumGap {

    public int maximumGap(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int n = nums.length;
        if (n < 2) return 0;
        int bucketSize = Math.max(1, (max - min) / (n - 1)); // Create n-1 buckets with size (max-min)/(n-1)
        Map<Integer, int[]> buckets = new HashMap<>(); // key:(min,max)
        for (int num : nums) {
            int key = (num - min) / bucketSize;
            if (buckets.containsKey(key)) {
                buckets.put(key, new int[]{Math.min(buckets.get(key)[0], num), Math.max(buckets.get(key)[1], num)});
            } else {
                buckets.put(key, new int[]{num, num});
            }
        }
        int res = 0;
        int preKey = -1;
        List<Integer> sortedKeys = new ArrayList<>(buckets.keySet());
        Collections.sort(sortedKeys);
        for (int key : sortedKeys) {
            if (preKey != -1) res = Math.max(res, buckets.get(key)[0] - buckets.get(preKey)[1]);
            preKey = key;
        }
        return res;
    }

}
