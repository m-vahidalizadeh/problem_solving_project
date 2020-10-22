package leetcode.companies.amazon;

/**
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together
 * the nodes of the first two lists.
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [1,2,4], l2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * <p>
 * Input: l1 = [], l2 = []
 * Output: []
 * Example 3:
 * <p>
 * Input: l1 = [], l2 = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both l1 and l2 are sorted in non-decreasing order.
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
        ListNode fakeHead = new ListNode();
        ListNode curr = fakeHead;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int n2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (n1 <= n2) {
                ListNode node = new ListNode(n1);
                curr.next = node;
                curr = node;
                l1 = l1.next;
            } else {
                ListNode node = new ListNode(n2);
                curr.next = node;
                curr = node;
                l2 = l2.next;
            }
        }
        return fakeHead.next;
    }

}
