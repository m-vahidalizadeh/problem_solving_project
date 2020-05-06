package leetcode.medium;

/**
 * XOR Queries of a Subarray
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * Example 2:
 * <p>
 * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * Output: [8,0,4,4]
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XORQueriesOfSubarray {

    public static void main(String[] args) {
        XORQueriesOfSubarray x = new XORQueriesOfSubarray();
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        leetcode.base.Utils.printArray(x.xorQueries(arr, queries));
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int m = queries.length;
        int[][] preProcess = getPreProcessMatrix(arr, n);
        int[] resultOfQueries = new int[m];
        for (int i = 0; i < m; i++) {
            resultOfQueries[i] = getXOR(preProcess, queries, i);
        }
        return resultOfQueries;
    }

    private int[][] getPreProcessMatrix(int[] arr, int n) {
        int[][] preProcess = new int[32][n];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    preProcess[i][j] += preProcess[i][j - 1];
                }
                preProcess[i][j] += (arr[j] & (1 << i)) >= 1 ? 1 : 0;
            }
        }
        return preProcess;
    }

    private int getXOR(int[][] preProcess, int[][] queries, int queryNumber) {
        int result = 0;
        int l = queries[queryNumber][0];
        int r = queries[queryNumber][1];
        for (int i = 0; i < 32; i++) {
            if ((preProcess[i][r] - (l > 0 ? preProcess[i][l - 1] : 0)) % 2 != 0) result += 1 << i;
        }
        return result;
    }

}
