package leetcode.medium;

/**
 * The kth Factor of n
 * Given two positive integers n and k.
 * <p>
 * A factor of an integer n is defined as an integer i where n % i == 0.
 * <p>
 * Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
 * Example 2:
 * <p>
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2nd factor is 7.
 * Example 3:
 * <p>
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
 * Example 4:
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 * Explanation: Factors list is [1], the 1st factor is 1.
 * Example 5:
 * <p>
 * Input: n = 1000, k = 3
 * Output: 4
 * Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000].
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 1000
 */
public class KthFactor {

    public int kthFactor(int n, int k) {
        if (k == 1) return 1;
        else if (n == 1) return k <= 1 ? 1 : -1;
        else if (isPrime(n)) return k == 2 ? n : -1;
        else {
            int counter = 1;
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    counter++;
                    if (counter == k) return i;
                }
            }
            return -1;
        }
    }

    private boolean isPrime(long input) {
        if (input <= 1) return false; // Primes start from 2
        if (input <= 3) return true; // 2 and 3 are primes
        if (input % 2 == 0 || input % 3 == 0) return false; // Not prime if dividable by 2 or 3
        // The rest of the primes are in the shape of 6k-1 and 6k+1
        for (long i = 5; i <= Math.sqrt(input); i += 6) if (input % i == 0 || input % (i + 2) == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        KthFactor k = new KthFactor();
        System.out.println(k.kthFactor(155, 3));
    }

}
