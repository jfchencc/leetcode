/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE; // do not forget. node val can be negative
        maxPath(root, max);
        return max[0];
    }
    
    public int maxPath(TreeNode root, int[] max) {
        if (root == null) return 0;
        int l = Math.max(0, maxPath(root.left, max));
        int r = Math.max(0, maxPath(root.right, max));
        max[0] = Math.max(max[0], l + r + root.val); // max[0] is the result
        return Math.max(l, r) + root.val;// current max path 
    }
}