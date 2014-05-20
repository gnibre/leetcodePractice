package highPassRate;

import data.TreeNode;

public class PathSum {
	
	
	
	
    public boolean hasPathSum(TreeNode root, int sum) {
        return checkExist(root,0,sum);
    }
    
    /***
     * check if exist this pathsum.
     * input n: current node.
     * pSum,  path sum before this node ( parent path sum)
     * reuired: sum.
     */
    private boolean checkExist(TreeNode n,int pSum,int requiredSum){
        if(n==null){
            return false;
        }
        pSum+=n.val;
        if(n.left==null&&n.right==null){
            // im a leaf!
            if(pSum==requiredSum){
                return true;
            }else{
                return false;
            }
        }
        // not a leaf,
        // have a chance to search nodes.
        
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // it's an || , do not return for a subcase!!!!
        
        boolean find = false;
        if(n.left!=null){
            find = checkExist(n.left,pSum,requiredSum);
            if(find){
                return find;
            }
        }
        
        if(n.right!=null){
            find = checkExist(n.right,pSum,requiredSum);
        }
        return find;
    }
}