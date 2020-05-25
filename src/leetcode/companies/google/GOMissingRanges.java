package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

public class GOMissingRanges {

    public static void main(String[] args) {
        /*
        Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
        Output: ["2", "4->49", "51->74", "76->99"]
         */
        int[] input = new int[]{0, 1, 3, 50, 75};
        List<String> result = findMissingRanges(input, 0, 99);
        for (String element : result) {
            System.out.print(element);
        }
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int openGap = -1;
        int closeGap = -1;
        int index = 0;
        int n = nums.length;
        if (n == 0) {
            if (upper > lower) {
                result.add(lower + "->" + upper);
                return result;
            } else {
                result.add(String.valueOf(lower));
                return result;
            }
        }
        for (int i = lower; i <= upper; i++) {
            if (i == nums[index] && openGap == -1) {
                index++;
            } else if (i < nums[index] && openGap == -1) {
                openGap = i;
                closeGap = -1;
            } else if (i == nums[index] && openGap != -1) {
                closeGap = i - 1;
                if (closeGap > openGap) {
                    result.add(openGap + "->" + closeGap);
                    openGap = -1;
                    closeGap = -1;
                    index++;
                } else {
                    result.add(String.valueOf(openGap));
                    openGap = -1;
                    closeGap = -1;
                    index++;
                }
            }
            if (index > upper) {
                if (i == upper) {
                    return result;
                } else {
                    result.add(i + "->" + upper);
                    return result;
                }
            }
            if (index > n - 1) {
                if (i == upper) {
                    return result;
                }
                openGap = nums[index - 1] + 1;
                closeGap = upper;
                if (closeGap > openGap) {
                    result.add(openGap + "->" + closeGap);
                    return result;
                } else {
                    result.add(String.valueOf(openGap));
                    return result;
                }
            }
        }
        return result;
    }

}
