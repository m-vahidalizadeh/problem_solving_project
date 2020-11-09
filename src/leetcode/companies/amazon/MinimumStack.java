package leetcode.companies.amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 155. Min Stack
 * Add to List
 *
 * Share
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * Constraints:
 *
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinimumStack {

    PriorityQueue<Integer> pq;
    Deque<Integer> s;

    public MinimumStack() {
        pq = new PriorityQueue<>();
        s = new ArrayDeque<>();
    }

    public void push(int x) {
        pq.add(x);
        s.push(x);
    }

    public void pop() {
        int e = s.pop();
        pq.remove(e);
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return pq.peek();
    }

}
