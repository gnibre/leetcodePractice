package highPassRate;

import data.TreeLinkNode;

/**
 * Definition for binary tree with next pointer. public class TreeLinkNode { int
 * val; TreeLinkNode left, right, next; TreeLinkNode(int x) { val = x; } }
 */
public class PopulateNextRightPointerInBinaryTree {

	
	public void go(){
		
		TreeLinkNode n1 = new TreeLinkNode(1);
		TreeLinkNode n2 = new TreeLinkNode(2);
		TreeLinkNode n3 = new TreeLinkNode(3);
		n1.left = n2;
		n1.right = n3;
		
		connect(n1);
	}
	
	/***
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
			System.out.println(" visit: "+visit.val);
			firstChildOfThisLevel = null; // it's now for next level.
			lastChild = null; // if don't do this. we can link two levels
								// together.
			// for one level of fathers.
			while (visit!=null) {
				if(visit.left==null){
					break;
				}
				// get visit's two children
				if (firstChildOfThisLevel == null) {
					firstChildOfThisLevel = visit.left;
				}

				if (lastChild != null) {
					lastChild.next = visit.left;
				}
				lastChild = visit.left;

				if (visit.right != null) {
					lastChild.next = visit.right;
					lastChild = visit.right;
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