/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
	public ListNode removeNthFromEnd(ListNode a, int b) {
	    ListNode prev = null;
	    ListNode slow = a;
	    ListNode fast = a;
	    int i = 0;
	    while (fast != null && i < b) {
	        fast = fast.next;
	        i++;
	    }
	    if (fast == null && i < b) {
	        ListNode dummy = a;
	        a = a.next;
	        dummy.next = null;
	        return a;
	    }
	    while (fast != null) {
	        prev = slow;
	        slow = slow.next;
	        fast = fast.next;
	    }
	    if (prev != null) {
	        prev.next = slow.next;
	    } else {
	        a = slow.next;
	    }
	    slow.next = null;
	    slow = null;
	    fast = null;
	    return a;
	}
}
