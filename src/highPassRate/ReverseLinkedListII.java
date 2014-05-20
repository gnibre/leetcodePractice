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
public class ReverseLinkedListII {
    
    /**
     * straight forward idead, but not easy to code, and shall take very care of boundary conditions.
     * 
     * cut list apart and get three part.
     * 1, head to the cut at m
     * 2, m to n
     * 3, n to the tail;
     * 4 reverse m to n part.
     * 5 connect head, reversed part, tail;
     */ 
    public ListNode reverseBetween(ListNode head, int m, int n) {
        
        if(m==n){
            return head;
        }
        
        ListNode beforeCutHead = head;// list node for before cut.
        ListNode beforeCutTail = head;
        if(m==1){
            beforeCutHead = null;
            beforeCutTail = null;
        }else{
            //as guaranteed 1<=m<=n<=len;
            // if m==2, beforeCutTail is beforeCutHead and is head of the original array.;
            for(int i=0;i<m-2;++i){
                beforeCutTail = beforeCutTail.next;
            }
        }
        
        // got mid part start from.
        ListNode midPartHead;
        
        if(beforeCutTail==null){
            midPartHead = head;
        }else{
            midPartHead = beforeCutTail.next; // mid
        }
        
        
        // cut first part.
        if(beforeCutTail!=null){
            beforeCutTail.next = null;
        }
        
        //length of the mid part. :   n-m+1; => so if m==n, mid part is only one node. we add a quickreturn to the beginning.
        
        // so length is n-m+1; mid-tail is n-m times from mid-head
        ListNode midPartTail = midPartHead;
        for(int i=0;i<n-m;++i){
            midPartTail = midPartTail.next;
        }
        
        // got mid part tail;
        ListNode restPartHead = midPartTail.next; // maybe null as the end of the list.
        // cut mid part.
        midPartTail.next = null;
        
        //alright, we got three independent part.
        
        ListNode reversedHead = reverseList(midPartHead); // reverse a list and get the new head.
        //as we still keep the ref of head of the mid part before it is reversed.
        // and it's now tail of the reversed   . we do connect all the list based on this.
        
        
        ListNode newHead = null;
        //done. take care head, of case m==1;
        if(beforeCutHead==null){
            //nothing in the first cut.
            newHead = reversedHead;
        }else{
            newHead = beforeCutHead;
            beforeCutTail.next = reversedHead;
        }
        midPartHead.next = restPartHead;// midPartHead is now the tail of the reversed.
        
        return newHead;
    }
    
    
    // reverse a list and get the new head.
    private ListNode reverseList(ListNode head){
        if(head==null){
            return head;
        }
        
        ListNode cur = head;
        ListNode next = cur.next;
        cur.next = null; //cut first one. as it will be the end of the new list, the next of it shall be null;
        ListNode afterNext;
        while(next!=null){
            afterNext = next.next;
            next.next = cur; // point to the one before it.
            cur = next;
            next = afterNext;
        }
        return cur; // this one is the end.
    }
}