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
    public int isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return 1;
        }
        if (a != null && b != null) {
            if ((a.val == b.val) &&
            isSameTree(a.left, b.left) == 1
            && isSameTree(a.right, b.right) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
