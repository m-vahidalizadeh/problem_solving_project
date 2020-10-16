package leetcode.companies.amazon;

import java.util.List;

/**
 * 12. Integer to Roman
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral.
 *
 * Example 1:
 *
 * Input: num = 3
 * Output: "III"
 * Example 2:
 *
 * Input: num = 4
 * Output: "IV"
 * Example 3:
 *
 * Input: num = 9
 * Output: "IX"
 * Example 4:
 *
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 */
public class ConvertIntToRoman {

    public class RomanLetter {
        String l;
        int val;

        public RomanLetter(String l, int val) {
            this.l = l;
            this.val = val;
        }
    }

    List<RomanLetter> letters;

    public String intToRoman(int num) {
        letters = List.of(
                new RomanLetter("M", 1000),
                new RomanLetter("CM", 900),
                new RomanLetter("D", 500),
                new RomanLetter("CD", 400),
                new RomanLetter("C", 100),
                new RomanLetter("XC", 90),
                new RomanLetter("L", 50),
                new RomanLetter("XL", 40),
                new RomanLetter("X", 10),
                new RomanLetter("IX", 9),
                new RomanLetter("V", 5),
                new RomanLetter("IV", 4),
                new RomanLetter("I", 1)
        );
        StringBuilder sb = new StringBuilder();
        for (RomanLetter l : letters) {
            if (num < l.val) continue;
            int count = num / l.val;
            num = num - count * l.val;
            for (int i = 0; i < count; i++) sb.append(l.l);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertIntToRoman c = new ConvertIntToRoman();
        System.out.println(c.intToRoman(3));
        System.out.println(c.intToRoman(4));
        System.out.println(c.intToRoman(9));
        System.out.println(c.intToRoman(58));
        System.out.println(c.intToRoman(1994));
    }

}
