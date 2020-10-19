package leetcode.companies.amazon;

/**
 * 1099. Two Sum Less Than K
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
public class SumSLessThanK {

    public int twoSumLessThanK(int[] A, int K) {
        int s = -1;
        int[] counts = new int[1001];
        for (int a : A) counts[a]++;
        int l = 1;
        int r = 1000;
        while (l < r) {
            int sum = l + r;
            if (counts[r] == 0 || sum >= K) {
                r--;
            } else {
                if (counts[l] != 0) s = Math.max(s, sum);
                l++;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        SumSLessThanK s = new SumSLessThanK();
        System.out.println(s.twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60));
        System.out.println(s.twoSumLessThanK(new int[]{10, 20, 30}, 15));
    }

}
