package leetcode.medium;

import leetcode.base.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PseudoPalindromicPathsInABTTest {

    PseudoPalindromicPathsInABT p;

    @BeforeEach
    public void initialize() {
        p = new PseudoPalindromicPathsInABT();
    }

    @Test
    public void testPseudoPalindromicPaths() {
        /*
            2
            /\
           3  1
          /\   \
         3  1   1
         */
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3_1 = new TreeNode(3);
        TreeNode node1_1 = new TreeNode(1);
        TreeNode node1_2 = new TreeNode(1);
        node2.left = node3;
        node2.right = node1;
        node3.left = node3_1;
        node3.right = node1_1;
        node1.right = node1_2;
        Assertions.assertEquals(2, p.pseudoPalindromicPaths(node2));
    }

}
