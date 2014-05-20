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
public class ConvertSortedArrayToBinarySearchTree {
	
	public void go(){
		int[] num ={
				-1,0,1,2
		};
		
		TreeNode res = sortedArrayToBST(num);
		Printer.inOrderTreeTraversal(res);
		
	}
	
    
    /**
     * we've done change list node to binaray search tree;
     * this time we got array, easier to count, easier to link;
     * 
     * left and right, at least one of them is full :  of size 2^n-1 ; last level number: 2^(n-1) 
     */ 
    public TreeNode sortedArrayToBST(int[] num) {
        if(num==null) return null;
        return sortedArrayToBST(num,0,num.length-1);
    }
    
    private TreeNode sortedArrayToBST(int[] num,int s,int e){
    	System.out.println(" sortedArrayToBST    s: "+s+"  e: "+e);
        if(s>e) return null;
        TreeNode tn;
        if(s==e){
            tn = new TreeNode(num[s]);
            return tn;
        }
        int count = e-s+1;
        // 0 is full tree of level 0; 
        // 1 is full tree of level 1;
        int fullTreeSize = 0;  // full tree size is 2^n-1;  :  0,1,3,7,15; 
        
        while(fullTreeSize<count){
            // fullTreeSize = (fullTreeSize+1)*2-1;
            fullTreeSize = 2*fullTreeSize+1; // like this formula better; 2 litte tree and root , is bigger tree;
        }
        //when while loop break.  fullTreeSize>=count; so this fullTree contain the result tree;
        
        // last level can contain  (fullTreeSize+1) nodes totally 
        // last level of left tree can take at most (fullTreeSize+1)/2 ; the rest of nodes will be taken by right tree ( or take 0 make right tree a smaller full tree);
        fullTreeSize = (fullTreeSize+1)/2-1;
        
        int rest = count-fullTreeSize;
        int lastLevelMax = (fullTreeSize+1)/2;
        
        int leftTreeLastLevel = rest>lastLevelMax?lastLevelMax:rest;
        // int rightTreeLastLevel = rest-leftTreeLastLevel;
        
        int leftRightInFullTree = (fullTreeSize-1)/2;
        
        int leftCount = leftTreeLastLevel+leftRightInFullTree;
        // int rightCount = rightTreeLastLevel+leftRightInFullTree;
        
        
        System.out.println(" count : "+count);
        System.out.println(" fullTreeSize : "+fullTreeSize);
        System.out.println(" rest : "+rest);
        System.out.println(" lastLevelMax : "+lastLevelMax);
        System.out.println(" leftRightInFullTree : "+leftRightInFullTree);
        System.out.println(" leftTreeLastLevel : "+leftTreeLastLevel);
        System.out.println("leftRightInFullTree : "+leftRightInFullTree);
        System.out.println(" left count : "+leftCount);
        // found we don't need right tree count;
        TreeNode leftTree = sortedArrayToBST(num,s,s+leftCount-1);
        TreeNode root = new TreeNode(num[s+leftCount]);
        TreeNode rightTree = sortedArrayToBST(num,s+leftCount+1,e);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }
    
    
}