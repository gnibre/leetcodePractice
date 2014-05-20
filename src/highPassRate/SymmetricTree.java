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
public class SymmetricTree {
    
    
    /***
     * 
     * learned English ,  iteratively visit a tree? : it's breadth first search; level by level; save one level in a stack(arraylist)
     * keep visiting, adding to the stack. and visit next leve;
     * 
     * recursive visit a tree?  Tree traversal , function for left,for right, and node itself;
     * 
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        
        // return isSymmetricIteratively(root);
        return isSymmetricRecursively(root);
        
    }
    
    
    private boolean isSymmetricRecursively(TreeNode root){
        return isTwoNodeSymRecursive(root.left,root.right);
    }
    
    private boolean isTwoNodeSymRecursive(TreeNode n1,TreeNode n2){
        if(n1==null&&n2==null){
            return true;
        }
        if(n1==null||n2==null){
            return false;
        }
        if(n1.val!=n2.val){
            return false;
        }
        
        boolean left = isTwoNodeSymRecursive(n1.left,n2.right);
        boolean right = isTwoNodeSymRecursive(n1.right,n2.left);
        return left&&right;
        
    }
    
    
    
    
    private boolean isSymmetricIteratively(TreeNode root){
        //do it level by level;
        
        ArrayList<TreeNode> curLevel = new ArrayList<TreeNode>();
        curLevel.add(root); //init;
        
        int size;
        while(curLevel.size()>0){
            size = curLevel.size();
            int head = 0;
            int tail = size-1;
            
            ArrayList<TreeNode> nextLevel = new ArrayList<TreeNode>();
            // we don't need to check this level; we are sure this level is symmetric;
            // for next level, 
            while(head<=tail){
                if(head!=tail){
                    //first child and last child of this level;
                    
                    if(!isTwoNodeSymmetric(curLevel.get(head),curLevel.get(tail))){
                        return false;
                    }
                    
                    
                    //ok; next level still symmetric; add next level;  
                    //!!! we only add half here, cause it's symmetric, we will add the other half latter.
                    if(curLevel.get(head).left!=null){
                        nextLevel.add(0,curLevel.get(head).left); //one to the head;
                        nextLevel.add(curLevel.get(tail).right);    // one to the end; as we are checking symmetric;
                                                // the final order in next Level is not like the next level of tree;
                                                // but it works for symmetric check; because it's added symmetricly;
                    }
                    if(curLevel.get(head).right!=null){
                        nextLevel.add(0,curLevel.get(head).right); //one to the head;
                        nextLevel.add(curLevel.get(tail).left);    // one to tail;
                    }
                }else{
                    // head is tail; check two child is symeetric
                    if(curLevel.get(head).left==null){
                        if(curLevel.get(head).right!=null){
                            return false;
                        }
                    }else{
                        if(curLevel.get(head).right==null||curLevel.get(head).right.val!=curLevel.get(head).left.val){
                            return false;
                        }
                    }
                    
                    // still ok; cool; add half;
                    
                    if(curLevel.get(head).left!=null){
                        nextLevel.add(0,curLevel.get(head).right);
                        nextLevel.add(curLevel.get(head).left);
                    }
                }
                
                head++;
                tail--;
            } //end of while loop of head-tail;
            
            
            // this level is checked;
            
            for(int i=0;i<size;++i){
                curLevel.remove(0); // going to next level
            }
            
            curLevel = nextLevel;
            
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! this duplicated node is not the node that was symmetric....
            // this copy will make next level error;
            // size = curLevel.size();
            // for(int i=size-1;i>=0;--i){
            //     curLevel.add(curLevel.get(i)); //symmetricly add to this level;
            // }
        }
        
        return true; //not easy to be here;
    }
    
    
    
    private boolean isTwoNodeSymmetric(TreeNode n1,TreeNode n2){
        if(n1.left==null){
            if(n2.right!=null){
                return false;
            }
        }else{
            if(n2.right==null||n1.left.val!=n2.right.val){
                return false;
            }
        }
        // n1-left; n2-right pass;
        
        
        if(n1.right==null){
            if(n2.left!=null)
            return false;
        }else{
            if(n2.left==null||n2.left.val!=n1.right.val){
                return false;
            }
        }
        return true;
    }
    
    
    
    
}