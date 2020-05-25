package leetcode.companies.facebook;

/**
 * Slow Sums
 * Suppose we have a list of N numbers, and repeat the following operation until we're left with only a single number: Choose any two consecutive numbers and replace them with their sum. Moreover, we associate a penalty with each operation equal to the value of the new number, and call the penalty for the entire list as the sum of the penalties of each operation.
 * For example, given the list [1, 2, 3, 4, 5], we could choose 2 and 3 for the first operation, which would transform the list into [1, 5, 4, 5] and incur a penalty of 5. The goal in this problem is to find the worst possible penalty for a given input.
 * Signature:
 * int getTotalTime(int[] arr)
 * Input:
 * An array arr containing N integers, denoting the numbers in the list.
 * Output format:
 * An int representing the worst possible total penalty.
 * Constraints:
 * 1 ≤ T ≤ 102
 * 1 ≤ N ≤ 106
 * 1 ≤ Ai ≤ 107, where *Ai denotes the ith initial element of an array.
 * The sum of values of N over all test cases will not exceed 5 * 106.
 * Example
 * arr = [4, 2, 1, 3]
 * output = 23
 * First, add 4 + 2 for a penalty of 6. Now the array is [6, 1, 3]
 * Add 6 + 1 for a penalty of 7. Now the array is [7, 3]
 * Add 7 + 3 for a penalty of 10. The penalties sum to 23.
 */
public class SlowSums {

    // Add any helper functions you may need here

    int getTotalTime(int[] arr) {
        int n = arr.length;
        boolean[] merged = new boolean[n];
        int mergedNum = 0;
        int penaltySum = 0;

        while (mergedNum < n - 1) {
            int max = Integer.MIN_VALUE;
            int maxI = 0;
            int maxJ = 1;
            for (int i = 0; i < n - 1; i++) {
                int j = i + 1;
                while (j < n && merged[j]) {
                    j++;
                }
                if (j == n)
                    continue;
                if (merged[i] || merged[j])
                    continue;
                int tempPenalty = arr[i] + arr[j];
                if (tempPenalty > max) {
                    max = tempPenalty;
                    maxI = i;
                    maxJ = j;
                }
            }
            merged[maxI] = true;
            arr[maxJ] = max;
            penaltySum += max;
            mergedNum++;
        }

        return penaltySum;
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
        int[] arr_1 = {4, 2, 1, 3};
        int expected_1 = 23;
        int output_1 = getTotalTime(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 3, 9, 8, 4};
        int expected_2 = 88;
        int output_2 = getTotalTime(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new SlowSums().run();
    }

}
