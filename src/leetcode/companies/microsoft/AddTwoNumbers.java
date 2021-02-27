package leetcode.companies.microsoft;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers {

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> d1 = new ArrayDeque<>();
        Deque<Integer> d2 = new ArrayDeque<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                d1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                d2.push(l2.val);
                l2 = l2.next;
            }
        }
        ListNode curr = new ListNode();
        int carryOver = 0;
        while (!d1.isEmpty() || !d2.isEmpty()) {
            int a = d1.isEmpty() ? 0 : d1.pop();
            int b = d2.isEmpty() ? 0 : d2.pop();
            int sum = carryOver + a + b;
            if (sum > 9) carryOver = 1;
            else carryOver = 0;
            sum = sum % 10;
            curr.val = sum;
            ListNode temp = new ListNode();
            temp.next = curr;
            curr = temp;
        }
        if (carryOver == 1) {
            curr.val = 1;
            ListNode temp = new ListNode();
            temp.next = curr;
            curr = temp;
        }
        return curr.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers a = new AddTwoNumbers();
        System.out.println(a.addTwoNumbers(
                new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))))
                ,
                new ListNode(5, new ListNode(6, new ListNode(4))))
        );
    }

}
