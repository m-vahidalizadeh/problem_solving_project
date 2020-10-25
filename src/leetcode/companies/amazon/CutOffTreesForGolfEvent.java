package leetcode.companies.amazon;

import javafx.util.Pair;

import java.util.*;

/**
 * 675. Cut Off Trees for Golf Event
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 * In one step you can walk in any of the four directions top, bottom, left and right also when standing in a point which is a tree you can decide whether or not to cut off the tree.
 *
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,4],
 *  [7,6,5]
 * ]
 * Output: 6
 *
 * Example 2:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,0],
 *  [7,6,5]
 * ]
 * Output: -1
 *
 * Example 3:
 *
 * Input:
 * [
 *  [2,3,4],
 *  [0,0,5],
 *  [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 *
 * Constraints:
 *
 * 1 <= forest.length <= 50
 * 1 <= forest[i].length <= 50
 * 0 <= forest[i][j] <= 10^9
 */
public class CutOffTreesForGolfEvent {

    public class Point {
        Pair<Integer, Integer> pair;
        int h;

        public Point(int i, int j, int h) {
            this.pair = new Pair<>(i, j);
            this.h = h;
        }
    }

    int[][] directions;

    public int cutOffTree(List<List<Integer>> forest) {
        directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        List<Point> trees = new ArrayList<>();
        int n = forest.size();
        int m = forest.get(0).size();
        int[][] forestArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            List<Integer> row = forest.get(i);
            for (int j = 0; j < m; j++) {
                int h = row.get(j);
                forestArr[i][j] = h;
                if (h > 1) trees.add(new Point(i, j, h));
            }
        }
        trees.sort((a, b) -> a.h - b.h);
        int size = trees.size();
        if (size == 0) return 0;
        Point currPoint = trees.get(0);
        int steps = findSteps(forestArr, new Pair<Integer, Integer>(0, 0), currPoint.pair, n, m);
        for (int i = 1; i < size; i++) {
            Point nextPoint = trees.get(i);
            int tempSteps = findSteps(forestArr, currPoint.pair, nextPoint.pair, n, m);
            if (tempSteps == -1) return -1;
            steps += tempSteps;
            currPoint = nextPoint;
        }
        return steps;
    }

    private int findSteps(int[][] forestArr, Pair<Integer, Integer> s, Pair<Integer, Integer> e, int n, int m) {
        Pair<Integer, Integer> curr;
        Queue<Pair<Integer, Integer>> currQ = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        int steps = -1;
        if (s.equals(e)) return 0;
        currQ.add(s);
        while (!currQ.isEmpty()) {
            steps++;
            Queue<Pair<Integer, Integer>> nextQ = new LinkedList<>();
            while (!currQ.isEmpty()) {
                curr = currQ.poll();
                if (visited.contains(curr)) continue;
                if (curr.equals(e)) return steps;
                visited.add(curr);
                for (int[] dir : directions) {
                    int newX = curr.getKey() + dir[0];
                    int newY = curr.getValue() + dir[1];
                    if (newX < 0 || newX >= n || newY < 0 || newY >= m || forestArr[newX][newY] == 0) continue;
                    nextQ.add(new Pair<>(newX, newY));
                }
            }
            currQ = nextQ;
        }
        return -1;
    }

    public static void main(String[] args) {
        CutOffTreesForGolfEvent c = new CutOffTreesForGolfEvent();
        System.out.println(c.cutOffTree(List.of(
                List.of(1, 2, 3),
                List.of(0, 0, 4),
                List.of(7, 6, 5)
        )));
        System.out.println(c.cutOffTree(List.of(
                List.of(1, 2, 3),
                List.of(0, 0, 0),
                List.of(7, 6, 5)
        )));
        System.out.println(c.cutOffTree(List.of(
                List.of(2, 3, 4),
                List.of(0, 0, 5),
                List.of(8, 7, 6)
        )));
    }

}
