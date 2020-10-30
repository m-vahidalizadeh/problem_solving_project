package leetcode.companies.amazon;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Set<Integer>> tm = new TreeMap<>((a, b) -> b - a);
        Map<Integer, Integer> hm = new HashMap<>();
        for (int n : nums) {
            hm.computeIfAbsent(n, a -> 0);
            int freq = hm.get(n);
            int newFreq = freq + 1;
            tm.computeIfAbsent(freq, a -> new HashSet<>());
            tm.get(freq).remove(n);
            tm.computeIfAbsent(newFreq, a -> new HashSet<>());
            tm.get(newFreq).add(n);
            hm.put(n, newFreq);
        }
        int[] result = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Set<Integer>> e : tm.entrySet()) {
            for (int n : e.getValue()) {
                if (i == k) break;
                result[i++] = n;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements t = new TopKFrequentElements();
        System.out.println(Arrays.toString(t.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(t.topKFrequent(new int[]{1}, 1)));
        System.out.println(Arrays.toString(t.topKFrequent(new int[]{3, 0, 1, 0}, 1)));
    }

}
