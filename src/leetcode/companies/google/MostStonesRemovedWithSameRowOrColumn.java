package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Most Stones Removed with Same Row or Column
 * <p>
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 * <p>
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * <p>
 * What is the largest possible number of moves we can make?
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */
public class MostStonesRemovedWithSameRowOrColumn {

    public class UF {
        int[] parent;

        public UF(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            while (x != parent[x]) x = parent[x];
            return x;
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public int removeStones(int[][] stones) {
        UF uf = new UF(20_000);
        for (int[] stone : stones) uf.union(stone[0], stone[1] + 10_000);
        Set<Integer> seen = new HashSet<>();
        for (int[] stone : stones) seen.add(uf.find(stone[0]));
        return stones.length - seen.size();
    }

}
