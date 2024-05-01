package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 *
 * Hint
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
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class FindSpecialInt {

    public int findSpecialInteger(int[] arr) {
        int tf = arr.length / 4;
        Map<Integer, Integer> occMap = new HashMap<>();
        for (int a : arr) {
            int newOcc = occMap.getOrDefault(a, 0) + 1;
            if (newOcc > tf) return a;
            occMap.put(a, newOcc);
        }
        return -1;
    }

}
