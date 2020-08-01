package leetcode.companies.apple;

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
public class GetRandomNodeFromSinglyLL {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    int size;
    Map<Integer, Integer> nodesMap;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public GetRandomNodeFromSinglyLL(ListNode head) {
        int counter = 0;
        nodesMap = new HashMap<>();
        while (head != null) {
            nodesMap.put(counter, head.val);
            counter++;
            head = head.next;
        }
        this.size = counter;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        Random random = new Random();
        return nodesMap.get(random.nextInt(size));
    }

}
