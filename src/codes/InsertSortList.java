package codes;

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
public class InsertSortList {
	
	public void go(){
		ListNode head = new ListNode(1);
		ListNode ln = new ListNode(1);
		head.next = ln;
		ln.next = null;
		
//		ListNode res = head;
		ListNode res = insertionSortList(head);
		
		printlist(res);
	}
	
	private void printlist(ListNode res){
		while(res!=null){
			System.out.print(" "+res.val+"->");
			res = res.next;
		}
		System.out.println("");
	}
	
	
    public ListNode insertionSortList(ListNode head) {
        //insert the first element to new list.
        ListNode newhead = head;
        if(head==null){
            return newhead;
        }
        ListNode toInsertHead = head.next;
        newhead.next = null;
        // now insert the rest in the list to newhead.
        
        ListNode insertNode;
        while(toInsertHead!=null){
            // get first one to insert 
            insertNode = toInsertHead;
            toInsertHead = toInsertHead.next;
            insertNode.next = null;
            
            System.out.println(" insert : "+insertNode.val);
            newhead = insertANodeToList(newhead,insertNode);   
        }
        
        return newhead;
    }
    
    // main function. when a node want to insert to a exsiting list.
    // returns (new)head of list.
    private ListNode insertANodeToList(ListNode head,ListNode insertNode){
        if(insertNode.val<=head.val){
            //head change.
            insertNode.next = head;
            return insertNode;
        }
        
        
        ListNode ln = head;
        ListNode before = head;
        //to do an insert, we have to get two nodes between which two we do the insertion.
        while(ln!=null){
            if(ln.val<insertNode.val){
                // record this one before we go next.
                before = ln;
                ln = ln.next;
            }else{
                break;
            }
        }
        // while loop break out in two condition:  
        // (1)node ln.val>=insertNode.val.      insert result         before->insertNode->ln
        // (2) node ln ==null;        insert result   before->insertNode->ln(null)
        
        before.next = insertNode;
        insertNode.next = ln;
        return head;
    }
}