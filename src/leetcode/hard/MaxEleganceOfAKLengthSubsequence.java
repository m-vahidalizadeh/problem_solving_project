package leetcode.hard;

import java.util.*;

/**
 * 2813. Maximum Elegance of a K-Length Subsequence
 * Hard
 *
 * Topics
 *
 * Hint
 * You are given a 0-indexed 2D integer array items of length n and an integer k.
 *
 * items[i] = [profiti, categoryi], where profiti and categoryi denote the profit and category of the ith item respectively.
 *
 * Let's define the elegance of a subsequence of items as total_profit + distinct_categories2, where total_profit is the sum of all profits in the subsequence, and distinct_categories is the number of distinct categories from all the categories in the selected subsequence.
 *
 * Your task is to find the maximum elegance from all subsequences of size k in items.
 *
 * Return an integer denoting the maximum elegance of a subsequence of items with size exactly k.
 *
 * Note: A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order.
 *
 * Example 1:
 *
 * Input: items = [[3,2],[5,1],[10,1]], k = 2
 * Output: 17
 * Explanation: In this example, we have to select a subsequence of size 2.
 * We can select items[0] = [3,2] and items[2] = [10,1].
 * The total profit in this subsequence is 3 + 10 = 13, and the subsequence contains 2 distinct categories [2,1].
 * Hence, the elegance is 13 + 22 = 17, and we can show that it is the maximum achievable elegance.
 * Example 2:
 *
 * Input: items = [[3,1],[3,1],[2,2],[5,3]], k = 3
 * Output: 19
 * Explanation: In this example, we have to select a subsequence of size 3.
 * We can select items[0] = [3,1], items[2] = [2,2], and items[3] = [5,3].
 * The total profit in this subsequence is 3 + 2 + 5 = 10, and the subsequence contains 3 distinct categories [1,2,3].
 * Hence, the elegance is 10 + 32 = 19, and we can show that it is the maximum achievable elegance.
 * Example 3:
 *
 * Input: items = [[1,1],[2,1],[3,1]], k = 3
 * Output: 7
 * Explanation: In this example, we have to select a subsequence of size 3.
 * We should select all the items.
 * The total profit will be 1 + 2 + 3 = 6, and the subsequence contains 1 distinct category [1].
 * Hence, the maximum elegance is 6 + 12 = 7.
 *
 * Constraints:
 *
 * 1 <= items.length == n <= 10^5
 * items[i].length == 2
 * items[i][0] == profiti
 * items[i][1] == categoryi
 * 1 <= profiti <= 10^9
 * 1 <= categoryi <= n
 * 1 <= k <= n
 */
public class MaxEleganceOfAKLengthSubsequence {

    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        long res = 0, cur = 0;
        Set<Integer> seen = new HashSet<>();
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if (i < k) {
                if (seen.contains(items[i][1])) dup.add(items[i][0]);
                cur += items[i][0];
            } else if (!seen.contains(items[i][1])) {
                if (dup.isEmpty()) break;
                cur += items[i][0] - dup.remove(dup.size() - 1);
            }
            seen.add(items[i][1]);
            res = Math.max(res, cur + (long) seen.size() * seen.size());
        }
        return res;
    }

}
