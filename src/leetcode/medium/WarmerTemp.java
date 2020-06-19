package leetcode.medium;

/**
 * Daily Temperatures
 * <p>
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class WarmerTemp {

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] result = new int[n];
        int a = 0, b = 0;
        int distance = 0;
        while (a < n) {
            distance++;
            b++;
            if (b == n) {
                a++;
                b = a;
                distance = 0;
            } else if (T[a] < T[b]) {
                result[a] = distance;
                distance = 0;
                a++;
                b = a;
            }
        }
        return result;
    }

}
