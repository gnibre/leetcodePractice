package highPassRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Subsets {
    
    /**
     * 
     * sort the input if not sorted yet. for non-descending order.
     * 
     * for each element one by one,  two choices: take or not.
     * 
     * for S = [a,b,c];
     * so we can code this like : 011 (3) for take b and c, and 101(5) for take a and c.
     * 
     * 
     *            
     *  
     * when the end reachs, output.
     * 
     * 
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(S==null||S.length<1){
            return res;
        }
        Arrays.sort(S);
        ArrayList<Integer> pr = new ArrayList<Integer>();
        Collections.sort(pr);
        subsetAdd(S,0,pr,res);
        return res;
    }
    
    private void subsetAdd(int[] S, int cur,ArrayList<Integer> pr,ArrayList<ArrayList<Integer>> res){
        
        // end of it.
        if(cur==S.length-1){
            // if take
            ArrayList<Integer> oneSubset = new ArrayList<Integer>();
            ArrayList<Integer> anotherSubset = new ArrayList<Integer>();
            for(int i:pr){
                oneSubset.add(i);
                anotherSubset.add(i);
            }
            
            // if take 
            oneSubset.add(S[cur]);
            res.add(oneSubset);
            
            // if not take.
            //!=== must use a copy of this pr.
            res.add(anotherSubset);
            return;
        }
        
        // not the end, 
        //if take.
        pr.add(S[cur]);
        subsetAdd(S,cur+1,pr,res);
        // if not take this one.
        pr.remove(pr.size()-1);
        
        subsetAdd(S,cur+1,pr,res);
        //# pr is the same as before it is passed into the function.
        
    }
}