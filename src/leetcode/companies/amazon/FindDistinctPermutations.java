package leetcode.companies.amazon;

import java.util.*;

/**
 * 46. Permutations
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class FindDistinctPermutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currPermutation = new ArrayList<>();
        for (int num : nums) currPermutation.add(num);
        rec(currPermutation, result, nums.length, 0);
        return result;
    }

    private void rec(List<Integer> currPermutation, List<List<Integer>> result, int n, int first) {
        if (first == n) result.add(new ArrayList<>(currPermutation));
        else {
            for (int i = first; i < n; i++) {
                Collections.swap(currPermutation, first, i);
                rec(currPermutation, result, n, first + 1);
                Collections.swap(currPermutation, first, i);
            }
        }
    }

    public static void main(String[] args) {
        FindDistinctPermutations f = new FindDistinctPermutations();
        System.out.println(f.permute(new int[]{1, 2, 3}));
    }

}
