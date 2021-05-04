package leetcode.companies.facebook;

import java.util.LinkedList;

/**
 * 143. Reorder List
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 */
public class ReorderList {

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

    public void reorderList(ListNode head) {
        LinkedList<ListNode> q = new LinkedList<>();
        ListNode curr = head;
        while (curr != null) {
            q.add(curr);
            curr = curr.next;
        }
        while (!q.isEmpty()) {
            ListNode first = q.pollFirst();
            ListNode last = null;
            if (!q.isEmpty()) last = q.pollLast();
            if (q.isEmpty()) first.next = null;
            if (last != null) last.next = first.next;
            first.next = last;
        }
    }

}
