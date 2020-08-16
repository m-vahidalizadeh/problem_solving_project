package leetcode.companies.google;

import java.math.BigInteger;

/**
 * Multiply Strings
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 * <p>
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        BigInteger sum = new BigInteger("0");
        BigInteger multiplier = new BigInteger("1");
        BigInteger ten = new BigInteger("10");
        int n1 = num1.length();
        int n2 = num2.length();
        for (int j = n2 - 1; j >= 0; j--) {
            BigInteger tempMultiplier = multiplier;
            for (int i = n1 - 1; i >= 0; i--) {
                int t = Integer.parseInt(String.valueOf(num2.charAt(j))) * Integer.parseInt(String.valueOf(num1.charAt(i)));
                sum = sum.add(tempMultiplier.multiply(new BigInteger(String.valueOf(t % 10))));
                tempMultiplier = tempMultiplier.multiply(ten);
                if (t > 9) sum = sum.add(tempMultiplier.multiply(new BigInteger(String.valueOf(t / 10))));
            }
            multiplier = multiplier.multiply(ten);
        }
        return sum.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings m = new MultiplyStrings();
        System.out.println(m.multiply("2", "3"));
        System.out.println(m.multiply("123", "456"));
        System.out.println(m.multiply("9", "9"));
    }

}
