package leetcode.companies.bloomberg;

import java.util.ArrayList;
import java.util.List;

/**
 * Populating Next Right Pointers in Each Node II
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Follow up:
 * <p>
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 */
public class BTSetNext {

    public Node connect(Node root) {
        if (root == null) return root;
        List<Node> currLevel = new ArrayList<>();
        List<Node> nextLevel = new ArrayList<>();
        currLevel.add(root);
        while (!currLevel.isEmpty()) {
            for (Node n : currLevel) {
                if (n.left != null) nextLevel.add(n.left);
                if (n.right != null) nextLevel.add(n.right);
            }
            for (int i = 0; i < nextLevel.size() - 1; i++) nextLevel.get(i).next = nextLevel.get(i + 1);
            currLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
