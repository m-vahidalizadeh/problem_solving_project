package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * Missing Ranges
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * <p>
 * Example:
 * <p>
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 */
public class FindMissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            if (lower == upper) {
                result.add(String.valueOf(lower));
            } else {
                result.add(lower + "->" + upper);
            }
            return result;
        }
        if (lower < nums[0]) {
            if (nums[0] - (double) lower > 1) {
                result.add(lower + "->" + (nums[0] - 1));
            } else {
                result.add(String.valueOf(lower));
            }
        }
        for (int i = 1; i < n; i++) {
            double diff = (double) nums[i] - (double) nums[i - 1];
            if (diff > 2) {
                result.add((nums[i - 1] + 1) + "->" + (nums[i] - 1));
            } else if (diff > 1) {
                result.add(String.valueOf(nums[i] - 1));
            }
        }
        if (nums[n - 1] < upper) {
            if ((double) upper - nums[n - 1] > 1) {
                result.add((nums[n - 1] + 1) + "->" + upper);
            } else {
                result.add(String.valueOf(upper));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindMissingRanges f = new FindMissingRanges();
        int[] nums = {-2147483648, 2147483647};
        f.findMissingRanges(nums, -2147483648, 2147483647);
    }

}
