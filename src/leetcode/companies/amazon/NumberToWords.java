package leetcode.companies.amazon;

/**
 * 273. Integer to English Words
 * Convert a non-negative integer num to its English words representation.
 * <p>
 * Example 1:
 * <p>
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * <p>
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * <p>
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 * <p>
 * Input: num = 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 231 - 1
 */
public class NumberToWords {

    String[] lessThanTwenty = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
            , "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = new String[]{"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        int i = 0;
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            if (num % 1000 != 0) {
                StringBuilder threeDigitSb = new StringBuilder();
                buildThreeDigit(threeDigitSb, num % 1000);
                result.insert(0, threeDigitSb.append(thousands[i]).append(" "));
            }
            // Next
            num /= 1000;
            i++;
        }
        return result.toString().trim();
    }

    private void buildThreeDigit(StringBuilder threeDigitSb, int num) {
        if (num == 0) return;
        if (num < 20) {
            threeDigitSb.append(lessThanTwenty[num]).append(" ");
        } else if (num < 100) {
            threeDigitSb.append(tens[num / 10]).append(" ");
            buildThreeDigit(threeDigitSb, num % 10);
        } else {
            threeDigitSb.append(lessThanTwenty[num / 100]).append(" Hundred ");
            buildThreeDigit(threeDigitSb, num % 100);
        }
    }

    public static void main(String[] args) {
        NumberToWords n = new NumberToWords();
        System.out.println(n.numberToWords(123));
        System.out.println(n.numberToWords(12345));
        System.out.println(n.numberToWords(1234567));
        System.out.println(n.numberToWords(1234567891));
    }

}
