package highPassRate;

import data.TreeLinkNode;

/**
 * Definition for binary tree with next pointer. public class TreeLinkNode { int
 * val; TreeLinkNode left, right, next; TreeLinkNode(int x) { val = x; } }
 * 
 * 
 * 
 */
public class PopulateNextRightPointerInBinaryTreeII {
	    /***
		 * 
		 * 
		 * // from <Populating Next Right Pointers in Each Node> see if it works.  maybe need fix
		 * // 
		 * 
		 * get this done from top to bot. so fater konws condition of all his
		 * children, make a father connect all it's children , and left childrens do
		 * that again and it's done.
		 * 
		 */
		public void connect(TreeLinkNode root) {
			if(root==null){
				return;
			}
			
			TreeLinkNode father = root;
			// what father do is link all child.
			father.next = null;
			
			
			
			TreeLinkNode lastChild = null;
			TreeLinkNode firstChildOfThisLevel = root; // father of all node, but
														// still named as child of
														// this level.
			//
			while (firstChildOfThisLevel != null) {
				TreeLinkNode visit = firstChildOfThisLevel; // in this visiting.
															// visit is father of
															// following level.
//	 			System.out.println(" visit: "+visit.val);
				firstChildOfThisLevel = null; // it's now for next level.
				lastChild = null; // if don't do this. we can link two levels
									// together.
				// for one level of fathers.
				TreeLinkNode vc; //visitor's child.
				while (visit!=null) {
				    
				    //have left.
				    if(visit.left!=null){
				        vc = visit.left;
				        if(lastChild!=null){
				            lastChild.next = vc;
				        }
				        lastChild = vc;
				        if (firstChildOfThisLevel == null) {
						    firstChildOfThisLevel = vc;
					    }
				    }
				    
					
					if (visit.right != null) {
					    vc = visit.right;
					    if(lastChild!=null){
				            lastChild.next = vc;
				        }
				        lastChild = vc;
				        if (firstChildOfThisLevel == null) {
						    firstChildOfThisLevel = vc;
					    }
					}
					visit = visit.next; // chage visit to next father.
				}
				// we are going to next level. you are the right most in this lvel.
				if (lastChild != null) {
					lastChild.next = null;
				}
			}

		}
	}