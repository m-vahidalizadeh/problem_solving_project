package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1434. Number of Ways to Wear Different Hats to Each Other
 * There are n people and 40 types of hats labeled from 1 to 40.
 *
 * Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.
 *
 * Return the number of ways that the n people wear different hats to each other.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: hats = [[3,4],[4,5],[5]]
 * Output: 1
 * Explanation: There is only one way to choose hats given the conditions.
 * First person choose hat 3, Second person choose hat 4 and last one hat 5.
 * Example 2:
 *
 * Input: hats = [[3,5,1],[3,5]]
 * Output: 4
 * Explanation: There are 4 ways to choose hats
 * (3,5), (5,3), (1,3) and (1,5)
 * Example 3:
 *
 * Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
 * Output: 24
 * Explanation: Each person can choose hats labeled from 1 to 4.
 * Number of Permutations of (1,2,3,4) = 24.
 * Example 4:
 *
 * Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
 * Output: 111
 *
 * Constraints:
 *
 * n == hats.length
 * 1 <= n <= 10
 * 1 <= hats[i].length <= 40
 * 1 <= hats[i][j] <= 40
 * hats[i] contains a list of unique integers.
 */
public class NumWaysToWearDiffHatsPeople {

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        List<Integer>[] h2p = new List[41];
        for (int i = 1; i <= 40; i++) h2p[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int hat : hats.get(i)) {
                h2p[hat].add(i);
            }
        }
        Integer[][] dp = new Integer[41][1024];
        return rec(h2p, (1 << n) - 1, 1, 0, dp);
    }

    private int rec(List<Integer>[] h2p, int allPeopleAssigned, int hat, int peopleAssigned, Integer[][] dp) {
        if (peopleAssigned == allPeopleAssigned) return 1;
        if (hat > 40) return 0;
        if (dp[hat][peopleAssigned] != null) return dp[hat][peopleAssigned];
        int res = rec(h2p, allPeopleAssigned, hat + 1, peopleAssigned, dp); // Skip this hat
        for (int p : h2p[hat]) {
            if (((peopleAssigned >> p) & 1) == 1) continue; // The person has a hat
            res += rec(h2p, allPeopleAssigned, hat + 1, peopleAssigned | (1 << p), dp);
            res %= 1_000_000_007;
        }
        return dp[hat][peopleAssigned] = res;
    }

}
