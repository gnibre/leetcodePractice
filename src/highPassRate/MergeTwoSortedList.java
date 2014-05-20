package highPassRate;

import data.ListNode;

public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode v1 = l1;
        ListNode v2 = l2; /// visiting;
        ListNode res = new ListNode(-1); // add dummy makes it easy;
        ListNode cur = res;
        while(v1!=null&&v2!=null){
            if(v1.val<v2.val){
                cur.next = v1;
                cur = cur.next;
                v1 = v1.next;
            }else{
                cur.next = v2;
                cur = cur.next;
                v2 = v2.next;
            }
        }
        
        while(v1!=null){
            cur.next = v1;
            cur = cur.next;
            v1 = v1.next;
        }
        
        while(v2!=null){
            cur.next = v2;
            cur = cur.next;
            v2 = v2.next;
        }
        
        return res.next;
    }
}