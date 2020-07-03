package leetcode.companies.adobe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Self Dividing Numbers
 * A self-dividing number is a number that is divisible by every digit it contains.
 * <p>
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * <p>
 * Also, a self-dividing number is not allowed to contain the digit zero.
 * <p>
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * left = 1, right = 22
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * Note:
 * <p>
 * The boundaries of each input argument are 1 <= left <= right <= 10000.
 */
public class SelfDividingNumber {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> digits;
        boolean dividable = false;
        for (int i = left; i <= right; i++) {
            if (i > 0 && i < 10) {
                result.add(i);
                continue;
            }
            dividable = true;
            digits = getDigits(i);
            if (digits.contains(0)) continue;
            for (int d : digits) {
                if (i % d != 0) {
                    dividable = false;
                    break;
                }
            }
            if (dividable) result.add(i);
        }
        return result;
    }

    private Set<Integer> getDigits(int num) {
        Set<Integer> digits = new HashSet<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        return digits;
    }

    public static void main(String[] args) {
        SelfDividingNumber s = new SelfDividingNumber();
        List<Integer> result = s.selfDividingNumbers(1, 22);
        System.out.println();
    }

}
