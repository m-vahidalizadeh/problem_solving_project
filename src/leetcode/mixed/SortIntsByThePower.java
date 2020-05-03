package leetcode.mixed;

import java.util.Objects;
import java.util.PriorityQueue;

public class SortIntsByThePower {

    static class IntWithPower implements Comparable<IntWithPower> {
        Integer val;
        Integer power;

        IntWithPower(int val, int power) {
            this.val = val;
            this.power = power;
        }

        @Override
        public int compareTo(IntWithPower intWithPower) {
            if (this.power.equals(intWithPower.power)) {
                return this.val.compareTo(intWithPower.val);
            }
            return this.power.compareTo(intWithPower.power);
        }
    }

    public static void main(String[] args) {
        /*
Example 1:
Input: lo = 12, hi = 15, k = 2
Output: 13
Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.

Example 2:
Input: lo = 1, hi = 1, k = 1
Output: 1

Example 3:
Input: lo = 7, hi = 11, k = 4
Output: 7
Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.

Example 4:
Input: lo = 10, hi = 20, k = 5
Output: 13

Example 5:
Input: lo = 1, hi = 1000, k = 777
Output: 570
         */
        SortIntsByThePower sortIntsByThePower = new SortIntsByThePower();
        System.out.println(sortIntsByThePower.getKth(12, 15, 2));
        System.out.println(sortIntsByThePower.getKth(1, 1, 1));
        System.out.println(sortIntsByThePower.getKth(7, 11, 4));
        System.out.println(sortIntsByThePower.getKth(10, 20, 5));
        System.out.println(sortIntsByThePower.getKth(1, 1000, 777));
    }

    public int getKth(int lo, int hi, int k) {
        PriorityQueue<IntWithPower> pq = new PriorityQueue<>();
        for (int i = lo; i <= hi; i++) {
            pq.add(new IntWithPower(i, getPower(i)));
        }
        for (int i = 1; i <= k - 1; i++) {
            pq.poll();
        }
        return Objects.requireNonNull(pq.poll()).val;
    }

    private int getPower(int x) {
        int counter = 0;
        while (x != 1) {
            if (x % 2 == 0) {
                // Even
                x = x / 2;
            } else {
                // Odd
                x = (3 * x) + 1;
            }
            counter++;
        }
        return counter;
    }

}
