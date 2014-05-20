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
public class BinaryTreeZigZag {
    
    
    /***
     * 
     * breadth first search add a litter fun;
     * 
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        
        ArrayList<TreeNode> curLevel = new ArrayList<TreeNode>();
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(root==null){
            return res;
        }
        
        curLevel.add(root);
        int size;
        int direct =0;
        TreeNode node;
        while(curLevel.size()>0){
            size = curLevel.size();
            ArrayList<Integer> resThisLevel = new ArrayList<Integer>();
            for(int i=0;i<size;++i){
                node = curLevel.get(i);
                if(direct==0){
                    resThisLevel.add(node.val);
                }else{
                    resThisLevel.add(0,node.val);
                }
                if(node.left!=null){
                   curLevel.add(node.left); 
                }
                if(node.right!=null){
                   curLevel.add(node.right); 
                }
            }
            
            for(int i=0;i<size;++i){
                curLevel.remove(0);
            }
            res.add(resThisLevel);
            direct = 1-direct; // change direct; zigzag;
        }
        return res;
    }
}
