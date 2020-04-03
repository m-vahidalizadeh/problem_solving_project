package base;

public class TreeNode {

    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer x) {
        val = x;
    }

    public TreeNode(TreeNode treeNode) {
        if (treeNode != null) {
            this.val = treeNode.val;
            this.left = new TreeNode(treeNode.left);
            this.right = new TreeNode(treeNode.right);
        }
    }

}
