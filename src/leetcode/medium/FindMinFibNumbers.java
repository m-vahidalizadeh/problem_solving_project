package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 * <p>
 * The Fibonacci numbers are defined as:
 * <p>
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 * <p>
 * Example 1:
 * <p>
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * Example 2:
 * <p>
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 * <p>
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 10^9
 */
public class FindMinFibNumbers {

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibs = getFibs(k);
        int count = 0;
        int i = fibs.size() - 1;
        while (k > 0) {
            int fibI = fibs.get(i--);
            count += k / fibI;
            k %= fibI;
        }
        return count;
    }

    private List<Integer> getFibs(int k) {
        List<Integer> fibs = new ArrayList<>();
        fibs.add(1);
        fibs.add(1);
        if (k <= 2) return fibs;
        int a = 1;
        int b = 1;
        while (b <= k) {
            int temp = a + b;
            a = b;
            b = temp;
            fibs.add(b);
        }
        return fibs;
    }

    public static void main(String[] args) {
        FindMinFibNumbers f = new FindMinFibNumbers();
        System.out.println(f.findMinFibonacciNumbers(7));
        System.out.println(f.findMinFibonacciNumbers(10));
        System.out.println(f.findMinFibonacciNumbers(19));
    }

}
