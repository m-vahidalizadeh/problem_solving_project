package hackerrank;

import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {

    static Stack<Integer> s1 = new Stack<>();
    static Stack<Integer> s2 = new Stack<>();

    public static void main(String[] args) {
        /*
Sample Input
10
1 42
2
1 14
3
1 28
3
1 60
1 78
2
2
Sample Output
14
14
         */
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                int command = scanner.nextInt();
                if (command == 1) {
                    int val = scanner.nextInt();
                    enqueue(val);
                } else if (command == 2) {
                    dequeue();
                } else {
                    print();
                }
            }
        }
    }

    public static void enqueue(int val) {
        s1.push(val);
    }

    public static void dequeue() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        s2.pop();
    }

    public static void print() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        System.out.println(s2.peek());
    }

}
