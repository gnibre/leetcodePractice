package codes;

import ulti.readfile;



//http://oj.leetcode.com/problems/sort-list/



/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */



public class SortListSolution {
	private class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
		
	}
	
	public void go(){
		
		
		int[] input = 
				readfile.readIntArrayFromFile(null);
		
//		int[] input = {19,2,6,8,9,15,7,3,29,55,42,33};
		
//		int[] input = {1,2,3,4,5,6,7,4,3,3,2,1,2,3,2,32,3,2,3,3,2,3,2,3,2,3,2,3,3,2,1,2,3,2,1,2,3,2,1,2,3,2,12,3,2,12,3,2,12,32,1,2,3,2,12,3,2,12,3,2,12,1,32,12,32,1,2,3,2,1,2,3,1,2,3,12,3,1,2,3,1,2,3,12};
		
		
		
		ListNode head = null;
		ListNode cur = null;
		for(int i:input){
			if(head==null){
				head = new ListNode(i);
				cur = head;
			}else{
				cur.next = new ListNode(i);
				cur = cur.next;
			}
		}
		
//		showList(head);
		
		ListNode newHead = sortList(head);
		
		System.out.println("final result: ");
		showList(newHead);
		
		
		
	}
	
	private void showList(ListNode head){
		System.out.print("list is :  ");
		int i=0;
		while(head!=null){
			System.out.print(" "+head.val);
			head = head.next;
			if(i%100==0){
				System.out.println("");
			}
			++i;
		}
		System.out.println("");
	}
	
	
	
	
	
    public ListNode sortList(ListNode head) {
        //sort the list step by step, first of all, each 2^1 consecutive node as a group, and sort it.
        // the next step, sort 2^2 consecutive nodes as a group
        // the third time sort 2^3 nodes as a group
        // goes on till 2^x reach's size of the list: n.
        // take logn steps to do the sort, each step taks o(n) time.
        
        
        int n=0;
        ListNode cur = head;
        while(cur!=null){
            n++;
            cur = cur.next;
        }
        
//        System.out.println("list size: "+n);
        int step =1;
//        int safe = (n+1);
        ListNode newHead = head;
        while(step<n){
            newHead = sortListByStep(newHead,step);
//            showList(newHead);
            step*=2;
        }
        return newHead;
    }
    
    
    private ListNode sortListByStep(ListNode head,int step){
//    	System.out.println(" sortlistbystep, head: "+head.val+" step: "+step);
        ListNode h1,h2,t1,t2; // find these and we can do sublist mergesort
        
        ListNode nextHead = head;
        ListNode newHeadOrded = null;
        ListNode lastTailOrded = null;
        int in =0;
        while(nextHead!=null){
        	in++;
            h1 = nextHead;
            t1 = nextHead;
//            System.out.println(" sortlistbystep, nexthead: "+nextHead.val+" step: "+step);
            for(int i=1;i<step;++i){
                if(t1.next==null){
                    nextHead = null;
                    break;
                }
                t1 = t1.next;
            }
            
            if(t1.next==null){
                h2=null;
                t2=null;
                nextHead = null;
            }else{
                h2 = t1.next;
                t2 = h2;
                for(int i=1;i<step;++i){
                    if(t2.next==null){
                        nextHead = null;
                        break;
                    }
                    t2 = t2.next;
                }
            }
            if(t2!=null){
            	nextHead = t2.next;
            }
            
            // we got them, then go sort sublist. and connect sorted sublists.
            ListNode[] sortedListNodes = mergeSubList(h1,h2,t1,t2);
            if(newHeadOrded==null){
                newHeadOrded = sortedListNodes[0];
                lastTailOrded = sortedListNodes[1];
            }else{
//            	System.out.print(" sort sublist result,  old tail: "+lastTailOrded.val+"  new head : "+sortedListNodes[0].val+"  new tail: "+sortedListNodes[1].val);
                lastTailOrded.next = sortedListNodes[0];
                lastTailOrded = sortedListNodes[1];
                lastTailOrded.next = null;
            }
            
//            System.out.print(" sorted sublist connected, : ");
//            ListNode ph = newHeadOrded;
//            int a=0;
//            while(ph!=null){
//            	a++;
//            	System.out.print(" "+ph.val);
//            	if(a==step*2){
//            		System.out.print(" , ");
//            		a=0;
//            	}
//            	ph=ph.next;
//            }
//            if(nextHead!=null)
//            System.out.println("          , going nexthead: "+nextHead.val);
            
        }
        
        // sort list of this step done.
        return newHeadOrded;
    }
    
    
    /** merge two sub list, one from h1 end at h2, the other from h2, of length len.   *NOTE* sublist is already sorted.
     * h1-------t1 h2------t2
     * return the new head and tail of the sorted listnodes.
    */
    private ListNode[] mergeSubList(ListNode h1,ListNode h2,ListNode t1,ListNode t2){
//    	System.out.print("  mergeSubList :   "+h1.val+"--   "+t1.val);
//    	if(h2!=null){
//    		System.out.print(", "+h2.val+"--   ");
//    	}
//    	if(t2!=null){
//    		System.out.print(""+t2.val);
//    	}
//    	System.out.println("");
    	
        ListNode c1,c2,a1,a2; //a1 a2 the one after the target one.
        c1 = h1;
        c2 = h2;
        ListNode[] ret = new ListNode[2];
        if(h1==null){
        	return null;
        }
        if(h2==null){
        	ret[0] = h1;
        	ret[1] = t1;
        	return ret;
        }
        
        a1 = c1.next;
        a2 = c2.next;
        
        ListNode headReturn=null;
        ListNode cur=null;
        ListNode tailReturn = null;
        
        // now it's two seperated list
        t1.next = null;
        t2.next = null;
        
        // we reorder the old list.
        while(c1!=null&&c2!=null){
            if(c1.val<c2.val){
                if(cur!=null){
                    cur.next = c1;
                }
                cur = c1;
                c1 = c1.next;
            }else{
                if(cur!=null){
                    cur.next = c2;
                }
                cur = c2;
                c2 = c2.next;
            }
            if(headReturn == null){
                headReturn = cur;
            }
        }
        
        //add rest to the new list.
        while(c1!=null){
            cur.next = c1;
            cur = cur.next;
            c1 = c1.next;
        }
        while(c2!=null){
            cur.next = c2;
            cur = cur.next;
            c2 = c2.next;
        }
        tailReturn = cur;
       
        ret[0]=headReturn;
        ret[1] = tailReturn;
        return ret;
    }
    
    
    /**
     * node is defined as only have val(int) and next, swap data is simple, we don't really need to swap datastructure.
     * */
    private void swap(ListNode n1,ListNode n2){
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
    
    
}