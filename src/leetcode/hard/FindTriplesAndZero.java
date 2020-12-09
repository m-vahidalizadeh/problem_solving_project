package leetcode.hard;

/**
 * 982. Triples with Bitwise AND Equal To Zero
 * Given an array of integers A, find the number of triples of indices (i, j, k) such that:
 *
 * 0 <= i < A.length
 * 0 <= j < A.length
 * 0 <= k < A.length
 * A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.
 *
 * Example 1:
 *
 * Input: [2,1,3]
 * Output: 12
 * Explanation: We could choose the following i, j, k triples:
 * (i=0, j=0, k=1) : 2 & 2 & 1
 * (i=0, j=1, k=0) : 2 & 1 & 2
 * (i=0, j=1, k=1) : 2 & 1 & 1
 * (i=0, j=1, k=2) : 2 & 1 & 3
 * (i=0, j=2, k=1) : 2 & 3 & 1
 * (i=1, j=0, k=0) : 1 & 2 & 2
 * (i=1, j=0, k=1) : 1 & 2 & 1
 * (i=1, j=0, k=2) : 1 & 2 & 3
 * (i=1, j=1, k=0) : 1 & 1 & 2
 * (i=1, j=2, k=0) : 1 & 3 & 2
 * (i=2, j=0, k=1) : 3 & 2 & 1
 * (i=2, j=1, k=0) : 3 & 1 & 2
 *
 * Note:
 *
 * 1 <= A.length <= 1000
 * 0 <= A[i] < 2^16
 */
public class FindTriplesAndZero {

    Integer[][] cache;
    int n;
    int[] a;

    public int countTriplets(int[] A) {
        this.a = A;
        this.n = A.length;
        int max = 0;
        for (int num : A) max = Math.max(max, num);
        cache = new Integer[max + 1][3]; // a (term 1) & b (term 2) & c (term 3)
        int counter = 0;
        for (int num : A) {
            counter += dfs(num, 0);
        }
        return counter;
    }

    private int dfs(int curr, int term) {
        if (term == 2) return curr == 0 ? 1 : 0; // a&b&c==0-> return 1
        if (cache[curr][term] != null) return cache[curr][term]; // User cache
        int counter = 0;
        for (int num : a) counter += dfs(curr & num, term + 1);
        cache[curr][term] = counter;
        return counter;
    }

}
