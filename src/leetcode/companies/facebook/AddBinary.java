package leetcode.companies.facebook;

/**
 * 67. Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int aN = a.length();
        int bN = b.length();
        int aI = aN - 1;
        int bI = bN - 1;
        while (aI >= 0 && bI >= 0) {
            int sum = Character.getNumericValue(a.charAt(aI)) + Character.getNumericValue(b.charAt(bI)) + carry;
            res.insert(0, sum % 2 + "");
            carry = sum / 2;
            aI--;
            bI--;
        }
        while (aI >= 0) {
            int sum = Character.getNumericValue(a.charAt(aI)) + carry;
            res.insert(0, sum % 2 + "");
            carry = sum / 2;
            aI--;
        }
        while (bI >= 0) {
            int sum = Character.getNumericValue(b.charAt(bI)) + carry;
            res.insert(0, sum % 2 + "");
            carry = sum / 2;
            bI--;
        }
        if (carry == 1) res.insert(0, "1");
        return res.toString();
    }

    public static void main(String[] args) {
        AddBinary a = new AddBinary();
        System.out.println(a.addBinary("11", "1"));
    }

}
