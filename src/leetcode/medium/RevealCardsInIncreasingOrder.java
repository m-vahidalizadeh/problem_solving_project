package leetcode.medium;

import java.util.*;

import static leetcode.base.Utils.printArray;

/**
 * Reveal Cards In Increasing Order
 * In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.
 * <p>
 * Initially, all the cards start face down (unrevealed) in one deck.
 * <p>
 * Now, you do the following steps repeatedly, until all cards are revealed:
 * <p>
 * Take the top card of the deck, reveal it, and take it out of the deck.
 * If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
 * If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 * <p>
 * The first entry in the answer is considered to be the top of the deck.
 * <p>
 * Example 1:
 * <p>
 * Input: [17,13,11,2,3,5,7]
 * Output: [2,13,3,11,5,17,7]
 * Explanation:
 * We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
 * After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
 * We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
 * We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
 * We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
 * We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
 * We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
 * We reveal 13, and move 17 to the bottom.  The deck is now [17].
 * We reveal 17.
 * Since all the cards revealed are in increasing order, the answer is correct.
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 1000
 * 1 <= A[i] <= 10^6
 * A[i] != A[j] for all i != j
 */
public class RevealCardsInIncreasingOrder {

    public int[] deckRevealedIncreasing(int[] deck) {
        int nMinusOne = deck.length - 1;
        Arrays.sort(deck);
        Queue<Integer> q = new LinkedList<>();
        int qSize = 0;
        int index = nMinusOne;
        for (int i = nMinusOne; i >= 0; i--) {
            if (qSize++ > 1) q.add(q.poll());
            q.add(deck[index--]);
        }
        index = nMinusOne;
        while (!q.isEmpty()) deck[index--] = q.poll();
        return deck;
    }

    public static void main(String[] args) {
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        RevealCardsInIncreasingOrder r = new RevealCardsInIncreasingOrder();
        System.out.println("Input:");
        printArray(deck);
        System.out.println("Output:");
        printArray(r.deckRevealedIncreasing(deck));
    }

}
