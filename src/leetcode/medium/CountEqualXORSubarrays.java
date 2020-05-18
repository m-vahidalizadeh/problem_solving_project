package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Count Triplets That Can Form Two Arrays of Equal XOR
 * Given an array of integers arr.
 * <p>
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 * <p>
 * Let's define a and b as follows:
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 * <p>
 * Return the number of triplets (i, j and k) Where a == b.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 * Example 2:
 * <p>
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 * Example 3:
 * <p>
 * Input: arr = [2,3]
 * Output: 0
 * Example 4:
 * <p>
 * Input: arr = [1,3,5,7,9]
 * Output: 3
 * Example 5:
 * <p>
 * Input: arr = [7,11,12,9,5,2,7,17,22]
 * Output: 8
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class CountEqualXORSubarrays {

    public int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) return 0;
        if (n == 2) return arr[0] == arr[1] ? 1 : 0;
        // Keeps how may time we saw this XOR
        Map<Integer, Integer> freqMap = new HashMap<>();
        /*
        Keeps the summation of indexes with the same XOR.
        if the XOR was in i1, i2 and i3 (3 times):
        total += (i-i1)+(i-i2)+(i-i3) = [3=freq.get]*i - [(i1+i2+i3)=sum.get]
        */
        Map<Integer, Integer> sumMap = new HashMap<>();
        int prevXOR = arr[0];
        int currXOR;
        int total = 0;
        /*
        To support case {a,a} -> total = 1 -> i=0, j=1 and k=1
         */
        freqMap.put(0, 1);
        sumMap.put(0, 0);
        for (int i = 1; i < n; i++) {
            currXOR = prevXOR ^ arr[i];
            if (freqMap.containsKey(currXOR)) total += freqMap.get(currXOR) * i - sumMap.get(currXOR);
            freqMap.put(prevXOR, freqMap.getOrDefault(prevXOR, 0) + 1);
            sumMap.put(prevXOR, sumMap.getOrDefault(prevXOR, 0) + i);
            prevXOR = currXOR;
        }
        return total;
    }

    public static void main(String[] args) {
        CountEqualXORSubarrays c = new CountEqualXORSubarrays();
        int[] arr1 = {2, 3, 1, 6, 7};
        System.out.println(c.countTriplets(arr1));
    }

}
