package leetcode.companies.bloomberg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Palindrome Linked List
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class IsPalindromeLL {

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

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        else if (head.next == null) return true;
        Queue<ListNode> q = new LinkedList<>();
        Stack<ListNode> s = new Stack<>();
        int size = 0;
        while (head != null) {
            q.add(head);
            s.add(head);
            head = head.next;
            size++;
        }
        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            if (q.poll().val != s.pop().val) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindromeLL i = new IsPalindromeLL();
        ListNode example1 = new ListNode(1, new ListNode(2));
        System.out.println(i.isPalindrome(example1));
        ListNode example2 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println(i.isPalindrome(example2));
    }

}
