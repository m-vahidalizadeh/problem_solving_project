package leetcode.medium;

/**
 * Encode Number
 * Given a non-negative integer num, Return its encoding string.
 * <p>
 * The encoding is done by converting the integer to a string using a secret function that you should deduce from the following table:
 * <p>
 * Example 1:
 * <p>
 * Input: num = 23
 * Output: "1000"
 * Example 2:
 * <p>
 * Input: num = 107
 * Output: "101100"
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 10^9
 * Accepted
 * 3,357
 * Submissions
 * 5,117
 */
public class EncodeNumber {

    public String encode(int num) {
        if (num == 0) return "";
        int numPlusOne = num + 1;
        int length = Double.valueOf(Math.log(numPlusOne) / Math.log(2)).intValue();
        int remainder = Double.valueOf(numPlusOne % Math.pow(2, length)).intValue();
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toString(remainder, 2));
        int n = sb.length();
        for (int i = 0; i < length - n; i++) sb.insert(0, "0");
        return sb.toString();
    }

    public static void main(String[] args) {
        EncodeNumber e = new EncodeNumber();
        System.out.println(e.encode(7));
        System.out.println(e.encode(23));
        System.out.println(e.encode(107));
    }

}
