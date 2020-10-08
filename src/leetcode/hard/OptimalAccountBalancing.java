package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 465. Optimal Account Balancing
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * <p>
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * <p>
 * Note:
 * <p>
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 * <p>
 * Input:
 * [[0,1,10], [2,0,5]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * <p>
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 * <p>
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * <p>
 * Output:
 * 1
 * <p>
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * <p>
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> balances = new HashMap<>();
        for (int[] t : transactions) {
            balances.put(t[0], balances.getOrDefault(t[0], 0) + t[2]); // He should be paid (positive).
            balances.put(t[1], balances.getOrDefault(t[1], 0) - t[2]); // He should pay back (negative).
        }
        List<Integer> nonZeroBalances = new ArrayList<>();
        for (int balance : balances.values()) {
            if (balance != 0) nonZeroBalances.add(balance);
        }
        return rec(0, nonZeroBalances, nonZeroBalances.size());
    }

    private int rec(int index, List<Integer> nonZeroBalances, int n) {
        if (index == n) return 0; // If we are at the end of the list, no more to settle.
        int curr = nonZeroBalances.get(index);
        if (curr == 0) return rec(index + 1, nonZeroBalances, n); // If balance is zero, skip it.
        int min = Integer.MAX_VALUE; // Find the minimum.
        for (int i = index + 1; i < n; i++) { // Consider all the options.
            int elementI = nonZeroBalances.get(i);
            if (curr * elementI < 0) { // Check to find a negative positive pair: negative X positive<0.
                nonZeroBalances.set(i, curr + elementI);
                min = Math.min(min, 1 + rec(index + 1, nonZeroBalances, n)); // 1+other transactions.
                nonZeroBalances.set(i, elementI);
                if (curr + elementI == 0) break; // This is the optimal solution a-a=0 -> 1 transaction.
            }
        }
        return min;
    }

}
