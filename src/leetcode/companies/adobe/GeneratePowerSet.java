package leetcode.companies.adobe;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class GeneratePowerSet {

    public class Result {
        List<List<Integer>> list;

        public Result() {
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        Result result = new Result();
        findSubsets(nums, 0, nums.length, new ArrayList<>(), result);
        return result.list;
    }

    private void findSubsets(int[] nums, int index, int n, List<Integer> currList, Result result) {
        if (index == n) result.list.add(currList);
        else {
            List<Integer> currListWithoutI = new ArrayList<>(currList);
            List<Integer> currListWithI = new ArrayList<>(currList);
            currListWithI.add(nums[index]);
            findSubsets(nums, index + 1, n, currListWithI, result);
            findSubsets(nums, index + 1, n, currListWithoutI, result);
        }
    }

}
