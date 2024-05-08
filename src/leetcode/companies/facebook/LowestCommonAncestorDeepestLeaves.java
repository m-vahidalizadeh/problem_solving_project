package leetcode.companies.facebook;

import java.util.*;

/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest leaf-nodes of the tree.
 * Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
 * Example 2:
 *
 * Input: root = [1]
 * Output: [1]
 * Explanation: The root is the deepest node in the tree, and it's the lca of itself.
 * Example 3:
 *
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [1, 1000].
 * 0 <= Node.val <= 1000
 * The values of the nodes in the tree are unique.
 */
public class LowestCommonAncestorDeepestLeaves {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root.left==null&&root.right==null) return root;
        Map<TreeNode,TreeNode> parents=new HashMap<>();
        PriorityQueue<LevelNode> pq=new PriorityQueue<>((a,b)->b.level-a.level);
        dfs(root,0,null,parents,pq);
        LevelNode l1=null,l2=null;
        if(!pq.isEmpty()) l1=pq.poll();
        if(!pq.isEmpty()&&pq.peek().level==l1.level) l2=pq.poll();
        if(l2==null){
            return l1.node;
        }else{
            TreeNode a=l1.node,b=l2.node;
            Set<TreeNode> parA=new HashSet<>();
            while(a!=null) {
                parA.add(a);
                a=parents.get(a);
            }
            while(b!=null) {
                if(parA.contains(b)) return b;
                b=parents.get(b);
            }
        }
        return null;
    }

    private void dfs(TreeNode node,int level,TreeNode parent,Map<TreeNode,TreeNode> parents,PriorityQueue<LevelNode> pq){
        parents.put(node,parent);
        if(node.left==null&&node.right==null){
            pq.add(new LevelNode(node,level));
        }else{
            if(node.left!=null) dfs(node.left,level+1,node,parents,pq);
            if(node.right!=null) dfs(node.right,level+1,node,parents,pq);
        }
    }

    public class LevelNode{
        TreeNode node;
        int level;
        public LevelNode(TreeNode node,int level){
            this.node=node;
            this.level=level;
        }
    }

    public static void main(String[] args){
        LowestCommonAncestorDeepestLeaves l=new LowestCommonAncestorDeepestLeaves();
        TreeNode node0=new TreeNode(0);
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        TreeNode node8=new TreeNode(8);
        node3.left=node5;
        node3.right=node1;
        node1.left=node0;
        node1.right=node8;
        node5.left=node6;
        node5.right=node2;
        node2.left=node7;
        node2.right=node4;
        System.out.println(l.lcaDeepestLeaves(node3).val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
