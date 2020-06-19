package leetcode.companies.random;

import leetcode.base.ListNode;

import java.util.Stack;

/**
 * Reorder List
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class LLReorder {

    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode currNode = head;
        Stack<ListNode> s = new Stack<>();
        while (currNode != null) {
            s.push(currNode);
            currNode = currNode.next;
        }
        int n = s.size();
        ListNode next = null;
        ListNode prev = null;
        for (int i = 0; i < n / 2; i++) {
            next = head.next;
            ListNode sTop = s.pop();
            if (prev != null) prev.next = head;
            head.next = sTop;
            sTop.next = null;
            prev = sTop;
            head = next;
        }
        if (!(n % 2 == 0)) {
            if (prev != null) prev.next = head;
            head.next = null;
        }
    }

}
