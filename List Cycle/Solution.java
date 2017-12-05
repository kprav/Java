/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
	public ListNode detectCycle(ListNode a) {
	    ListNode slow = a;
	    ListNode fast = a;
	    boolean cycleDetected = false;
	    while (slow != null && fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	        if (slow == fast) {
	            cycleDetected = true;
	            break;
	        }
	    }
	    if (cycleDetected) {
	        slow = a;
	        while (slow != fast) {
	            slow = slow.next;
	            fast = fast.next;
	        }
	        return slow;
	    }
	    return null;
	}
}
