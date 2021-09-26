package leetcode.medium;

import java.util.ArrayList;
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
public class PermutationsOfIntArray {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        startList.add(nums[0]);
        res.add(startList);
        for (int i = 1; i < nums.length; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                List<Integer> perm = res.remove(j);
                for (int k = 0; k <= perm.size(); k++) {
                    List<Integer> newPerm = new ArrayList<>();
                    newPerm.addAll(perm.subList(0, k));
                    newPerm.add(nums[i]);
                    newPerm.addAll(perm.subList(k, perm.size()));
                    res.add(newPerm);
                }
            }
        }
        return res;
    }

}
