package leetcode.medium;

/**
 * 1669. Merge In Between Linked Lists
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * The blue edges and nodes in the following figure incidate the result:
 *
 * Build the result list and return its head.
 *
 * Example 1:
 *
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
 * Example 2:
 *
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 *
 * Constraints:
 *
 * 3 <= list1.length <= 104
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 104
 */
public class MergeInBetween {

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

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // c1->c2->c3->c4
        ListNode head = list1;
        int i = 0;
        ListNode c1 = null, c2 = null, c3 = null, c4 = null;
        while (list1 != null) {
            if (i == a - 1) c1 = list1;
            if (i == b + 1) {
                c4 = list1;
                break;
            }
            // Next
            list1 = list1.next;
            i++;
        }
        i = 0;
        while (list2 != null) {
            if (i == 0) c2 = list2;
            if (list2.next == null) c3 = list2;
            // Next
            list2 = list2.next;
            i++;
        }
        c1.next = c2;
        c3.next = c4;
        return head;
    }

}
