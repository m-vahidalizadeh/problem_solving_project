package leetcode.companies.google;

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
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> resSet = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        Set<Integer> duplicates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (duplicates.add(nums[i])) {
                for (int j = i + 1; j < n; j++) {
                    int complement = -(nums[i] + nums[j]);
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(list);
                        resSet.add(list);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(resSet);
    }

}
