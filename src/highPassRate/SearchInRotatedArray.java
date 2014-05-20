package highPassRate;


public class SearchInRotatedArray {
    
    /**
     * normal one goes liek    
     *    -> |
     *    -> v
     * 
     *  which means there is a source(was left  top)  to a target (was right bot)
     * 
     * first thing is find the directing, 
     * 
     * 
     * 
     * NO a matrix, just a rotated array, like           456789123
     * 
     * 
     */
    public boolean search(int[] A, int target) {
        if(A==null||A.length<1) return false;
        
        int len = A.length;
        int ind = 1;
        int last = A[0];
        // remove duplicate;
        for(int i=1;i<len;++i){
            if(A[i]==last){
                continue;// i move to next; for duplicate number found;
            }else{
                last = A[i];
                A[ind++] = last;
            }
        }
        
        len = ind;// the new array A without duplicate;
        
        if(len<10){
            for(int i=0;i<len;++i){
                if(A[i]==target) return true;
            }
            return false;
        }
        
        int direct =0;
        
        // find 3 direction;
        int cur = 0;
        for(int i=0;i<3;++i){
            // if(A[cur]==target) return true; // already deleted dup;
            // while(cur<len&&A[cur]==A[cur+1]){
            //     cur++;
            // }
            // if(cur==len) return false; // same number array we can't even find direction;
            
            if(A[cur]<A[cur+1]){
                direct++; // ->
            }else{
                direct--; // <-
            }
            cur++;
        }
        
        // from 3 direction. we can know the direction of the sorted array;
        
        if(direct<0){ // <-  don't like this order;
            int k = len-1;
            int tmp;
            for(int i=0;i<k;++i){
                tmp = A[i];
                A[i] = A[k];
                A[k] = tmp;
                k--;
            }
            //reversed if <- is the direction;
        }
        
        
        
        // already find the order;       and fixed length;
        
        // do the search, a binary search will be good; but the array is now rotated;
        
        //  [0,x] /  
        //         [x+1,len-1] /                    for all  0<i<x and x<j<len;   A[i] >A[j]
        
        // shall we find x first?
        
        int x; // find the x position that   A[x+1] is a peek;
        int piv = A[0];  
        int l = 1; ///must be > piv      left part;  see how farm left part can go;
        int r = len-1; // must be <piv   right part; 
        
            while(r-l>1){
                int t = (l+r)/2;
                if(A[t]>piv){ /// left half;
                    l =t;
                }else{
                    r = t;
                }
            }
        
        x = r;  // define x as the left most point of right part;
        
        // new array is from [x, x+len-1];  part >len use %len function;  it's a increase array, good to use binaray search;
     
        return binaraySearch(x,x+len-1,len,A,target);   
    }
    
    
    private boolean binaraySearch(int from,int to, int mode,int[] A,int target){
        int t; 
        int realIndex;
        while(from<=to){
            t = (from+to)/2;
            if(t>=mode){
                realIndex = t-mode;
            }else{
                realIndex = t;
            }
            
            if(A[realIndex]==target){
                return true;
            }
            if(A[realIndex]<target){
                from = t+1;
            }else{
                to = t-1;
            }
        }
        return false;
    }
    
    
}