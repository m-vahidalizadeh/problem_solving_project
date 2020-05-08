package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class CombinationIterator {

    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
        System.out.println(iterator.next()); // returns "ab"
        System.out.println(iterator.hasNext()); // returns true
        System.out.println(iterator.next()); // returns "ac"
        System.out.println(iterator.hasNext()); // returns true
        System.out.println(iterator.next()); // returns "bc"
        System.out.println(iterator.hasNext()); // returns false
    }

    private Queue<String> combinations;

    public CombinationIterator(String characters, int combinationLength) {
        combinations = new LinkedList<>();
        int n = characters.length();
        generateCombinations(characters, 0, combinationLength, n, "", 0);
    }

    private void generateCombinations(String characters, int index, int combinationLength, int n, String sub, int subLength) {
        if (subLength == combinationLength) {
            combinations.add(sub);
        } else if (index < n) {
            int newIndex = index + 1;
            generateCombinations(characters, newIndex, combinationLength, n, sub + characters.charAt(index), subLength + 1);
            generateCombinations(characters, newIndex, combinationLength, n, sub, subLength);
        }
    }

    public String next() {
        return combinations.poll();
    }

    public boolean hasNext() {
        return !combinations.isEmpty();
    }

}
