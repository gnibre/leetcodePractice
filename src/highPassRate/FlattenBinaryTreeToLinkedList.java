package highPassRate;

import ulti.Printer;
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
public class FlattenBinaryTreeToLinkedList {
	
	public void go(){
		
		TreeNode root = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		
		root.left = two;
		
		flatten(root);
		
		System.out.println(" print tree");
		Printer.inOrderTreeTraversal(root);
		System.out.println(" print tree-left");
		Printer.inOrderTreeTraversal(root.left);
		System.out.println(" print tree-right");
		Printer.inOrderTreeTraversal(root.right);
	}
	
    /**
     * 
     * morris tree traversal?
     * 
     */
    public void flatten(TreeNode root) {
        if(root==null) return;
        
        TreeNode first = root;
        while(first.left!=null){
            first=first.left;
        }
        treePreOrderTraversal(root,null);
        root = first;
    }
    
    
    /**
     * return last one visited in this subtree;
     * 
     * @param n
     * @param last
     * @return
     */
    private TreeNode treePreOrderTraversal(TreeNode n,TreeNode last){
    	if(n==null){
    		return last;
    	}
    	if(last!=null){
    		last.right = n;
    		last.left = null;
    	}    	
    	
    	
    	// as n is passed as the last of this left tree; the right child of n will be changed; so;
    	//!!!!!!!!!!!!!! this in important;
    	TreeNode savedRightChild = n.right;
    	TreeNode lastLeft = treePreOrderTraversal(n.left,n);
    	
    	TreeNode lastThisSubTree = n;
    	if(lastLeft!=null){
    		lastThisSubTree = lastLeft;
    	}
    	
    	TreeNode lastRight = treePreOrderTraversal(savedRightChild,lastThisSubTree);
    	if(lastRight!=null){
    		lastThisSubTree = lastRight;
    	}
    	return lastThisSubTree;
    }
    
    
    
    
    
    /**
     * need return last node of this traversal, to connect the next;
     * last: the one before this subtree;
     */ 
    private TreeNode treeInOrderTraversal(TreeNode n, TreeNode last){
    	
        if(n==null){
            return last;
        }
        System.out.println("  treePreOrderTraversal : "+n.val +" ,last: "+(last==null?0:last.val));
        
        
        TreeNode lastInLeftTree = treeInOrderTraversal(n.left,last);
        
        //connect node; ; will overwrite the old right child. so shall take care;
        TreeNode saveRightChild = n.right;
        
        if(lastInLeftTree!=null){
        	System.out.println(" connect:  from left tree   "+lastInLeftTree.val+"  => "+n.val);
            lastInLeftTree.right = n;
        }else{
            if(last!=null){
                last.right = n;
                System.out.println(" connect: from last    "+last.val+"  => "+n.val);
            }
        }
        n.left = null;
        
        TreeNode lastInRightTree = treeInOrderTraversal(n.right,n);
        if(lastInRightTree==null){
            return n;
        }
        return lastInRightTree;
    }
}