package leetcode.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 913. Cat and Mouse
 * A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 *
 * The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
 *
 * The mouse starts at node 1 and goes first, the cat starts at node 2 and goes second, and there is a hole at node 0.
 *
 * During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 *
 * Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 *
 * Then, the game can end in three ways:
 *
 * If ever the Cat occupies the same node as the Mouse, the Cat wins.
 * If ever the Mouse reaches the Hole, the Mouse wins.
 * If ever a position is repeated (i.e., the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
 * Given a graph, and assuming both players play optimally, return
 *
 * 1 if the mouse wins the game,
 * 2 if the cat wins the game, or
 * 0 if the game is a draw.
 *
 * Example 1:
 *
 * Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * Output: 0
 * Example 2:
 *
 * Input: graph = [[1,3],[0],[3],[0,2]]
 * Output: 1
 *
 * Constraints:
 *
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] is unique.
 * The mouse and the cat can always move.
 */
public class CatAndMouse {

    public int catMouseGame(int[][] graph) {
        int N = graph.length, DRAW = 0, MOUSE = 1, CAT = 2;
        int[][][] color = new int[50][50][3];
        int[][][] degree = new int[50][50][3];
        for (int m = 0; m < N; m++)
            for (int c = 0; c < N; c++) {
                degree[m][c][1] = graph[m].length;
                degree[m][c][2] = graph[c].length;
                for (int x : graph[c])
                    if (x == 0) {
                        degree[m][c][2]--;
                        break;
                    }
            }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++)
            for (int t = 1; t <= 2; t++) {
                color[0][i][t] = MOUSE;
                queue.add(new int[]{0, i, t, MOUSE});
                if (i > 0) {
                    color[i][i][t] = CAT;
                    queue.add(new int[]{i, i, t, CAT});
                }
            }
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int i = node[0], j = node[1], t = node[2], c = node[3];
            for (int[] parent : parents(graph, i, j, t)) {
                int i2 = parent[0], j2 = parent[1], t2 = parent[2];
                if (color[i2][j2][t2] == DRAW) {
                    if (t2 == c) {
                        color[i2][j2][t2] = c;
                        queue.add(new int[]{i2, j2, t2, c});
                    } else {
                        degree[i2][j2][t2]--;
                        if (degree[i2][j2][t2] == 0) {
                            color[i2][j2][t2] = 3 - t2;
                            queue.add(new int[]{i2, j2, t2, 3 - t2});
                        }
                    }
                }
            }
        }
        return color[1][2][1];
    }

    private List<int[]> parents(int[][] graph, int m, int c, int t) {
        List<int[]> ans = new ArrayList<>();
        if (t == 2) { // curr turn is cat and prev turn was mouse
            for (int m2 : graph[m]) ans.add(new int[]{m2, c, 3 - t});
        } else { // t==1 curr turn was mouse and prev turn was cat
            for (int c2 : graph[c]) if (c2 > 0) ans.add(new int[]{m, c2, 3 - t}); // c2==0 -> mouse wins already colored
        }
        return ans; // return the list of parents
    }

}
