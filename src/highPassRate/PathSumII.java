package highPassRate;

import java.util.ArrayList;

import data.TreeNode;

public class PathSumII {
    /**
     * 
     * like path sumI,     * 
     * difficult part must be the output.... do not take too much time in output, or you will get a TLE..
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        ArrayList<Integer> pl = new ArrayList<Integer>();
        checkPathSum(root,pl,0,sum,res);
        return res;
    }
    
    
    /**
     * check this subcase, if exsit, add to res, if not , just continue;
     */
    private void checkPathSum(TreeNode n,ArrayList<Integer> pl, int pSum,int sum,ArrayList<ArrayList<Integer>> res){
        
        if(n==null){
            return;
        }
        int newSum = pSum+n.val;
        
        if(n.left==null&&n.right==null){
            //leaf
            if(newSum==sum){
                ArrayList<Integer> one = new ArrayList<Integer>();
                for(int p:pl){
                    one.add(p);
                }
                one.add(n.val);
                res.add(one);
            }
            return;
        }
        
        // not a leaf.
        pl.add(n.val);
        if(n.left!=null){
            checkPathSum(n.left,pl,newSum,sum,res);
        }
        
        if(n.right!=null){
            checkPathSum(n.right,pl,newSum,sum,res);
        }
        
        
        //after function, pl same as it was. 
        pl.remove(pl.size()-1);
    }
    
    
    
}