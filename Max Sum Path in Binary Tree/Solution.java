/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSubTreePathSum = maxPathSumHelper(node.left);
        int rightSubTreePathSum = maxPathSumHelper(node.right);
        int currentNodeSum = node.val;
        if (leftSubTreePathSum > 0) {
            currentNodeSum += leftSubTreePathSum;
        }
        if (rightSubTreePathSum > 0) {
            currentNodeSum += rightSubTreePathSum;
        }
        maxSum = currentNodeSum > maxSum ? currentNodeSum : maxSum;
        int pathSum = node.val;
        if (leftSubTreePathSum > 0 && rightSubTreePathSum > 0) {
            pathSum += Math.max(leftSubTreePathSum, rightSubTreePathSum);
        } else if (leftSubTreePathSum > 0) {
            pathSum += leftSubTreePathSum;
        } else {
            pathSum += rightSubTreePathSum;
        }
        return pathSum;
    }
}
