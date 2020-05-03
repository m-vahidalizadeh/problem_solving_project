package leetcode.mixed;

import leetcode.base.TreeNode;

public class LeetFindTargetInCloneBT {

    private boolean isFound;
    private String path;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node2.left = node3;
        TreeNode node1c = new TreeNode(1);
        TreeNode node2c = new TreeNode(2);
        TreeNode node3c = new TreeNode(3);
        node1c.left = node2c;
        node2c.left = node3c;
        LeetFindTargetInCloneBT leetFindTargetInCloneBT = new LeetFindTargetInCloneBT();
        leetFindTargetInCloneBT.getTargetCopy(node1, node1c, node3);
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return cloned;
        }
        TreeNode l = original.left;
        TreeNode r = original.right;
        if (l == null && r == null) {
            return cloned;
        }
        isFound = false;
        path = "";
        findPath("", original, target);

        char[] pathChars = path.toCharArray();
        TreeNode theNode = cloned;
        for (int i = 0; i < pathChars.length; i++) {
            if ('l' == pathChars[i]) {
                theNode = theNode.left;
            } else {
                theNode = theNode.right;
            }
        }

        return theNode;
    }

    private void findPath(String currentPath, TreeNode node, TreeNode target) {
        if (isFound) {
            return;
        }
        if (node.val == target.val) {
            path = currentPath;
            isFound = true;
            return;
        } else {
            TreeNode l = node.left;
            TreeNode r = node.right;
            if (r != null) {
                findPath(currentPath + "r", r, target);
            }
            if (l != null) {
                findPath(currentPath + "l", l, target);
            }
        }
    }

}
