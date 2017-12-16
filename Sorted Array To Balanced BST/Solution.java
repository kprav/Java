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
    private TreeNode sortedArrayToBSTHelper(List<Integer> a, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(a.get(mid));
        root.left = sortedArrayToBSTHelper(a, start, mid - 1);
        root.right = sortedArrayToBSTHelper(a, mid + 1, end);
        return root;
    }
    
    public TreeNode sortedArrayToBST(final List<Integer> a) {
        if (a == null || a.size() == 0) {
            return null;
        }
        return sortedArrayToBSTHelper(a, 0, a.size() - 1);
    }
}
