package hackerrank;

import java.util.Scanner;

public class FibonacciDP {

    public static int fibonacci(int n) {
        int[] fibonacciNumbers = new int[n + 1];
        fibonacciNumbers[0] = 0;
        fibonacciNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciNumbers[i] = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
        }
        return fibonacciNumbers[n];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(fibonacci(n));
        }
    }

}
