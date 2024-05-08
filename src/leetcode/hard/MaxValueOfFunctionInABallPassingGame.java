package leetcode.hard;

import java.util.List;

/**
 * 2836. Maximize Value of Function in a Ball Passing Game
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given an integer array receiver of length n and an integer k. n players are playing a ball-passing game.
 *
 * You choose the starting player, i. The game proceeds as follows: player i passes the ball to player receiver[i], who then passes it to receiver[receiver[i]], and so on, for k passes in total. The game's score is the sum of the indices of the players who touched the ball, including repetitions, i.e. i + receiver[i] + receiver[receiver[i]] + ... + receiver(k)[i].
 *
 * Return the maximum possible score.
 *
 * Notes:
 *
 * receiver may contain duplicates.
 * receiver[i] may be equal to i.
 *
 * Example 1:
 *
 * Input: receiver = [2,0,1], k = 4
 *
 * Output: 6
 *
 * Explanation:
 *
 * Starting with player i = 2 the initial score is 2:
 *
 * Pass	Sender Index	Receiver Index	Score
 * 1	2	1	3
 * 2	1	0	3
 * 3	0	2	5
 * 4	2	1	6
 * Example 2:
 *
 * Input: receiver = [1,1,1,2,3], k = 3
 *
 * Output: 10
 *
 * Explanation:
 *
 * Starting with player i = 4 the initial score is 4:
 *
 * Pass	Sender Index	Receiver Index	Score
 * 1	4	3	7
 * 2	3	2	9
 * 3	2	1	10
 *
 * Constraints:
 *
 * 1 <= receiver.length == n <= 10^5
 * 0 <= receiver[i] <= n - 1
 * 1 <= k <= 10^10
 */
public class MaxValueOfFunctionInABallPassingGame {

    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int m = 35, n = receiver.size();
        long[][][] dp = new long[m][n][2]; // next,profit
        for (int j = 0; j < n; j++) dp[0][j][0] = dp[0][j][1] = receiver.get(j).longValue();
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = (int) dp[i - 1][j][0];
                dp[i][j] = new long[]{dp[i - 1][x][0], dp[i - 1][j][1] + dp[i - 1][x][1]};
            }
        }
        long profit = 0;
        for (int i = 0; i < n; i++) {
            long p = i;
            int now = i;
            for (int j = 0; j < m; j++) {
                if (((1L << j) & k) == 0) continue;
                p += dp[j][now][1];
                now = (int) dp[j][now][0];
            }
            profit = Math.max(profit, p);
        }
        return profit;
    }

}
