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
public class RemoveNthNodeFromEnd {
    /**
     * don't know the list length with one scan only;
     * so we wanna keep a look of n elements ahead when we reach tail;
     * 
     * what we want: that node (nth)before tail; 
     * if indexed as n; the one before it is n+1; the next one is n-1; tail is 1th;
     * if we want the node before nth, the check list is of size [n+1,1] = n+1;
     */ 
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        
        
        //this code is with good big logic; but for things to be considered:
        
        //1 , n=1; means we delete the last one; it's still ok,cause will make a train of size 2; we will point the correct one to null;
        //2, n=length of list; means we gonna delete old head; that why this function need a return head; cause it maybe changed;
            // so dummy shall be used; cause 
        
        
        ListNode dummyHead = new ListNode(1);
        dummyHead.next = head;
        
        ListNode trainHead=  dummyHead;
        for(int i=0;i<n;++i){
            trainHead = trainHead.next;
        }
        ListNode trainTail = dummyHead;
        while(trainHead.next!=null){
            trainHead = trainHead.next;
            trainTail = trainTail.next;
        }
        
        // when while loop break;, trainHead is end of list; trainTail is (n+1)th;
        ListNode nth = trainTail.next;
        // skip nth;
        trainTail.next = nth.next;
        return dummyHead.next;
    }
}