package leetcode.hard;

import java.util.Arrays;

/**
 * 2188. Minimum Time to Finish the Race
 * You are given a 0-indexed 2D integer array tires where tires[i] = [fi, ri] indicates that the ith tire can finish its xth successive lap in fi * ri(x-1) seconds.
 *
 * For example, if fi = 3 and ri = 2, then the tire would finish its 1st lap in 3 seconds, its 2nd lap in 3 * 2 = 6 seconds, its 3rd lap in 3 * 22 = 12 seconds, etc.
 * You are also given an integer changeTime and an integer numLaps.
 *
 * The race consists of numLaps laps and you may start the race with any tire. You have an unlimited supply of each tire and after every lap, you may change to any given tire (including the current tire type) if you wait changeTime seconds.
 *
 * Return the minimum time to finish the race.
 *
 * Example 1:
 *
 * Input: tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
 * Output: 21
 * Explanation:
 * Lap 1: Start with tire 0 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in another 2 seconds.
 * Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
 * The minimum time to complete the race is 21 seconds.
 * Example 2:
 *
 * Input: tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
 * Output: 25
 * Explanation:
 * Lap 1: Start with tire 1 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 3: Change tires to a new tire 1 for 6 seconds and then finish the lap in another 2 seconds.
 * Lap 4: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 5: Change tires to tire 0 for 6 seconds then finish the lap in another 1 second.
 * Total time = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 seconds.
 * The minimum time to complete the race is 25 seconds.
 *
 * Constraints:
 *
 * 1 <= tires.length <= 105
 * tires[i].length == 2
 * 1 <= fi, changeTime <= 105
 * 2 <= ri <= 105
 * 1 <= numLaps <= 1000
 */
public class MinimumTimeToFinishRace {

    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int[] minTime = new int[numLaps + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        for (int[] tire : tires) checkMinTime(tire, minTime, changeTime, numLaps);
        for (int i = 2; i <= numLaps; i++) {
            for (int j = 1; j < i; j++) {
                int remain = i % j;
                int currMin;
                if (remain != 0) currMin = (i / j) * (minTime[j] + changeTime) + minTime[remain];
                else
                    currMin = (i / j) * (minTime[j] + changeTime) - changeTime; // no remaining, remove the last change time
                minTime[i] = Math.min(minTime[i], currMin);
            }
        }
        return minTime[numLaps];
    }

    private void checkMinTime(int[] tire, int[] minTime, int changeTime, int numLaps) {
        int base = tire[0];
        int lap = 1;
        int curr = base;
        minTime[lap] = Math.min(minTime[lap], curr);
        int sum = base;
        while (curr * tire[1] <= base + changeTime && lap++ < numLaps) {
            curr *= tire[1];
            sum += curr;
            minTime[lap] = Math.min(minTime[lap], sum);
        }
    }

}
