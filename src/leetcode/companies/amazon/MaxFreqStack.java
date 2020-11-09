package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 895. Maximum Frequency Stack
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 * Example 1:
 *
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 * Note:
 *
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class MaxFreqStack {

    public class Element {
        int val;
        int freq;
        int time;

        public Element(int val, int freq, int time) {
            this.val = val;
            this.freq = freq;
            this.time = time;
        }
    }

    PriorityQueue<Element> pq;
    Map<Integer, Integer> freqMap;
    int time;

    public MaxFreqStack() {
        this.pq = new PriorityQueue<>((a, b) -> a.freq == b.freq ? b.time - a.time : b.freq - a.freq);
        this.freqMap = new HashMap<>();
        this.time = 0;
    }

    public void push(int x) {
        freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        pq.add(new Element(x, freqMap.get(x), time++));
    }

    public int pop() {
        int val = pq.poll().val;
        int newFreq = freqMap.get(val) - 1;
        if (newFreq == 0) freqMap.remove(val);
        freqMap.put(val, newFreq);
        return val;
    }

}
