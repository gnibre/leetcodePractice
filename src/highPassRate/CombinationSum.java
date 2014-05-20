package highPassRate;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSum {
    
    
    /**
     * DP? 
     * for a group of x duplicate numbers, choices are pick 0,1,2, x of them. ;
     * and recursively call the function with new sum, new list( new offset) .
     * 
     */ 
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates==null||candidates.length<1){
            return res;
        }
        
        // if need to sort.
        Arrays.sort(candidates);
        ArrayList<Integer> picked = new ArrayList<Integer>();
        addResToComSum(candidates,candidates.length-1,target,picked,res);
        return res;
    }
    
    
    private void addResToComSum(int[] candidates,int end,int target,ArrayList<Integer> picked,ArrayList<ArrayList<Integer>> res){
        
        
        if(target==0){
            //picked just worked.
            ArrayList<Integer> copy = new ArrayList<Integer>();
            for(int p:picked){
                copy.add(p);
            }
            res.add(copy);
            return;
        }
        
        if(target<0){
            return; //over picked. dead end.
        }
        
        if(end<0){
            return; // no more candidates to pick, dead end.
        }
        
        
        // pick elements from end of the array first.;
        // end ones are bigger,  so will lead to dead end more quickly , and we can reuse elements in the head of hte array easily
        int v = candidates[end];
        
        // find how many in the list equal to this value.
        int s = end-1;
        while(s>-1&&candidates[s]==v){
            s--;
        }
        
        
        // now s is before the group of same candidates v;
        
        
        //!--------------- from the test case i understand what this one means
        // numbers in the candidate array can apear anytime to combine the T.   like [2,3,6]=>15 we can have{2,2,2,2,2,2,3}
        
        // if(v*(end+1)<target){
        //     // even choose all the elements left in the array can't reach the target.
        //     // so don't continue;
        //     return;
        // }
        
        int maxCount = target/v;
        
        for(int c = 0;c<=maxCount;++c){
            //try reuse this picked array. as well as reuse the candidate list.
            addResToComSum(candidates,s,target-c*v,picked,res);
            picked.add(0,v);
        }
        
        // after we tried all this. we return state.
        for(int c=0;c<=maxCount;++c){
            picked.remove(0);
        }
        
    }
    
}