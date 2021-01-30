package leetcode.hard;

import java.util.PriorityQueue;

/**
 * 499. The Maze III
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.
 *
 * Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.
 *
 * Example 1:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
 *
 * Output: "lul"
 *
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
 *
 * Example 2:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 *
 * Output: "impossible"
 *
 * Explanation: The ball cannot reach the hole.
 *
 * Note:
 *
 * There is only one ball and one hole in the maze.
 * Both the ball and hole exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
 */
public class MazeIII {

    public class Point {
        int row;
        int col;
        int dist;
        String path;

        public Point(int row, int col, int dist, String path) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.path = path;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.dist == b.dist ? a.path.compareTo(b.path) : a.dist - b.dist);
        pq.add(new Point(ball[0], ball[1], 0, ""));
        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        char[] chs = {'d', 'l', 'r', 'u'};
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (curr.row == hole[0] && curr.col == hole[1]) return curr.path;
            if (visited[curr.row][curr.col]) continue;
            visited[curr.row][curr.col] = true;
            for (int i = 0; i < 4; i++) {
                int[] dir = dirs[i];
                int r = curr.row;
                int c = curr.col;
                int d = curr.dist;
                String p = curr.path;
                while (r + dir[0] >= 0 && r + dir[0] < n && c + dir[1] >= 0 && c + dir[1] < m && maze[r + dir[0]][c + dir[1]] != 1) {
                    r += dir[0];
                    c += dir[1];
                    d++;
                    if (r == hole[0] && c == hole[1]) break;
                }
                if (!visited[r][c]) pq.add(new Point(r, c, d, p + chs[i]));
            }
        }
        return "impossible";
    }

}
