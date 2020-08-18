package leetcode.companies.google;

/**
 * Merge k Sorted Lists
 * Given an array of linked-lists lists, each linked list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sort linked-list and return it.
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

    static class ListNode {
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
        int n = lists.length;
        ListNode beforeHead = new ListNode();
        ListNode curr = beforeHead;
        boolean anyChange = true;
        while (anyChange) {
            anyChange = false;
            int minIndex = 0;
            ListNode min = null;
            for (int i = 0; i < n; i++) {
                if (
                        lists[i] != null &&
                                (
                                        min == null
                                                ||
                                                min.val > lists[i].val
                                )
                ) {
                    min = lists[i];
                    minIndex = i;
                    anyChange = true;
                }
            }
            if (anyChange) {
                curr.next = min;
                lists[minIndex] = min.next;
                min.next = null;
                curr = curr.next;
            }
        }
        return beforeHead.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists m = new MergeKSortedLists();
        ListNode[] lists = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };
        System.out.println(m.mergeKLists(lists));
    }

}
