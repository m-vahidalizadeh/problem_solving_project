package leetcode.hard;

/**
 * 335. Self Crossing
 * You are given an array of integers distance.
 *
 * You start at point (0,0) on an X-Y plane and you move distance[0] meters to the north, then distance[1] meters to the west, distance[2] meters to the south, distance[3] meters to the east, and so on. In other words, after each move, your direction changes counter-clockwise.
 *
 * Return true if your path crosses itself, and false if it does not.
 *
 * Example 1:
 *
 * Input: distance = [2,1,1,2]
 * Output: true
 * Example 2:
 *
 * Input: distance = [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: distance = [1,1,1,1]
 * Output: true
 *
 * Constraints:
 *
 * 1 <= distance.length <= 105
 * 1 <= distance[i] <= 105
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        for (int i = 3; i < n; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) return true;
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2])
                return true;
            if (i >= 5 && distance[i] + distance[i - 4] >= distance[i - 2] && distance[i - 1] + distance[i - 5] >= distance[i - 3] &&
                    distance[i - 2] > distance[i - 4] && distance[i - 3] > distance[i - 1]) return true;
        }
        return false;
    }

}
