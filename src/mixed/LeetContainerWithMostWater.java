package mixed;

public class LeetContainerWithMostWater {

    public static void main(String[] args) {
        /*
Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
         */
        LeetContainerWithMostWater leetContainerWithMostWater = new LeetContainerWithMostWater();
        int[] height = {1, 2};
        System.out.format("Max area is %s", leetContainerWithMostWater.maxArea(height));
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int width = j - i;
                int heightR = Math.min(height[i], height[j]);
                int area = width * heightR;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

}
