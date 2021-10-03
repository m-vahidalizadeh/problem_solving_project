package leetcode.companies.google;

/**
 * 1320. Minimum Distance to Type a Word Using Two Fingers
 * You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.
 *
 * For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
 * Given the string word, return the minimum total distance to type such string using only two fingers.
 *
 * The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
 *
 * Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
 *
 * Example 1:
 *
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 * Example 2:
 *
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation:
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 * Example 3:
 *
 * Input: word = "NEW"
 * Output: 3
 * Example 4:
 *
 * Input: word = "YEAR"
 * Output: 7
 *
 * Constraints:
 *
 * 2 <= word.length <= 300
 * word consists of uppercase English letters.
 */
public class TypeWithTwoFingers {

    Integer[][][] cost;
    int n;
    String word;

    public int minimumDistance(String word) {
        this.n = word.length();
        this.word = word;
        this.cost = new Integer[n][26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                rec(0, i, j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                min = Math.min(min, cost[0][i][j]);
            }
        }
        return min;
    }

    private int rec(int index, int finger1, int finger2) {
        if (index == n) return 0;
        if (cost[index][finger1][finger2] != null) return cost[index][finger1][finger2];
        char c = word.charAt(index);
        int min = rec(index + 1, getId(c), finger2) + getDistance(finger1, c);
        min = Math.min(min, rec(index + 1, finger1, getId(c)) + getDistance(finger2, c));
        cost[index][finger1][finger2] = min;
        return min;
    }

    private int getDistance(int fingerId, char newChar) {
        int newFingerId = newChar - 'A';
        if (fingerId == newFingerId) return 0;
        int[] aCoordinate = getCoordinate(fingerId);
        int[] bCoordinate = getCoordinate(newFingerId);
        return Math.abs(aCoordinate[0] - bCoordinate[0]) + Math.abs(aCoordinate[1] - bCoordinate[1]);
    }

    private int[] getCoordinate(int fingerId) {
        return new int[]{fingerId / 6, fingerId % 6};
    }

    private int getId(char c) {
        return c - 'A';
    }

}
