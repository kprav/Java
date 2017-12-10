/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    ListNode head;
    
    private void sortedInsert(ListNode node) {
        if (head == null || node.val <= head.val) {
            node.next = head;
            head = node;
        } else {
            ListNode current = head;
            while (current.next != null && current.next.val < node.val) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
    }
    
    public ListNode insertionSortList(ListNode a) {
        head = null;
        ListNode current = a;
        while (current != null) {
            ListNode next = current.next;
            sortedInsert(current);
            current = next;
        }
        return head;
    }
}
