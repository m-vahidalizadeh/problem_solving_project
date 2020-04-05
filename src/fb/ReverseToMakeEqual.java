package fb;

import java.util.HashMap;
import java.util.Map;

/**
 * Reverse to Make Equal
 * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.
 * Signature
 * String areTheyEqual(int[] arr_a, int[] arr_b)
 * Input
 * All integers in array are in the range [0, 1,000,000,000].
 * Output
 * Return "Yes" if B can be made equal to A, return "No" otherwise.
 * Example
 * A = [1, 2, 3, 4]
 * B = [1, 4, 3, 2]
 * output = true
 * After reversing the subarray of B from indices 1 to 3, array B will equal array A.
 */
public class ReverseToMakeEqual {

    private void updateFrequency(Map<Integer, Integer> frequencies, int x) {
        int newFrequency = 1;
        if (frequencies.containsKey(x)) {
            newFrequency = frequencies.get(x) + 1;
        }
        frequencies.put(x, newFrequency);
    }

    private boolean doTheyHaveSameFreqquencies(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        for (Map.Entry<Integer, Integer> aEntry : a.entrySet()) {
            if (!b.containsKey(aEntry.getKey()) || !b.get(aEntry.getKey()).equals(aEntry.getValue()))
                return false;
        }
        return true;
    }

    String areTheyEqual(int[] array_a, int[] array_b) {
        int n = array_a.length;
        Map<Integer, Integer> aFrequencies = new HashMap<>();
        Map<Integer, Integer> bFrequencies = new HashMap<>();
        for (int i = 0; i < n; i++) {
            updateFrequency(aFrequencies, array_a[i]);
            updateFrequency(bFrequencies, array_b[i]);
        }
        return doTheyHaveSameFreqquencies(aFrequencies, bFrequencies) ? "Yes" : "No";
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(String expected, String output) {
        boolean result = (expected.equals(output));
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

    void printInteger(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        String expected_1 = "Yes";
        String output_1 = areTheyEqual(array_a_1, array_b_1);
        check(expected_1, output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        String expected_2 = "No";
        String output_2 = areTheyEqual(array_a_2, array_b_2);
        check(expected_2, output_2);
        // Add your own test cases here
    }

    public static void main(String[] args) {
        new ReverseToMakeEqual().run();
    }

}
