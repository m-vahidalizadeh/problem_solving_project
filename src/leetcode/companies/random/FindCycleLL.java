package leetcode.companies.random;

import leetcode.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Linked List Cycle II
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * <p>
 * Note: Do not modify the linked list.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 * <p>
 * Follow-up:
 * Can you solve it without using extra space?
 */
public class FindCycleLL {

    Set<ListNode> seen;

    public ListNode detectCycle(ListNode head) {
        seen = new HashSet<>();
        while (head != null) {
            if (seen.contains(head)) {
                return head;
            } else {
                seen.add(head);
                head = head.next;
            }
        }
        return head;
    }

}
