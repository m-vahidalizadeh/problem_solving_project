import java.util.*;

public class FBLongestArithmaticSequence {

    public static void main(String[] args) {
    /*
Example 1:
Input: [3,6,9,12]
Output: 4
Explanation:
The whole array is an arithmetic sequence with steps of length = 3.

Example 2:
Input: [9,4,7,2,10]
Output: 3
Explanation:
The longest arithmetic subsequence is [4,7,10].

Example 3:
Input: [20,1,15,3,10,5,8]
Output: 4
Explanation:
The longest arithmetic subsequence is [20,15,10,5].
     */
        FBLongestArithmaticSequence fbLongestArithmaticSequence = new FBLongestArithmaticSequence();
        int[] A = {3, 6, 9, 12};
        System.out.println(fbLongestArithmaticSequence.longestArithSeqLength(A));
        int[] B = {9, 4, 7, 2, 10};
        System.out.println(fbLongestArithmaticSequence.longestArithSeqLength(B));
        int[] C = {20, 1, 15, 3, 10, 5, 8};
    }

    public int longestArithSeqLength(int[] A) {
        int max = 2;
        int maxSeen = Integer.MIN_VALUE;
        int n = A.length;
        Map<Integer, PriorityQueue<Integer>> occurances = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (A[i] > maxSeen) {
                maxSeen = A[i];
            }
            if (occurances.containsKey(A[i])) {
                PriorityQueue<Integer> occurance = occurances.get(A[i]);
                occurance.add(i);
                occurances.put(A[i], occurance);
            } else {
                PriorityQueue<Integer> occurance = new PriorityQueue<>();
                occurance.add(i);
                occurances.put(A[i], occurance);
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = A[j] - A[i];
                int maxLOfProgression = getMaxLOfProgression(i, j, diff, n, occurances, A);
                if (max < maxLOfProgression) {
                    max = maxLOfProgression;
                }
            }
        }
        return max;
    }

    private int getMaxLOfProgression(int i, int j, int diff, int n, Map<Integer, PriorityQueue<Integer>> occurances, int[] A) {
        int length = 2;
        int index = j;
        boolean done = false;
        while (index < n && !done) {
            int target = A[index] + diff;
            if (occurances.containsKey(target)) {
                boolean found = false;
                PriorityQueue<Integer> o = new PriorityQueue<>(occurances.get(target));
                while (!o.isEmpty() && !found) {
                    int currO = o.poll();
                    if (currO > index) {
                        index = currO;
                        target += diff;
                        found = true;
                        length++;
                    }
                }
                if (!found) {
                    done = true;
                }
            } else {
                done = true;
            }
        }
        return length;
    }

}
