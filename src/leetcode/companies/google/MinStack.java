package leetcode.companies.google;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
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
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {

    Stack<Integer> stack;
    PriorityQueue<Integer> pq;

    public MinStack() {
        stack = new Stack<>();
        pq = new PriorityQueue<>();
    }

    public void push(int val) {
        Integer element = val;
        stack.push(element);
        pq.add(element);
    }

    public void pop() {
        Integer element = stack.pop();
        pq.remove(element);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pq.peek();
    }

}
