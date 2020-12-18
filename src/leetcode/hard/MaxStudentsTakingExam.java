package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1349. Maximum Students Taking Exam
 * Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
 *
 * Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..
 *
 * Students must be placed in seats in good condition.
 *
 * Example 1:
 *
 * Input: seats = [["#",".","#","#",".","#"],
 *                 [".","#","#","#","#","."],
 *                 ["#",".","#","#",".","#"]]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
 * Example 2:
 *
 * Input: seats = [[".","#"],
 *                 ["#","#"],
 *                 ["#","."],
 *                 ["#","#"],
 *                 [".","#"]]
 * Output: 3
 * Explanation: Place all students in available seats.
 *
 * Example 3:
 *
 * Input: seats = [["#",".",".",".","#"],
 *                 [".","#",".","#","."],
 *                 [".",".","#",".","."],
 *                 [".","#",".","#","."],
 *                 ["#",".",".",".","#"]]
 * Output: 10
 * Explanation: Place students in available seats in column 1, 3 and 5.
 *
 * Constraints:
 *
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 */
public class MaxStudentsTakingExam {

    int n;
    int m;
    int[][] available;
    int[][] directionsCheck;
    int size;

    public int maxStudents(char[][] seats) {
        this.n = seats.length;
        this.m = seats[0].length;
        List<int[]> availableList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (seats[i][j] == '.') availableList.add(new int[]{i, j});
            }
        }
        int max = 0;
        size = availableList.size();
        this.directionsCheck = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
        available = new int[size][2];
        for (int i = 0; i < size; i++) available[i] = availableList.get(i);
        for (int i = 0; i < size; i++) {
            char[][] grid = getClone(seats);
            max = Math.max(max, arrange(grid, available[i][0], available[i][1]));
        }
        return max;
    }

    private int arrange(char[][] grid, int i, int j) {
        if (grid[i][j] == 's') return 0;
        int seated = canSeat(grid, i, j) ? 1 : 0;
        if (seated == 0) return 0;
        grid[i][j] = 's';
        for (int k = 0; k < size; k++) seated += arrange(grid, available[k][0], available[k][1]);
        return seated;
    }

    private boolean canSeat(char[][] grid, int i, int j) {
        for (int[] dir : directionsCheck) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
            if (grid[newI][newJ] == 's') return false;
        }
        return true;
    }

    private char[][] getClone(char[][] seats) {
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) grid[i] = seats[i].clone();
        return grid;
    }

    public static void main(String[] args) {
        MaxStudentsTakingExam m = new MaxStudentsTakingExam();
        System.out.println(m.maxStudents(new char[][]{{'#', '.', '#', '#', '.', '#'},
                {'.', '#', '#', '#', '#', '.'},
                {'#', '.', '#', '#', '.', '#'}}));
    }

}
