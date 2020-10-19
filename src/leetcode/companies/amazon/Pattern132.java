package leetcode.companies.amazon;

import java.util.*;

/**
 * 456. 132 Pattern
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
 * such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * <p>
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * <p>
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * <p>
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Pattern132 {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        Deque<Integer> memStack = new ArrayDeque<>();
        int[] minArr = new int[n];
        minArr[0] = nums[0];
        for (int i = 1; i < n; i++) minArr[i] = Math.min(minArr[i - 1], nums[i]);
        for (int j = n - 1; j >= 0; j--) { // num[j]
            if (minArr[j] < nums[j]) { // minArr = num[i]
                while (!memStack.isEmpty() && memStack.peek() <= minArr[j]) memStack.poll();
                if (!memStack.isEmpty() && memStack.peek() < nums[j]) return true; // num[k]
                memStack.push(nums[j]); // put in the mem for the next j.
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Pattern132 p = new Pattern132();
        System.out.println(p.find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(p.find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(p.find132pattern(new int[]{-1, 3, 2, 0}));
        System.out.println(p.find132pattern(new int[]{1, 0, 1, -4, -3}));
        System.out.println(p.find132pattern(new int[]{-2, 1, 2, -2, 1, 2}));
    }

}
