package highPassRate;

import data.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveDuplicateFromSortedList {
    /**
     * 
     * very like delete from sorted Array.
     * except the access,
     * 
     * so, two cursor like the sorted array one.
     * one scan the nodes ahead to see that's content is .
     * another keep the tail position of the list to return.
     * 
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return head;
        }
        
        ListNode cur = head;
        ListNode sav = head;
        
        int v;
        while(cur!=null){
            v = cur.val;
            cur=cur.next;
            while(cur!=null){
            	cur = cur.next;
            }
            //cur is now point to a different element.
            
            sav.next = cur;
            sav = cur;
        }
        // when while loop break, sav and cur are both null; list well done, well ended.
        return head;
    }
}