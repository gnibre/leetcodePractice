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
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        // new head is from head more to next  (lenth-n) times.
        
        //or rather we keep two cursor, of distance n, 
        // when the one moved to the end, the other is the new head.
        
        if(head==null){
            return head;
        }
        
        //!!! ok, cool. from the test case, we know that n can be any number that must be much longer than length of the list.
        
        int len=0;
        ListNode scanHead = head;
        ListNode scanTail = head;
        while(scanHead!=null){
            scanHead = scanHead.next;
            len++;
        }
        
        if(n>=len){
            n = n%len; // fixed.
        }
        if(n==0){
            return head;
        }
        
        // move tail to position. now,tail is n distance from head.
        for(int i=0;i<n;++i){
            scanTail = scanTail.next;
        }
        scanHead = head;
        
        // move tail to end of the list.
        while(scanTail.next!=null){
            scanHead = scanHead.next;
            scanTail = scanTail.next;
        }
        
        // when scanTail.next is null:
        // 1 scanTail is the tail of the old list.
        // 2 scanHead.next is the new head.
        
        // what we shall do is 
        // to connect to old head to the tail of the original list. 
        // cut link to the new head( new head have no node point to it.)  return new head.
        
        ListNode newHead = scanHead.next;
        scanHead.next= null; //cut old list from the newhead.
        scanTail.next = head; //connect old list from tail to head.
        return newHead;
    }
}