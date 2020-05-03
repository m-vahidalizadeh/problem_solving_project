package hackerrank;

import java.util.*;

class HeightOfABinaryTreeRecursive {

    private static int treeHeight = 0;

    public static int height(Node root) {
        getHeight(root, 0);
        return treeHeight;
    }

    private static void getHeight(Node currentNode, int currentHeight) {
        Node left = currentNode.left;
        Node right = currentNode.right;
        boolean isLeftNull = left == null;
        boolean isRightNull = right == null;
        if (isLeftNull && isRightNull) {
            // This is a leaf node. Compare its height with the tree height to see if tree height should be updated.
            if (treeHeight < currentHeight) {
                treeHeight = currentHeight;
            }
        } else {
            int newHeight = currentHeight + 1;
            // Search in the left child.
            if (!isLeftNull)
                getHeight(left, newHeight);
            // Search in the right child.
            if (!isRightNull)
                getHeight(right, newHeight);
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int t = scan.nextInt();
            Node root = null;
            while (t-- > 0) {
                int data = scan.nextInt();
                root = insert(root, data);
            }
            int height = height(root);
            System.out.println(height);
        }
    }
}