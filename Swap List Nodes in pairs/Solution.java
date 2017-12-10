/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode a) {
        if (a == null) {
            return null;
        }
        ListNode zero = null;
        ListNode one = a;
        ListNode two = a.next;
        if (two == null) {
            return a;
        }
        boolean swapHead = true;
        while (true) {
            ListNode temp = two.next;
            two.next = one;
            one.next = temp;
            temp = two;
            two = one;
            one = temp;
            temp = null;
            if (zero != null) {
                zero.next = one;
            }
            zero = two;
            if (swapHead) {
                a = one;
                swapHead = false;
            }
            if (one.next.next == null || two.next.next == null) {
                break;
            }
            one = one.next.next;
            two = two.next.next;
        }
        return a;
    }
}
