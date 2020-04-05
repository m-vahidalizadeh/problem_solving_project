package medium;

public class CustomStack {

    int[] stack;
    int stackIndex;
    int maxSizeMinusOne;

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
        System.out.println(customStack.pop());
        customStack.push(2);
        customStack.push(3);
        customStack.push(4);
        customStack.increment(5, 100);
        customStack.increment(2, 100);
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());
    }

    public CustomStack(int maxSize) {
        this.maxSizeMinusOne = maxSize - 1;
        stack = new int[maxSize];
        stackIndex = -1;
    }

    public void push(int x) {
        if (!isFull()) {
            stackIndex++;
            stack[stackIndex] = x;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        } else {
            int result = stack[stackIndex];
            stackIndex--;
            return result;
        }
    }

    public void increment(int k, int val) {
        int stackSize = stackIndex + 1;
        int shouldBeIncremented = Math.min(stackSize, k);
        for (int i = 0; i < shouldBeIncremented; i++) {
            stack[i] += val;
        }
    }

    private boolean isEmpty() {
        return stackIndex == -1;
    }

    private boolean isFull() {
        return stackIndex == maxSizeMinusOne;
    }
}
