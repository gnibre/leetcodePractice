package highPassRate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

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
public class MergeKSortedLists {
    
    /**
     * say, k lists,  each with n elements;  total k*n elements;
     * if merge each two lists together, it's like doing a  k number merge sort with complexity klogk; and each time merge two list of size n: 2n
     * so totally klogk*n
     * 
     * // if we focusing the level of merging tree, level is  logk; each level, compare time is 
     * (number of all nodes; for a merge sort, each level, each node is used once); total is   logk*nk   _thelevel *( _totalNodes )
     * 
     * =======================
     * if we use a heap to add everything;  add each element take time log(nk) ; total time (nk)*log(nk) ; not as good; and we need space for heap;
     * 
     * ======================
     * 
     * it's like , doing a k-way?  
     * compare k number takes k steps; but if we remain the compare result in a heap/stack ( extra space of k though)
     * we can find the min/max of k values in O(1) for each input; but for add a new to heap, we need O(logk) time ;  final is nk*log(k);
     * 
     * to add a number, for most of the case; we may not need o(logk) time; hope so; 
     * or in industry, k-way hardware can do this in O(1) time;
     * that's why k-way pass is fast;
     */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if(lists==null||lists.size()<1) return null;
        int k = lists.size();
        // PriorityQueue<ListNode> heap;
        Comparator<ListNode> cmp = new Comparator<ListNode>(){
    		@Override
    		public int compare(ListNode n1, ListNode n2) {
    			return n1.val-n2.val;
    		}
	    };
	    PriorityQueue<ListNode> p = new PriorityQueue<ListNode>(k,cmp);
	    
	        ListNode node;
	    for(int i=0;i<k;++i){
	        node = lists.get(i);
	        if(node!=null){
	            p.add(node);
	        }
	    }
	    
	    
	    ListNode res = new ListNode(-1);
	    ListNode cur = res;
	    
	    while(!p.isEmpty()){
	        //!!!!!!!!!!!!!!!!!!!! poll not pull...
	        node = p.poll(); // get this;
	        cur.next = node;
	        cur = cur.next;
	        
	        // pulled one, need to add one from same list;
	        if(node.next!=null){
	            p.add(node.next);
	        }
	    }
	    return res.next;
	    //after finish, i found i did like nothing; but function done;
    }
}