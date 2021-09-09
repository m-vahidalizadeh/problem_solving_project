package leetcode.hard;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 587. Erect the Fence
 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 *
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
 *
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 *
 * Example 1:
 *
 * Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
 * Example 2:
 *
 * Input: points = [[1,2],[2,2],[4,2]]
 * Output: [[4,2],[2,2],[1,2]]
 *
 * Constraints:
 *
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * All the given points are unique.
 */
public class ErectTheFence {

    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) return trees; // only one triangle exists
        int leftMostIdx = 0;
        for (int i = 0; i < n; i++) {
            int[] tree = trees[i];
            if (tree[0] < trees[leftMostIdx][0]) leftMostIdx = i;
        }
        Set<Pair<Integer, Integer>> ans = new HashSet<>();
        int curr = leftMostIdx;
        while (true) {
            int candidate = (curr + 1) % n;
            for (int i = 0; i < n; i++) { // Find the most clockwise
                if (isMoreClockwise(trees[curr], trees[candidate], trees[i]) > 0) candidate = i;
            }
            for (int i = 0; i < n; i++) { // Add all the trees that are the most clockwise
                if (isMoreClockwise(trees[curr], trees[candidate], trees[i]) == 0)
                    ans.add(new Pair(trees[i][0], trees[i][1]));
            }
            curr = candidate;
            if (curr == leftMostIdx) break;
        }
        int[][] res = new int[ans.size()][2];
        int i = 0;
        for (Pair<Integer, Integer> tree : ans) {
            res[i][0] = tree.getKey();
            res[i][1] = tree.getValue();
            i++;
        }
        return res;
    }

    private int isMoreClockwise(int[] a, int[] b, int[] c) { // The dot product of ac and ab vectors
        return (c[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (c[1] - a[1]); // if positive ac is more clockwise
    }

}
