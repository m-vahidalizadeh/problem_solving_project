package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * Example 2:
 *
 * Input: arr = [1,1]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 105
 */
public class FindSpecialInteger {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = -1;
        int max = -1;
        for (int a : arr) {
            int newFreq = freqMap.getOrDefault(a, 0) + 1;
            freqMap.put(a, newFreq);
            if (newFreq > maxFreq) {
                maxFreq = newFreq;
                max = a;
            }
        }
        return max;
    }

}
