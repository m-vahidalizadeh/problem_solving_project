package leetcode.companies.adobe;

/**
 * Container With Most Water
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class MaxAreaContainer {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, maxArea = 0;
        while (l < r) {
            maxArea = Math.max(maxArea, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaxAreaContainer m = new MaxAreaContainer();
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(m.maxArea(height1));
        int[] height2 = {1, 2, 3, 4, 5, 25, 24, 3, 4};
        System.out.println(m.maxArea(height2));
    }

}
