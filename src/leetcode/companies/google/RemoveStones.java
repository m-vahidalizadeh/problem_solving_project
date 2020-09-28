package leetcode.companies.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Most Stones Removed with Same Row or Column
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
public class RemoveStones {

    public int removeStones(int[][] stones) {
        UF uf = new UF();
        for (int[] stone : stones) uf.join(stone[0], stone[1] + 10_000);
        Set<Integer> components = new HashSet<>();
        for (int[] stone : stones) {
            components.add(uf.find(stone[0]));
            components.add(uf.find(stone[1] + 10_000));
        }
        return stones.length - components.size();
    }

    public class UF {
        Map<Integer, Integer> parents;

        public UF() {
            parents = new HashMap<>();
        }

        public int find(int x) {
            while (parents.containsKey(x)) x = parents.get(x);
            return x;
        }

        public void join(int x, int y) {
            int xR = find(x);
            int yR = find(y);
            if (xR != yR) parents.put(xR, yR);
        }
    }

    public static void main(String[] args) {
        RemoveStones r = new RemoveStones();
        System.out.println(r.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(r.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
        System.out.println(r.removeStones(new int[][]{{0, 0}}));
        System.out.println(r.removeStones(new int[][]{{0, 1}, {1, 0}}));
    }

}
