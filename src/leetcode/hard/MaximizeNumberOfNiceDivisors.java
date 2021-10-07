package leetcode.hard;

/**
 * 1808. Maximize Number of Nice Divisors
 * You are given a positive integer primeFactors. You are asked to construct a positive integer n that satisfies the following conditions:
 *
 * The number of prime factors of n (not necessarily distinct) is at most primeFactors.
 * The number of nice divisors of n is maximized. Note that a divisor of n is nice if it is divisible by every prime factor of n. For example, if n = 12, then its prime factors are [2,2,3], then 6 and 12 are nice divisors, while 3 and 4 are not.
 * Return the number of nice divisors of n. Since that number can be too large, return it modulo 109 + 7.
 *
 * Note that a prime number is a natural number greater than 1 that is not a product of two smaller natural numbers. The prime factors of a number n is a list of prime numbers such that their product equals n.
 *
 * Example 1:
 *
 * Input: primeFactors = 5
 * Output: 6
 * Explanation: 200 is a valid value of n.
 * It has 5 prime factors: [2,2,2,5,5], and it has 6 nice divisors: [10,20,40,50,100,200].
 * There is not other value of n that has at most 5 prime factors and more nice divisors.
 * Example 2:
 *
 * Input: primeFactors = 8
 * Output: 18
 *
 * Constraints:
 *
 * 1 <= primeFactors <= 109
 */
public class MaximizeNumberOfNiceDivisors {

    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors <= 4) return primeFactors;
        int mod = (int) (1e9 + 7); // 10^9+7
        int power = primeFactors / 3;
        int rem = primeFactors % 3;
        if (rem == 0) return (int) power3(power, mod); // Try to group the factors 3 by 3
        else if (rem == 1) return (int) ((power3(power - 1, mod) * 4) % mod); // instead of last 4*1, make it 2*2
        return (int) ((power3(power, mod) * 2) % mod); // rem==2- cases like 5 make it 3*2
    }

    private long power3(int n, int mod) {
        if (n == 0) return 1;
        if (n == 1) return 3;
        long res = power3(n / 2, mod);
        res = (res * res) % mod; // power3(n/2)*power3(n/2)=power3(n)
        if (n % 2 != 0) res = (res * 3) % mod; // n not even add the +1 to result n=n/2+n/2+1 in case of odd n
        return res;
    }

}
