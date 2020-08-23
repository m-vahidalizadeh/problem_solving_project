package leetcode.companies.google;

/**
 * Count Complete Tree Nodes
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Note:
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Example:
 * <p>
 * Input:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * Output: 6
 */
public class CountNodes {

    public static class TreeNode {
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

    private int first = 0;
    private int last = 0;

    public int getDepth(TreeNode node) {
        int d = 0;
        while (node.left != null) {
            node = node.left;
            d++;
        }
        return d;
    }

    public boolean doesExist(int target, int d, TreeNode node) {
        int l = first, r = last, pivot = 0;
        for (int i = 0; i < d; i++) {
            pivot = l + (r - l) / 2;
            if (target <= pivot) {
                r = pivot;
                node = node.left;
            } else {
                l = pivot + 1;
                node = node.right;
            }
        }
        return node != null;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int d = getDepth(root);
        if (d == 0) return 1;
        first = 1 << d;
        last = (first << 1) - 1;
        int curr = last;
        while (true) {
            if (doesExist(curr, d, root)) return curr;
            curr--;
        }
    }

    public static void main(String[] args) {
        CountNodes c = new CountNodes();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        // Example 1
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        System.out.println(c.countNodes(node1));
    }

}
