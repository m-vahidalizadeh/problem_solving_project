package leetcode.companies.google;

/**
 * Maximize Distance to Closest Person
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to closest person.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 * <p>
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * <p>
 * Constraints:
 * <p>
 * 2 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 */
public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int l = 0, r = 0, max = 1;
        while (r < n) {
            while (l < n && seats[l] == 1) l++;
            r = l;
            while (r < n && seats[r] == 0) r++;
            if (l == 0 || r == n) {
                max = Math.max(max, r - l);
            } else {
                max = Math.max(max, Double.valueOf(Math.ceil((r - l) / 2.0)).intValue());
            }
            l = r + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        MaximizeDistanceToClosestPerson m = new MaximizeDistanceToClosestPerson();
        System.out.println(m.maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
        System.out.println(m.maxDistToClosest(new int[]{1, 0, 0, 0}));
        System.out.println(m.maxDistToClosest(new int[]{0, 0, 1}));
    }

}
