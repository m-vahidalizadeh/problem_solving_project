package leetcode.medium;

import java.util.Arrays;

/**
 * Range Addition
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * <p>
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * <p>
 * Return the modified array after all k operations were executed.
 * <p>
 * Example:
 * <p>
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 * Explanation:
 * <p>
 * Initial state:
 * [0,0,0,0,0]
 * <p>
 * After applying operation [1,3,2]:
 * [0,2,2,2,0]
 * <p>
 * After applying operation [2,4,3]:
 * [0,2,5,5,3]
 * <p>
 * After applying operation [0,2,-2]:
 * [-2,0,3,5,3]
 */
public class ModifyArray {

    public int[] getModifiedArray(int length, int[][] updates) {
        int n = updates.length;
        int[] result = new int[length];
        Arrays.sort(updates, (a, b) -> b[1] - a[1]);
        for (int i = 0; i < length; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                if (updates[j][1] < i) break;
                if (updates[j][0] <= i) temp += updates[j][2];
            }
            result[i] = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        ModifyArray m = new ModifyArray();
//        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
//        m.getModifiedArray(5, updates);
        int[][] updates = {{2, 4, 6}, {5, 6, 8}, {1, 9, -4}};
        m.getModifiedArray(10, updates);
    }

}
