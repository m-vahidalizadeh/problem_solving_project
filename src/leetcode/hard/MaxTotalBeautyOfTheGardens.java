package leetcode.hard;

import java.util.Arrays;

/**
 * 2234. Maximum Total Beauty of the Gardens
 * Alice is a caretaker of n gardens and she wants to plant flowers to maximize the total beauty of all her gardens.
 *
 * You are given a 0-indexed integer array flowers of size n, where flowers[i] is the number of flowers already planted in the ith garden. Flowers that are already planted cannot be removed. You are then given another integer newFlowers, which is the maximum number of flowers that Alice can additionally plant. You are also given the integers target, full, and partial.
 *
 * A garden is considered complete if it has at least target flowers. The total beauty of the gardens is then determined as the sum of the following:
 *
 * The number of complete gardens multiplied by full.
 * The minimum number of flowers in any of the incomplete gardens multiplied by partial. If there are no incomplete gardens, then this value will be 0.
 * Return the maximum total beauty that Alice can obtain after planting at most newFlowers flowers.
 *
 * Example 1:
 *
 * Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
 * Output: 14
 * Explanation: Alice can plant
 * - 2 flowers in the 0th garden
 * - 3 flowers in the 1st garden
 * - 1 flower in the 2nd garden
 * - 1 flower in the 3rd garden
 * The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
 * There is 1 garden that is complete.
 * The minimum number of flowers in the incomplete gardens is 2.
 * Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
 * No other way of planting flowers can obtain a total beauty higher than 14.
 * Example 2:
 *
 * Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
 * Output: 30
 * Explanation: Alice can plant
 * - 3 flowers in the 0th garden
 * - 0 flowers in the 1st garden
 * - 0 flowers in the 2nd garden
 * - 2 flowers in the 3rd garden
 * The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
 * There are 3 gardens that are complete.
 * The minimum number of flowers in the incomplete gardens is 4.
 * Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
 * No other way of planting flowers can obtain a total beauty higher than 30.
 * Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.
 *
 * Constraints:
 *
 * 1 <= flowers.length <= 105
 * 1 <= flowers[i], target <= 105
 * 1 <= newFlowers <= 1010
 * 1 <= full, partial <= 105
 */
public class MaxTotalBeautyOfTheGardens {

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        long[] prefix = new long[n + 1];
        Arrays.sort(flowers);
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + Math.min(flowers[i], target);
        long res = 0;
        for (int c = 0, i = n - 1; c <= n; c++) {
            long remain = prefix[n] - prefix[n - c] + newFlowers - c * (long) target, min = 0;
            if (0 > remain) break;
            i = Math.min(i, n - c - 1);
            while (0 <= i && (target <= flowers[i] || flowers[i] * (long) (i + 1) - prefix[i + 1] > remain)) i--;
            if (0 <= i) {
                long diff = flowers[i] * (long) (i + 1) - prefix[i + 1];
                min = Math.min(target - 1, flowers[i] + (remain - diff) / (i + 1));
                if (i + 1 < n - c) min = Math.min(min, flowers[i + 1]);
            }
            res = Math.max(res, c * (long) full + min * (long) partial);
        }
        return res;
    }

}
