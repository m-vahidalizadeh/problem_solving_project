package leetcode.hard;

import java.util.*;

/**
 * 2561. Rearranging Fruits
 * You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
 *
 * Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
 * The cost of the swap is min(basket1[i],basket2[j]).
 * Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
 *
 * Return the minimum cost to make both the baskets equal or -1 if impossible.
 *
 * Example 1:
 *
 * Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
 * Output: 1
 * Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
 * Example 2:
 *
 * Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make both the baskets equal.
 *
 * Constraints:
 *
 * basket1.length == bakste2.length
 * 1 <= basket1.length <= 105
 * 1 <= basket1[i],basket2[i] <= 109
 */
public class RearrangingFruits {

    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> diffFrequencyMap = new HashMap<>();
        int min1 = basket1[0], min2 = basket2[0];
        for (int i = 0; i < basket1.length; i++) {
            diffFrequencyMap.put(basket1[i], diffFrequencyMap.getOrDefault(basket1[i], 0) + 1);
            min1 = Math.min(min1, basket1[i]);
            diffFrequencyMap.put(basket2[i], diffFrequencyMap.getOrDefault(basket2[i], 0) - 1);
            min2 = Math.min(min2, basket2[i]);
        }
        int minx = Math.min(min1, min2);
        List<Integer> diffList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> diffEntry : diffFrequencyMap.entrySet()) {
            int diffValue = diffEntry.getValue();
            if (diffValue % 2 != 0) return -1;
            for (int i = 0; i < Math.abs(diffValue) / 2; i++) diffList.add(diffEntry.getKey());
        }
        Collections.sort(diffList);
        long res = 0;
        for (int i = 0; i < diffList.size() / 2; i++) {
            res += Math.min(diffList.get(i), minx * 2);
        }
        return res;
    }

}
