package hackerrank;

import java.util.Scanner;

/**
 * Fibonacci with recursion.
 */
public class FibonacciRecursion {

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(fibonacci(n));
        }
    }

}
