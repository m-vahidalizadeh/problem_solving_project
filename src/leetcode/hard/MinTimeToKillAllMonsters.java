package leetcode.hard;

import java.util.Arrays;

/**
 * 2403. Minimum Time to Kill All Monsters
 * You are given an integer array power where power[i] is the power of the ith monster.
 *
 * You start with 0 mana points, and each day you increase your mana points by gain where gain initially is equal to 1.
 *
 * Each day, after gaining gain mana, you can defeat a monster if your mana points are greater than or equal to the power of that monster. When you defeat a monster:
 *
 * your mana points will be reset to 0, and
 * the value of gain increases by 1.
 * Return the minimum number of days needed to defeat all the monsters.
 *
 * Example 1:
 *
 * Input: power = [3,1,4]
 * Output: 4
 * Explanation: The optimal way to beat all the monsters is to:
 * - Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 2nd monster.
 * - Day 2: Gain 2 mana points to get a total of 2 mana points.
 * - Day 3: Gain 2 mana points to get a total of 4 mana points. Spend all mana points to kill the 3rd monster.
 * - Day 4: Gain 3 mana points to get a total of 3 mana points. Spend all mana points to kill the 1st monster.
 * It can be proven that 4 is the minimum number of days needed.
 * Example 2:
 *
 * Input: power = [1,1,4]
 * Output: 4
 * Explanation: The optimal way to beat all the monsters is to:
 * - Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1st monster.
 * - Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2nd monster.
 * - Day 3: Gain 3 mana points to get a total of 3 mana points.
 * - Day 4: Gain 3 mana points to get a total of 6 mana points. Spend all mana points to kill the 3rd monster.
 * It can be proven that 4 is the minimum number of days needed.
 * Example 3:
 *
 * Input: power = [1,2,4,9]
 * Output: 6
 * Explanation: The optimal way to beat all the monsters is to:
 * - Day 1: Gain 1 mana point to get a total of 1 mana point. Spend all mana points to kill the 1st monster.
 * - Day 2: Gain 2 mana points to get a total of 2 mana points. Spend all mana points to kill the 2nd monster.
 * - Day 3: Gain 3 mana points to get a total of 3 mana points.
 * - Day 4: Gain 3 mana points to get a total of 6 mana points.
 * - Day 5: Gain 3 mana points to get a total of 9 mana points. Spend all mana points to kill the 4th monster.
 * - Day 6: Gain 4 mana points to get a total of 4 mana points. Spend all mana points to kill the 3rd monster.
 * It can be proven that 6 is the minimum number of days needed.
 *
 * Constraints:
 *
 * 1 <= power.length <= 17
 * 1 <= power[i] <= 109
 */
public class MinTimeToKillAllMonsters {

    public long minimumTime(int[] power) {
        int gain = 1; // Gain is one at the beginning and mana is 0
        Arrays.sort(power); // Sort the monsters based on their power ascending
        Long[][] cache = new Long[(1 << power.length) + 1][power.length + 1]; // the first dimension is the bitmap of the defeated monsters (1 means defeated) the second dimension is current gain
        return getNumberOfDays(power, 0, gain, cache);
    }

    private long getNumberOfDays(int[] power, int state, int gain, Long[][] dp) {
        if (state == (1 << power.length) - 1) return 0;
        if (dp[state][gain] != null) return dp[state][gain]; // It is already calculated
        long days = Long.MAX_VALUE;
        for (int i = 0; i < power.length; i++) {
            if ((state & (1 << i)) == 0) { // If you haven't defeated the monster i, see if you have enough mana (1 day), if not how many gains you should wait to defeat this monster (power[i]/gain).
                days = Math.min(days, (gain >= power[i] ? 1 : (long) Math.ceil((double) power[i] / gain)) + getNumberOfDays(power, state | (1 << i), gain + 1, dp));
            }
        }
        return dp[state][gain] = days;
    }

}
