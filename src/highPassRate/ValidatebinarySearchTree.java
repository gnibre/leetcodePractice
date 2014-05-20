package highPassRate;

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
public class ValidatebinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isInScope(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    
    /**
     * if numbers in this tree are all   l<  and < r. it's ok;
     */
    private boolean isInScope(TreeNode root, int l, int r){
        if(root==null){
            //!!!!!!!!!!!!!!!11 fixed bugs added this.
            return l<r;
        }
        int v = root.val;
        return isInScope(root.left,l,v)&&isInScope(root.right,v,r);
    }
    
}