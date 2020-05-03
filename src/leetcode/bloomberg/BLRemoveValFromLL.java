package leetcode.bloomberg;
import leetcode.base.ListNode;

public class BLRemoveValFromLL {

    /*
    Input:  1->2->6->3->4->5->6, val = 6
    Output: 1->2->3->4->5
     */

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6_1 = new ListNode(6);
        ListNode node6_2 = new ListNode(6);
        ListNode head = node1;
        node1.next = node2;
        node2.next = node6_1;
        node6_1.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6_2;
        System.out.println(removeElements(head, 6));
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode currentNode = head;
        ListNode previousNode = null;
        while (currentNode != null) {
            if (currentNode.val == val) {
                if (previousNode == null) {
                    // Remove head
                    previousNode = null;
                    head = currentNode.next;
                    currentNode = currentNode.next;
                } else if (currentNode.next == null) {
                    // Remove tail
                    previousNode.next = null;
                    currentNode = null;
                } else {
                    previousNode.next = currentNode.next;
                    currentNode = currentNode.next;
                }
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        return head;
    }

}
