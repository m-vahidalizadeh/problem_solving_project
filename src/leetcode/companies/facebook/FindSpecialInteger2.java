package leetcode.companies.facebook;

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
public class FindSpecialInteger2 {

    public int findSpecialInteger(int[] arr) {
        if (arr.length == 1) return arr[0];
        for (int i = 0, n = arr.length, oneFourth = n / 4; i < n; ) {
            int curr = arr[i];
            if ((i + oneFourth < n) && arr[i + oneFourth] == curr) return curr;
            while (i < n && arr[i] == curr) i++;
        }
        return -1;
    }

}
