package leetcode.microsoft;

import leetcode.base.ListNode;

import java.util.HashMap;
import java.util.Map;

public class MSSwapLinkedListPairs {

    public static void main(String[] args) {
    /*
    Given 1->2->3->4, you should return the list as 2->1->4->3.
     */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode result = swapPairs(node1);
        System.out.println();
    }

    public static ListNode swapPairs(ListNode head) {
        int n = 0;
        ListNode nextNode = head;
        Map<Integer, ListNode> nodesMap = new HashMap<>();
        Map<ListNode, ListNode> prevs = new HashMap<>();
        while (nextNode.next != null) {
            n++;
            prevs.put(nextNode.next, nextNode);
            nodesMap.put(n, nextNode);
            nextNode = nextNode.next;
        }
        n++;
        nodesMap.put(n, nextNode);
        for (int i = 1; i <= n; i += 2) {
            if (nodesMap.containsKey(i) && nodesMap.containsKey(i + 1)) {
                ListNode A = null;
                ListNode B = null;
                ListNode nodeI = nodesMap.get(i);
                ListNode nodeIPlusOne = nodesMap.get(i + 1);
                nodeIPlusOne.next = nodeI;
                if (nodesMap.containsKey(i - 1)) {
                    A = nodesMap.get(i - 1);
                    A.next = nodeIPlusOne;
                }
                if (nodesMap.containsKey(i + 2)) {
                    B = nodesMap.get(i + 2);
                    nodeI.next = B;
                }
            }
        }
        return nodesMap.containsKey(2) ? nodesMap.get(2) : head;
    }
}
