package highPassRate;

import java.util.ArrayList;
import java.util.Stack;


public class TrappingRainWater_sec {
	int calcedLowest = 0; // below this height, we already calculated.
    /**
     * 
     * 
     * 
     * second try after 2 months, 
     * glad it passed the oj very fast even before past it to the eclipse;
     * but it's still, not as easy as old version,
     * please read old version...............
     * 
     * 
     * 
     * 
     * 
     * 1, keep a stack(or maybe list working as stack.) of left boundary.
     * 
     * 2  scan the list from left to right, get current hight, current position.
     * 3,  a.  current < left bount,  record the distance,
     *     b.     current>left bount, checkout catched by left boundary
     *           update left bountary stack;
     *           remember the lowest, keep checking out.
     * 
     *   29, 18, 5, 3 , 2 , 4
     *   1    1  1  1   1   
     * 
     * 
     * 
     */
    public int trap(int[] A) {
        if(A==null||A.length<3){
            return 0;
        }
        
        ArrayList<Integer> leftBA = new ArrayList<Integer>();
        // use 0 as top;
        leftBA.add(0)               ;// offer, poll for priorityqueue,  push pop for stack; ; 
        int res = 0;
        int curH;
        
        //scan once;
        for(int i=1;i<A.length;++i){
            
            if(A[i]==A[leftBA.get(0)]){
                //case same, update new lowest left boundary
                leftBA.set(0,i);
            }else if(A[i]<A[leftBA.get(0)]){
                leftBA.add(0,i);
            }else{
                res+=checkout(A,leftBA,i); // give boundary list, lowest, right boundary;
            }
        }
        
        return res;
    }
    
    
    
    private int checkout(int[]A,ArrayList<Integer> bl, int rb){
        int res = 0;
        int lb;
        while(bl.size()>0){
            lb = bl.get(0);
            if(A[lb]<A[rb]){
                res+=(rb-lb-1)*(A[lb]-calcedLowest);   // width * height;
                //the first time we got rightboundary higher than left, it just update calcedlowest, to make it right;
                calcedLowest = A[lb];  // lowest below left is calculated.
            }else if(A[lb]==A[rb]){
                
                // well same as case above, shall merge.
                res+=(rb-lb-1)*(A[lb]-calcedLowest);   // width * height;
                calcedLowest = A[lb];  // lowest below left is calculated.
            }else{
                
                // left boundary > right;
                res+=(rb-lb-1)*(A[rb]-calcedLowest);
                calcedLowest = A[rb];
                break;
            }
            
            //only when while breaked, we don't move left boundary from the stack;
            bl.remove(0);
        }
        
        // don't forget this at last;
        bl.add(0,rb);
        return res;
    }
}