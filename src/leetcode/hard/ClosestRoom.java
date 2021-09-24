package leetcode.hard;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1847. Closest Room
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.
 *
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth query is the room number id of a room such that:
 *
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.
 *
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 *
 * Example 1:
 *
 * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * Output: [3,-1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
 * Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
 * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
 * Example 2:
 *
 * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * Output: [2,1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
 * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
 * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 *
 * Constraints:
 *
 * n == rooms.length
 * 1 <= n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= roomIdi, preferredj <= 107
 * 1 <= sizei, minSizej <= 107
 */
public class ClosestRoom {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int[][] sortedQueries = new int[queries.length][3]; // min size, preferred ID, original index
        for (int i = 0; i < queries.length; i++) sortedQueries[i] = new int[]{queries[i][1], queries[i][0], i};
        Arrays.sort(sortedQueries, (a, b) -> b[0] - a[0]);
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        int j = 0;
        TreeSet<Integer> candidates = new TreeSet<>();
        for (int[] query : sortedQueries) {
            int m = query[0];
            int p = query[1];
            int i = query[2];
            while (j < rooms.length && rooms[j][1] >= m) candidates.add(rooms[j++][0]);
            if (candidates.contains(p)) ans[i] = p;
            else {
                Integer lower = candidates.lower(p);
                Integer higher = candidates.higher(p);
                if (lower != null && higher != null) {
                    int lowerDiff = Math.abs(p - lower);
                    int higherDiff = Math.abs(p - higher);
                    if (lowerDiff <= higherDiff) ans[i] = lower;
                    else ans[i] = higher;
                } else if (lower != null) ans[i] = lower;
                else if (higher != null) ans[i] = higher;
            }
        }
        return ans;
    }

}
