package hackerrank;

import java.util.Scanner;

public class HeightofaBinaryTree {

    /*
    class Node
        int data;
        Node left;
        Node right;
    */
    public static int height(Node root) {
        Node right = root.right;
        Node left = root.left;
        if (left == null && right == null) {
            return 0;
        } else if (right == null) {
            return 1 + height(left);
        } else if (left == null) {
            return 1 + height(right);
        }
        return 1 + Math.max(height(left), height(right));
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
        try (
                Scanner scan = new Scanner(System.in)
        ) {
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


