import java.util.Arrays;

public class BLCustomStack {

    private int[] stack;
    private int index;
    private int maxIndex;
    private int max;

    public static void main(String[] args) {
        // Sample 1
        BLCustomStack stack = new BLCustomStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top()); // -> 5
        System.out.println(stack.popMax()); // -> 5
        System.out.println(stack.top()); // -> 1
        System.out.println(stack.peekMax()); // -> 5
        System.out.println(stack.pop()); // -> 1
        System.out.println(stack.top()); // -> 5

        // Sample 2
        stack = new BLCustomStack();
        stack.push(5);
        stack.push(1);
        System.out.println(stack.popMax());
        System.out.println(stack.peekMax());
    }

    public BLCustomStack() {
        stack = new int[100];
        Arrays.fill(stack, Integer.MIN_VALUE);
        index = -1;
        maxIndex = -1;
        max = Integer.MIN_VALUE;
    }

    public void push(int x) {
        index++;
        stack[index] = x;
        if (x >= max) {
            maxIndex = index;
            max = x;
        }
    }

    public int pop() {
        int result = stack[index];
        stack[index] = Integer.MIN_VALUE;
        index--;
        // Replace max
        int secondMax = Integer.MIN_VALUE;
        int secondMaxIndex = -1;
        for (int i = index; i >= 0; i--) {
            if (stack[i] > secondMax) {
                secondMaxIndex = i;
                secondMax = stack[i];
            }
        }
        max = secondMax;
        maxIndex = secondMaxIndex;
        return result;
    }

    public int top() {
        return stack[index];
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        int result = max;
        // Remove max
        if (maxIndex == index) {
            stack[index] = 0;
            index--;
        } else {
            for (int i = maxIndex; i < index; i++) {
                stack[i] = stack[i + 1];
            }
            stack[index] = Integer.MIN_VALUE;
            index--;
        }
        // Replace max
        int secondMax = Integer.MIN_VALUE;
        int secondMaxIndex = -1;
        for (int i = index; i >= 0; i--) {
            if (stack[i] > secondMax) {
                secondMaxIndex = i;
                secondMax = stack[i];
            }
        }
        max = secondMax;
        maxIndex = secondMaxIndex;
        // Return max
        return result;
    }

}
