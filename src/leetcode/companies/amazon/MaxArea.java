package leetcode.companies.amazon;

/**
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Example 1:
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * Example 4:
 * <p>
 * Input: height = [1,2,1]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * 2 <= height.length <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int l = 0;
        int n = height.length;
        int r = n - 1;
        int max = Integer.MIN_VALUE;
        while (l < r) {
            int minHeight = Math.min(height[l], height[r]);
            max = Math.max(max, minHeight * (r - l));
            if (minHeight == height[l]) l++;
            else r--;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxArea m = new MaxArea();
        System.out.println(m.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(m.maxArea(new int[]{1, 1}));
        System.out.println(m.maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(m.maxArea(new int[]{1, 2, 1}));
        System.out.println(m.maxArea(new int[]{1, 3, 2, 5, 25, 24, 5}));
    }

}
