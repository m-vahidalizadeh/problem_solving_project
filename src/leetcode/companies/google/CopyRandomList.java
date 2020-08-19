package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Copy List with Random Pointer
 * Medium
 * <p>
 * 3532
 * <p>
 * 688
 * <p>
 * Add to List
 * <p>
 * Share
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
 * Number of Nodes will not exceed 1000.
 */
public class CopyRandomList {
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

    Map<Node, Node> clones = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node clone = getClone(head);
        clone.random = getClone(head.random);
        clone.next = copyRandomList(head.next);
        return clone;
    }

    private Node getClone(Node node) {
        if (node == null) return null;
        if (clones.containsKey(node)) {
            return clones.get(node);
        }
        Node clone = new Node(node.val);
        clones.put(node, clone);
        return clone;
    }

}
