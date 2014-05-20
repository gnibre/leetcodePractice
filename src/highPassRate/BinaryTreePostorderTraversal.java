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
public class BinaryTreePostorderTraversal {
    /**
     * postorderTraversal:
     * 
     *  
     * 1 left
     * 2 right
     * 3 root.
     * 
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        postorderTraversal(root,res);
        return res;
    }
    
    /**
     * add traversal res to the al.
     */
    private void postorderTraversal(TreeNode node,ArrayList<Integer> al){
        if(node==null){
            return;
        }
        postorderTraversal(node.left,al);
        postorderTraversal(node.right,al);
        al.add(node.val);
    }
}