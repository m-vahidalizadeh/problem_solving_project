package leetcode.bloomberg;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
public class BLMaxStack {

    PriorityQueue<Integer> pq;
    Stack<Integer> stack;

    public static void main(String[] args) {
        /*
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
         */
        BLMaxStack blMaxStack = new BLMaxStack();
        blMaxStack.push(5);
        blMaxStack.push(1);
        blMaxStack.push(5);
        System.out.println(blMaxStack.top()); //-> 5
        System.out.println(blMaxStack.popMax()); //-> 5
        System.out.println(blMaxStack.top()); //-> 1
        System.out.println(blMaxStack.peekMax()); //-> 5
        System.out.println(blMaxStack.pop()); //-> 1
        System.out.println(blMaxStack.top()); //-> 5
    }

    /**
     * initialize your data structure here.
     */
    public BLMaxStack() {
        pq = new PriorityQueue<>(Collections.reverseOrder());
        stack = new Stack<>();
    }

    public void push(int x) {
        pq.add(x);
        stack.push(x);
    }

    public int pop() {
        int popResult = stack.pop();
        pq.remove(popResult);
        return popResult;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return pq.peek();
    }

    public int popMax() {
        int pqPeek = pq.peek();
        int stackPeek = stack.peek();
        if (stackPeek == pqPeek) {
            pq.poll();
            stack.pop();
            return pqPeek;
        }
        Stack<Integer> tempStack = new Stack<>();
        int stackPop = stack.pop();
        tempStack.push(stackPop);
        while (!stack.isEmpty() && stackPop != pqPeek) {
            stackPop = stack.pop();
            tempStack.push(stackPop);
        }
        if (stackPop == pqPeek) {
            pq.poll();
            tempStack.pop();
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
            return pqPeek;
        }
        return -1;
    }

}
