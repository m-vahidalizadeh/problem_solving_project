package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Fraction to Recurring Decimal
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class PrintFraction {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuffer sb = new StringBuffer();
        if (isNegative(numerator, denominator)) sb.append("-");
        if (Math.abs(denominator) == 1) return sb.append(Math.abs(Long.valueOf(numerator))).toString();
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        sb.append(dividend / divisor);
        long remainder = dividend % divisor;
        if (remainder == 0) return sb.toString();
        sb.append(".");
        Map<Long, Integer> seenMap = new HashMap<>();
        int size = sb.length();
        while (remainder != 0) {
            if (seenMap.containsKey(remainder)) {
                sb.insert(seenMap.get(remainder), "(");
                sb.append(")");
                return sb.toString();
            }
            seenMap.put(remainder, size++);
            remainder *= 10;
            sb.append(remainder / divisor);
            remainder %= divisor;
        }
        return sb.toString();
    }

    private boolean isNegative(int a, int b) {
        boolean isANegative = a < 0;
        boolean isBNegative = b < 0;
        if (isANegative && isBNegative) return false;
        return (isANegative || isBNegative);
    }

    public static void main(String[] args) {
        PrintFraction p = new PrintFraction();
        System.out.println(p.fractionToDecimal(1, 2));
        System.out.println(p.fractionToDecimal(2, 1));
        System.out.println(p.fractionToDecimal(2, 3));
        System.out.println(p.fractionToDecimal(1, 6));
        System.out.println(p.fractionToDecimal(4, 333));
        System.out.println(p.fractionToDecimal(1, 333));
        System.out.println(p.fractionToDecimal(1, 17));
        System.out.println(p.fractionToDecimal(-1, -2147483648));
        System.out.println(p.fractionToDecimal(-2147483648, 1));
    }

}
