package leetcode.hard;

/**
 * 878. Nth Magical Number
 * A positive integer is magical if it is divisible by either a or b.
 *
 * Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 1, a = 2, b = 3
 * Output: 2
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3
 * Output: 6
 *
 * Constraints:
 *
 * 1 <= n <= 109
 * 2 <= a, b <= 4 * 104
 */
public class NthMagicalNumber {

    public int nthMagicalNumber(int n, int a, int b) {
        int lcm = a / gcd(a, b) * b;
        long low = 0;
        long high = (long) n * Math.min(a, b);
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (mid / a + mid / b - mid / lcm < n) low = mid + 1;
            else high = mid;
        }
        return (int) (low % (1e9 + 7));
    }

    private int gcd(int x, int y) {
        if (x == 0) return y;
        return gcd(y % x, x);
    }

}
