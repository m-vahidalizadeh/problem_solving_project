package leetcode.companies.bloomberg;

import java.util.Arrays;

/**
 * String Compression
 * Given an array of characters, compress it in-place.
 * <p>
 * The length after compression must always be smaller than or equal to the original array.
 * <p>
 * Every element of the array should be a character (not int) of length 1.
 * <p>
 * After you are done modifying the input array in-place, return the new length of the array.
 * <p>
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["a","a","b","b","c","c","c"]
 * <p>
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * <p>
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * <p>
 * Example 2:
 * <p>
 * Input:
 * ["a"]
 * <p>
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * <p>
 * Explanation:
 * Nothing is replaced.
 * <p>
 * Example 3:
 * <p>
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * <p>
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * <p>
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * <p>
 * Note:
 * <p>
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */
public class CompressArray {

    public int compress(char[] chars) {
        int index = 0;
        int n = chars.length;
        if (n < 2) return n;
        char[] original = Arrays.copyOf(chars, n);
        int counter = 0;
        char currChar = chars[0];
        for (int i = 0; i < n; i++) {
            if (original[i] == currChar) {
                counter++;
            } else {
                chars[index++] = currChar;
                if (counter > 1) {
                    String countS = String.valueOf(counter);
                    for (char c : countS.toCharArray()) {
                        chars[index++] = c;
                    }
                }
                counter = 1;
                currChar = original[i];
            }
        }
        chars[index++] = currChar;
        if (counter > 1) {
            String countS = String.valueOf(counter);
            for (char c : countS.toCharArray()) {
                chars[index++] = c;
            }
        }
        if (index <= n) {
            return index;
        } else {
            chars = original;
            return n;
        }
    }

    public static void main(String[] args) {
        CompressArray c = new CompressArray();
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(c.compress(chars1));
        char[] chars2 = {'a'};
        System.out.println(c.compress(chars2));
        char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(c.compress(chars3));
    }

}
