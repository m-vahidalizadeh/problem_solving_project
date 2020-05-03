package leetcode.medium;

/**
 * Counting Bits
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * <p>
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class CountingBits {

    public static void main(String[] args) {
        CountingBits c = new CountingBits();
        leetcode.base.Utils.printArray(c.countBits(2));
        leetcode.base.Utils.printArray(c.countBits(5));
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];// result[0] is already zero. Zero has no set bit.
        int nextCompletePowerOfTwo = 1;// 2 to the power of zero=1 -> 2 -> 4. Each time, shift left == multiply by two
        int remainder = 0;// Each number if 2 to the power of y + x. The remainder is keeping x
        for (int i = 1; i <= num; i++) {
            if (i == nextCompletePowerOfTwo) {
                result[i] = 1;// Only one bit is set for 2 to the power of y
                nextCompletePowerOfTwo <<= 1;// Multiply by two by one shift to left
                remainder = 1;// Reset the remainder
            } else {
                result[i] = result[remainder] + 1;
                remainder++;
            }
        }
        return result;
    }

}
