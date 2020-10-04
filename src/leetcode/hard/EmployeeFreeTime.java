package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 759. Employee Free Time
 * We are given a list schedule of employees, which represents the working time for each employee.
 * <p>
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * <p>
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * <p>
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't
 * include intervals like [5, 5] in our answer, as they have zero length.
 * <p>
 * Example 1:
 * <p>
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 * <p>
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime {

    public static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Interval> intervals = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (List<Interval> s : schedule) intervals.addAll(s);
        List<Interval> result = new ArrayList<>();
        int end = intervals.peek().end;
        while (!intervals.isEmpty()) {
            Interval curr = intervals.poll();
            if (curr.start > end) result.add(new Interval(end, curr.start));
            end = Math.max(end, curr.end);
        }
        return result;
    }

    public static void main(String[] args) {
        EmployeeFreeTime e = new EmployeeFreeTime();
        System.out.println(e.employeeFreeTime(List.of(
                List.of(new Interval(1, 2), new Interval(5, 6)),
                List.of(new Interval(1, 3)),
                List.of(new Interval(4, 10))
        )));
        System.out.println(e.employeeFreeTime(List.of(
                List.of(new Interval(1, 3), new Interval(6, 7)),
                List.of(new Interval(2, 4)),
                List.of(new Interval(2, 5), new Interval(9, 12))
        )));
    }

}
