package leetcode.medium;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Reduce Array Size to The Half
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 * <p>
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
 * Example 2:
 * <p>
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * Example 3:
 * <p>
 * Input: arr = [1,9]
 * Output: 1
 * Example 4:
 * <p>
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 * Example 5:
 * <p>
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 */
public class ReduceArraySizeToHalf {

    public static void main(String[] args) {
        ReduceArraySizeToHalf r = new ReduceArraySizeToHalf();
        int[] arr1 = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(r.minSetSize(arr1));
        int[] arr2 = {7, 7, 7, 7, 7, 7};
        System.out.println(r.minSetSize(arr2));
        int[] arr3 = {1, 9};
        System.out.println(r.minSetSize(arr3));
        int[] arr4 = {1000, 1000, 3, 7};
        System.out.println(r.minSetSize(arr4));
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(r.minSetSize(arr5));
    }

    public int minSetSize(int[] arr) {
        List<Integer> freqSorted = getSortedFrequencies(arr);
        int halfOfN = arr.length / 2;
        int counter = 0;
        int sum = 0;
        for (int currentElement : freqSorted) {
            if (sum >= halfOfN) {
                break;
            } else {
                sum += currentElement;
                counter++;
            }
        }
        return counter;
    }

    private List<Integer> getSortedFrequencies(int[] arr) {
        Map<Integer, Integer> frequencies = new ConcurrentHashMap<>();
        for (int e : arr) {
            updateMap(frequencies, e);
        }
        return frequencies.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    private void updateMap(Map<Integer, Integer> frequencies, int newNumber) {
        if (frequencies.containsKey(newNumber)) frequencies.put(newNumber, frequencies.get(newNumber) + 1);
        else frequencies.put(newNumber, 1);
    }

}
