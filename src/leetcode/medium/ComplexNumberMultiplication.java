package leetcode.medium;

/**
 * Complex Number Multiplication
 * Given two strings representing two complex numbers.
 * <p>
 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 * <p>
 * Example 1:
 * <p>
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 * <p>
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 * <p>
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
 */
public class ComplexNumberMultiplication {

    public static void main(String[] args) {
        ComplexNumberMultiplication c = new ComplexNumberMultiplication();
        System.out.println(c.complexNumberMultiply("2+3i", "4+5i"));
    }

    public String complexNumberMultiply(String a, String b) {
        int r1, i1, r2, i2;
        int aPlusIndex = a.indexOf("+");
        r1 = Integer.parseInt(a.substring(0, aPlusIndex));
        i1 = Integer.parseInt(a.substring(aPlusIndex + 1, a.length() - 1));
        int bPlusIndex = b.indexOf("+");
        r2 = Integer.parseInt(b.substring(0, bPlusIndex));
        i2 = Integer.parseInt(b.substring(bPlusIndex + 1, b.length() - 1));
        return (r1 * r2 - i1 * i2) + "+" + (r1 * i2 + r2 * i1) + "i";
    }

}
