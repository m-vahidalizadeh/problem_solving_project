package leetcode.hard;

import java.util.*;

/**
 * Recover a Tree From Preorder Traversal
 * We run a preorder depth first search on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output S of this traversal, recover the tree and return its root.
 * <p>
 * Example 1:
 * <p>
 * Input: "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * Example 2:
 * <p>
 * Input: "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 * <p>
 * Example 3:
 * <p>
 * Input: "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 * <p>
 * Note:
 * <p>
 * The number of nodes in the original tree is between 1 and 1000.
 * Each node will have a value between 1 and 10^9.
 */
public class RecoverATreeFromPreTraversal {

    public class Token {
        int label;
        int level;

        public Token(String label, int level) {
            this.label = Integer.parseInt(label);
            this.level = level;
        }
    }

    public TreeNode recoverFromPreorder(String S) {
        Queue<Token> q = new LinkedList<>();
        int i = 0;
        int n = S.length();
        while (i < n) {
            int d = 0;
            while (i < n && S.charAt(i) == '-') {
                d++;
                i++;
            }
            int j = i;
            while (j < n && S.charAt(j) != '-') j++;
            q.add(new Token(S.substring(i, j), d));
            i = j;
        }
        Deque<TreeNode> sNodes = new ArrayDeque<>();
        Deque<Integer> sLevels = new ArrayDeque<>();
        TreeNode root = new TreeNode(q.poll().label);
        sNodes.push(root);
        sLevels.push(0);
        while (!q.isEmpty()) {
            Token curr = q.poll();
            while (sLevels.peek() >= curr.level) {
                sLevels.pop();
                sNodes.pop();
            }
            TreeNode node = new TreeNode(curr.label);
            if (sNodes.peek().left == null) {
                sNodes.peek().left = node;
            } else {
                sNodes.peek().right = node;
            }
            sNodes.push(node);
            sLevels.push(curr.level);
        }
        return root;
    }

    public static void main(String[] args) {
        RecoverATreeFromPreTraversal r = new RecoverATreeFromPreTraversal();
        System.out.println(r.recoverFromPreorder("1-2--3--4-5--6--7"));
        System.out.println(r.recoverFromPreorder("1-2--3---4-5--6---7"));
        System.out.println(r.recoverFromPreorder("1-401--349---90--88"));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
