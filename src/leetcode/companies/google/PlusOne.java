package leetcode.companies.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Plus One
 * <p>
 * Solution
 * Given a non-empty array of digits representing a non-negative integer, increment one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        List<Integer> result = new ArrayList<>();
        int n = digits.length;
        int sum = digits[n - 1] + 1;
        int carryover = sum / 10;
        result.add(sum % 10);
        int i = n - 2;
        while (i >= 0 && carryover > 0) {
            sum = digits[i] + carryover;
            result.add(0, sum % 10);
            carryover = sum / 10;
            i--;
        }
        if (i == -1 && carryover > 0) result.add(0, carryover);
        while (i >= 0) result.add(0, digits[i--]);
        int[] resultArray = new int[result.size()];
        for (i = 0; i < resultArray.length; i++) resultArray[i] = result.get(i);
        return resultArray;
    }

    public static void main(String[] args) {
        PlusOne p = new PlusOne();
        int[] digits1 = {1, 2, 3};
        System.out.println(Arrays.toString(p.plusOne(digits1)));
        int[] digits2 = {4, 3, 2, 1};
        System.out.println(Arrays.toString(p.plusOne(digits2)));
        int[] digits3 = {9, 9, 9, 9};
        System.out.println(Arrays.toString(p.plusOne(digits3)));
    }

}
