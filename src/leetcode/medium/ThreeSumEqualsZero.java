package leetcode.medium;

import java.util.*;

/**
 * 15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 * Constraints:
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSumEqualsZero {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resSet = new HashSet<>();
        Set<Integer> firstSet = new HashSet<>();
        Map<Integer, Integer> secondToRoundMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (firstSet.add(nums[i])) {
                int first = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    int third = nums[j];
                    int second = -(first + third);
                    if (secondToRoundMap.containsKey(second) && secondToRoundMap.get(second) == i) { // If you see it in this round
                        List<Integer> list = Arrays.asList(first, second, third);
                        list.sort((a, b) -> a - b);
                        resSet.add(list);
                    }
                    secondToRoundMap.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(resSet);
    }

}
