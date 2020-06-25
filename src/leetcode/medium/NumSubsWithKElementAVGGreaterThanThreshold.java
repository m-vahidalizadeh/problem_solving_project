package leetcode.medium;

/**
 * Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 * Given an array of integers arr and two integers k and threshold.
 * <p>
 * Return the number of sub-arrays of size k and average greater than or equal to threshold.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
 * Example 2:
 * <p>
 * Input: arr = [1,1,1,1,1], k = 1, threshold = 0
 * Output: 5
 * Example 3:
 * <p>
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 * Example 4:
 * <p>
 * Input: arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * Output: 1
 * Example 5:
 * <p>
 * Input: arr = [4,4,4,4], k = 4, threshold = 1
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 */
public class NumSubsWithKElementAVGGreaterThanThreshold {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int counter = 0;
        int n = arr.length;
        if (k > n) return 0;
        int totalThreshold = k * threshold;
        int sum = 0;
        for (int i = 0; i < k; i++) sum += arr[i];
        if (sum >= totalThreshold) counter++;
        int next = k;
        int prevStart = 0;
        while (next < n) {
            sum -= arr[prevStart++];
            sum += arr[next++];
            if (sum >= totalThreshold) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        NumSubsWithKElementAVGGreaterThanThreshold n = new NumSubsWithKElementAVGGreaterThanThreshold();
        int[] arr1 = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(n.numOfSubarrays(arr1, 3, 4));
    }

}
