package leetcode.companies.amazon;

/**
 * 25. Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * Follow up:
 * <p>
 * Could you solve the problem in O(1) extra memory space?
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 * <p>
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 * <p>
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 * <p>
 * Input: head = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesInKGroup {

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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode beforeKHead = fakeHead;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        ListNode kHead = head;
        int count = 1;
        while (curr != null) {
            if (count == 1) {
                if (prev != null) beforeKHead = prev;
                kHead = curr;
                // Next
                count++;
                prev = curr;
                curr = next;
                if (curr != null) next = curr.next;
            } else if (count == k) {
                reverse(beforeKHead, kHead, k);
                kHead.next = next;
                count = 1;
                prev = kHead;
                curr = next;
                if (curr != null) next = curr.next;
            } else {
                // Next
                count++;
                prev = curr;
                curr = next;
                if (curr != null) next = curr.next;
            }
        }
        return fakeHead.next == null ? head : fakeHead.next;
    }

    private void reverse(ListNode beforeHead, ListNode head, int k) {
        ListNode curr = beforeHead.next;
        ListNode next = head.next;
        for (int i = 0; i < k; i++) {
            if (i == 0) {
                curr = next;
                if (curr != null) next = curr.next;
            } else {
                ListNode n = beforeHead.next;
                beforeHead.next = curr;
                beforeHead.next.next = n;
                curr = next;
                if (curr != null) next = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup r = new ReverseNodesInKGroup();
        System.out.println(r.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
        System.out.println(r.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 3));
        System.out.println(r.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1));
        System.out.println(r.reverseKGroup(new ListNode(1), 1));
        System.out.println(r.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))), 4));
    }

}
