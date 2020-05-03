package leetcode.mixed;

import java.util.*;

public class LeetStringToInteger {

    static Map<Character, Integer> digits = Map.of(
            '0', 0,
            '1', 1,
            '2', 2,
            '3', 3,
            '4', 4,
            '5', 5,
            '6', 6,
            '7', 7,
            '8', 8,
            '9', 9
    );

    public static void main(String[] args) {
    /*
Example 1:
Input: "42"
Output: 42

Example 2:
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.

Example 3:
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Example 4:
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical
             digit or a +/- sign. Therefore no valid conversion could be performed.

Example 5:
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
     */
        LeetStringToInteger leetStringToInteger = new LeetStringToInteger();
//        System.out.println(leetStringToInteger.myAtoi("42"));
//        System.out.println(leetStringToInteger.myAtoi("-42"));
//        System.out.println(leetStringToInteger.myAtoi("4193 with words"));
//        System.out.println(leetStringToInteger.myAtoi("words and 987"));
//        System.out.println(leetStringToInteger.myAtoi("-91283472332"));
        System.out.println(leetStringToInteger.myAtoi("        +1114054094z0"));
    }

    public int myAtoi(String str) {
        boolean shouldSeeDigit = false;
        char[] myAtoiChars = str.toCharArray();
        int n = myAtoiChars.length;
        int power = 0;
        boolean signSeen = false;
        boolean spaceSeen = false;
        double result = 0;
        for (int i = n - 1; i >= 0; i--) {
            char currentChar = myAtoiChars[i];
            if (digits.containsKey(currentChar)) {
                shouldSeeDigit = false;
                if (signSeen) {
                    signSeen = false;
                    result = 0;
                    power = 0;
                }
                if (spaceSeen) {
                    result = 0;
                    power = 0;
                    spaceSeen = false;
                }
                result += Math.pow(10, power) * digits.get(currentChar);
                power++;
            } else if ('-' == currentChar) {
                if (spaceSeen) {
                    return 0;
                }
                if (!signSeen) {
                    result *= -1;
                    signSeen = true;
                } else {
                    shouldSeeDigit = true;
                }
            } else if ('+' == currentChar) {
                if (spaceSeen) {
                    return 0;
                }
                if (!signSeen) {
                    signSeen = true;
                } else {
                    shouldSeeDigit = true;
                }
            } else if (result > 0 && '.' == currentChar) {
                result = 0;
                power = 0;
            } else if (Character.isLetter(currentChar)) {
                result = 0;
                power = 0;
            } else if (result > 0 && ' ' == currentChar) {
                spaceSeen = true;
            } else if (result > 0 && ' ' != currentChar) {
                return 0;
            }
        }
        if (shouldSeeDigit)
            return 0;
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) result;
    }

}
