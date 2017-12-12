/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        int heightDiff = Math.abs(leftHeight - rightHeight);
        if (heightDiff > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
      
    public int isBalanced(TreeNode a) {
        if (getHeight(a) == -1) {
            return 0;
        } else {
            return 1;
        }
    }
}
