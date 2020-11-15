package leetcode.companies.bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1200. Minimum Absolute Difference
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 *
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 *
 * a, b are from arr
 * a < b
 * b - a equals to the minimum absolute difference of any two elements in arr
 *
 * Example 1:
 *
 * Input: arr = [4,2,1,3]
 * Output: [[1,2],[2,3],[3,4]]
 * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
 * Example 2:
 *
 * Input: arr = [1,3,6,10,15]
 * Output: [[1,3]]
 * Example 3:
 *
 * Input: arr = [3,8,-10,23,19,-4,-14,27]
 * Output: [[-14,-10],[19,23],[23,27]]
 *
 * Constraints:
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinAbsDiff {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int n = arr.length;
        int absDiff = Integer.MAX_VALUE;
        int[] diffs = new int[n];
        for (int i = 1; i < n; i++) {
            diffs[i] = arr[i] - arr[i - 1];
            absDiff = Math.min(absDiff, diffs[i]);
        }
        for (int i = 1; i < n; i++) {
            if (diffs[i] == absDiff) result.add(List.of(arr[i - 1], arr[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        MinAbsDiff m = new MinAbsDiff();
        System.out.println(m.minimumAbsDifference(new int[]{4, 2, 1, 3}));
        System.out.println(m.minimumAbsDifference(new int[]{1, 3, 6, 10, 15}));
        System.out.println(m.minimumAbsDifference(new int[]{3, 8, -10, 23, 19, -4, -14, 27}));
    }

}
