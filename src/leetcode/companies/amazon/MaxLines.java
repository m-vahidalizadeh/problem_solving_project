package leetcode.companies.amazon;

/**
 * 1035. Uncrossed Lines
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 *
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * Example 1:
 *
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
 * Example 2:
 *
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 *
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 */
public class MaxLines {

    public int maxUncrossedLines(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int l = j - 1 < 0 ? 0 : dp[i][j - 1];
                int lt = i - 1 < 0 || j - 1 < 0 ? 0 : dp[i - 1][j - 1];
                int t = i - 1 < 0 ? 0 : dp[i - 1][j];
                if (A[i] == B[j]) dp[i][j] = Math.max(dp[i][j], lt + 1);
                else dp[i][j] = Math.max(dp[i][j], Math.max(lt, Math.max(l, t)));
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        MaxLines m = new MaxLines();
        System.out.println(m.maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
        System.out.println(m.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }

}
