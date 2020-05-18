package leetcode.base;

public class Utils {

    private Utils() {
    }

    /**
     * Prints a 2D array.
     *
     * @param array The 2D array
     */
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints an array.
     *
     * @param array The input array
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Returns the number of digits of a decimal number's binary representation.
     *
     * @param number The decimal number
     * @return The number of digits in the binary representation
     */
    public static int countBits(int number) {
        int counter = 0;
        while (number > 0) {
            counter++;
            number >>= 1;
        }
        return counter;
    }

    /**
     * Returns the binary representation of a decimal number.
     *
     * @param number The decimal number
     * @return The binary representation of a decimal number
     */
    public static int[] getBinaryRepresentation(int number) {
        int n = countBits(number);
        int[] binaryNumber = new int[n];
        int index = n - 1;
        while (number > 0) {
            binaryNumber[index--] = number % 2;
            number >>= 1;
        }
        return binaryNumber;
    }

    public static void main(String[] args) {
        /*
         * Decimal to binary conversion tests:
         * Input : 7
         * Output : 111
         *
         * Input : 10
         * Output : 1010
         *
         * Input: 33
         * Output: 100001
         */
        int[] binaryNumber = getBinaryRepresentation(7);
        printArray(binaryNumber);
        binaryNumber = getBinaryRepresentation(10);
        printArray(binaryNumber);
        binaryNumber = getBinaryRepresentation(33);
        printArray(binaryNumber);
    }

}
