package highPassRate;

import ulti.Printer;
import data.ListNode;
import data.TreeNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ConvertSortedListToBinarySearchTree {
	
	public void go(){
		ListNode hl = new ListNode(3);
		ListNode nl = new ListNode(5);
		hl.next = nl;
		ListNode n2 = new ListNode(8);
		nl.next = n2;
		ListNode n3 = new ListNode(9);
		n2.next  = n3;
		
		TreeNode res= sortedListToBST(hl);
		
		Printer.inOrderTreeTraversal(res);
	}
	
    /**
     * we know how to convert to bst when we got 2 of these 3:  preorderlist,inorderlist,postorderlist; 
     * because we can know each node location of each tree
     * in this one, we want the tree to be balanced , so left tree size is 2^n; right tree is 2^(n-1)~ 2^n;
     */ 
    public TreeNode sortedListToBST(ListNode head) {
        
        if(head==null) return null;
        // if root to level n is full; 
        // contains 2^(n+1)-1 nodes; in these levels;
        
        int count = 0;
        ListNode cur = head;
        while(cur!=null){
            cur = cur.next;
            count++;
        }
        // get count and cur == null 
        
        if(count==1){
            TreeNode one = new TreeNode(head.val);
            return one;
        }
        
        
        
        
        // full tree of level n have nodes number 2^n-1; 
        
        int nodesLeft = count-1; // root cut;
        
        int n=1;
        int levelMaxNode=2; // first level can have 2 nodes;
        while(nodesLeft>levelMaxNode){
            // this level is full of nodes, go next;
        	nodesLeft-= levelMaxNode;
        	//next level size;
            levelMaxNode*=2;
            n++;
        }
        //when quit, for example, count =2; quit nodesLeft =1 (nodes of the last level); levelMaxNode = 2( nodes can be in this level); n=1,level
        //how many nodes do we have in left and right tree, before this level?  :  levelMaxNode/2-1 ;  when last level is 4, the left tree have 1 node before this level; so does the right tree;
        
        int lastLevel = nodesLeft;
        
        int lastLevelLeftTree = (lastLevel<(levelMaxNode/2))?lastLevel:levelMaxNode/2;
        int lastLevelRightTree = lastLevel-lastLevelLeftTree;
        
         int totalLeftTree = lastLevelLeftTree+levelMaxNode/2-1;
         int totalRightTree = lastLevelRightTree+levelMaxNode/2-1;
         
        
         System.out.println("  count: "+count);
         System.out.println("  levelMaxNode: "+levelMaxNode);
         System.out.println("  nodesLeft: "+nodesLeft);
         
         System.out.println("  lastLevel: "+lastLevel);
         System.out.println("  lastLevelLeftTree: "+lastLevelLeftTree);
         System.out.println("  lastLevelRightTree: "+lastLevelRightTree);
         
         
         System.out.println("  totalLeftTree: "+totalLeftTree);
         System.out.println("  totalRightTree: "+totalRightTree);
         ListNode leftHead=null;
         ListNode rootThisSubtree=null;
         ListNode rightHead=null;
         
         if(totalLeftTree==0){
             leftHead = null;
             rootThisSubtree = head;
             rightHead = null; // when you don't have left? can't have right
         }else{
             leftHead = head;
             cur =head;
             for(int i=0;i<totalLeftTree-1;++i){
                 cur = cur.next;
             }
             //now cur is the tail of left tree;
             rootThisSubtree = cur.next;
             cur.next = null; //cut left tree from the list; 
             rightHead = rootThisSubtree.next;
             rootThisSubtree.next = null; // also cut this node from list;
         }
         
         TreeNode leftTreeRootReturn = sortedListToBST(leftHead);
         TreeNode rightTreeRootReturn = sortedListToBST(rightHead);
         TreeNode root = new TreeNode(rootThisSubtree.val);
         //connect nodes to be a tree;
         root.left = leftTreeRootReturn;
         root.right = rightTreeRootReturn;
         
        return root;
    }
}