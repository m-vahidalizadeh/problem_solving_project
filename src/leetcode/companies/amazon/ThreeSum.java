package leetcode.companies.amazon;

import java.util.*;

/**
 * 15. 3Sum
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (duplicates.add(nums[i])) {
                for (int j = i + 1; j < n; j++) {
                    int complement = -1 * (nums[i] + nums[j]);
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(result);
    }

}
