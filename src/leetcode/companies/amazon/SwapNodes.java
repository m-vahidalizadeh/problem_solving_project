package leetcode.companies.amazon;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10^5
 * 0 <= Node.val <= 100
 */
public class SwapNodes {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode node1 = head;
        for (int i = 1; i < k; i++) node1 = node1.next; // find the kth node and save it in node1
        ListNode node2 = node1, prevK = head; // head is k element behind the current node, keep prevK pointing to kth element behind the current node
        while (node2.next != null) { // go till you find the last element in the list
            node2 = node2.next; // advance list pointer
            prevK = prevK.next; // advance kth element behind the list pointer
        }
        int temp = node1.val; // Swap kth from left node and kth from right node values
        node1.val = prevK.val;
        prevK.val = temp;
        return head; // Return the existing head
    }

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

}
