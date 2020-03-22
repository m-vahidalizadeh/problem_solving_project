import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RemoveGreenLeaf {

    Map<TreeNode, TreeNode> lParents = new HashMap<>();
    Map<TreeNode, TreeNode> rParents = new HashMap<>();

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if ((root == null) ||
                (root.val == target && isLeaf(root))) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            if (isLeaf(currentNode)) {
                if (currentNode.val == target) {
                    if (lParents.containsKey(currentNode)) {
                        TreeNode parent = lParents.get(currentNode);
                        parent.left = null;
                        q.add(parent);
                    } else if (rParents.containsKey(currentNode)) {
                        TreeNode parent = rParents.get(currentNode);
                        parent.right = null;
                        q.add(parent);
                    } else {
                        // Delete root
                        return null;
                    }
                }
            } else {
                TreeNode l = currentNode.left;
                if (l != null) {
                    lParents.put(l, currentNode);
                    q.add(l);
                }
                TreeNode r = currentNode.right;
                if (r != null) {
                    rParents.put(r, currentNode);
                    q.add(r);
                }
            }
        }
        return root;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
