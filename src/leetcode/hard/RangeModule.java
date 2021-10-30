package leetcode.hard;

import java.util.TreeMap;

/**
 * 715. Range Module
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 *
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 *
 * Implement the RangeModule class:
 *
 * RangeModule() Initializes the object of the data structure.
 * void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 * void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 * Example 1:
 *
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 *
 * Explanation
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
 * rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 * Constraints:
 *
 * 1 <= left < right <= 109
 * At most 104 calls will be made to addRange, queryRange, and removeRange.
 */
public class RangeModule {

    TreeMap<Integer, Boolean> map; // See the boolean as true: forward, false: backward

    public RangeModule() {
        map = new TreeMap<>(); // True means includes from this to right (forward). False means from this point to the left (backward).
        map.put(0, false); // Add (-infinity,-1) to prevent null issues
        map.put(Integer.MAX_VALUE, true); // Add [int.max,+infinity) to prevent null issues
    }

    public void addRange(int left, int right) {
        map.subMap(left, true, right, true).clear(); // two true inputs show inclusive. Remove all points between left and right.
        if (!map.floorEntry(left).getValue())
            map.put(left, true); // Add from left forward, if the prev entry is not covering it (lower is backward)
        if (map.ceilingEntry(right).getValue())
            map.put(right, false); // Add from right backward, if the next is not covering it (higher is forward)
    }

    public boolean queryRange(int left, int right) { // lower,higher are strictly lower/greater than the search key, but floor/ceiling can equal the search key.
        return map.floorKey(left) == map.lowerKey(right) && // From right check if it covers left to right
                map.higherKey(left) == map.ceilingKey(right) && // From left check if it covers left to right
                map.floorEntry(left).getValue(); // Must include the left
    }

    public void removeRange(int left, int right) { // Exactly opposite the addRange
        map.subMap(left, true, right, true).clear();
        if (map.floorEntry(left).getValue()) map.put(left, false);
        if (!map.ceilingEntry(right).getValue()) map.put(right, true);
    }

}
