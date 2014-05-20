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
public class BinaryTreeLevelOrderTranversal {
    
    /***
     * 
     * if we can use memory of size as one level of tree nodes. 
     * this will be easy to do,
     * hope no TLE of oj.
     * it's classic breadth first search.
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<TreeNode> levelNodes = new ArrayList<TreeNode>();
        
        if(root!=null){
            levelNodes.add(root); ///first level have only root //only if root is not null.
            // we don't add null nodes to the level nodes.
        }
        
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        while(levelNodes.size()>0){
            // each time after this callled,  levelNodes will becase nodes of next level.
            addResEachLevel(levelNodes,res);
        }
        return res;
    }
    
    
    /**
     *  go each level, add nodes visited to the res.; add next level to levelNodes.
     */ 
    private void addResEachLevel(ArrayList<TreeNode> levelNodes, ArrayList<ArrayList<Integer>> res){
        int n = levelNodes.size();
        ArrayList<Integer> thisLevel = new ArrayList<Integer>();
        TreeNode node;
        for(int i=0;i<n;++i){
            node = levelNodes.get(i);
            thisLevel.add(node.val);
            if(node.left!=null){
                levelNodes.add(node.left);
            }
            if(node.right!=null){
                levelNodes.add(node.right);
            }
        }
        res.add(thisLevel); //add this level.
        
        for(int i=0;i<n;++i){
            levelNodes.remove(0); // moveon we are going to visit next  ;. this level's res has added.
        }
    }
    
}