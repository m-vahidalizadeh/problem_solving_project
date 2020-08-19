package leetcode.companies.google;

/**
 * Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        int i1 = 0, i2 = 0;
        ListNode beforeHead = new ListNode();
        ListNode curr = beforeHead;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                ListNode next = l2.next;
                l2.next = null;
                curr.next = l2;
                curr = curr.next;
                l2 = next;
            } else if (l2 == null) {
                ListNode next = l1.next;
                l1.next = null;
                curr.next = l1;
                curr = curr.next;
                l1 = next;
            } else {
                if (l2.val < l1.val) {
                    ListNode next = l2.next;
                    l2.next = null;
                    curr.next = l2;
                    curr = curr.next;
                    l2 = next;
                } else {
                    ListNode next = l1.next;
                    l1.next = null;
                    curr.next = l1;
                    curr = curr.next;
                    l1 = next;
                }
            }
        }
        return beforeHead.next;
    }

}
