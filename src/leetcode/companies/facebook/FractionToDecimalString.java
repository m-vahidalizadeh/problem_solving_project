package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 *
 * Constraints:
 *
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class FractionToDecimalString {

    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0) return "0";
        StringBuilder res=new StringBuilder();
        res.append((numerator>0)^(denominator>0)?"-":"");
        long num=Math.abs((long)numerator);
        long denom=Math.abs((long)denominator);
        res.append(num/denom);
        num%=denom;
        if(num==0) return res.toString();
        res.append(".");
        Map<Long,Integer> map=new HashMap<>();
        while(num>0){
            num*=10;
            res.append(num/denom);
            num%=denom;
            if(map.containsKey(num)){
                res.insert(map.get(num),"(");
                res.append(")");
                break;
            }else map.put(num,res.length());
        }
        return res.toString();
    }

}
