package leetcode.companies.random;

import java.util.HashMap;
import java.util.Map;

/**
 * Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class FindIntersection {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> n1Freq = getFreqMap(nums1);
        Map<Integer, Integer> n2Freq = getFreqMap(nums2);
        int n1 = n1Freq.size();
        int n2 = n2Freq.size();
        Map<Integer, Integer> resultFreq = new HashMap<>();
        int resultSize = 0;
        if (n1 < n2) {
            for (Map.Entry<Integer, Integer> e : n1Freq.entrySet()) {
                int num = e.getKey();
                int freq = e.getValue();
                int joinFreq = Math.min(freq, n2Freq.getOrDefault(num, 0));
                if (joinFreq > 0) {
                    resultFreq.put(num, joinFreq);
                    resultSize += joinFreq;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> e : n2Freq.entrySet()) {
                int num = e.getKey();
                int freq = e.getValue();
                int joinFreq = Math.min(freq, n1Freq.getOrDefault(num, 0));
                if (joinFreq > 0) {
                    resultFreq.put(num, joinFreq);
                    resultSize += joinFreq;
                }
            }
        }
        int[] result = new int[resultSize];
        int index = 0;
        for (Map.Entry<Integer, Integer> e : resultFreq.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) result[index++] = e.getKey();
        }
        return result;
    }

    private Map<Integer, Integer> getFreqMap(int[] input) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : input) freq.put(n, freq.getOrDefault(n, 0) + 1);
        return freq;
    }

    public static void main(String[] args) {
        FindIntersection f = new FindIntersection();
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
//        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {9, 4, 9, 8, 4};
        f.intersect(nums1, nums2);

    }

}
