package leetcode.fb;

import java.util.*;

/**
 * Seating Arrangements
 * There are n guests attending a dinner party, numbered from 1 to n. The ith guest has a height of arr[i] inches.
 * The guests will sit down at a circular table which has n seats, numbered from 1 to n in clockwise order around
 * the table. As the host, you will choose how to arrange the guests, one per seat. Note that there are n! possible
 * permutations of seat assignments.
 * Once the guests have sat down, the awkwardness between a pair of guests sitting in adjacent seats is defined as
 * the absolute difference between their two heights. Note that, because the table is circular, seats 1 and n are
 * considered to be adjacent to one another, and that there are therefore n pairs of adjacent guests.
 * The overall awkwardness of the seating arrangement is then defined as the maximum awkwardness of any pair of
 * adjacent guests. Determine the minimum possible overall awkwardness of any seating arrangement.
 * Signature
 * int minOverallAwkwardness(int[] arr)
 * Input
 * n is in the range [3, 1000].
 * Each height arr[i] is in the range [1, 1000].
 * Output
 * Return the minimum achievable overall awkwardness of any seating arrangement.
 * Example
 * n = 4
 * arr = [5, 10, 6, 8]
 * output = 4
 * If the guests sit down in the permutation [3, 1, 4, 2] in clockwise order around the table (having
 * heights [6, 5, 8, 10], in that order), then the four awkwardnesses between pairs of adjacent guests will
 * be |6-5| = 1, |5-8| = 3, |8-10| = 2, and |10-6| = 4, yielding an overall awkwardness of 4. It's impossible
 * to achieve a smaller overall awkwardness.
 */
public class SeatingArrangements {

    int minOverallAwkwardness(int[] arr) {
        int n = arr.length;
        if (n == 2) {
            return Math.abs(arr[0] - arr[1]);
        }
        Arrays.sort(arr);
        int[] seats = new int[n];
        seats[0] = arr[0];
        int left = n - 1;
        int right = 1;
        seats[right] = arr[1];
        seats[left] = arr[2];
        int max = Math.max(Math.abs(seats[left] - seats[0]), Math.abs(seats[right] - seats[0]));
        for (int i = 3; i < n - 1; i += 2) {
            // Place i and i+1
            int eI = arr[i];
            int eIPlusOne = arr[i + 1];
            int o1 = Math.max(Math.abs(seats[left] - arr[i]), Math.abs(seats[right] - arr[i + 1]));
            int o2 = Math.max(Math.abs(seats[left] - arr[i + 1]), Math.abs(seats[right] - arr[i]));
            left--;
            right++;
            if (o1 < o2) {
                seats[left] = arr[i];
                seats[right] = arr[i + 1];
                if (o1 > max) {
                    max = o1;
                }
            } else {
                seats[left] = arr[i];
                seats[right] = arr[i + 1];
                if (o2 > max) {
                    max = o2;
                }
            }
        }
        // If n is even
        if (n % 2 == 0) {
            // Place the last element
            int cost = Math.max(Math.abs(arr[n - 1] - seats[left]), Math.abs(arr[n - 1] - seats[right]));
            left--;
            seats[left] = arr[n - 1];
            if (cost > max) {
                max = cost;
            }

        }
        return max;
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
        int[] arr_1 = {5, 10, 6, 8};
        int expected_1 = 4;
        int output_1 = minOverallAwkwardness(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {1, 2, 5, 3, 7};
        int expected_2 = 4;
        int output_2 = minOverallAwkwardness(arr_2);
        check(expected_2, output_2);

    }

    public static void main(String[] args) {
        new SeatingArrangements().run();
    }

}
