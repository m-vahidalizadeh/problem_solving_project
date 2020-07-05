package leetcode.companies.adobe;

import java.util.Stack;

/**
 * Remove Nth Node From End of List
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromLL {

    public static class ListNode {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || (head.next == null)) return null;
        Stack<ListNode> s = new Stack<>();
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            prev = curr;
            s.push(prev);
            curr = curr.next;
        }
        for (int i = 1; i <= n - 1; i++) {
            s.pop();
        }
        ListNode toBeRemoved = s.pop();
        ListNode next = toBeRemoved.next;
        if (s.isEmpty()) {
            // Removing the head
            head = head.next;
        } else {
            prev = s.peek();
            prev.next = next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromLL r = new RemoveNthNodeFromLL();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(r.removeNthFromEnd(head, 2));
    }

}
