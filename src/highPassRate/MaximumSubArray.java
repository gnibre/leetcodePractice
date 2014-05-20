package highPassRate;


public class MaximumSubArray {
    /**
     * 
     * scan once; for each element visiting, think one thing: is this number in the maximum subarray; (or shall we take this)
     * if we can't find a subarry end at this element and sum>0, we give up this element;
     */ 
    public int maxSubArray(int[] A) {
        if(A==null||A.length<1) return 0;
        
        boolean tryDC = true;
        if(tryDC) return maxSubArrayDC(A,0,A.length-1);
        
        // second algorithm; 
        
        int subSum = 0;
        int max = A[0];
        for(int i=0;i<A.length;++i){
            subSum+=A[i];
            if(subSum>max){
                    max = subSum;
            }
            if(subSum<0){
                //giveup this sub, include this will get smaller sum;
                subSum = 0;
            }
        }
        return max;
    }
    
    /**
     * use divide and conquer as sugguested; 
     * 
     * how to devide? 
     *  -----part1-------- value? ------part2---------
     * 
     * choice 1: contains end  + part2 contains beginning;
     * 2 : in part1; (auto get recursive)
     * 3 : in part2;(auto get recursive)
     * 
     * so how to get choice 1? 
     * 
     * as we MUST contain the beginning of part2 and MUST contain the end of part1;
     * it's just the max of subsequnce from assigned start;
     * 
     * 
     * add s and e so we don't need to copy array;
     */
    private int maxSubArrayDC(int[] A,int s, int e){
        if(s==e){
            return A[s];
        }
        int max;
        int p = (s+e)/2;
        if(p==s){
            max = A[s]>A[e]?A[s]:A[e];
            max = max>(A[s]+A[e])?max:(A[s]+A[e]);
            return max;
        }
        //atleast have 3 element here;
        
        //choice one :  result connects  two array, containers subarray from [x,p-1] ,value p,  and contianer subarray[p+1,e];
        // add value p here so we don't need to contain at least 1 element from left part or right part, just pick max;
        int maxEndPart1 = 0; // at least 0 ( take no element )
        int sum = 0;
        for(int i=p-1;i>=s;--i){
            sum+=A[i];
            if(sum>maxEndPart1){
                maxEndPart1 = sum;
            }
        }
        
        int maxStartPart2 = 0;
        sum = 0;
        for(int i=p+1;i<=e;++i){
            sum+=A[i];
            if(sum>maxStartPart2){
                maxStartPart2 = sum;
            }
        }
        
        int maxChoice1 = maxEndPart1+A[p]+maxStartPart2; // we must contain element A[p]; for choice 1 , connect;
        
        int maxChoice2 = maxSubArrayDC(A,s,p-1);
        int maxChoice3 = maxSubArrayDC(A,p+1,e);
        
        max = maxChoice1>maxChoice2?maxChoice1:maxChoice2;
        max = max>maxChoice3?max:maxChoice3;
        return max;
    }
}