package leetcode.companies.adobe;

/**
 * Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i][j] <= 10^5
 */
public class ReachTheLastIndex {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int lastIndex = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i + nums[i] >= lastIndex) lastIndex = i;
        }
        return lastIndex == 0;
    }

    public static void main(String[] args) {
        ReachTheLastIndex r = new ReachTheLastIndex();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(r.canJump(nums1));
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(r.canJump(nums2));
    }

}
