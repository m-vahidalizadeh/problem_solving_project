package leetcode.companies.google;

/**
 * Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int[] lMax = new int[n];
        lMax[0] = height[0];
        for (int i = 1; i < n; i++) lMax[i] = Math.max(lMax[i - 1], height[i]);
        int[] rMax = new int[n];
        rMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) rMax[i] = Math.max(rMax[i + 1], height[i]);
        int trapped = 0;
        for (int i = 1; i < n - 1; i++) trapped += Math.min(lMax[i], rMax[i]) - height[i];
        return trapped;
    }

}
