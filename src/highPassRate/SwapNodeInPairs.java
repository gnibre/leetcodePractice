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
public class SwapNodeInPairs {
    /**
     * what we get is D,
     * save 1, save 2, save 2->next =x; 
     * D->1->2->x
     * D->2->x
     * D->next = 2; 2->next = 1; 1->next = x? 
     * D = 1;
     * 
     * --------
     * take care if  
     * D->1->null?
     * D->null?
     * 
     * 
     */ 
    public ListNode swapPairs(ListNode head) {
        ListNode Dummy = new ListNode(0);
        Dummy.next = head;
        
        ListNode cur = Dummy;
        ListNode n1;
        ListNode n2;
        ListNode tail;
        // cur->1->2->any; for normal case
        // case I cur->null;  cool;
        // case II cur->1->null; swap? better..just done;
        while(cur!=null){
            n1 = cur.next;
            if(n1==null){
                break; 
            }
            n2 = n1.next;
            if(n2==null){
                break;
            }
            tail = n2.next;
            
            cur.next = n2;
            n2.next = n1;
            n1.next = tail;
            cur = n1;
        }
        return Dummy.next;
    }
}