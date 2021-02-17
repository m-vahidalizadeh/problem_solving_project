package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1187. Make Array Strictly Increasing
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 * Example 1:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 * Example 2:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 * Example 3:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 */
public class MakeArrStrictlyIncreasing {

    int[] arr1;
    int[] arr2;
    int l1;
    int l2;
    int k;
    Map<String, Integer> cache;

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        l1 = arr1.length;
        l2 = arr2.length;
        cache = new HashMap<>();
        Arrays.sort(this.arr2);
        k = 1;
        for (int i = 1; i < l2; i++) {
            if (arr2[i - 1] != arr2[i]) arr2[k++] = arr2[i];
        }
        int count = dfs(0, 0, -1);
        return count > k ? -1 : count;
    }

    private int dfs(int i1, int i2, int prev) {
        if (i1 == l1) return 0;
        String key = i1 + "," + prev;
        if (cache.containsKey(key)) return cache.get(key);
        int min = k + 1;
        if (prev < arr1[i1]) min = dfs(i1 + 1, i2, arr1[i1]);
        while (i2 < k && arr2[i2] <= prev) i2++;
        if (i2 < k) min = Math.min(min, 1 + dfs(i1 + 1, i2 + 1, arr2[i2]));
        cache.put(key, min);
        return min;
    }

    public static void main(String[] args) {
        MakeArrStrictlyIncreasing m = new MakeArrStrictlyIncreasing();
        System.out.println(m.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{1, 3, 2, 4}));
    }

}
