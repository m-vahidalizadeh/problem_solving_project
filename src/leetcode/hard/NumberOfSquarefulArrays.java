package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 996. Number of Squareful Arrays
 * Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
 * <p>
 * Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
 * <p>
 * Example 1:
 * <p>
 * Input: [1,17,8]
 * Output: 2
 * Explanation:
 * [1,8,17] and [17,8,1] are the valid permutations.
 * Example 2:
 * <p>
 * Input: [2,2,2]
 * Output: 1
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 12
 * 0 <= A[i] <= 1e9
 */
public class NumberOfSquarefulArrays {

    Map<Integer, List<Integer>> graph;
    Integer[][] dp;
    int allVisitedMask;

    public int numSquarefulPerms(int[] A) {
        graph = new HashMap<>();
        int n = A.length;
        dp = new Integer[n][1 << n]; // dimension 1: n nodes dimension 2: a mask of n bits (visited)
        allVisitedMask = ((1 << n) - 1);
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int i = 0; i < n; i++) { // Build the graph (adjacency list)
            for (int j = i + 1; j < n; j++) {
                if (isPerfectSquare(A[i], A[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        int counter = 0;
        int maxRepetition = 0;
        for (int i = 0; i < n; i++) counter += dfs(i, 1 << i);
        Map<Integer, Integer> repetitions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int newRepetition = repetitions.getOrDefault(A[i], 0) + 1;
            repetitions.put(A[i], newRepetition);
            maxRepetition = Math.max(maxRepetition, newRepetition);
        }
        int[] facts = getFactMap(maxRepetition); // Calculate facts for the repetitions. ans/fact(rep) ex. 2,2,2-> ans/3!
        for (int repetition : repetitions.values()) counter = counter / facts[repetition];
        return counter;
    }

    private int dfs(int currNode, int visitedMask) {
        if (visitedMask == allVisitedMask) return 1; // All nodes are visited.
        if (dp[currNode][visitedMask] != null) return dp[currNode][visitedMask]; // The answer exists in the memory.
        int counter = 0;
        for (int n : graph.get(currNode)) {
            if (((visitedMask >> n) & 1) == 0) { // The n is not visited yet.
                counter += dfs(n, visitedMask | (1 << n)); // mark n as visited and dfs it.
            }
        }
        dp[currNode][visitedMask] = counter;
        return counter;
    }

    private boolean isPerfectSquare(int a, int b) {
        double sumSqrt = Math.sqrt(a + (double) b);
        return sumSqrt == Math.floor(sumSqrt);
    }

    private int[] getFactMap(int max) {
        int[] facts = new int[max + 1];
        facts[0] = 1;
        for (int i = 1; i <= max; i++) facts[i] = i * facts[i - 1];
        return facts;
    }

    public static void main(String[] args) {
        NumberOfSquarefulArrays n = new NumberOfSquarefulArrays();
        System.out.println(n.numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println(n.numSquarefulPerms(new int[]{2, 2, 2}));
        System.out.println(n.numSquarefulPerms(new int[]{1, 1}));
    }

}
