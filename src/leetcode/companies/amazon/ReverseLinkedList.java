package leetcode.companies.amazon;

/**
 * 206. Reverse Linked List
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

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

    public ListNode reverseList(ListNode head) {
        ListNode fakeHead = new ListNode();
        ListNode curr = head;
        ListNode next = null;
        ListNode n = null;
        while (curr != null) {
            next = curr.next;
            n = fakeHead.next;
            fakeHead.next = curr;
            curr.next = n;
            curr = next;
        }
        return fakeHead.next;
    }

}
