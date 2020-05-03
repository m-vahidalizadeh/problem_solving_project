package hackerrank;

import java.util.*;

public class QHeap1 {

    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        // min heap insert
                        pq.add(scanner.nextInt());
                        break;
                    case 2:
                        // min heap remove
                        pq.remove(scanner.nextInt());
                        break;
                    case 3:
                        // print min
                        System.out.println(pq.peek());
                        break;
                }
            }
        }
    }
}