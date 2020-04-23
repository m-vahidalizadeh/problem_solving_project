package mixed;

import base.ListNode;

public class LeetAddTwoNumbers {

    public static void main(String[] args) {
        /*
Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
         */
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node2.next = node4;
        node4.next = node3;

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node4_2 = new ListNode(4);
        node5.next = node6;
        node6.next = node4_2;

        LeetAddTwoNumbers leetAddTwoNumbers = new LeetAddTwoNumbers();
        ListNode result = leetAddTwoNumbers.addTwoNumbers(node2, node5);
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultStart = null;
        ListNode result = null;
        int remaining = 0;
        while (l1 != null || l2 != null) {
            int n = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + remaining;
            remaining = n / 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            if (result == null) {
                result = new ListNode(n % 10);
                resultStart = result;
            } else {
                result.next = new ListNode(n % 10);
                result = result.next;
            }
        }
        if (remaining > 0) {
            result.next = new ListNode(remaining);
        }
        return resultStart;
    }

}
