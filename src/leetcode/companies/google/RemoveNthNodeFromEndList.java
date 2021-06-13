package leetcode.companies.google;

/**
 * 19. Remove Nth Node From End of List
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEndList {

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
        ListNode p1Pre = null;
        ListNode p1 = head;
        ListNode p2 = head;
        int count = 1;
        while (p2 != null) {
            if (count >= n + 1) {
                p1Pre = p1;
                p1 = p1.next;
            }
            p2 = p2.next;
            count++;
        }
        if (p1Pre == null) return head.next;
        if (p1.next == null) {
            p1Pre.next = null;
            return head;
        }
        p1Pre.next = p1Pre.next.next;
        return head;
    }

}
