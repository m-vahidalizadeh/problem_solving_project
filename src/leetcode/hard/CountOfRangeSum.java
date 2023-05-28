package leetcode.hard;

/**
 * 327. Count of Range Sum
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 *
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 * Example 1:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 * Example 2:
 *
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * -105 <= lower <= upper <= 105
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class CountOfRangeSum {

    private int lower;
    private int upper;
    private int count = 0;
    private long[] prefixSum;

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        this.lower = lower;
        this.upper = upper;
        this.prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + nums[i];
        mergeSort(0, n);
        return count;
    }

    private void mergeSort(int low, int high) {
        if (low >= high) return;
        int mid = low + (high - low) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        int i = mid + 1, j = mid + 1;
        for (int k = low; k <= mid; k++) {
            while (i <= high && prefixSum[i] - prefixSum[k] < lower) i++;
            while (j <= high && prefixSum[j] - prefixSum[k] <= upper) j++;
            count += j - i;
        }
        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {
        long[] helper = new long[high - low + 1];
        for (int i = low; i <= high; i++) helper[i - low] = prefixSum[i];
        int i = low, j = mid + 1, idx = low;
        while (i <= mid && j <= high) {
            if (helper[i - low] < helper[j - low]) prefixSum[idx++] = helper[i++ - low];
            else prefixSum[idx++] = helper[j++ - low];
        }
        while (i <= mid) prefixSum[idx++] = helper[i++ - low];
    }

}
