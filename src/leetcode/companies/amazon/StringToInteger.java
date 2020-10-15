package leetcode.companies.amazon;

/**
 * 8. String to Integer (atoi)
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered a whitespace character.
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: str = "42"
 * Output: 42
 * Example 2:
 * <p>
 * Input: str = "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 * <p>
 * Input: str = "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * <p>
 * Input: str = "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * <p>
 * Input: str = "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is returned.
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits, ' ', '+', '-' and '.'.
 */
public class StringToInteger {

    public int myAtoi(String s) {
        s = s.trim();
        int n = s.length();
        if (n == 0) return 0;
        char firstChar = s.charAt(0);
        if (!Character.isDigit(firstChar) && firstChar != '-' && firstChar != '+') return 0;
        boolean isNegative = false;
        if (firstChar == '-') {
            isNegative = true;
            s = s.substring(1, n);
            n--;
        } else if (firstChar == '+') {
            s = s.substring(1, n);
            n--;
        }
        if (n == 0) return 0;
        if (!Character.isDigit(s.charAt(0))) return 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n && Character.isDigit(s.charAt(i))) sb.append(s.charAt(i++));
        if (sb.length() == 0) return 0;
        Double result = Double.parseDouble(sb.toString());
        if (isNegative) result = result * -1;
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return result.intValue();
    }

    public static void main(String[] args) {
        StringToInteger s = new StringToInteger();
        System.out.println(s.myAtoi("42"));
        System.out.println(s.myAtoi("   -42"));
        System.out.println(s.myAtoi("4193 with words"));
        System.out.println(s.myAtoi("words and 987"));
        System.out.println(s.myAtoi("-91283472332"));
        System.out.println(s.myAtoi("3.14159"));
        System.out.println(s.myAtoi(".1"));
        System.out.println(s.myAtoi("20000000000000000000"));
    }

}
