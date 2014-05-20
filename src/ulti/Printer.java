package ulti;

import java.util.ArrayList;

import data.TreeNode;

public class Printer {

	public static void pArray(int[] al, int s, int e) {
		System.out.println(" print int array , from : " + s + "  to: " + e);
		for (int i = s; i <= e; ++i) {
			System.out.print(" " + al[i]);
		}
		System.out.println(" ");
	}
	
	public static void pArray(char[] al, int s, int e) {
		System.out.println(" print int array , from : " + s + "  to: " + e);
		for (int i = s; i <= e; ++i) {
			System.out.print(" " + al[i]);
		}
		System.out.println(" ");
	}

	
	public static <T> void printArray( T[] al){
		printArray(al,0,al.length-1);
	}
	
	public static <T> void printArray( T[] al,int s,int e){
		System.out.println(" print int array , from : " + s + "  to: " + e);
		for (int i = s; i <= e; ++i) {
			System.out.print(" " + al[i]);
		}
		System.out.println(" ");
	}
	
	public static void pArray(int[] al) {
		pArray(al, 0, al.length - 1);
	}
	public static void pArray(char[] al) {
		pArray(al, 0, al.length - 1);
	}

	public static <T>void pArrayArray(ArrayList<ArrayList<T>> all){
		System.out.println(" ==========print this array;      size : "+all.size());
		for(int i=0;i<all.size();++i){
			System.out.println(" i: "+i+"  "+all.get(i));
			
			for(T o:all.get(i)){
				System.out.print(" "+o);
			}
			System.out.println();
			
		}
	}
	
	
	public static void pArray(ArrayList<String> al){
		
		System.out.println(" ==========print this array;      size : "+al.size());
		for(int i=0;i<al.size();++i){
			System.out.println(" i: "+i+"  "+al.get(i));
		}
		
	}
	
	
	public static void inOrderTreeTraversal(TreeNode n){
		if(n==null) return;
		inOrderTreeTraversal(n.left);
		System.out.print(" -> "+n.val);
		inOrderTreeTraversal(n.right);
		
	}
}
