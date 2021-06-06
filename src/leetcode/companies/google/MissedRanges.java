package leetcode.companies.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 163. Missing Ranges
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 * Example 1:
 *
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * Example 2:
 *
 * Input: nums = [], lower = 1, upper = 1
 * Output: ["1"]
 * Explanation: The only missing range is [1,1], which becomes "1".
 * Example 3:
 *
 * Input: nums = [], lower = -3, upper = -1
 * Output: ["-3->-1"]
 * Explanation: The only missing range is [-3,-1], which becomes "-3->-1".
 * Example 4:
 *
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 * Example 5:
 *
 * Input: nums = [-1], lower = -2, upper = -1
 * Output: ["-2"]
 *
 * Constraints:
 *
 * -109 <= lower <= upper <= 109
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class MissedRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null) return Collections.emptyList();
        int n = nums.length;
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add(getRes(lower - 1, upper + 1));
            return res;
        }
        if (lower < nums[0]) res.add(getRes(lower - 1, nums[0]));
        for (int i = 0; i < n - 1; i++) {
            int l = nums[i];
            int r = nums[i + 1];
            if (r == l + 1) continue;
            res.add(getRes(l, r));
        }
        if (nums[n - 1] < upper) res.add(getRes(nums[n - 1], upper + 1));
        return res;
    }

    private String getRes(int l, int r) {
        if (r - l == 2) return (l + 1) + "";
        else return (l + 1) + "->" + (r - 1);
    }

}
