public class MSExcelTitleToNumber {

    public static void main(String[] args) {
/*
Example 1:
Input: "A"
Output: 1

Example 2:
Input: "AB"
Output: 28

Example 3:
Input: "ZY"
Output: 701
 */
        System.out.println(titleToNumber("ZY"));
    }

    public static int titleToNumber(String s) {
        char[] sChars = s.toCharArray();
        Double result = 0.0;
        int power = 0;
        for (int i = sChars.length - 1; i >= 0; i--) {
            int diff = sChars[i] - 'A' + 1;
            result = result + Math.pow(26, power) * diff;
            power++;
        }
        return result.intValue();
    }

}
