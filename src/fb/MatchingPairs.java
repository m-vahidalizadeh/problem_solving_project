package fb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Matching Pairs
 * Given two strings s and t of length N, find the maximum number of possible matching pairs in strings s and t after swapping exactly two characters within s.
 * A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at the ith and jth index of s, respectively. The matching pairs of the two strings are defined as the number of indices for which s[i] and t[i] are equal.
 * Note: This means you must swap two characters at different indices.
 * Signature
 * int matchingPairs(String s, String t)
 * Input
 * s and t are strings of length N
 * N is between 1 and 1,000,000
 * Output
 * Return an integer denoting the maximum number of matching pairs
 * Example
 * s = "abcd"
 * t = "adcb"
 * output = 4
 * Explanation:
 * Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
 * Therefore, the number of matching pairs of s and t will be 4.
 */
public class MatchingPairs {

    private void updateOccurances(Map<Character, Set<Integer>> sCharOccurance, int i, char sCharI) {
        if (sCharOccurance.containsKey(sCharI)) {
            Set<Integer> newOccurances = sCharOccurance.get(sCharI);
            newOccurances.add(i);
            sCharOccurance.put(sCharI, newOccurances);
        } else {
            Set<Integer> newOccurances = new HashSet<>();
            newOccurances.add(i);
            sCharOccurance.put(sCharI, newOccurances);
        }
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    int matchingPairs(String s, String t) {
        Map<Character, Set<Integer>> sCharOccurances = new HashMap<>();
        Set<Integer> tShouldBeFixedIndexes = new HashSet<>();
        Set<Integer> tFixedOccurances = new HashSet<>();
        int existingMatching = 0;
        int n = s.length();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < n; i++) {
            char sCharI = sChars[i];
            char tCharI = tChars[i];
            updateOccurances(sCharOccurances, i, sCharI);
            if (sCharI == tCharI) {
                existingMatching++;
                tFixedOccurances.add(i);
            } else {
                tShouldBeFixedIndexes.add(i);
            }
        }
        int budget = 2;
        if (tShouldBeFixedIndexes.size() == 0)
            return existingMatching - budget;
        for (int i : tShouldBeFixedIndexes) {
            if (sCharOccurances.containsKey(tChars[i])) {
                Set<Integer> sCharOccurance = sCharOccurances.get(tChars[i]);
                for (Integer sO : sCharOccurance) {
                    if (!tFixedOccurances.contains(sO)) {
                        existingMatching++;
                        budget--;
                        if (budget == 0) {
                            return existingMatching;
                        }
                        swap(tChars, i, sO);
                    }
                }
            }
        }
        return existingMatching;
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
        String s_1 = "abcde";
        String t_1 = "adcbe";
        int expected_1 = 5;
        int output_1 = matchingPairs(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String t_2 = "abcd";
        int expected_2 = 2;
        int output_2 = matchingPairs(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new MatchingPairs().run();
    }

}
