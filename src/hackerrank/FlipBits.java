package hackerrank;

/**
 * Flipping bits of a decimal Integer.
 */
public class FlipBits {

    public static final char ONE_CHAR = '1';
    public static final char ZERO_CHAR = '0';

    static int flipBits(int n) {
        String nBinary = Integer.toBinaryString(n);
        System.out.println("Original number is decimal " + n + ", and binary  " + nBinary);
        char[] result = new char[nBinary.length()];
        char[] nBinaryChars = nBinary.toCharArray();
        for (int i = 0; i < nBinaryChars.length; i++) {
            result[i] = nBinaryChars[i] == ONE_CHAR ? ZERO_CHAR : ONE_CHAR;
        }
        int resultDecimal = Integer.parseInt(String.valueOf(result), 2);
        System.out.println("Flipped number in decimal is " + resultDecimal
                + ", and in binary is " + String.valueOf(result));
        return resultDecimal;
    }

    public static void main(String[] args) {
        int input = 21;
        int flippedInteger = flipBits(input);
        System.out.println(input + " becomes " + flippedInteger + " after flipping the bits.");
    }

}
