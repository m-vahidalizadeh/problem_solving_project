package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Number of Valid Subarrays
 * Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:
 * <p>
 * The leftmost element of the subarray is not larger than other elements in the subarray.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,4,2,5,3]
 * Output: 11
 * Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
 * Example 2:
 * <p>
 * Input: [3,2,1]
 * Output: 3
 * Explanation: The 3 valid subarrays are: [3],[2],[1].
 * Example 3:
 * <p>
 * Input: [2,2,2]
 * Output: 6
 * Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 100000
 */
public class NumberOfValidSubs {

    public int validSubarrays(int[] nums) {
        int n = nums.length;
        int count = 0;
        if (n <= 1) return count;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);
        count++;
        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            while (!stack.isEmpty() && curr < stack.peek()) stack.pop();
            stack.push(curr);
            count += stack.size();
        }
        return count;
    }

}
