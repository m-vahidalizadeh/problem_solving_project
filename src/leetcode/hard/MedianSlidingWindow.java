package leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 480. Sliding Window Median
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 */
public class MedianSlidingWindow {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        boolean flag = (k % 2 == 0);
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < k - 1; j++) list.add(nums[j]);
        Collections.sort(list);
        for (int i = k - 1, p = 0; i < len; i++, p++) {
            int expectedIndex = Collections.binarySearch(list, nums[i]); // if not found -(expectedIndex+1)
            if (expectedIndex > -1) list.add(expectedIndex + 1, nums[i]);
            else list.add(Math.abs(expectedIndex + 1), nums[i]);
            if (flag) res[i - k + 1] = list.get((k / 2) - 1) / 2.0 + list.get(k / 2) / 2.0;
            else res[i - k + 1] = list.get(k / 2);
            int index = Collections.binarySearch(list, nums[p]);
            list.remove(index);
        }
        return res;
    }

}
