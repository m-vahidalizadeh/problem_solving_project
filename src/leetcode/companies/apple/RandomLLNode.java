package leetcode.companies.apple;

import leetcode.base.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Linked List Random Node
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * <p>
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
 * <p>
 * Example:
 * <p>
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class RandomLLNode {

    Map<Integer, ListNode> nodesMap;
    int size;

    public RandomLLNode(ListNode head) {
        size = 0;
        nodesMap = new HashMap<>();
        while (head != null) {
            nodesMap.put(size++, head);
            head = head.next;
        }
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        Random r = new Random();
        return nodesMap.get(r.nextInt(size)).val;
    }

    public static void main(String[] args) {
        // Init a singly linked list [1,2,3].
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        RandomLLNode r = new RandomLLNode(head);
// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
        System.out.println(r.getRandom());
    }

}
