package leetcode.companies.uber;

import java.util.*;

/**
 * 1019. Next Greater Node In Linked List
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
 *
 * Example 1:
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 */
public class NextLargerNode {

    public int[] nextLargerNodes(ListNode head) {
        Stack<int[]> stack = new Stack<>(); // val,idx
        int i = 0;
        List<int[]> list = new ArrayList<>(); // larger,idx
        while (head != null) {
            int val = head.val;
            while (!stack.isEmpty() && stack.peek()[0] < val) {
                int[] curr = stack.pop();
                list.add(new int[]{val, curr[1]});
            }
            stack.add(new int[]{val, i++});
            head = head.next;
        }
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            list.add(new int[]{0, curr[1]});
        }
        int[] answer = new int[list.size()];
        for (int[] entry : list) {
            answer[entry[1]] = entry[0];
        }
        return answer;
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
