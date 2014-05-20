package codes;

public class ReorderList {
	
	
	public void go(){
		
		ListNode head = null;
		ListNode ln = null;
		ListNode tmp=null;
		head = new ListNode(1);
		ln = new ListNode(2);
		head.next = ln;
		tmp = ln;
		ln = new ListNode(3);
		tmp.next = ln;
		tmp = ln;
		ln = new ListNode(4);
		tmp.next = ln;
		tmp = ln;
		
		reorderList(head);
		
		System.out.println(" result: ");
		printList(head);
		
	}
	
	private void printList(ListNode head){
		System.out.print(" print list:  ");
		while(head!=null){
			System.out.print(" "+head.val);
			head = head.next;
		}
		System.out.println("");
	}
	
	private class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
		
	}
	
    public void reorderList(ListNode head) {
        
        int size=0;
        ListNode ln = head;
        while(ln!=null){
            ln=ln.next;
            size++;
        }
        
        int half = (1+size)/2;
        
        //first half and second half
        ListNode listHead1 = head;
        ListNode listTail1 = head;
        ListNode listHead2 = head;
        for(int i=0;i<half-1;++i){
        	listTail1 = listTail1.next;
        }
        if(listTail1==null){
        	return;
        }
        listHead2 = listTail1.next;
        listTail1.next = null; //cut first half.
        if(listHead2==null){
        	return;
        }
        
//        System.out.println("first half ");
//        printList(listHead1);
//        System.out.println("second half ");
//        printList(listHead2);
        
        
        //reverse second.
        ListNode tmp,next,past;
        tmp = listHead2;
        next = tmp.next;
        tmp.next = null;
        ListNode reverseHead = tmp;
        while(next!=null){
            // move 1
            past = tmp;
            tmp = next;
            next = next.next;
            // got reverse list.
            tmp.next = past;
            reverseHead = tmp;
        }
        
//        System.out.println("print reverse list; ");
//        printList(reverseHead);
        
        // link two lists.
        ListNode sortedHead,cur = null;
        sortedHead = null;
        next=null;
        int t = 0;
        while(listHead1!=null&&reverseHead!=null){
            if(t%2==0){
                next = listHead1;
                listHead1 = listHead1.next;
            }else{
                next = reverseHead;
                reverseHead = reverseHead.next;
            }
            t++;
//            System.out.println("next added is : "+next.val);
            if(sortedHead==null){
                sortedHead = next;
                cur = next;
            }else{
                cur.next = next;
                cur = next;
            }
        }
        
//        if(cur==null){
//        	System.out.println("wtf");
//        }
        
        if(listHead1!=null){
            cur.next = listHead1;
        }
        if(reverseHead!=null){
            cur.next = reverseHead;
        }
        
        //head not changed. so no need to return.
        
        
    }
}