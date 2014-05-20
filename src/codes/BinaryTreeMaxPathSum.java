package codes;

import data.TreeNode;

/**
*
* don't have much idea.  so many paths in a tree, 
* 
* what to do? dp 
* 
* for root node ,
* 1 , root is in the max-sum path; 
*     1.a find the max-sum in the left tree, can't have both left and right child of root of left tree.
*     1.b find the max-sum in the right tree, can't have both left and right child of root of right tree
* 2,  root is not in the max-sum path;  so it' either in left tree or in right tree
* 
* 
* for a tree, we difine two function/values  
* 1:(mi) max-sum-subtree-intree (  in the subtree , or can pass through the root)
* 2:(mr) max-sum-subtree-root ( must contain root or contain nothing, the max sum with root)
* 
* and for a binary tree root at root and have left and right children,
* mi(root) 
* case1, in left or right tree,       mi(left) , mi(right)
* case2 ,through root.    val(root)+max{0,mr(left)} +max{0,mr(right)}  // we have to include root even val(root)<0, 
*         as it's a case for through root. but we can giveup any of right/left brunch if they <0
* so mi(root) is the bigger one of two. if they are same, choose either one don't matter, cause this is the end of mi, 
* will not be connected to outworld through tree root.
* 
* mr(root)
* case 1,  from left ,   max{0, mr{left}}+ max{0, val(root)}
* case 2, from right,   max{0,mr(right)}+ max{0,val(root)}
* 
* if 0 is always the max, haha, mr(root) = 0 and is empty, means that when you need this tree as a child for max-sub-path, 
* better take nothing from this tree, cause it's all positive. so the mr(root) is 0.
* 
* 
* so when we got mi and mr of a tree, we can build the whole forest and know mi/mr of all the nodes.
* 
* and maxPathSum = mi(root)
* 
*/
public class BinaryTreeMaxPathSum {
	
	public void go(){
		TreeNode root = new TreeNode(-2);
		TreeNode n = new TreeNode(-1);
		root.left = n;
		
		int res = maxPathSum(root);
		System.out.println(" res: "+res);
	}
	
	
  public int maxPathSum(TreeNode root) {
      int[] res = maxPathAux(root);
      return res[0];
      
  }
  
  /**
   *  input: node
   *  res:  int[2]= { mi,mr}
   */
  private int[] maxPathAux(TreeNode root){
	  
      int mi,mr;
      int[] res = new int[2];
      
      // sure, no root of this tree;
      if(root==null){
    	// !!!!!!!!!!!!!!!!!!!!!!!! what's in this tree?  nothing, 
    	  //we can't set a 0 or -1, as this value maybe the MAX value of the whole tree.. and take as the max-sum-path.
    	  // for mr, set to 0 is ok. cause we chose this as mr equals we chose nothing as a subtree. 
          res[0] = Integer.MIN_VALUE; 
          res[1] = 0;
          return res;
      }
      
      
      
      // base case, root and only root...
      if(root.left==null&&root.right==null){
          res[0] = root.val; // mi, 
          res[1] = Math.max(0,root.val); // mr , mr is always >=0; cause we can choose not to pick 
          return res;
      }
      
      System.out.println("  maxPathAux :  "+root.val);
      int[] leftRes = maxPathAux(root.left);
      int[] rightRes = maxPathAux(root.right);
      
      System.out.println("  left sub tree  mi, mr:  "+leftRes[0]+"  , "+leftRes[1]);
      System.out.println("  right sub tree  mi, mr:  "+rightRes[0]+"  , "+rightRes[1]);
      
      
      int leftOrRight;
      leftOrRight = Math.max(leftRes[0], rightRes[0]); //  choose left mi or right mi
      int throughRoot = root.val+Math.max(0,leftRes[1])+Math.max(0,rightRes[1]); // root and leftMR? and rightMR? pick ones that >0
      
      System.out.println("   leftOrRight:  "+leftOrRight+"      throughRoot : "+throughRoot);
      
      mi = Math.max(leftOrRight,throughRoot); // got mi
      
      // for mr. we have to contain root or we contain nothing.
      int fromLeft = Math.max(0,root.val+Math.max(leftRes[1],0));
      int fromright = Math.max(0,root.val+Math.max(rightRes[1],0));
      mr = Math.max(fromLeft,fromright);
      
      System.out.println("   fromLeft:  "+fromLeft+"      fromright : "+fromright);
      
      res[0] = mi;
      res[1] = mr;
      
      System.out.println("   result for this tree:   root "+root.val+"      mi: "+mi+"    mr: "+mr);
      
      return res;
      
  }
  
}