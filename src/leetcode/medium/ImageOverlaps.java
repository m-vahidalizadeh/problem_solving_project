package leetcode.medium;

import javafx.util.Pair;

import java.util.*;

/**
 * 835. Image Overlap
 * You are given two images img1 and img2 both of size n x n, represented as binary, square matrices of the same size. (A binary matrix has only 0s and 1s as values.)
 * <p>
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
 * <p>
 * (Note also that a translation does not include any kind of rotation.)
 * <p>
 * What is the largest possible overlap?
 * <p>
 * Example 1:
 * <p>
 * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
 * Output: 3
 * Explanation: We slide img1 to right by 1 unit and down by 1 unit.
 * <p>
 * The number of positions that have a 1 in both images is 3. (Shown in red)
 * <p>
 * Example 2:
 * <p>
 * Input: img1 = [[1]], img2 = [[1]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: img1 = [[0]], img2 = [[0]]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * n == img1.length
 * n == img1[i].length
 * n == img2.length
 * n == img2[i].length
 * 1 <= n <= 30
 * img1[i][j] is 0 or 1.
 * img2[i][j] is 0 or 1.
 */
public class ImageOverlaps {

    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Pair<Integer, Integer>> ones1 = getNonzeroList(img1, n);
        List<Pair<Integer, Integer>> ones2 = getNonzeroList(img2, n);
        int maxOverlaps = 0;
        Map<Pair<Integer, Integer>, Integer> countMap = new HashMap<>();
        for (Pair<Integer, Integer> a : ones1) {
            for (Pair<Integer, Integer> b : ones2) {
                Pair<Integer, Integer> key = new Pair<>(b.getKey() - a.getKey(), b.getValue() - a.getValue());
                int newCount = countMap.getOrDefault(key, 0) + 1;
                countMap.put(key, newCount);
                maxOverlaps = Math.max(maxOverlaps, newCount);
            }
        }
        return maxOverlaps;
    }

    private List<Pair<Integer, Integer>> getNonzeroList(int[][] m, int n) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m[i][j] == 1) result.add(new Pair<>(i, j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ImageOverlaps i = new ImageOverlaps();
        System.out.println(i.largestOverlap(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}}));
        System.out.println(i.largestOverlap(new int[][]{{1}}, new int[][]{{1}}));
        System.out.println(i.largestOverlap(new int[][]{{0}}, new int[][]{{0}}));
    }

}
