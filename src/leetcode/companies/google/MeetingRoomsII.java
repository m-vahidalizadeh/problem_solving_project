package leetcode.companies.google;

import java.util.Map;
import java.util.TreeMap;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer,Integer> criticalPoints=new TreeMap<>();
        for(int[] interval:intervals){
            criticalPoints.put(interval[0],criticalPoints.getOrDefault(interval[0],0)+1);
            criticalPoints.put(interval[1],criticalPoints.getOrDefault(interval[1],0)-1);
        }
        int max=0;
        int rooms=0;
        for(Map.Entry<Integer,Integer> point:criticalPoints.entrySet()){
            rooms+=point.getValue();
            max=Math.max(max,rooms);
        }
        return max;
    }

}
