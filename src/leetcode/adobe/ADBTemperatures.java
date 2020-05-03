package leetcode.adobe;

import java.util.Arrays;

public class ADBTemperatures {

    public static void main(String[] args) {
    /*
    For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
    your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     */
        int[] input = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(input);
        System.out.println(result);
    }

    public static int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] result = new int[n];
        int max = Arrays.stream(T).max().getAsInt();
        for (int i = 0; i < n; i++) {
            if (T[i] == max) {
                result[i] = 0;
            } else {
                int j = i + 1;
                boolean found = false;
                while (j < n && !found) {
                    if (T[j] > T[i]) {
                        found = true;
                        result[i] = j - i;
                    } else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

}
