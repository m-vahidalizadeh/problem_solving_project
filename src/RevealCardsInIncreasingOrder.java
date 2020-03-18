import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RevealCardsInIncreasingOrder {

    public static void main(String[] args) {
        RevealCardsInIncreasingOrder revealCardsInIncreasingOrder = new RevealCardsInIncreasingOrder();
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        int[] result = revealCardsInIncreasingOrder.deckRevealedIncreasing(deck);
        for (int i = 0; i < result.length; i++) {
            System.out.format("%s ", result[i]);
        }
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        if (n == 0) {
            return new int[0];
        }
        Queue<Integer> deckQ = new LinkedList<>();
        PriorityQueue<Integer> deckPQ = new PriorityQueue<>();
        for (int i = 0; i < deck.length; i++) {
            deckQ.add(deck[i]);
            deckPQ.add(deck[i]);
        }
        int[] result = new int[n];
        int index = 0;
        while (index < n) {
            int tempElement1 = deckPQ.poll();
            result[index] = tempElement1;
            index++;
            deckQ.remove(tempElement1);
            if (index < n) {
                int tempElement2 = deckQ.poll();
                result[index] = tempElement2;
                index++;
            }
        }
        return result;
    }

}
