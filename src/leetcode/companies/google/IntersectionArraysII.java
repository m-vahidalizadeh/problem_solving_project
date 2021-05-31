package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect(nums2, nums1);
        Map<Integer, Integer> freq = intersect(getFreq(nums1), getFreq(nums2));
        int count = 0;
        for (int c : freq.values()) count += c;
        int[] res = new int[count];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            for (int j = 0; j < e.getValue(); j++) res[i++] = e.getKey();
        }
        return res;
    }

    private Map<Integer, Integer> getFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        return freq;
    }

    private Map<Integer, Integer> intersect(Map<Integer, Integer> nums1, Map<Integer, Integer> nums2) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (Map.Entry<Integer, Integer> e1 : nums1.entrySet()) {
            if (nums2.containsKey(e1.getKey())) freq.put(e1.getKey(), Math.min(e1.getValue(), nums2.get(e1.getKey())));
        }
        return freq;
    }

}
