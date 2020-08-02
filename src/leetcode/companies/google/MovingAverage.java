package leetcode.companies.google;

/**
 * Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * Example:
 * <p>
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverage {

    Integer[] nums;
    int curr;
    int size;
    int sum;
    int count;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.nums = new Integer[size];
        this.curr = 0;
        this.size = size;
        this.count = 0;
        this.sum = 0;
    }

    public double next(int val) {
        if (nums[curr] != null) {
            sum -= nums[curr];
            count--;
        }
        nums[curr] = val;
        sum += nums[curr];
        count++;
        curr = (curr + 1) % size;
        return sum / (double) count;
    }

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

}
