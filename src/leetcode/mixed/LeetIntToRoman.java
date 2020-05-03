package leetcode.mixed;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeetIntToRoman {

    static Map<Integer, String> romans = new LinkedHashMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    /*
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
     */

    public static void main(String[] args) {
/*
Example 1:
Input: 3
Output: "III"

Example 2:
Input: 4
Output: "IV"

Example 3:
Input: 9
Output: "IX"

Example 4:
Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

Example 5:
Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
        LeetIntToRoman leetIntToRoman = new LeetIntToRoman();
        int num = 1994;
        System.out.format("The roman of %d is %s", num, leetIntToRoman.intToRoman(num));
    }

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 1)
            return result.toString();
        for (Map.Entry<Integer, String> r : romans.entrySet()) {
            int key = r.getKey();
            int romanInt = num / key;
            if (romanInt > 0) {
                num = num % key;
                result.append(r.getValue().repeat(romanInt));
            }
            if (num < 1)
                return result.toString();
        }
        return result.toString();
    }

}
