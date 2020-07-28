package leetcode.medium;

/**
 * Minimum Flips to Make a OR b Equal to c
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * <p>
 * Example 1:
 * <p>
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 * <p>
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 * <p>
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 */
public class MinFlipsToMakeAOrBEqualsC {

    public int minFlips(int a, int b, int c) {
        String aBinary = Integer.toBinaryString(a);
        String bBinary = Integer.toBinaryString(b);
        String cBinary = Integer.toBinaryString(c);
        int aIndex = aBinary.length() - 1;
        int bIndex = bBinary.length() - 1;
        int cIndex = cBinary.length() - 1;
        int max = Math.max(aBinary.length(), Math.max(bBinary.length(), cBinary.length()));
        int flips = 0;
        for (int i = 0; i < max; i++) {
            char aChar = aIndex < 0 ? '0' : aBinary.charAt(aIndex--);
            char bChar = bIndex < 0 ? '0' : bBinary.charAt(bIndex--);
            char cChar = cIndex < 0 ? '0' : cBinary.charAt(cIndex--);
            if (cChar == '0') {
                if (aChar == '1') flips++;
                if (bChar == '1') flips++;
            } else {
                if (aChar == '0' && bChar == '0') flips++;
            }
        }
        return flips;
    }

    public static void main(String[] args) {
        MinFlipsToMakeAOrBEqualsC m = new MinFlipsToMakeAOrBEqualsC();
        System.out.println(m.minFlips(2, 6, 5));
        System.out.println(m.minFlips(4, 2, 7));
        System.out.println(m.minFlips(1, 2, 3));
    }

}
