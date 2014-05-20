package highPassRate;

import java.util.ArrayList;

import ulti.Printer;

public class Combinations {
    
	public void go(){
		
		int n =2;
		int k= 1;
		
		ArrayList<ArrayList<Integer>> res = combine(n,k);
		
		Printer.pArrayArray(res);
	}
	
	
    /**
     * 
     * 
     * pick k out of n;
     * 
     * ordered C(n,k) ; 
     * res size is ;
     * is  n*(n-1)*..(n-k+1)/ k*(k-1)***1
     * 
     * recursive; pick one (do the rest)
     * 
     */
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(n<0||k>n){
            return res;
        }
        
         ArrayList<Integer> post = new  ArrayList<Integer>();
         combineAux(res,post,n,k);
         return res;
    }
    
    /**
     * record post is easier, so we don't need to save the array'
     */
    private void combineAux(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> post, int n, int k) {
        System.out.println( " n: "+n+" k: "+k);
        if(k==0){
        	
        	ArrayList<Integer> copy = new ArrayList<Integer>();
        	for(int i:post){
        		copy.add(i);
        	}
        	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! mistakes;
        	//this post array will be changed later;
            res.add(copy); //one result end;
            return;
        }
        if(n<1){
            return; //dead end
        }
        
        // not end, need recursively call
        // question is , do we take the last element or not?
        //take; 
        post.add(0,n); 
        combineAux(res,post,n-1,k-1);
        post.remove(0);
        
        // no take; still n-1 cause we don't have no chance to pick that number again;
        combineAux(res,post,n-1,k);
    }
    
    
}