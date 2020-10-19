package leetcode.companies.amazon;

/**
 * 268. Missing Number
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 * Example 4:
 * <p>
 * Input: nums = [0]
 * Output: 1
 * Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 1 is the missing number in the range since it does not appear in nums.
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean zeroMissing = true;
        int expectedSum = (n * (n + 1)) / 2;
        int sum = 0;
        for (int num : nums) {
            if (num == 0) zeroMissing = false;
            else sum += num;
        }
        return zeroMissing ? 0 : expectedSum - sum;
    }

    public static void main(String[] args) {
        MissingNumber m = new MissingNumber();
        System.out.println(m.missingNumber(new int[]{3, 0, 1}));
        System.out.println(m.missingNumber(new int[]{0, 1}));
        System.out.println(m.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(m.missingNumber(new int[]{0}));
    }

}
