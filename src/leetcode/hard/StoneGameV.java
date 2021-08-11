package leetcode.hard;

/**
 * 1563. Stone Game V
 * There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 *
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 *
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 *
 * Return the maximum score that Alice can obtain.
 *
 * Example 1:
 *
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
 * Example 2:
 *
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 * Example 3:
 *
 * Input: stoneValue = [4]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 */
public class StoneGameV {

    int[] preSum;
    Integer[][] cache;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        preSum = new int[n];
        cache = new Integer[n][n];
        preSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) preSum[i] = preSum[i - 1] + stoneValue[i];
        return rec(0, n - 1);
    }

    private int rec(int s, int e) {
        if (s == e) return 0;
        if (cache[s][e] != null) return cache[s][e];
        int res = 0;
        for (int i = s; i <= e; i++) {
            int leftSum = preSum[i] - preSum[s - 1];
            int rightSum = preSum[e] - preSum[i];
            if (leftSum < rightSum) res = Math.max(res, leftSum + rec(s, i));
            else if (rightSum < leftSum) res = Math.max(res, rightSum + rec(i + 1, e));
            else res = Math.max(res, Math.max(leftSum + rec(s, i), rightSum + rec(i + 1, e)));
        }
        return cache[s][e] = res;
    }

}
