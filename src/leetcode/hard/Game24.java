package leetcode.hard;

/**
 * 679. 24 Game
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * <p>
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * <p>
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class Game24 {

    public boolean judgePoint24(int[] nums) {
        // Ignore ( and ). Change nums to double since we have * and /.
        double[] d = new double[]{nums[0], nums[1], nums[2], nums[3]};
        return rec(d);
    }

    private boolean rec(double[] d) {
        int n = d.length;
        if (n == 1) return Math.abs(d[0] - 24) < 0.001;
        int nMinuesOne = n - 1;
        int nMinusTwo = n - 2;
        // Pick two nums and copy the rest of numbers into the new array.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int index = 0;
                double[] d2 = new double[nMinuesOne];
                for (int a = 0; a < n; a++) {
                    if (a != i && a != j) d2[index++] = d[a];
                }
                for (double num : compute(d[i], d[j])) {
                    d2[nMinusTwo] = num;
                    if (rec(d2)) return true;
                }
            }
        }
        return false;
    }

    private double[] compute(double a, double b) {
        return new double[]{a + b, a - b, b - a, a * b, a / b, b / a};
    }

}
