package leetcode.medium;

/**
 * Check If All 1's Are at Least Length K Places Away
 * Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,0,0,1,0,0,1], k = 2
 * Output: true
 * Explanation: Each of the 1s are at least 2 places away from each other.
 * Example 2:
 * <p>
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: false
 * Explanation: The second 1 and third 1 are only one apart from each other.
 * Example 3:
 * <p>
 * Input: nums = [1,1,1,1,1], k = 0
 * Output: true
 * Example 4:
 * <p>
 * Input: nums = [0,1,0,1], k = 1
 * Output: true
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] is 0 or 1
 */
public class OnesAtLeastKCellsAway {

    public static void main(String[] args) {
        OnesAtLeastKCellsAway o = new OnesAtLeastKCellsAway();
        int[] input = {1, 0, 0, 0, 1, 0, 0, 1};
        System.out.println(o.kLengthApart(input, 2));
        int[] input2 = {1, 0, 0, 1, 0, 1};
        System.out.println(o.kLengthApart(input2, 2));
        int[] input4 = {0, 1, 0, 1};
        System.out.println(o.kLengthApart(input4, 1));
    }

    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0)
            return true;
        int prevOneIndex = -1;
        int currentIndex = 0;
        int n = nums.length;
        while (currentIndex < n && nums[currentIndex] == 0) {
            currentIndex++;
        }
        if (currentIndex == n)
            return true;
        prevOneIndex = currentIndex;
        currentIndex++;
        while (currentIndex < n) {
            if (nums[currentIndex] == 1) {
                // One
                if (currentIndex - prevOneIndex > k) {
                    prevOneIndex = currentIndex;
                    currentIndex++;
                } else {
                    return false;
                }
            } else {
                // Zero
                currentIndex++;
            }
        }
        return true;
    }

}
