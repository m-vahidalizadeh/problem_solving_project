package leetcode.companies.uber;

public class UberFraction {

    /*
Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"

     */

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, 2));
        System.out.println(fractionToDecimal(2, 1));
        System.out.println(fractionToDecimal(2, 3));
        System.out.println(fractionToDecimal(4, 333));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        double result = (double) numerator / denominator;
        String resultTwoDecimal = String.valueOf(result);
        String[] fraction = resultTwoDecimal.split("\\.");
        char[] fraction1ToChars = fraction[1].toCharArray();
        if (fraction.length >= 2 && fraction1ToChars.length >= 2 &&
                resultTwoDecimal.charAt(2) == resultTwoDecimal.charAt(3)) {
            return fraction[0] + ".(" + String.valueOf(fraction[1].charAt(0)) + ")";
        } else {
            if (fraction.length < 2) {
                return fraction[0];
            } else {
                if ("0".equals(String.valueOf(fraction[1].charAt(0))) && fraction[1].length() <= 1) {
                    return fraction[0];
                } else {
                    String tempResult = fraction[0];
                    if (fraction1ToChars.length >= 1) {
                        tempResult += "." + String.valueOf(fraction1ToChars[0]);
                    }
                    if (fraction1ToChars.length >= 2) {
                        tempResult += String.valueOf(fraction1ToChars[1]);
                    }
                    if (fraction1ToChars.length >= 3) {
                        tempResult += String.valueOf(fraction1ToChars[2]);
                    }
                    return tempResult;
                }
            }
        }
    }

}
