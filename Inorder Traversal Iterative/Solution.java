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
    public ArrayList<Integer> inorderTraversal(TreeNode a) {
        if (a == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (a != null) {
            stack.push(a);
            a = a.left;
        }
        while(stack.size() > 0) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return list;
    }
}
