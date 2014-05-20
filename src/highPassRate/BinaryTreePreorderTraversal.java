package highPassRate;

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
public class BinaryTreePreorderTraversal {
    /**
     * preorderTraversal:
     * 
     * 1 root,
     * 2 left
     * 3 right
     * 
     * 
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        preorderTraversal(root,res);
        return res;
    }
    
    /**
     * add traversal res to the al.
     */
    private void preorderTraversal(TreeNode node,ArrayList<Integer> al){
        if(node==null){
            return;
        }
        al.add(node.val);
        preorderTraversal(node.left,al);
        preorderTraversal(node.right,al);
    }
    
}