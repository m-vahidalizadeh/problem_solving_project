package leetcode.mixed;

import java.util.HashSet;
import java.util.Set;

public class LetterTilePossibilities {

    Set<String> allPermutations = new HashSet<>();
    Set<String> allSubsetsOfPermutations = new HashSet<>();

    public static void main(String[] args) {
        /*
Example 1:
Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:
Input: "AAABBC"
Output: 188
         */
        LetterTilePossibilities letterTilePossibilities = new LetterTilePossibilities();
        String tiles = "AAABBC";
        System.out.format("Number of distinct permutation of %s is %d", tiles,
                letterTilePossibilities.numTilePossibilities(tiles));
    }

    public int numTilePossibilities(String tiles) {
        findPermutations(tiles.toCharArray(), 0, tiles.length());

        for (String solution : allPermutations) {
            for (int i = 1; i <= solution.length(); i++) {
                addSubsetsSizeK(solution.toCharArray(), i);
            }
        }
        return allSubsetsOfPermutations.size();
    }

    public void findPermutations(char[] str, int index, int n) {
        if (index >= n) {
            allPermutations.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < n; i++) {
            if (shouldSwap(str, index, i)) {
                swap(str, index, i);
                findPermutations(str, index + 1, n);
                swap(str, index, i);
            }
        }
    }

    public void addSubsetsSizeK(char[] input, int k) {
        for (int i = 0; i < input.length; i += k) {
            StringBuilder tempS = new StringBuilder();
            for (int j = i; j < j + k && j < input.length; j++) {
                tempS.append(input[j]);
            }
            allSubsetsOfPermutations.add(tempS.toString());
        }
    }

    public boolean shouldSwap(char[] str, int start, int current) {
        char currentChar = str[current];
        for (int i = start; i < current; i++) {
            if (str[i] == currentChar) {
                return false;
            }
        }
        return true;
    }

    public void swap(char[] str, int i, int j) {
        char tempChar = str[i];
        str[i] = str[j];
        str[j] = tempChar;
    }

}
