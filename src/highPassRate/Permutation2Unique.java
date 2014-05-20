package highPassRate;

import java.util.ArrayList;
import java.util.HashSet;



public class Permutation2Unique {

	public void go(){
//		permutation(3);
//		permutation(4);
//		permutation(5);
	}
	

/**
 * 
 * number input maybe with replication. like [1,2,3,1,2,3,1], permutation don't show same result twice.
 * 
 * @author Yubing
 *
 */
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        
        int len = num.length;
        ArrayList<Integer> head = new ArrayList<Integer>();
        return pAux(head,num);
    }
    
    
    /**
     * head is what's have choose before the num array to be permutated.
     * the permutation result of num array apply to the end of already exist head.
     * 
     * head is destroyed/ruined after use?
     * 
     */ 
    private ArrayList<ArrayList<Integer>> pAux(ArrayList<Integer> head,int[] num){
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        
        
        if(num.length==1){
        	ArrayList<Integer> headCopy = new ArrayList<Integer>();
        	for(int i:head){
                headCopy.add(i);
            }
            headCopy.add(num[0]);
            res.add(headCopy);
            return res;
        }
        
        
        //pick ith as next.
        HashSet<Integer> uniquePickSet = new HashSet<Integer>();
        for(int i=0;i<num.length;++i){
        	/////////////////////////////////////////////////////////////only thing need to fix is here.
        	//about to pick ith. check first.
            if(uniquePickSet.contains(num[i])){
                continue;
            }
            uniquePickSet.add(num[i]);
            
        	
        	
            int[] left = new int[num.length-1];
            for(int j=0;j<i;++j){
                left[j] = num[j];
            }
            for(int j=i;j<num.length-1;++j){
                left[j] = num[j+1];
            }
            
            ArrayList<Integer> headCopy = new ArrayList<Integer>();
            for(int h:head){
                headCopy.add(h);
            }
            //chose ith.
            headCopy.add(num[i]);
            
            ArrayList<ArrayList<Integer>> part = pAux(headCopy,left);
            for(ArrayList<Integer> path:part){
                res.add(path);
            }
        }
        return res;
    }
	
	
	
}
