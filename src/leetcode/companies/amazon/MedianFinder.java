package leetcode.companies.amazon;

import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder {

    PriorityQueue<Integer> minHeap; // Higher half: mid ... max
    PriorityQueue<Integer> maxHeap; // lower half: min ... mid-1

    public MedianFinder() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        minHeap.add(num); // Add the num to the higher half.
        maxHeap.add(minHeap.poll()); // Add the min of higher half to lower half.
        if (maxHeap.size() > minHeap.size()) minHeap.add(maxHeap.poll()); // If odd, we keep the mid in the higher half.
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) return minHeap.peek();
        else return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder m = new MedianFinder();
        m.addNum(-1);
        m.addNum(-2);
        m.addNum(-3);
        m.addNum(-4);
        m.addNum(-5);
        System.out.println(m.findMedian());
    }

}
