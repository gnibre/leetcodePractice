package highPassRate;

import data.ListNode;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode n1 = head;
        if(head==null||head.next==null){
            return false;
        }
        ListNode n2 = head.next;
        while(n1!=null&&n2!=null){
            if(n1.val==n2.val){
                return true;
            }
            n1 = n1.next;
            n2 = n2.next;
            if(n2==null){
                return false;
            }
            n2 = n2.next;
            if(n2==null){
                return false;
            }
        }
        return false;
    }
}