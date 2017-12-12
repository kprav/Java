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
    private class TreeNodeWithVert {
        TreeNode node;
        int vert;
        TreeNodeWithVert(TreeNode x, int v) { node = x; vert = v;}
    }
    
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (A == null) {
            return result;
        }
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
        Queue<TreeNodeWithVert> queue = new LinkedList<TreeNodeWithVert>();
        queue.add(new TreeNodeWithVert(A, 0));
        while (!queue.isEmpty()) {
            TreeNodeWithVert current = queue.remove();
            ArrayList<Integer> list = map.getOrDefault(current.vert, new ArrayList<Integer>());
            map.put(current.vert, list);
            list.add(current.node.val);
            if (current.node.left != null) {
                queue.add(new TreeNodeWithVert(current.node.left, current.vert - 1));
            }
            if (current.node.right != null) {
                queue.add(new TreeNodeWithVert(current.node.right, current.vert + 1));
            }
        }
        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }
        return result;
    }
}
