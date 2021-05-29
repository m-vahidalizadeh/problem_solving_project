package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1569. Number of Ways to Reorder Array to Get Same BST
 * Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.
 *
 * For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
 *
 * Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
 *
 * Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: nums = [2,1,3]
 * Output: 1
 * Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
 * Example 2:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 5
 * Explanation: The following 5 arrays will yield the same BST:
 * [3,1,2,4,5]
 * [3,1,4,2,5]
 * [3,1,4,5,2]
 * [3,4,1,2,5]
 * [3,4,1,5,2]
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: There are no other orderings of nums that will yield the same BST.
 * Example 4:
 *
 * Input: nums = [3,1,2,5,4,6]
 * Output: 19
 * Example 5:
 *
 * Input: nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
 * Output: 216212978
 * Explanation: The number of ways to reorder nums to get the same BST is 3216212999. Taking this number modulo 10^9 + 7 gives 216212978.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= nums.length
 * All integers in nums are distinct.
 */
public class NumOfWaysReorderBST {

    int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) numList.add(num);
        return (int) ways(numList, getCombs(n + 1)) - 1;
    }

    private long ways(List<Integer> nums, long[][] combs) {
        if (nums.size() <= 2) return 1;
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : nums) {
            if (num < root) left.add(num);
            else if (num > root) right.add(num);
        }
        return (
                (combs[left.size() + right.size()][right.size()] * (ways(left, combs) % MOD)) % MOD * ways(right, combs)) % MOD;
    }

    private long[][] getCombs(int n) {
        long[][] combs = new long[n][n];
        for (int i = 0; i < n; i++) combs[i][0] = combs[i][i] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                combs[i][j] = (combs[i - 1][j] + combs[i - 1][j - 1]) % MOD;
            }
        }
        return combs;
    }

}
