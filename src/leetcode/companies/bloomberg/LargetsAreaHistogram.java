package leetcode.companies.bloomberg;

import java.util.Stack;

/**
 * Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class LargetsAreaHistogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        if (n == 1) return heights[0];
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            while (s.peek() != -1 && heights[s.peek()] >= heights[i])
                maxArea = Math.max(maxArea, heights[s.pop()] * (i - s.peek() - 1));
            s.push(i);
        }
        while (s.peek() != -1) maxArea = Math.max(maxArea, heights[s.pop()] * (n - s.peek() - 1));
        return maxArea;
    }

    public static void main(String[] args) {
        LargetsAreaHistogram l = new LargetsAreaHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(l.largestRectangleArea(heights));
    }

}
