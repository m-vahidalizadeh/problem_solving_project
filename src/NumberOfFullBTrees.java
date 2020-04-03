import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class NumberOfFullBTrees {

    public static void main(String[] args) {
        int N = 5;
        NumberOfFullBTrees numberOfFullBTrees = new NumberOfFullBTrees();
        List<TreeNode> result = numberOfFullBTrees.allPossibleFBT(N);
    }

    /**
     * Builds all possible full binary trees. We start by 1 node in left, 1 note at root and n-2 node in right.
     * We move the nodes in pairs from right to left. This method uses recursion. This method can be improved by
     * using dynamic programming.
     *
     * @param N The number of nodes
     * @return The possible full binary trees with N nodes as a list
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> result = new ArrayList<>();
        if (N % 2 == 0) {
            return result;
        }
        if (N == 1) {
            result.add(new TreeNode(0));
            return result;
        }
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> lefts = allPossibleFBT(i);
            List<TreeNode> rights = allPossibleFBT(N - i - 1);
            for (TreeNode l : lefts) {
                for (TreeNode r : rights) {
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }
        return result;
    }

}
