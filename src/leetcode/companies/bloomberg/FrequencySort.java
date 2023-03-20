package leetcode.companies.bloomberg;

import java.util.*;

/**
 * 1636. Sort Array by Increasing Frequency
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,2,2,3]
 * Output: [3,1,1,2,2,2]
 * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 * Example 2:
 *
 * Input: nums = [2,3,1,3,2]
 * Output: [1,3,3,2,2]
 * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
 * Example 3:
 *
 * Input: nums = [-1,1,-6,4,5,-6,1,4,1]
 * Output: [5,-1,4,4,-6,-6,1,1,1]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class FrequencySort {

    public int[] frequencySort(int[] nums) {
        int[] newNums = new int[nums.length];
        Map<Integer, Integer> numsFreqMap = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> freqToNumsMap = new TreeMap<>();
        for (int num : nums) {
            int prevFreq = numsFreqMap.getOrDefault(num, 0);
            int newFreq = prevFreq + 1;
            freqToNumsMap.computeIfAbsent(prevFreq, x -> new PriorityQueue<>((Collections.reverseOrder()))).remove(num);
            freqToNumsMap.computeIfAbsent(newFreq, x -> new PriorityQueue<>((Collections.reverseOrder()))).add(num);
            numsFreqMap.put(num, newFreq);
        }
        int i = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : freqToNumsMap.entrySet()) {
            int freq = entry.getKey();
            PriorityQueue<Integer> pq = entry.getValue();
            while (!pq.isEmpty()) {
                int num = pq.poll();
                for (int j = 0; j < freq; j++) newNums[i++] = num;
            }
        }
        return newNums;
    }

}
