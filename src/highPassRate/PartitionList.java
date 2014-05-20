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
public class PartitionList {
    
    /**
     * just spilt one list into two, one below(equal to x;) one greater than x;
     * 
     */ 
    public ListNode partition(ListNode head, int x) {
        ListNode sm = new ListNode(0); // smaller; add a dummy head;
        ListNode be = new ListNode(0);;  // big or equal;  // add a dummy too; ease the code
        ListNode sCur = sm;
        ListNode bCur = be;
        
        ListNode cur = head;
        //one pass;
        while(cur!=null){
            if(cur.val<x){
                sCur.next = cur;
                sCur = cur;
            }else{
                bCur.next = cur;
                bCur = cur;
            }
            cur = cur.next;
            bCur.next = null; //end b;
        }
        
        //connect; 
        sCur.next = be.next;
        
        return sm.next;
    }
    
}