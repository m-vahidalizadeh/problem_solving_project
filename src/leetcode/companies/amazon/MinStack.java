package leetcode.companies.amazon;

import java.util.Stack;

/**
 * Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * Constraints:
 * <p>
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinStack {

    Stack<Integer> st;
    int min;

    public MinStack() {
        st = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        st.push(x);
        if (x < min) min = x;
    }

    public void pop() {
        int x = st.pop();
        if (x == min) findNewMin();
    }

    private void findNewMin() {
        Stack<Integer> tempStack = new Stack<>();
        min = Integer.MAX_VALUE;
        while (!st.isEmpty()) {
            int x = st.pop();
            if (x < min) min = x;
            tempStack.push(x);
        }
        while (!tempStack.isEmpty()) st.push(tempStack.pop());
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {

    }

}
