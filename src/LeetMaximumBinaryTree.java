public class LeetMaximumBinaryTree {

    public static void main(String[] args) {
/*
Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:
      6
    /   \
   3     5
    \    /
     2  0
       \
        1
 */
        int[] nums = {3, 2, 1, 6, 0, 5};
        LeetMaximumBinaryTree leetMaximumBinaryTree = new LeetMaximumBinaryTree();
        TreeNode result = leetMaximumBinaryTree.constructMaximumBinaryTree(nums);
        System.out.println();
    }

    /**
     * constructs a max binary tree from nums.
     *
     * @param nums The nums
     * @return The root of a max binary tree from nums
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * It builds the max binary tree recursively.
     *
     * @param nums  The nums
     * @param start The start
     * @param end   The end
     * @return A max binary tree root by the nums
     */
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (end < start)
            return null;
        int maxIndex = getMaxIndex(nums, start, end);
        TreeNode currentNode = new TreeNode(nums[maxIndex]);
        currentNode.left = buildTree(nums, start, maxIndex - 1);
        currentNode.right = buildTree(nums, maxIndex + 1, end);
        return currentNode;
    }

    /**
     * Get the index of max from start to end in nums (inclusive).
     *
     * @param nums  The nums
     * @param start The start
     * @param end   The end
     * @return The index of max between start and end
     */
    private int getMaxIndex(int[] nums, int start, int end) {
        int max = nums[start];
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
