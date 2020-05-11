package leetcode.medium;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
