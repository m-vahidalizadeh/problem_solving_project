package hackerrank;

import java.util.Scanner;

class BinarySearchTreeInsertion {

    public static void preOrder(Node root) {

        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            insertIntoBST(root, data);
            return root;
        }
    }

    private static void insertIntoBST(Node currentNode, int data) {
        int currentNodeVal = currentNode.data;
        if (data < currentNodeVal) {
            Node left = currentNode.left;
            if (left == null) {
                currentNode.left = new Node(data);
            } else {
                insertIntoBST(left, data);
            }
        } else if (data > currentNodeVal) {
            Node right = currentNode.right;
            if (right == null) {
                currentNode.right = new Node(data);
            } else {
                insertIntoBST(right, data);
            }
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
            preOrder(root);
        }
    }
}
