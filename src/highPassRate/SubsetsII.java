package highPassRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



/****
 * 
 * 
 * 
 * 
 * @author Yubing
 *
 */
public class SubsetsII {
    
    /**
     *  different from subsetsI,   have duplicate elements now, in the array.
     *  different part is only when add. 
     * 
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] S) {
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
    	// find a group of same char first.
    	
    	int to = cur+1;
    	while(to<S.length&&S[to]==S[cur]){
    		to++;
    	}
    	// to is next.
    	
    	
    	
    	int count = to-cur;
    	if(to==S.length){
        	// choice is count+1;
        	// add 0 to count of this number. 
        	for(int i=0;i<=count;++i){
        		ArrayList<Integer> oneSubset = new ArrayList<Integer>();
        		for(int p:pr){
                    oneSubset.add(p);
                }
        		// add count times
        		for(int c=0;c<i;++c){
        			oneSubset.add(S[cur]);
        		}
        		res.add(oneSubset);
        	}
        	return;
    	}
    	
    	// else, start from to.
    	
        //if take. take count?
    	// take 0 of this number.
    	subsetAdd(S,to,pr,res);
    	
    	for(int i=0;i<count;++i){
//    		take i+1; of this number.
    		pr.add(S[cur]);
    		subsetAdd(S,to,pr,res);
    	}
    	
    	//at last, we return this pr as not take anything.
    	
    	for(int i=0;i<count;++i){
    		pr.remove(pr.size()-1);
    	}
        //# pr is the same as before it is passed into the function.
        
    }
}