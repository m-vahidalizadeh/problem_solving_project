package hackerrank;

import java.util.Scanner;

public class BinarySearchTreeLowestCommonAncestor {

    private static Node ans;

    /*
    class Node
    	int data;
    	Node left;
    	Node right;
	*/
    public static Node lca(Node root, int v1, int v2) {
        recurseTree(root, v1, v2);
        return ans;
    }

    private static boolean recurseTree(Node currentNode, int v1, int v2) {
        if (currentNode == null)
            return false;
        int left = recurseTree(currentNode.left, v1, v2) ? 1 : 0;
        int right = recurseTree(currentNode.right, v1, v2) ? 1 : 0;
        int mid = (currentNode.data == v1 || currentNode.data == v2) ? 1 : 0;
        if (mid + left + right >= 2)
            ans = currentNode;
        return (mid + left + right > 0);
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
            int v1 = scan.nextInt();
            int v2 = scan.nextInt();
            scan.close();
            Node ans = lca(root, v1, v2);
            System.out.println(ans.data);
        }
    }

}
