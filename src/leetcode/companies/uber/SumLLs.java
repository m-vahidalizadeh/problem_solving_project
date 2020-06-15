package leetcode.companies.uber;

import leetcode.base.ListNode;

/**
 * Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class SumLLs {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode resultHead = null;
        ListNode currNode = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode tempNode = new ListNode(sum % 10);
            if (resultHead == null) {
                resultHead = tempNode;
                currNode = tempNode;
            } else {
                currNode.next = tempNode;
                currNode = tempNode;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode tempNode = new ListNode(sum % 10);
            currNode.next = tempNode;
            currNode = tempNode;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode tempNode = new ListNode(sum % 10);
            currNode.next = tempNode;
            currNode = tempNode;
            l2 = l2.next;
        }
        if (carry > 0) {
            currNode.next = new ListNode(1);
        }
        return resultHead;
    }

}
