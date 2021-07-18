package leetcode.hard;

/**
 * 1246. Palindrome Removal
 * Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.
 *
 * Return the minimum number of moves needed to remove all numbers from the array.
 *
 * Example 1:
 *
 * Input: arr = [1,2]
 * Output: 2
 * Example 2:
 *
 * Input: arr = [1,3,4,1,5]
 * Output: 3
 * Explanation: Remove [4] then remove [1,3,1] then remove [5].
 *
 * Constraints:
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 20
 */
public class PalindromeRemoval {

    public int minimumMoves(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) dp[i][j] = 1; // sub with length 1
                else if (i + 1 == j) dp[i][j] = arr[i] == arr[j] ? 1 : 2; // sub with length 2: cost -> XX=1 or XY=2
                else { // Length greater than 2
                    dp[i][j] = n;
                    if (arr[i] == arr[j])
                        dp[i][j] = dp[i + 1][j - 1]; // when there is one palindrome left in [i+1,j-1], we remove it with arr[i] and arr[j] as a bigger palindrome
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j - 1]) + 1); // remove i or j
                    for (int k = i + 1; k < j - 1; k++) dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
