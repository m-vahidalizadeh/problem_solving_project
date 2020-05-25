package leetcode.companies.facebook;

import java.util.Stack;

/**
 * Minimizing Permutations
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as
 * (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly
 * making the following operation:
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 * Signature
 * int minOperations(int[] arr)
 * Input
 * Size N is between 1 and 8
 * Array arr is a permutation of all integers from 1 to N
 * Output
 * An integer denoting the minimum number of operations required to arrange the permutation in increasing order
 * Example
 * If N = 3, and P = (3, 1, 2), we can do the following operations:
 * Select (1, 2) and reverse it: P = (3, 2, 1).
 * Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 * output = 2
 */
public class MinimizingPermutations {

    int minOperations(int[] arr) {
        int maxDecStart = 0;
        int maxDecEnd = 0;
        int maxLength = 0;
        int operations = 0;
        int n = arr.length;
        while (true) {
            // Find the sub
            for (int i = 0; i < n; i++) {
                int tempLength = 0;
                int j = i + 1;
                while (j < n && arr[j - 1] > arr[j]) {
                    tempLength++;
                    j++;
                }
                if (tempLength > maxLength) {
                    maxDecStart = i;
                    maxDecEnd = j;
                    maxLength = tempLength;
                }
            }
            if (maxLength == 0) {
                // The array is sorted
                return operations;
            }
            // Reverse the sub
            operations++;
            Stack<Integer> s = new Stack<>();
            for (int i = maxDecStart; i < maxDecEnd; i++) {
                s.push(arr[i]);
            }
            for (int i = maxDecStart; i < maxDecEnd; i++) {
                arr[i] = s.pop();
            }
            // Reset
            maxLength = 0;
            maxDecStart = 0;
            maxDecEnd = 0;
        }
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {

        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);
    }

    public static void main(String[] args) {
        new MinimizingPermutations().run();
    }

}
