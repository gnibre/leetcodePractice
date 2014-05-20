package highPassRate;

import java.util.ArrayList;

import data.TreeNode;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class UniqueBinarySearchTreesII {
    
    
    /***
     * 
     * we just need count for the last time,
     * this time, we need combination.
     * but all the same, we still break this down by assuming a root node,
     *  and recursivly work on left case and right case.
     * 
     * 
     * so, if you don't care runtime,
     * each time we want create a subtree of size x , and number from s to e, 
     * we do create it from the very beginning?  this will ease the programming, but don't know if it will be a TLE.
     * 
     * let's try this first.
     * 
     * say, we can create a tree(root) contaning number from s to e by doing :
     * assume the root of tree is m, (s<=m<=e)
     * there are l1 ways to create the left tree (s,m)
     * there are r1 ways to create the right tree
     * we combine all the case and get l1*r1 result.
     * 
     * and what's more, we assume the root of tree is from s to m.
     * 
     * this is kinda time comsuming.
      * 
     * 
     */
    public ArrayList<TreeNode> generateTrees(int n) {
        return genTree(1,n);
    }
    
    /**
     * give number from s to end, return all the cases that can be to gen trees.
     */
    private ArrayList<TreeNode> genTree(int s,int e){
        
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        
        if(s>e){
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! add a null to the array!!!
            res.add(null);
            return res; // return res size 1 for the empty tree. cause we are using multiply.  don't return null.
        }
        
        for(int root=s;root<=e;++root){
            ArrayList<TreeNode> leftRes = genTree(s,root-1);
            
            ArrayList<TreeNode> rightRes = genTree(root+1,e);
            
            int ls = leftRes.size();
            int rs = rightRes.size();
            
            
            for(int l=0;l<ls;++l){
                for(int r=0;r<rs;++r){
                    TreeNode rn = new TreeNode(root); // create a case.
                    rn.left = leftRes.get(l); //connect left.
                    rn.right = rightRes.get(r); //connect right.
                    res.add(rn);
                }
            }
        }
        return res;
    }
    
}