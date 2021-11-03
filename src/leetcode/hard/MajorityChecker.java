package leetcode.hard;

import java.util.*;

/**
 * 1157. Online Majority Element In Subarray
 * Design a data structure that efficiently finds the majority element of a given subarray.
 *
 * The majority element of a subarray is an element that occurs threshold times or more in the subarray.
 *
 * Implementing the MajorityChecker class:
 *
 * MajorityChecker(int[] arr) Initializes the instance of the class with the given array arr.
 * int query(int left, int right, int threshold) returns the element in the subarray arr[left...right] that occurs at least threshold times, or -1 if no such element exists.
 *
 * Example 1:
 *
 * Input
 * ["MajorityChecker", "query", "query", "query"]
 * [[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
 * Output
 * [null, 1, -1, 2]
 *
 * Explanation
 * MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
 * majorityChecker.query(0, 5, 4); // return 1
 * majorityChecker.query(0, 3, 3); // return -1
 * majorityChecker.query(2, 3, 2); // return 2
 *
 * Constraints:
 *
 * 1 <= arr.length <= 2 * 104
 * 1 <= arr[i] <= 2 * 104
 * 0 <= left <= right < arr.length
 * threshold <= right - left + 1
 * 2 * threshold > right - left + 1
 * At most 104 calls will be made to query.
 */
public class MajorityChecker {

    TreeMap<Integer, List<Integer>> treeMap;

    public MajorityChecker(int[] arr) {
        treeMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = treeMap.getOrDefault(arr[i], new ArrayList<>());
            if (list.isEmpty()) treeMap.put(arr[i], list);
            list.add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        for (Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            int num = entry.getKey();
            List<Integer> list = entry.getValue();
            if (list.size() < threshold) continue;
            int leftIndexOrInsertionPoint = Collections.binarySearch(list, left); // returns index if found, otherwise returns -(insertion point)-1
            int rightIndexOrInsertionPoint = Collections.binarySearch(list, right); // returns index if found, otherwise returns -(insertion point)-1
            if (leftIndexOrInsertionPoint < 0)
                leftIndexOrInsertionPoint = -leftIndexOrInsertionPoint - 1; // if not found, convert to insertion point
            if (rightIndexOrInsertionPoint < 0)
                rightIndexOrInsertionPoint = -rightIndexOrInsertionPoint - 1; // if not found, convert to insertion point
            else rightIndexOrInsertionPoint++;
            if (rightIndexOrInsertionPoint - leftIndexOrInsertionPoint >= threshold) return num;
        }
        return -1;
    }

}
