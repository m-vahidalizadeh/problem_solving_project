package hackerrank;

import java.util.*;

public class PartitionLabels {

    public static void main(String[] args) {
/*
Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */
        PartitionLabels partitionLabels = new PartitionLabels();
        String S = "ababcbacadefegdehijhklij";
        List<Integer> solutions = partitionLabels.partitionLabels(S);
        for (Integer s : solutions) {
            System.out.print(s + " ");
        }
    }

    public List<Integer> partitionLabels(String S) {
        int n = S.length();
        if (n == 0) {
            return Collections.emptyList();
        }
        char[] sChars = S.toCharArray();
        Map<Character, Integer> latestSeen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            latestSeen.put(sChars[i], i);
        }
        int index = 0;
        List<Integer> solutions = new LinkedList<>();
        while (index < n) {
            int end = getEnd(index, sChars, latestSeen);
            solutions.add(end - index + 1);
            index = end + 1;
        }
        return solutions;
    }

    public int getEnd(int index, char[] sChars, Map<Character, Integer> latestSeen) {
        int possibleEnd = latestSeen.get(sChars[index]);
        Set<Character> currentPartition = new HashSet<>();
        while (index <= possibleEnd) {
            char currentChar = sChars[index];
            if (!currentPartition.contains(currentChar)) {
                currentPartition.add(currentChar);
                int newEnding = latestSeen.get(currentChar);
                if (newEnding > possibleEnd) {
                    possibleEnd = newEnding;
                }
            }
            index++;
        }
        return possibleEnd;
    }

}
