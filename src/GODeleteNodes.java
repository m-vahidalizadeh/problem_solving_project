import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class GODeleteNodes {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode leftChild = new TreeNode(2);
        TreeNode rightChild = new TreeNode(3);
        root.left = leftChild;
        root.right = rightChild;
        leftChild.left = new TreeNode(4);
        leftChild.right = new TreeNode(5);
        rightChild.left = new TreeNode(6);
        rightChild.right = new TreeNode(7);
        int[] to_delete = new int[]{3, 5};
        delNodes(root, to_delete);
    }

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        result.add(root);
        // Delete the to_deletes one by one
        for (int toD : to_delete) {
            // Find the right tree
            TreeNode theRightTreeRoot = null;
            for (TreeNode treeRoot : result) {
                if (isTheRightTree(toD, treeRoot)) {
                    theRightTreeRoot = treeRoot;
                    break;
                }
            }
            // TODO Handle the case that we can't find the key in any of the trees
            // Find the right node in the right tree
            TreeNode theNode = findNode(toD, theRightTreeRoot);
            TreeNode theParent = findParent(toD, theRightTreeRoot);
            // Delete the right node in the right tree
            if (theNode.right != null) result.add(theNode.right);
            if (theNode.left != null) result.add(theNode.left);
            if (theNode == theRightTreeRoot) {
                result.remove(theRightTreeRoot);
            } else {
                if (theParent.right == theNode) {
                    theParent.right = null;
                }
                if (theParent.left == theNode) {
                    theParent.left = null;
                }
            }
        }
        return result;
    }

    private static boolean isTheRightTree(int key, TreeNode root) {
        if (root == null) {
            return false;
        }
        if (key == root.val) {
            return true;
        }
        if (isTheRightTree(key, root.left)) {
            return true;
        }
        if (isTheRightTree(key, root.right)) {
            return true;
        }
        return false;
    }

    private static TreeNode findNode(int key, TreeNode root) {
        if (root == null) {
            return null;
        }
        // find the right node
        if (root.val == key) {
            return root;
        }
        TreeNode leftSearch = findNode(key, root.left);
        if (leftSearch != null) {
            return leftSearch;
        }
        TreeNode rightSearch = findNode(key, root.right);
        return rightSearch;
    }

    private static TreeNode findParent(int key, TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;
        if (leftChild == null && rightChild == null) {
            return null;
        }
        if (leftChild != null && leftChild.val == key) {
            return root;
        }
        if (rightChild != null && rightChild.val == key) {
            return root;
        }
        TreeNode leftSearch = findParent(key, leftChild);
        if (leftSearch != null) {
            return leftSearch;
        }
        TreeNode rightSearch = findParent(key, rightChild);
        if (rightSearch != null) {
            return rightSearch;
        }
        return null;
    }

}
