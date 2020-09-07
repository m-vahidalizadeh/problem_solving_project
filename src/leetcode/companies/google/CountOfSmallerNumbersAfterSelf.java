package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class CountOfSmallerNumbersAfterSelf {

    public class Item {
        int index;
        int value;

        public Item(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) items[i] = new Item(i, nums[i]);
        int[] counts = new int[n];
        mergeSort(items, 0, n - 1, counts);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) result.add(counts[i]);
        return result;
    }

    private void mergeSort(Item[] items, int l, int h, int[] counts) {
        if (l >= h) return;
        int mid = l + (h - l) / 2;
        mergeSort(items, l, mid, counts);
        mergeSort(items, mid + 1, h, counts);
        merge(items, l, mid, mid + 1, h, counts);
    }

    private void merge(Item[] items, int l, int lEnd, int h, int hEnd, int[] counts) {
        int m = hEnd - l + 1;
        Item[] sorted = new Item[m];
        int index = 0, lPointer = l, hPointer = h, rightCounter = 0;
        while (lPointer <= lEnd && hPointer <= hEnd) {
            if (items[lPointer].value > items[hPointer].value) {
                rightCounter++;
                sorted[index++] = items[hPointer++];
            } else {
                counts[items[lPointer].index] += rightCounter;
                sorted[index++] = items[lPointer++];
            }
        }
        while (lPointer <= lEnd) {
            counts[items[lPointer].index] += rightCounter;
            sorted[index++] = items[lPointer++];
        }
        while (hPointer <= hEnd) sorted[index++] = items[hPointer++];
        System.arraycopy(sorted, 0, items, l, m);
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        System.out.println(c.countSmaller(new int[]{5, 2, 6, 1}));
    }

}
