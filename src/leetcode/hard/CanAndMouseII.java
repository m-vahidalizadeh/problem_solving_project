package leetcode.hard;

/**
 * 1728. Cat and Mouse II
 * A game is played by a cat and a mouse named Cat and Mouse.
 *
 * The environment is represented by a grid of size rows x cols, where each element is a wall, floor, player (Cat, Mouse), or food.
 *
 * Players are represented by the characters 'C'(Cat),'M'(Mouse).
 * Floors are represented by the character '.' and can be walked on.
 * Walls are represented by the character '#' and cannot be walked on.
 * Food is represented by the character 'F' and can be walked on.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * Mouse and Cat play according to the following rules:
 *
 * Mouse moves first, then they take turns to move.
 * During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the grid.
 * catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.
 * Staying in the same position is allowed.
 * Mouse can jump over Cat.
 * The game can end in 4 ways:
 *
 * If Cat occupies the same position as Mouse, Cat wins.
 * If Cat reaches the food first, Cat wins.
 * If Mouse reaches the food first, Mouse wins.
 * If Mouse cannot get to the food within 1000 turns, Cat wins.
 * Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.
 *
 * Example 1:
 *
 * Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * Output: true
 * Explanation: Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
 * Example 2:
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * Output: true
 * Example 3:
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * Output: false
 * Example 4:
 *
 * Input: grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * Output: false
 * Example 5:
 *
 * Input: grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
 * Output: true
 *
 * Constraints:
 *
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] consist only of characters 'C', 'M', 'F', '.', and '#'.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * 1 <= catJump, mouseJump <= 8
 */
public class CanAndMouseII {

    Boolean[][][][][] cache;
    int n;
    int m;
    int catJump;
    int mouseJump;
    int[] food;
    int[][] directions;
    String[] grid;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length();
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 4 directions down,right,up,left
        int[] cat = null;
        int[] mouse = null;
        cache = new Boolean[n][m][n][m][n * m * 2 + 1]; // A cache for our dp (cat i, cat j, mouse i, mouse j,moves)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == 'C') cat = new int[]{i, j}; // Find cat
                if (grid[i].charAt(j) == 'M') mouse = new int[]{i, j}; // Find mouse
                if (grid[i].charAt(j) == 'F') food = new int[]{i, j}; // Find food
            }
        }
        return dp(cat, mouse, 0);
    }

    private boolean dp(int[] cat, int[] mouse, int moves) {
        if (cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves] != null)
            return cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves];
        if (cat[0] == mouse[0] && cat[1] == mouse[1]) return false; // Cat found the food
        if (cat[0] == food[0] && cat[1] == food[1]) return false; // Cat caught the mouse (same cell)
        if (moves == m * n * 2)
            return false; // If mouse can't win after m*n*2 total moves (cat+mouse), it can't win anymore
        if (mouse[0] == food[0] && mouse[1] == food[1]) return true; // Mouse found the food
        if (moves % 2 == 0) { // Mouse turn
            for (int[] dir : directions) { // Start jumping in 4 directions
                for (int jump = 0; jump <= mouseJump; jump++) { // Jump till you hit a wall or outside
                    int nI = mouse[0] + dir[0] * jump; // new I
                    int nJ = mouse[1] + dir[1] * jump; // new J
                    if (0 <= nI && nI < n && 0 <= nJ && nJ < m && grid[nI].charAt(nJ) != '#') {
                        if (dp(cat, new int[]{nI, nJ}, moves + 1)) { // Can mouse find a way that can win in it?
                            cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves] = true; // Fill cache
                            return true; // Mouse could win
                        }
                    } else break; // No more ways to explore
                }
            }
            cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves] = false; // Fill cache
            return false; // No way for mouse to win
        } else { // Cat turn
            for (int[] dir : directions) { // Start jumping in 4 directions
                for (int jump = 0; jump <= catJump; jump++) { // Jump till you end up in the same cell with mouse or hit a wall or outside
                    int nI = cat[0] + dir[0] * jump; // new I
                    int nJ = cat[1] + dir[1] * jump; // new J
                    if (0 <= nI && nI < n && 0 <= nJ && nJ < m && grid[nI].charAt(nJ) != '#') {
                        if (!dp(new int[]{nI, nJ}, mouse, moves + 1)) { // Can cat find a path that mouse looses in it?
                            cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves] = false; // Fill cache
                            return false; // Cat wins = mouse loose
                        }
                    } else break; // No more ways to explore
                }
            }
            cache[cat[0]][cat[1]][mouse[0]][mouse[1]][moves] = true; // Fill cache
            return true; // Mouse wins, no way for cat to defeat mouse
        }
    }

}
