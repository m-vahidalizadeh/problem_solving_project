package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1819. Number of Different Subsequences GCDs
 * You are given an array nums that consists of positive integers.
 *
 * The GCD of a sequence of numbers is defined as the greatest integer that divides all the numbers in the sequence evenly.
 *
 * For example, the GCD of the sequence [4,6,16] is 2.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * Return the number of different GCDs among all non-empty subsequences of nums.
 *
 * Example 1:
 *
 * Input: nums = [6,10,3]
 * Output: 5
 * Explanation: The figure shows all the non-empty subsequences and their GCDs.
 * The different GCDs are 6, 10, 3, 2, and 1.
 * Example 2:
 *
 * Input: nums = [5,15,40,5,6]
 * Output: 7
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 2 * 105
 */
public class NumOfDifferentSubsGCDs {

    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = nums[0];
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            max = Math.max(max, num);
            numSet.add(num);
        }
        int ans = 0;
        for (int x = 1; x <= max; x++) { // Find if there is a gcd x for non-empty sub
            int currGCD = 0; // gcd of 0 and x is x. This is zero for the first round
            for (int num = x; num <= max; num += x) {
                if (numSet.contains(num)) {
                    currGCD = gcd(currGCD, num);
                    if (currGCD == x) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
