package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 1622. Fancy Sequence
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 *
 * Implement the Fancy class:
 *
 * Fancy() Initializes the object with an empty sequence.
 * void append(val) Appends an integer val to the end of the sequence.
 * void addAll(inc) Increments all existing values in the sequence by an integer inc.
 * void multAll(m) Multiplies all existing values in the sequence by an integer m.
 * int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
 *
 * Example 1:
 *
 * Input
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
 * Output
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 *
 * Explanation
 * Fancy fancy = new Fancy();
 * fancy.append(2);   // fancy sequence: [2]
 * fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
 * fancy.append(7);   // fancy sequence: [5, 7]
 * fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // return 10
 * fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
 * fancy.append(10);  // fancy sequence: [13, 17, 10]
 * fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // return 26
 * fancy.getIndex(1); // return 34
 * fancy.getIndex(2); // return 20
 *
 * Constraints:
 *
 * 1 <= val, inc, m <= 100
 * 0 <= idx <= 105
 * At most 105 calls total will be made to append, addAll, multAll, and getIndex.
 */
public class FancySequence {

    public static final int MOD = (int) (1e9 + 7);
    private static final int[] inv = IntStream.range(0, 101).map(a -> modInverse(a, MOD)).toArray();
    private List<Integer> sequence = new ArrayList<>();
    private long add = 0, mul = 1, revMul = 1;

    public void append(int val) { // Adding to the sequence
        sequence.add((int) (((MOD - add + val) * revMul) % MOD)); // Decrease by add and divide by mul (multiply by revMul)
    }

    public void addAll(int inc) { // Add inc to all elements of the sequence
        add = (add + inc) % MOD; // adjust addition
    }

    public void multAll(int m) { // Multiply all the elements by m
        mul = (mul * m) % MOD; // adjust mul
        revMul = (revMul * inv[m]) % MOD; // adjust reverse mul
        add = (add * m) % MOD; // adjust addition
    }

    public int getIndex(int idx) { // Get the element in idx
        return idx < sequence.size() ? (int) (((sequence.get(idx) * mul) + add) % MOD) : -1; // apply addition and mul to index
    }

    private static int modInverse(int a, int mod) { // mod inverse of a is x if (a*x)%mod==1
        int originalMod = mod, x = 1, y = 0;
        if (mod == 1) return 0;
        while (a > 1) { // Euclid algorithm: a and m are co-primes if gcd(a,m)=1
            int quotient = a / mod;
            int temp = mod;
            mod = a % mod;
            a = temp;
            temp = y;
            y = x - quotient * y;
            x = temp;
        }
        return x < 0 ? x + originalMod : x; // Make it positive if it is negative
    }

}
