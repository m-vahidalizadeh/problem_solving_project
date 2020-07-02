package leetcode.medium;

/**
 * Design Hit Counter
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * <p>
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * <p>
 * It is possible that several hits arrive roughly at the same time.
 * <p>
 * Example:
 * <p>
 * HitCounter counter = new HitCounter();
 * <p>
 * // hit at timestamp 1.
 * counter.hit(1);
 * <p>
 * // hit at timestamp 2.
 * counter.hit(2);
 * <p>
 * // hit at timestamp 3.
 * counter.hit(3);
 * <p>
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 * <p>
 * // hit at timestamp 300.
 * counter.hit(300);
 * <p>
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 * <p>
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 */
public class HitCounter {

    int[] times;
    int[] hits;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] == timestamp) {
            hits[index]++;
        } else {
            times[index] = timestamp;
            hits[index] = 1;
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int counter = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) counter += hits[i];
        }
        return counter;
    }

}
