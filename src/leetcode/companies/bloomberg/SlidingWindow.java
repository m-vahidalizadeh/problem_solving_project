package leetcode.companies.bloomberg;

/**
 * Sliding Window Maximum
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int max = nums[0];
        for (int i = 0; i < k; i++) max = Math.max(max, nums[i]);
        int[] maxElements = new int[n - k + 1];
        int maxIndex = 0;
        maxElements[maxIndex++] = max;
        for (int i = k; i < n; i++) {
            int toBeRemoved = nums[i - k];
            if (toBeRemoved == max) {
                max = nums[i - k + 1];
                for (int j = i - k + 1; j <= i; j++) max = Math.max(max, nums[j]);
            } else {
                max = Math.max(max, nums[i]);
            }
            maxElements[maxIndex++] = max;
        }
        return maxElements;
    }

    public static void main(String[] args) {
        SlidingWindow s = new SlidingWindow();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(s.maxSlidingWindow(nums, 3));
    }

}
