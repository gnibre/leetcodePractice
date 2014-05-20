package highPassRate;


public class SearchForARange {
    
    /**
     * sorted,  binaray search;
     * but with duplicate numbers;
     * 
     * 
     * 1 , before we find the target,  s, e and mid is easy to handle;  if v[m]<target, s= mid;  ifv[m]>target, e=mid;
     * 
     * 2 , if find the target; at m; we have to find the beginning of target and end of target;
     * 
     * 
     * glad that passed very fast, problems like this, that need serious boundary check 
     * 
     */
    public int[] searchRange(int[] A, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if(A==null||A.length<1){
            return res;
        }
        
        //binary;
        int s = 0;
        int e = A.length-1;
        
        int find=-1;
        int m;
        //first round; to be or not to be;
        while(s<=e){
            m = (s+e)/2;
            if(A[m]<target){
                s = m+1;
            }else if(A[m]>target){
                e = m-1;
            }else{
                //init find;
                find = m;
                break;
            }
        }
        if(find==-1){
            //can't find;
            return res;
        }
        
        // already got is : s <target;    m==target,  e>target;
        
        int f = find;
        while(s<f){
            m = (s+f)/2; // m : [s,f)
            if(A[m]<target){
                s = m+1;
            }else if(A[m]==target){
                f = m;
            }
            // if A[m]>target?  then this array must be not sorted...
        }
        res[0] = s;
        
        //to find end position of target;
        f = find;
        while(f<e){
            m = (f+e)/2;
            if(m==f){
                m = e;
            }
            
            if(A[m]>target){
                e = m-1;
            }else{
                // must be ==; or this array is not sorted;
                f = m;
            }
            
        }
        res[1] = e;
        
        return res;
        
    }
    
    
    
    
}