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
    private int kthsmallestNum;
    private int num = 0;
    
    private void kthsmallestHelper(TreeNode root, int k) {
        if (root == null) {
            num++;
            return;
        }
        kthsmallestHelper(root.left, k);
        if (num == k) {
            kthsmallestNum = root.val;
        }
        kthsmallestHelper(root.right, k);
    }
    
    public int kthsmallest(TreeNode root, int k) {
        kthsmallestHelper(root, k);
        return kthsmallestNum;
    }
}
