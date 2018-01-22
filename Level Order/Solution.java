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
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        if (A == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> level = new ArrayList<>();
        result.add(level);
        Queue<TreeNode> currentLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        currentLevel.add(A);
        while (!currentLevel.isEmpty()) {
            TreeNode node = currentLevel.remove();
            level.add(node.val);
            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
            if (currentLevel.isEmpty()) {
                Queue<TreeNode> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
                temp = null;
                if (!currentLevel.isEmpty()) {
                    level = new ArrayList<>();
                    result.add(level);
                }
            }
        }
        return result;
    }
}
