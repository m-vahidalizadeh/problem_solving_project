package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class CountSmaller {

    ArrayList<Integer> result;
    Number[] numbers;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        result = new ArrayList<>();
        for (int i = 0; i < n; i++) result.add(0);
        numbers = new Number[n];
        for (int i = 0; i < n; i++) numbers[i] = new Number(i, nums[i]);
        mergeSort(0, n);
        return result;
    }

    private void mergeSort(int l, int r) { // [l,r) doesn't include r
        if (r - l <= 1) return;
        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int i = l;
        int j = mid;
        List<Number> temp = new ArrayList<>();
        while (i < mid && j < r) {
            if (numbers[i].num <= numbers[j].num) { // till j, all the elements in the right half were smaller that i
                result.set(numbers[i].index, result.get(numbers[i].index) + j - mid); // now when we write i in the temp, j-mid elements are already in the temp
                temp.add(numbers[i++]);
            } else temp.add(numbers[j++]);
        }
        while (i < mid) {
            result.set(numbers[i].index, result.get(numbers[i].index) + j - mid);
            temp.add(numbers[i++]);
        }
        while (j < r) temp.add(numbers[j++]);
        for (int k = l; k < r; k++) numbers[k] = temp.get(k - l);
    }

    public class Number {
        int index;
        int num;

        public Number(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

}
