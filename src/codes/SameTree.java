package codes;

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
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        if(p==null){
            return q==null;
        }
        if(q==null){
            return false;
        }
        
        if(p.val!=q.val){
            return false;
        }
        
        int left = p.left.val;
        
        boolean same = false;
        same = isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        if(same){
            return same;
        }
        same =  isSameTree(p.left,q.right)&&isSameTree(p.right,q.left);
        return same;
    }
}