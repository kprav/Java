/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    private TreeNode sortedListToBSTHelper(ArrayList<Integer> input, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(input.get(mid));
        root.left = sortedListToBSTHelper(input, left, mid - 1);
        root.right = sortedListToBSTHelper(input, mid + 1, right);
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode a) {
        ArrayList<Integer> input = new ArrayList<>();
        while (a != null) {
            input.add(a.val);
            a = a.next;
        }
        return sortedListToBSTHelper(input, 0, input.size() - 1);
    }
}
