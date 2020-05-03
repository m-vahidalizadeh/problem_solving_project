package hackerrank;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib(12));
    }

    /**
     * Calculate the Fibonacci of n with two int variables and one temp variable. This is using dynamic programming.
     * Time complexity of this approach is O(n). Space complexity of this approach is O(k).
     * 0,1,1,2,3,5,8,13,21,34,55,89,144,...
     *
     * @param n The nth Fibonacci number
     * @return The nth Fibonacci
     */
    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        // Element 1
        int element1 = 0;
        // Element 2
        int element2 = 1;
        // Move the 2 elements window till one index before the nth.
        for (int i = 2; i < n; i++) {
            // Move the 2 elements window forward.
            int temp = element2;
            element2 = element1 + element2;
            element1 = temp;
        }
        // Return the nth Fibonacci by summing Fibonacci n-1 and n-2
        return element1 + element2;
    }

}
