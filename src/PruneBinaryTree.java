import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PruneBinaryTree {

    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();

    public static void main(String[] args) {
        PruneBinaryTree pruneBinaryTree = new PruneBinaryTree();
        TreeNode nodeA = new TreeNode(1);
        TreeNode nodeB = new TreeNode(0);
        TreeNode nodeC = new TreeNode(1);
        TreeNode nodeD = new TreeNode(0);
        TreeNode nodeE = new TreeNode(0);
        TreeNode nodeF = new TreeNode(0);
        TreeNode nodeG = new TreeNode(1);
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        TreeNode result = pruneBinaryTree.pruneTree(nodeA);
        System.out.println();
    }

    public TreeNode pruneTree(TreeNode root) {
        prune(root);
        return root;
    }

    public void prune(TreeNode currentNode) {
        TreeNode left = currentNode.left;
        TreeNode right = currentNode.right;
        if (left != null) {
            parents.put(left, currentNode);
        }
        if (right != null) {
            parents.put(right, currentNode);
        }
        if (left == null && right == null) {
            if (currentNode.val == 0) {
                TreeNode parent = parents.get(currentNode);
                if (parent.left != null && parent.left.equals(currentNode)) {
                    parent.left = null;
                    return;
                } else {
                    parent.right = null;
                    return;
                }
            } else {
                return;
            }
        }
        if ((right == null && visited.contains(left)) ||
                (visited.contains(right) && left == null) ||
                (visited.contains(left) && visited.contains(right))) {
            return;
        }
        if (left != null && !visited.contains(left)) {
            prune(left);
            visited.add(left);
        }
        if (right != null && !visited.contains(right)) {
            prune(right);
            visited.add(right);
        }
        if (!visited.contains(currentNode)) {
            prune(currentNode);
            visited.add(currentNode);
        }
    }

}
