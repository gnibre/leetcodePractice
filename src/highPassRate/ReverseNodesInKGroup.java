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
public class ReverseNodesInKGroup {
    
    /**
     * i just finished SwapNodeInPairs, so, just copy code here;
     * 
     */ 
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode Dummy = new ListNode(0);
        Dummy.next = head;
        ListNode cur = Dummy;
        
        ListNode subS;
        ListNode subE;
        ListNode tails;
        while(cur!=null){
            
            // find a list of size K, AFTER cur;
            // find the tail after this k-length sublist;
            // reverse the k-length sublist;
            //connect;
            //move cur to the tail of reversed;
            
            subS = cur.next;
            subE = cur.next;
            for(int i=1;i<k;++i){
                if(subE!=null)
                    subE = subE.next;
            }
            //subE is now end of sublist;
            if(subE==null){
                // don't need to handle this one, not enough number;
                break;
            }
            tails = subE.next;
            
            //cut sub list , or we can't reverse it;
            subE.next=  null;
            // cause this sublist from subS to subE, will be reversed; after reverse, this subS will be the end of the list; it's exactly where cur shall be; save it ;
            // oldStart = subS;
            
            //it's so special that we already know the head and tail, so we know the reversed head and tail; so we don't even need  returned head;
            reverseList(subS,subE); //reverse listnode, return head of new list;
            cur.next = subE;// subE is now new head of reversed list;
            cur= subS; //subS is now new tail of reversed list; so this will move cur to end of sublist;
            cur.next = tails; //connect;
            
        }
        return Dummy.next;
    }
    
    private ListNode reverseList(ListNode h,ListNode t){
        ListNode cur = h;
        ListNode lastVisited = null;
        ListNode nextToVisit = null;
        //save next to visit, connect cur to last visit;
        
        while(cur!=null){
            nextToVisit = cur.next;
            cur.next = lastVisited;
            lastVisited = cur;
            cur = nextToVisit;
        }
        return t;
    }
    
}
