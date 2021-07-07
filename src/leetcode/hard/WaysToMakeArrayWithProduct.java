package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1735. Count Ways to Make Array With Product
 * You are given a 2D integer array, queries. For each queries[i], where queries[i] = [ni, ki], find the number of different ways you can place positive integers into an array of size ni such that the product of the integers is ki. As the number of ways may be too large, the answer to the ith query is the number of ways modulo 109 + 7.
 *
 * Return an integer array answer where answer.length == queries.length, and answer[i] is the answer to the ith query.
 *
 * Example 1:
 *
 * Input: queries = [[2,6],[5,1],[73,660]]
 * Output: [4,1,50734910]
 * Explanation: Each query is independent.
 * [2,6]: There are 4 ways to fill an array of size 2 that multiply to 6: [1,6], [2,3], [3,2], [6,1].
 * [5,1]: There is 1 way to fill an array of size 5 that multiply to 1: [1,1,1,1,1].
 * [73,660]: There are 1050734917 ways to fill an array of size 73 that multiply to 660. 1050734917 modulo 109 + 7 = 50734910.
 * Example 2:
 *
 * Input: queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: [1,2,3,10,5]
 *
 * Constraints:
 *
 * 1 <= queries.length <= 104
 * 1 <= ni, ki <= 104
 */
public class WaysToMakeArrayWithProduct {

    int MOD = 1_000_000_007;
    List<Integer> primes;
    long[][] combs;

    public int[] waysToFillArray(int[][] queries) {
        int len = queries.length;
        int[] res = new int[len];
        primes = getPrimes(100);
        combs = getCombs(10015, 15);
        for (int i = 0; i < len; i++) res[i] = calculate(queries[i][0], queries[i][1]);
        return res;
    }

    private List<Integer> getPrimes(int n) {
        boolean[] noPrime = new boolean[n + 1];
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!noPrime[i]) {
                res.add(i);
                for (int j = i * i; j <= n; j += i) {
                    noPrime[j] = true;
                }
            }
        }
        return res;
    }

    public long[][] getCombs(int n, int k) {
        long[][] res = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= Math.min(i, k); j++) res[i][j] = (res[i - 1][j - 1] + res[i - 1][j]) % MOD;
        }
        return res;
    }

    private int calculate(int n, int k) {
        long res = 1;
        for (int prime : primes) {
            if (prime > k) break;
            int count = 0;
            while (k % prime == 0) {
                count++;
                k /= prime;
            }
            res = (res * combs[count + n - 1][count]) % MOD;
        }
        return k > 1 ? (int) (res * n % MOD) : (int) res;
    }

}
