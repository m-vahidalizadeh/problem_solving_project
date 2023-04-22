package leetcode.companies.uber;

import java.util.*;

/**
 * 465. Optimal Account Balancing
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 *
 * Return the minimum number of transactions required to settle the debt.
 *
 * Example 1:
 *
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * Constraints:
 *
 * 1 <= transactions.length <= 8
 * transactions[i].length == 3
 * 0 <= fromi, toi < 12
 * fromi != toi
 * 1 <= amounti <= 100
 */
public class SettleDebt {

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trans : transactions) {
            map.put(trans[0], map.getOrDefault(trans[0], 0) - trans[2]);
            map.put(trans[1], map.getOrDefault(trans[1], 0) + trans[2]);
        }
        int balance[] = new int[map.size()], i = 0;
        for (int k : map.keySet()) balance[i++] = map.get(k);
        return dfs(0, balance);
    }

    private int dfs(int idx, int[] balance) {
        if (idx == balance.length) return 0;
        if (balance[idx] == 0) return dfs(idx + 1, balance);
        int res = Integer.MAX_VALUE, currDebt = balance[idx];
        for (int i = idx + 1; i < balance.length; i++) {
            if (balance[idx] * balance[i] >= 0) continue;
            balance[i] += currDebt;
            res = Math.min(res, 1 + dfs(idx + 1, balance));
            balance[i] -= currDebt;
        }
        return res;
    }

}
