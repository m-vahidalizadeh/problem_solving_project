public class TreeNode {

    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode(Integer x) {
        val = x;
    }

    TreeNode(TreeNode treeNode) {
        if (treeNode != null) {
            this.val = treeNode.val;
            this.left = new TreeNode(treeNode.left);
            this.right = new TreeNode(treeNode.right);
        }
    }

}
