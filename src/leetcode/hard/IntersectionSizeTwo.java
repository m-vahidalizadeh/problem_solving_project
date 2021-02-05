package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 757. Set Intersection Size At Least Two
 * An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.
 *
 * Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has a size of at least two.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
 * Output: 3
 * Explanation: Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
 * Also, there isn't a smaller size set that fulfills the above condition.
 * Thus, we output the size of this set, which is 3.
 * Example 2:
 *
 * Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
 * Output: 5
 * Explanation: An example of a minimum sized set is {1, 2, 3, 4, 5}.
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 3000
 * intervals[i].length == 2
 * 0 <= ai < bi <= 108
 */
public class IntersectionSizeTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]); // Sort intervals: 1- end 2- start- O(nlogn)
        List<Integer> res = new ArrayList<>();
        res.add(intervals[0][1] - 1); // Add one before end
        res.add(intervals[0][1]); // Add end
        for (int i = 1; i < n; i++) { // O(n)
            int start = intervals[i][0];
            int end = intervals[i][1];
            int size = res.size();
            int last = res.get(size - 1);
            int secondLast = res.get(size - 2);
            if (start > last) { // We need to add two fresh points
                res.add(end - 1);
                res.add(end);
            } else if (start == last) res.add(end); // We already added one. We need to add the end of this interval
            else if (start > secondLast) res.add(end); // We already added last. We need one more
        }
        return res.size();
    }

}
