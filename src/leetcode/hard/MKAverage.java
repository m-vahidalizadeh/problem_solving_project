package leetcode.hard;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1825. Finding MK Average
 * You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
 *
 * The MKAverage can be calculated using these steps:
 *
 * If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
 * Remove the smallest k elements and the largest k elements from the container.
 * Calculate the average value for the rest of the elements rounded down to the nearest integer.
 * Implement the MKAverage class:
 *
 * MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
 * void addElement(int num) Inserts a new element num into the stream.
 * int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
 *
 * Example 1:
 *
 * Input
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * Output
 * [null, null, null, -1, null, 3, null, null, null, 5]
 *
 * Explanation
 * MKAverage obj = new MKAverage(3, 1);
 * obj.addElement(3);        // current elements are [3]
 * obj.addElement(1);        // current elements are [3,1]
 * obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
 * obj.addElement(10);       // current elements are [3,1,10]
 * obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
 *                           // After removing smallest and largest 1 element the container will be [3].
 *                           // The average of [3] equals 3/1 = 3, return 3
 * obj.addElement(5);        // current elements are [3,1,10,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
 *                           // After removing smallest and largest 1 element the container will be [5].
 *                           // The average of [5] equals 5/1 = 5, return 5
 *
 * Constraints:
 *
 * 3 <= m <= 105
 * 1 <= k*2 < m
 * 1 <= num <= 105
 * At most 105 calls will be made to addElement and calculateMKAverage.
 */
public class MKAverage {

    int m;
    int k;
    int totalSum;
    int totalCount;
    LinkedList<Integer> list; // Elements in adding order
    TreeMap<Integer, Integer> treeMap; // [element,frequency] of m elements

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.totalSum = 0;
        this.totalCount = 0;
        this.list = new LinkedList<>();
        this.treeMap = new TreeMap<>();
    }

    public void addElement(int num) {
        list.addLast(num); // Add the element to the end of list to keep the order.
        treeMap.put(num, treeMap.getOrDefault(num, 0) + 1); // Add the element to the tree map.
        totalSum += num; // Update total sum
        totalCount++; // Update total count
        if (totalCount > m) { // If the total count is greater than m, remove the first element of the list from both list and tree map.
            int removeNum = list.removeFirst(); // Remove the first element of the list
            totalSum -= removeNum; // the number that should be removed from the
            totalCount--; // One less element
            int freq = treeMap.get(removeNum); // One less count for this number
            if (freq == 1) treeMap.remove(removeNum); // If there is no more of this number, remove the entry
            else treeMap.put(removeNum, treeMap.get(removeNum) - 1); // Just update the remaining count of this number
        }
    }

    public int calculateMKAverage() {
        if (totalCount < m) return -1;
        int s = totalSum;
        int rem = k;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) { // Removing the k smallest-entry[num,frequency]
            if (rem == 0) break; // If we removed k elements, break.
            int num = entry.getKey();
            int removableFreq = Math.min(entry.getValue(), rem);
            s -= num * removableFreq;
            rem -= removableFreq;
        }
        rem = k;
        for (Map.Entry<Integer, Integer> entry : treeMap.descendingMap().entrySet()) { // Removing the k largest-entry[num,frequency]
            if (rem == 0) break; // If we removed k elements, break.
            int num = entry.getKey();
            int removableFreq = Math.min(entry.getValue(), rem);
            s -= num * removableFreq;
            rem -= removableFreq;
        }
        return s / (totalCount - 2 * k);
    }

}
