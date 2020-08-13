package leetcode.companies.google;

import java.util.*;

/**
 * Fruit Into Baskets
 * Medium
 * <p>
 * 869
 * <p>
 * 1276
 * <p>
 * Add to List
 * <p>
 * Share
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * <p>
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * <p>
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * <p>
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * <p>
 * What is the total amount of fruit you can collect with this procedure?
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 * <p>
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 * <p>
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 * <p>
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 * <p>
 * Note:
 * <p>
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 */
public class FruitIntoBaskets {

    public int totalFruit(int[] tree) {
        int n = tree.length;
        int i = 0, j = 0;
        int max = 0;
        Map<Integer, Integer> frequencies = new HashMap<>();
        while (j < n) {
            if (frequencies.size() <= 2) {
                frequencies.put(tree[j], j++);
            }
            if (frequencies.size() >= 3) {
                int min = frequencies.values().stream().min(Integer::compareTo).get();
                i = min + 1;
                frequencies.remove(tree[min]);
            }
            max = Math.max(max, j - i);
        }
        return max;
    }

}
