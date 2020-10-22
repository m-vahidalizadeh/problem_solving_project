package leetcode.companies.amazon;

/**
 * 23. Merge k Sorted Lists
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */
public class MergeKSortedLists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode fakeHead = new ListNode();
        ListNode curr = fakeHead;
        int n = lists.length;
        boolean allNull = false;
        while (!allNull) {
            allNull = true;
            int minIndex = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    minIndex = i;
                    min = lists[i].val;
                    allNull = false;
                }
            }
            if (!allNull) {
                ListNode node = new ListNode(min);
                curr.next = node;
                curr = node;
                lists[minIndex] = lists[minIndex].next;
            }
        }
        return fakeHead.next;
    }

}
