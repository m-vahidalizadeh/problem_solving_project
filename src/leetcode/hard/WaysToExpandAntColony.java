package leetcode.hard;

import java.util.*;

/**
 * 1916. Count Ways to Build Rooms in an Ant Colony
 * You are an ant tasked with adding n new rooms numbered 0 to n-1 to your colony. You are given the expansion plan as a 0-indexed integer array of length n, prevRoom, where prevRoom[i] indicates that you must build room prevRoom[i] before building room i, and these two rooms must be connected directly. Room 0 is already built, so prevRoom[0] = -1. The expansion plan is given such that once all the rooms are built, every room will be reachable from room 0.
 *
 * You can only build one room at a time, and you can travel freely between rooms you have already built only if they are connected. You can choose to build any room as long as its previous room is already built.
 *
 * Return the number of different orders you can build all the rooms in. Since the answer may be large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: prevRoom = [-1,0,1]
 * Output: 1
 * Explanation: There is only one way to build the additional rooms: 0 → 1 → 2
 * Example 2:
 *
 * Input: prevRoom = [-1,0,0,1,2]
 * Output: 6
 * Explanation:
 * The 6 ways are:
 * 0 → 1 → 3 → 2 → 4
 * 0 → 2 → 4 → 1 → 3
 * 0 → 1 → 2 → 3 → 4
 * 0 → 1 → 2 → 4 → 3
 * 0 → 2 → 1 → 3 → 4
 * 0 → 2 → 1 → 4 → 3
 *
 * Constraints:
 *
 * n == prevRoom.length
 * 2 <= n <= 105
 * prevRoom[0] == -1
 * 0 <= prevRoom[i] < n for all 1 <= i < n
 * Every room is reachable from room 0 once all the rooms are built.
 */
public class WaysToExpandAntColony {

    int n;
    int MOD = 1_000_000_007;
    List<Integer>[] tree;
    int[] size;

    public int waysToBuildRooms(int[] prevRoom) {
        n = prevRoom.length;
        tree = new List[n];
        Arrays.setAll(tree, x -> new ArrayList<>());
        for (int i = 1; i < n; i++) tree[prevRoom[i]].add(i);
        size = new int[n];
        dfsSetSize(0);
        long nFact = 1;
        for (int i = 2; i <= n; i++) nFact = (nFact * i) % MOD;
        long childSizes = 1;
        for (int childSize : size) childSizes = (childSizes * childSize) % MOD;
        long inverse = getInverse(childSizes, MOD - 2);
        return (int) ((nFact * inverse) % MOD);
    }

    private int dfsSetSize(int root) {
        int res = 1; // the root itself
        for (int child : tree[root]) res += dfsSetSize(child);
        return size[root] = res;
    }

    private long getInverse(long x, long y) {
        if (y == 0) return 1;
        long p = getInverse(x, y / 2) % MOD;
        p = (p * p) % MOD;
        return y % 2 == 0 ? p : (x * p) % MOD;
    }

}
