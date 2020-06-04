package leetcode.medium;

/**
 * Partition Array for Maximum Sum
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * <p>
 * Return the largest sum of the given array after partitioning.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class PartitionArrayForMaximumSum {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = A[0];
        int max = A[0];
        for (int i = 1; i < K; i++) {
            max = Math.max(max, A[i]);
            dp[i] = max * (i + 1);
        }
        for (int i = K; i < n; i++) {
            int subMax = A[i];
            for (int subSize = 1; subSize <= K; subSize++) {
                subMax = Math.max(subMax, A[i - subSize + 1]);
                dp[i] = Math.max(dp[i], dp[i - subSize] + subMax * subSize);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        PartitionArrayForMaximumSum p = new PartitionArrayForMaximumSum();
        int[] A = {1, 15, 7, 9, 2, 5, 10};
        System.out.println(p.maxSumAfterPartitioning(A, 3));
        int[] A2 = {1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3};
        System.out.println(p.maxSumAfterPartitioning(A2, 4));
    }
}
