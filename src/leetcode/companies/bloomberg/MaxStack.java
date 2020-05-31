package leetcode.companies.bloomberg;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Max Stack
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 * Example 1:
 *
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * Note:
 *
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */
public class MaxStack {

    Stack<Integer> st;
    PriorityQueue<Integer> pq;

    public MaxStack() {
        st = new Stack<>();
        pq = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void push(int x) {
        st.push(x);
        pq.add(x);
    }

    public int pop() {
        int result = st.pop();
        pq.remove(result);
        return result;
    }

    public int top() {
        return st.peek();
    }

    public int peekMax() {
        return pq.peek();
    }

    public int popMax() {
        int max = pq.poll();
        Stack<Integer> auxStack = new Stack<>();
        while (st.peek() != max) auxStack.push(st.pop());
        st.pop();
        while (!auxStack.isEmpty()) st.push(auxStack.pop());
        return max;
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.top());
        System.out.println(stack.peekMax());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }

}
