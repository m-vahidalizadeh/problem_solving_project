package leetcode.companies.random;

import java.util.HashMap;
import java.util.Map;

/**
 * 914. X of a Kind in a Deck of Cards
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 * Example 1:
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 *
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false´
 * Explanation: No possible partition.
 * Example 3:
 *
 * Input: deck = [1]
 * Output: false
 * Explanation: No possible partition.
 * Example 4:
 *
 * Input: deck = [1,1]
 * Output: true
 * Explanation: Possible partition [1,1].
 * Example 5:
 *
 * Input: deck = [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2].
 *
 * Constraints:
 *
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 */
public class HasGroupSizeX {

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : deck) freq.put(n, freq.getOrDefault(n, 0) + 1);
        for (int f : freq.values()) {
            if (f < 2) return false;
        }
        int size = 2;
        int n = deck.length;
        while (size <= n) {
            if (canSatisfy(freq, size)) return true;
            size++;
        }
        return false;
    }

    private boolean canSatisfy(Map<Integer, Integer> freq, int size) {
        for (int n : freq.values()) {
            if (n == size || n % size == 0) continue;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HasGroupSizeX h = new HasGroupSizeX();
        System.out.println(h.hasGroupsSizeX(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
        System.out.println(h.hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
        System.out.println(h.hasGroupsSizeX(new int[]{1}));
        System.out.println(h.hasGroupsSizeX(new int[]{1, 1}));
        System.out.println(h.hasGroupsSizeX(new int[]{1, 1, 2, 2, 2, 2}));
    }

}
