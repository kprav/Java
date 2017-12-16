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
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (a == null) {
            return result;
        }
        LinkedList<TreeNode> currentLevel = new LinkedList<TreeNode>();
        LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
        ArrayList<Integer> thisLevel = new ArrayList<Integer>();
        result.add(thisLevel);
        thisLevel.add(a.val);
        currentLevel.add(a);
        int level = 0;
        while (!currentLevel.isEmpty()) {
            TreeNode node = currentLevel.remove();
            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
            if (currentLevel.isEmpty()) {
                thisLevel = new ArrayList<Integer>();
                if (level % 2 == 0) {
                    Collections.reverse(nextLevel);
                }
                for (TreeNode n : nextLevel) {
                    thisLevel.add(n.val);
                }
                if (level % 2 == 0) {
                    Collections.reverse(nextLevel);
                }
                if (thisLevel.size() > 0) {
                    result.add(thisLevel);
                }
                level++;
                LinkedList<TreeNode> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
        return result;
    }
}
