package codes;

import java.util.ArrayList;

import data.TreeNode;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class RecoverBinarySearchTree {
    
    ////////////////////////////////////////////// so not completed in this one..... though passed oj without much fix after coded;
    
    
    
    int savedValue=Integer.MIN_VALUE;
    TreeNode lastNode;
    ArrayList<TreeNode> conflictList = new ArrayList<TreeNode>();
    /**************8
     * have no idea whtat's that O(N) space algorithm is... after read it.
     * 
     * 
     * tree traversal, in order traverse and find the bad two?   we can find it. but seems hard to swap...
     * 
     * for a tree problem, solve it from top down is most of time a good choice; 
     * 
     * cause most of time, we can devide the problem into two part:   left-subtree and right-subtree; and we can use recursive.
     * 
     * 
     * 1 , is top swapped by mistake? 
     *   A , top swap with some node in left tree;  =>    top? top.left;    top<top.right
     * 
     * =============================== xxxxxxxxxxx ooops, can't find a solution by top down 
     * 
     * do find a solution by traversal;
     * 
     * 1 , got the traversal node list.  
     * 2,  as the mistake is by swap; we can at most found 2 conflicts  in  the in order traversal arraylist.
     * 3, record the conflict, ; if only one conflict; swap these two
     *                             if two conflictes found,  left-one of first conflict and right one of second conflict are swaped one
     * 
     * 
     * 
     * 
     * ============================================================
     * after reading the discus, 
     * what i did, is actually good, except the inorder traversal; 
     * cause the recursive function call himself uses function stack and cost O(log n) space while traversing;
     * 
     * how to traversal a tree using O(1) space?        It's called threaded Binary Tree;!
     *  /// the traversal is called  Morris Traversal 
     *  http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
     *  
     *  how? 1 create links to in-order successor, when visiting, use these links.
     *  1. Initialize current as root 
		2. While current is not NULL
   			If current does not have left child
      			a) Print current’s data
      			b) Go to the right, i.e., current = current->right
   			Else
      			a) Make current as right child of the rightmost node in current's left subtree
      			b) Go to this left child, i.e., current = current->left
     *      
     * 
     * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
     * 
     * 
     * 
     * 
     * 
     */ 
    public void recoverTree(TreeNode root) {
        
        
//        treeInOrderTraversal(root);
    	
    	//!!!!!!!!!!!!!!!!!!!!!GREATE  , i learned Morris traversal of binary tree;
    	
    	treeMorrisTraversal(root);
        
        //after the traversal, the result is in the array : conflictList
        
        TreeNode newRoot;
        if(conflictList.size()==2){
            //swap this two nodes;
            swapTwoNodes(conflictList.get(0),conflictList.get(1),root);
        }else if(conflictList.size()==4){
            //    12745638 =>   74, 63 => swap 7 - 3
            swapTwoNodes(conflictList.get(0),conflictList.get(3),root);
        }
        //if conflicat list with other value? shit. wrong input from oj? ...
        // how too solve if there are 3 numbers swapped by mistake?  don't know...  so complex;
        // return newRoot;
    }
    
    
    
    
    private void treeInOrderTraversal(TreeNode node){
        
        if(node!=null){
            treeInOrderTraversal(node.left);
            
            nextNodeIsThis(node);
            
            treeInOrderTraversal(node.right);
        }
    }
    
    
    private void nextNodeIsThis(TreeNode n){
    	
    	int v = n.val;
        if(v<savedValue){
            /// conflict found;
            conflictList.add(lastNode);
            conflictList.add(n);
        }
        lastNode = n;
        savedValue = v;
    }
    
    //no recursive call needed!!!
    private void treeMorrisTraversal(TreeNode n){
    	
    	TreeNode cur = n;
    	//use two cursor to traverse whole tree;  one cur;  one pre ( predecessor of in-order traversal node of cur)
    	TreeNode predecessor;
    	
    	
    	while(cur!=null){ //while traversal not done;
    		
    		//rule1 : left is null;  so cool, no left,  node is visited cause left sub tree is AUTO done;
    		if(cur.left==null){
    			nextNodeIsThis(cur);
    			cur = cur.right;
    		}else{
    			//have left, means have predecessor;
    			
    			predecessor = cur.left;
    			
    			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!bugs; what we concern is this one's right child
    			while(predecessor.right!=null&&predecessor.right!=cur){
    				predecessor = predecessor.right;
    			}
    			//got predecessor;
    			
    			
    			/*
    			 *why we need if here? isn't the definition of predecessor is the one don't have right child?
    			 *cause in morrisTraversal, predecessor's right link is used to link to current node;
    			 * so for the first time we find it, we add/create the link
    			 * for the next time we find it;      we know left tree is done and we are back; ( suddenly known, ahh, im the successor ) 
    			 */
    			if(predecessor.right==null){
    				//first time find it;
    				predecessor.right = cur; //add a link; so we can finally back;
    				cur = cur.left;
    			}else{
    				// as we can know from the predecessor formula, predecessor while loop breaks. if it's not null, 
    				//it's because predecessor.right is cur;
    				//FIX THE TREE BACK TO IT'S SHAPE; 
    				//MORE importantly, the left tree is welldone;
    				predecessor.right = null;
    				
    				//visist it now;
    				nextNodeIsThis(cur);
    				cur = cur.right;
    			}
    		}	
    	}
    }
    
    
    /***
     * swap n1 and n2, incase one of them is root;  we return new root if root changed.
     * 
     * oshit. to return two nodes , we also need the parent of these two nodes?
     */ 
    private void swapTwoNodes(TreeNode n1,TreeNode n2, TreeNode root){
        if(n1==null||n2==null){
            return;
        }
        
        //                n1              n2
        //           n1-l  n1-r       n2-l  n2-r
        //  n2.left = n1-l; n2.right = n1-r;          n1.left=n2-l, n1.right=n2-r;        only if n1 and n2 are not connected;
        
        //            if                     n1
        //                                 n2  n1-r        swap are very complex;
        
        
        // so , give up object swapping again...... swap value for this simple algorithm;
        // as we can see from the return state of this one; cause we can't return new root; we don't really swap nodes; we only swap value;
        
        int swap = n1.val;
        n1.val = n2.val;
        n2.val = swap;
    }
    
}