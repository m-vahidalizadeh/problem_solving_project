package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. Subarrays with K Different Integers
 * Given an array nums of positive integers, call a (contiguous, not necessarily distinct) subarray of nums good if the number of different integers in that subarray is exactly k.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of nums.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * Example 2:
 *
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Note:
 *
 * 1 <= nums.length <= 20000
 * 1 <= nums[i] <= nums.length
 * 1 <= k <= nums.length
 */
public class GoodSubsInArray {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return rec(nums, k) - rec(nums, k - 1); // at most(k) - at most(k-1)=exact(k)
    }

    private int rec(int[] nums, int k) { // how many subs we have with at most k chars
        Map<Integer, Integer> window = new HashMap<>();
        int n = nums.length;
        int count = 0; // counts the number of valid subs
        for (int l = 0, r = 0; r < n; r++) { // l is always behind r because of size of window
            window.put(nums[r], window.getOrDefault(nums[r], 0) + 1);
            while (window.size() > k) { // shrink the window from left till you have a window with at most k
                int newFreq = window.get(nums[l]) - 1;
                window.put(nums[l], newFreq);
                if (newFreq == 0) window.remove(nums[l]);
                l++; // move the left pointer
            }
            count += r - l + 1; // We can have this many continuous subs
        }
        return count;
    }

}
