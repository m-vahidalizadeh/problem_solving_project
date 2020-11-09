package leetcode.companies.amazon;

/**
 * 1060. Missing Element in Sorted Array
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 *
 * Example 1:
 *
 * Input: A = [4,7,9,10], K = 1
 * Output: 5
 * Explanation:
 * The first missing number is 5.
 * Example 2:
 *
 * Input: A = [4,7,9,10], K = 3
 * Output: 8
 * Explanation:
 * The missing numbers are [5,6,8,...], hence the third missing number is 8.
 * Example 3:
 *
 * Input: A = [1,2,4], K = 3
 * Output: 6
 * Explanation:
 * The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * 1 <= A[i] <= 1e7
 * 1 <= K <= 1e8
 */
public class KthMissingNum {

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (missingCountTill(n - 1, nums) < k) return nums[n - 1] + k - missingCountTill(n - 1, nums);
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int pivot = l + (r - l) / 2;
            if (missingCountTill(pivot, nums) < k) l = pivot + 1;
            else r = pivot;
        }
        return nums[l - 1] + k - missingCountTill(l - 1, nums);
    }

    private int missingCountTill(int index, int[] nums) {
        return nums[index] - nums[0] - index;
    }

    public static void main(String[] args) {
        KthMissingNum k = new KthMissingNum();
        System.out.println(k.missingElement(new int[]{4, 7, 9, 10}, 1));
        System.out.println(k.missingElement(new int[]{4, 7, 9, 10}, 3));
        System.out.println(k.missingElement(new int[]{1, 2, 4}, 3));
    }

}
