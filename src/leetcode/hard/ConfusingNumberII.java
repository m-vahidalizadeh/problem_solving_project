package leetcode.hard;

import java.util.Map;

/**
 * 1088. Confusing Number II
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer n, return the number of confusing numbers between 1 and n inclusive.
 *
 * Example 1:
 *
 * Input: n = 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: n = 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 * Note:
 *
 * 1 <= n <= 109
 */
public class ConfusingNumberII {

    public int confusingNumberII(int n) {
        Map<Integer, Integer> map = Map.of(0, 0, 1, 1, 6, 9, 8, 8, 9, 6); // A map of valid digits and their rotations (k1,v1,k2,v2,etc.)
        return rec(map, 0, 0, 1, n);
    }

    private int rec(Map<Integer, Integer> map, int num, int rotatedNum, int base, int n) {
        if (num > n || num < 0) return 0; // num should be between 1 and n
        int count = num != rotatedNum && num != 0 ? 1 : 0; // Check if the current num is valid
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if ((num == 0 && entry.getKey() == 0) || num > 100_000_000) continue; // if by adding even one 0 the num becomes greater than 10 pow 9 or num is zero and we try to add digit 0 to it
            count += rec(map, num * 10 + entry.getKey(), entry.getValue() * base + rotatedNum, base * 10, n);
        }
        return count;
    }

}
