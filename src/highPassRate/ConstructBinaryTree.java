package highPassRate;

import data.TreeNode;

public class ConstructBinaryTree {


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length<1){
            return null;
        }
        return buildTreeAux(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    
    /**
     * use this Aux so we don't need to copy arrays; just use index ;
     */
         /***
     * 
     *      N
     *    L   R 
     * 
     * pre:     N,[L],[R]   0, [1,p] [p+1, ]
     * in :     [L],N,[R]  [0,p-1] p [p+1, ]
     *  []  presents array;  as we know the size of the array,  and Node it self is of size 1; 
     * 
     * we can do it recursively; 
     * 
     */
    private TreeNode buildTreeAux(int[] preorder, int ps, int pe, int[] inorder, int is,int ie) {
        if(ps>pe){
            return null; //empty tree;
        }
        int nVal = preorder[ps];
        TreeNode n = new TreeNode(nVal);
                // copy this
    //          * pre: [ps,pe]        N,[L],[R]   0, [1,p] [p+1, ]              ps,[ps+1,x],[x+1,pe] 
    //         * in :  [is,ie]        [L],N,[R]  [0,p-1] p [p+1, ]              [is,p-1] p [p+1,ie]    so  x-ps = p-is;
    // for case   
        
        int p =-1; // p is the position where node n is at inorder list;
        for(int i=is;i<=ie;++i){
            if(inorder[i]==nVal){
                p=i;
                break;
            }
        }
        
        
        int x = p-is+ps;
        TreeNode leftTree = buildTreeAux(preorder,ps+1,x,inorder,is,p-1);
        TreeNode rightTree = buildTreeAux(preorder,x+1,pe,inorder,p+1,ie);
        n.left = leftTree;
        n.right = rightTree;
        return n;
    }
    
    
    
    

    /**
     * 
     * use this Aux so we don't need to copy arrays; just use index ;
     * 
     * 
     */
         /***
     * 
     *      N
     *    L   R 
     * 
     * pre:     N,[L],[R]   0, [1,p] [p+1, ]
     * 
     * in :     [L],N,[R]  [0,p-1] p [p+1, ]
     * post:    [L],[R],N  
     * 
     *  []  presents array;  as we know the size of the array,  and Node it self is of size 1; 
     * 
     * we can do it recursively; 
     * 
     */
    private TreeNode buildTreeAux2(int[] inorder, int is,int ie,int[] postorder, int ps, int pe) {
        if(ps>pe){
            return null; //empty tree;
        }
        
        int nVal = postorder[pe];
        TreeNode n = new TreeNode(nVal);
                // copy this
    //          * pre: [ps,pe]        N,[L],[R]   0, [1,p] [p+1, ]              ps,[ps+1,x],[x+1,pe] 
    
    
    //         * in :  [is,ie]        [L],N,[R]  [0,p-1] p [p+1, ]              [is,p-1] p [p+1,ie]
    //          * post:[ps,pe]        [L],[R],N  [0,p-1],[p,len-2],len-1;       [ps,x-1][x,pe-1] pe ;   x-ps = p-is;
    
    // for case   
        
        int p =-1; // p is the position where node n is at inorder list;
        for(int i=is;i<=ie;++i){
            if(inorder[i]==nVal){
                p=i;
                break;
            }
        }
        
        int x = ps+p-is;
        
        TreeNode leftTree = buildTreeAux(inorder,is,p-1,postorder,ps,x-1);
        TreeNode rightTree = buildTreeAux(inorder,p+1,ie,postorder,x,pe-1);
        n.left = leftTree;
        n.right = rightTree;
        return n;
    }
}
