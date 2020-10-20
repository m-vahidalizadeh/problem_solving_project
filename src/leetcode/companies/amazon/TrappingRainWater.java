package leetcode.companies.amazon;

/**
 * 42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it can trap after raining.
 * <p>
 * Example 1:
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of
 * rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = height[0];
        for (int i = 1; i < n; i++) lMax[i] = Math.max(lMax[i - 1], height[i]);
        rMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) rMax[i] = Math.max(rMax[i + 1], height[i]);
        int count = 0;
        for (int i = 1; i <= n - 2; i++) {
            int rainHeight = Math.min(lMax[i - 1], rMax[i + 1]);
            if (rainHeight > height[i]) count += rainHeight - height[i];
        }
        return count;
    }

    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(t.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

}
