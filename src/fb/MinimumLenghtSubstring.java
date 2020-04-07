package fb;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Length Substrings
 * You are given two strings s and t. You can select any substring of string s and rearrange the characters of the selected substring. Determine the minimum length of the substring of s such that string t is a substring of the selected substring.
 * Signature
 * int minLengthSubstring(String s, String t)
 * Input
 * s and t are non-empty strings that contain less than 1,000,000 characters each
 * Output
 * Return the minimum length of the substring of s. If it is not possible, return -1
 * Example
 * s = "dcbefebce"
 * t = "fd"'
 * output = 5
 * Explanation:
 * Substring "dcbef" can be rearranged to "cfdeb", "cefdb", and so on. String t is a substring of "cfdeb". Thus, the minimum length required is 5.
 */
public class MinimumLenghtSubstring {

    private void updateTFreq(Map<Character, Integer> tFreq, int i, char tCharI) {
        if (tFreq.containsKey(tCharI)) {
            tFreq.put(tCharI, tFreq.get(tCharI) + 1);
        } else {
            tFreq.put(tCharI, 1);
        }
    }

    private int getSubLength(char[] sChars, int i, Map<Character, Integer> tFreq, int tN, int sN) {
        Map<Character, Integer> tempTFreq = new HashMap<>(tFreq);
        int strLength = 0;
        int matches = 0;
        for (int j = i; j < sN; j++) {
            strLength++;
            if (tempTFreq.containsKey(sChars[j])) {
                matches++;
                if (matches == tN) {
                    return strLength;
                }
                int freq = tempTFreq.get(sChars[j]);
                freq--;
                if (freq == 0) {
                    tempTFreq.remove(sChars[j]);
                } else {
                    tempTFreq.put(sChars[j], freq);
                }
            }
        }
        return -1;
    }

    int minLengthSubstring(String s, String t) {
        int sN = s.length();
        int tN = t.length();
        if (sN < tN)
            return 0;
        Map<Character, Integer> tFreq = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tN; i++) {
            updateTFreq(tFreq, i, tChars[i]);
        }
        int minSubLength = -1;
        for (int i = 0; i < sN; i++) {
            if (tFreq.containsKey(sChars[i])) {
                int subLength = getSubLength(sChars, i, tFreq, tN, sN);
                if (minSubLength == -1 && subLength != -1) {
                    minSubLength = subLength;
                } else {
                    if (subLength != -1 && subLength < minSubLength) {
                        minSubLength = subLength;
                    }
                }
            }
        }
        return minSubLength;
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

    public void run() throws IOException {
        String s_1 = "dcbefebce";
        String t_1 = "fd";
        int expected_1 = 5;
        int output_1 = minLengthSubstring(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
        String t_2 = "cbccfafebccdccebdd";
        int expected_2 = -1;
        int output_2 = minLengthSubstring(s_2, t_2);
        check(expected_2, output_2);
    }

    public static void main(String[] args) throws IOException {
        new MinimumLenghtSubstring().run();
    }

}
