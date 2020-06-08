package leetcode.companies.amazon;

/**
 * Two Sum Less Than K
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation:
 * We can use 34 and 24 to sum 58 which is less than 60.
 * Example 2:
 * <p>
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation:
 * In this case it's not possible to get a pair sum less that 15.
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
public class TwoSumLessThanK {

    public int twoSumLessThanK(int[] A, int K) {
        int n = A.length;
        if (n == 0 || n == 1) return -1;
        if (n == 2) return A[0] + A[1];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] >= K) continue;
            for (int j = i + 1; j < n; j++) {
                if (A[j] >= K) continue;
                int tempSum = A[i] + A[j];
                if (tempSum > maxSum && tempSum < K) maxSum = tempSum;
            }
        }
        return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
    }

    public static void main(String[] args) {
        TwoSumLessThanK t = new TwoSumLessThanK();
        int[] A = {34, 23, 1, 24, 75, 33, 54, 8};
        int K = 60;
        System.out.println(t.twoSumLessThanK(A, K));
    }

}
