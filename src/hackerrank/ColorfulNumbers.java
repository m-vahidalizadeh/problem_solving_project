package hackerrank;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Colorful Number: When in a given number, product of every digit of a sub-sequence are different. That number is called Colorful Number. See Example
 * Example:
 * Given Number : 3245
 * Output : Colorful
 * Number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
 * this number is a colorful number, since product of every digit of a sub-sequence are different.
 * That is, 3 2 4 5 (3*2)=6 (2*4)=8 (4*5)=20, (3*2*4)= 24 (2*4*5)= 40
 * Given Number : 326
 * Output : Not Colorful.
 * 326 is not a colorful number as it generates 3 2 6 (3*2)=6 (2*6)=12.
 * Resource: https://algorithms.tutorialhorizon.com/colorful-numbers/
 */
public class ColorfulNumbers {

    public static void main(String[] args) {
        ColorfulNumbers colorfulNumbers = new ColorfulNumbers();
        int number = 3245;
        System.out.println(colorfulNumbers.isColorful(number));
        number = 326;
        System.out.println(colorfulNumbers.isColorful(number));
    }

    /**
     * A dynamic programming solution to see if a number is colorful or not. Column 0 keeps the original digits.
     * products[i][j] means the product of the digits from index i to index j. Products[i][j] equals products[i][j-1]
     * multiply digit[j].
     *
     * @param number The input number
     * @return Whether the number is colorful or not
     */
    public boolean isColorful(int number) {
        Set<Integer> seenProducts = new HashSet<>();
        int[] digits = getDigits(number);
        int n = digits.length;
        int[][] products = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (j == 0) {
                    int currentDigit = digits[i];
                    if (seenProducts.contains(currentDigit)) {
                        return false;
                    } else {
                        seenProducts.add(currentDigit);
                        products[i][j] = currentDigit;
                    }
                } else {
                    if (i < j) {
                        int previousProduct = i == j - 1 ? products[i][0] : products[i][j - 1];
                        int currentProduct = previousProduct * products[j][0];
                        if (seenProducts.contains(currentProduct)) {
                            return false;
                        } else {
                            seenProducts.add(currentProduct);
                            products[i][j] = currentProduct;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns the digits of a number as an array.
     *
     * @param number The number
     * @return The digits of the number
     */
    private int[] getDigits(int number) {
        Stack<Integer> digitsStack = new Stack<>();
        while (number > 0) {
            int remainder = number % 10;
            digitsStack.push(remainder);
            number = number / 10;
        }
        int n = digitsStack.size();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = digitsStack.pop();
        }
        return digits;
    }

}
