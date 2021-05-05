package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. Permutations
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currPermutation = new ArrayList<>();
        for (int num : nums) currPermutation.add(num);
        rec(currPermutation, 0, nums.length, res);
        return res;
    }

    private void rec(List<Integer> currPermutation, int first, int n, List<List<Integer>> res) {
        if (first == n) res.add(new ArrayList<>(currPermutation));
        else {
            for (int i = first; i < n; i++) {
                Collections.swap(currPermutation, first, i);
                rec(currPermutation, first + 1, n, res);
                Collections.swap(currPermutation, i, first);
            }
        }
    }

}
