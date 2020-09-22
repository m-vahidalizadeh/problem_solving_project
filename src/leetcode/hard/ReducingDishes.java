package leetcode.hard;

import java.util.Arrays;

/**
 * Reducing Dishes
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 * <p>
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]
 * <p>
 * Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.
 * <p>
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 * <p>
 * Example 1:
 * <p>
 * Input: satisfaction = [-1,-8,0,5,-9]
 * Output: 14
 * Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
 * Example 2:
 * <p>
 * Input: satisfaction = [4,3,2]
 * Output: 20
 * Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
 * Example 3:
 * <p>
 * Input: satisfaction = [-1,-4,-5]
 * Output: 0
 * Explanation: People don't like the dishes. No dish is prepared.
 * Example 4:
 * <p>
 * Input: satisfaction = [-2,5,-1,0,3,-3]
 * Output: 35
 * <p>
 * Constraints:
 * <p>
 * n == satisfaction.length
 * 1 <= n <= 500
 * -10^3 <= satisfaction[i] <= 10^3
 */
public class ReducingDishes {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int i;
        for (i = 0; i < n; i++) {
            if (satisfaction[i] >= 0) break;
        }
        if (i == n) return 0;
        int max = getLikeTimeCoefficient(satisfaction, i, n);
        for (int j = i - 1; j >= 0; j--) max = Math.max(max, getLikeTimeCoefficient(satisfaction, j, n));
        return max;
    }

    private int getLikeTimeCoefficient(int[] satisfaction, int start, int n) {
        int time = 1;
        int sum = 0;
        for (int i = start; i < n; i++) sum += satisfaction[i] * (time++);
        return sum;
    }

    public static void main(String[] args) {
        ReducingDishes r = new ReducingDishes();
        System.out.println(r.maxSatisfaction(new int[]{-1, -8, 0, 5, -9}));
        System.out.println(r.maxSatisfaction(new int[]{4, 3, 2}));
        System.out.println(r.maxSatisfaction(new int[]{-1, -4, -5}));
        System.out.println(r.maxSatisfaction(new int[]{-2, 5, -1, 0, 3, -3}));
    }

}
