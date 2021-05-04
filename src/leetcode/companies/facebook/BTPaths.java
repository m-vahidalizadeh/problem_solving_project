package leetcode.companies.facebook;

import java.util.*;

/**
 * 257. Binary Tree Paths
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * Example 2:
 *
 * Input: root = [1]
 * Output: ["1"]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 */
public class BTPaths {

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

    public class Path {
        String curr;
        TreeNode node;

        public Path(String curr, TreeNode node) {
            this.curr = curr;
            this.node = node;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<String> paths = new ArrayList<>();
        Deque<Path> q = new ArrayDeque<>();
        q.add(new Path("", root));
        while (!q.isEmpty()) {
            Path curr = q.poll();
            if (curr.node.left == null && curr.node.right == null) {
                if ("".equals(curr.curr)) paths.add("" + curr.node.val);
                else paths.add(curr.curr + "->" + curr.node.val);
            } else {
                String newPath;
                if ("".equals(curr.curr)) newPath = "" + curr.node.val;
                else newPath = curr.curr + "->" + curr.node.val;
                if (curr.node.left != null) q.add(new Path(newPath, curr.node.left));
                if (curr.node.right != null) q.add(new Path(newPath, curr.node.right));
            }
        }
        return paths;
    }

}
