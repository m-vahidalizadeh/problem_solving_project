package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * <p>
 * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 * <p>
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 * <p>
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * Example 4:
 * <p>
 * Input: head = []
 * Output: []
 * Explanation: Given linked list is empty (null pointer), so return null.
 * <p>
 * Constraints:
 * <p>
 * -10000 <= Node.val <= 10000
 * Node.random is null or pointing to a node in the linked list.
 * The number of nodes will not exceed 1000.
 */
public class CopyListWithRandomPointers {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    Map<Node, Node> clonedNodes;

    public Node copyRandomList(Node head) {
        clonedNodes = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node clonedNode = getClonedNode(curr);
            clonedNode.next = getClonedNode(curr.next);
            clonedNode.random = getClonedNode(curr.random);
            curr = curr.next;
        }
        return clonedNodes.get(head);
    }

    private Node getClonedNode(Node node) {
        if (node == null) return null;
        if (clonedNodes.containsKey(node)) return clonedNodes.get(node);
        Node clonedNode = new Node(node.val);
        clonedNodes.put(node, clonedNode);
        return clonedNode;
    }

}
