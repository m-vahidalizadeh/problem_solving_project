package leetcode.hard;

/**
 * 1224. Maximum Equal Frequency
 * Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
 *
 * If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).
 *
 * Example 1:
 *
 * Input: nums = [2,2,1,1,5,3,3,5]
 * Output: 7
 * Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4] = 5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
 * Example 2:
 *
 * Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * Output: 13
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MaximumEqualFrequency {

    public int maxEqualFreq(int[] nums) {
        int length = nums.length;
        int[] freq = new int[100_001]; // How many of specific num. ex. there are 2 number 6
        int[] freqOfFreq = new int[100_001]; // How many nums with this frequency. ex. there are 2 nums with freq 3
        int max = 0;
        for (int i = 1; i <= length; i++) {
            int elementIMinusOne = nums[i - 1]; // Consider the i-1 since i is from 1 to length, so element is from 0 to len-1
            freqOfFreq[freq[elementIMinusOne]]--;
            int newFreq = ++freq[elementIMinusOne];
            freqOfFreq[newFreq]++;
            if (freqOfFreq[newFreq] * newFreq == i && i < length) max = i + 1; // We will add one more num
            int diff = i - freqOfFreq[newFreq] * newFreq;
            if ((diff == newFreq + 1 || diff == 1) && freqOfFreq[diff] == 1) max = i;
        }
        return max;
    }

}
