package leetcode.companies.google;

/**
 * Plus One
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
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
public class NumberPlusOne {

    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry == 0) {
                return digits;
            } else {
                int tempSum = digits[i] + carry;
                if (tempSum <= 9) {
                    carry = 0;
                    digits[i] = tempSum;
                } else {
                    digits[i] = tempSum % 10;
                }
            }
        }
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }
        return digits;
    }

    public static void main(String[] args) {
        NumberPlusOne n = new NumberPlusOne();
        int[] digits = {9, 9, 9, 9};
        printArray(n.plusOne(digits));
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
