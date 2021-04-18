package leetcode.companies.facebook;

/**
 * 273. Integer to English Words
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: num = 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * Constraints:
 *
 * 0 <= num <= 231 - 1
 */
public class NumberToWords {

    String[] sections = new String[]{"", "Thousand", "Million", "Billion"};
    String[] ones = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
            , "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        int secIndex = 0;
        if (num == 0) return "Zero";
        String result = "";
        while (num > 0) {
            int rem = num % 1_000;
            String temp = build(rem).trim();
            if (secIndex > 0 && !"".equals(temp)) temp = temp + " " + sections[secIndex];
            if (secIndex > 0) result = temp + " " + result;
            else result = temp;
            // Next
            result = result.trim();
            secIndex++;
            num /= 1_000;
        }
        return result.trim();
    }

    private String build(int num) {
        if (num >= 100) {
            return ones[num / 100] + " Hundred " + build(num % 100);
        } else if (num >= 90) {
            return "Ninety " + build(num % 90);
        } else if (num >= 80) {
            return "Eighty " + build(num % 80);
        } else if (num >= 70) {
            return "Seventy " + build(num % 70);
        } else if (num >= 60) {
            return "Sixty " + build(num % 60);
        } else if (num >= 50) {
            return "Fifty " + build(num % 50);
        } else if (num >= 40) {
            return "Forty " + build(num % 40);
        } else if (num >= 30) {
            return "Thirty " + build(num % 30);
        } else if (num >= 20) {
            return "Twenty " + build(num % 20);
        } else {
            return ones[num];
        }
    }

}
