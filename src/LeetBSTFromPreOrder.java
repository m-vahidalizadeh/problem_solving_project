import base.TreeNode;

public class LeetBSTFromPreOrder {

    public static void main(String[] args) {
        LeetBSTFromPreOrder leetBSTFromPreOrder = new LeetBSTFromPreOrder();
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = leetBSTFromPreOrder.bstFromPreorder(preorder);
        System.out.println();
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        int n = preorder.length;
        if (n == 0) {
            return root;
        }
        for (int i = 0; i < n; i++) {
            root = insertIntoBST(root, preorder[i]);
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode currentNode = root;
        if (root == null) {
            return new TreeNode(val);
        } else {
            boolean inserted = false;
            while (!inserted) {
                int currentVal = currentNode.val;
                if (val < currentVal) {
                    TreeNode left = currentNode.left;
                    if (left == null) {
                        currentNode.left = new TreeNode(val);
                        inserted = true;
                    } else {
                        currentNode = left;
                    }
                } else {
                    TreeNode right = currentNode.right;
                    if (right == null) {
                        currentNode.right = new TreeNode(val);
                        inserted = true;
                    } else {
                        currentNode = right;
                    }
                }
            }
        }
        return root;
    }

}
