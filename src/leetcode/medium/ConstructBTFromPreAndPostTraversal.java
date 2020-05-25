package leetcode.medium;

import leetcode.base.TreeNode;

import java.util.Arrays;

public class ConstructBTFromPreAndPostTraversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = pre.length;
        if (n == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (n == 1) return root;
        /*
        Pre: Root (LRoot LL LR) (RRoot RL RR)
        Post: (LL LR LRoot) (RL RR RRoot) Root
        int preLRoot = 1;
        int postRRoot = n - 2;
         */
        int preRRootIndex;
        for (preRRootIndex = 0; preRRootIndex < post.length; preRRootIndex++) {
            if (pre[preRRootIndex] == post[n - 2]) break;
        }
        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, preRRootIndex), Arrays.copyOfRange(post, 0, preRRootIndex - 1));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, preRRootIndex, n), Arrays.copyOfRange(post, preRRootIndex - 1, n - 1));
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {3, 4, 1, 2};
        int[] post = {1, 4, 2, 3};
        ConstructBTFromPreAndPostTraversal c = new ConstructBTFromPreAndPostTraversal();
        TreeNode root = c.constructFromPrePost(pre, post);
        System.out.println();
    }

}
