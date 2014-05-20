package highPassRate;


public class SearchInRotatedArrayNoDup {

	
	
	public void go(){
		
		int[] A ={
//				4,5,6,7,0,1,2
				3,4,5,6,1,2
		};
		
		int res = search(A,2);
		System.out.println("  res: "+res);
	}
			
			
			
			
    /**
     * much easier than get the median version? 
     * 
     * no dup, easy direction.
     * 
     * /// update, read the code i wrote... for binary search rotated sorted array; with duplicate numbers;
     * actually much easier.. cause in worst case, array with duplicate, takes even o(n) to find the order;
     * so i find the break point with o(n) algorithm, and use a standard binary search on the array; using mode to direct rotated array to a 
     * sorted array with consecutive address;   
     * 
     */
    public int search(int[] A, int target) {
        if(A==null||A.length<1) return -1;
        
        if(A.length<5){
            for(int i=0;i<A.length;++i){
                if(A[i]==target) return i;
            }
            return -1;
        }
        
        // atleast 4 numbers, do have a direction; 1 for incremental , -1 for decremental
        int direction = 0; //
        
        for(int i=0;i<3;++i){
            if(A[i+1]>A[i]){
                direction++;
            }else{
                direction--;
            }
        }
        direction = direction>0?1:-1;
        return searchAux(A,0,A.length-1,target,direction);
    }
    
    private int searchAux(int[] A,int l,int r,int target,int direct){
        System.out.println(" searchAux,  l:"+l+" r:"+r+" d:"+direct);
        int mid;
        
        //which one is selected as pivot?  
        int vl = A[l];
        int m = (l+r)/2;
        if(m==l||l>=r){
            //just 2 element left;
            if(A[l]==target) return l;
            if(A[r]==target) return r;
            return -1;
        }
        
        if(vl==target) return l;
        if(A[m]==target) return m;
        
        
        
        // first of all, check which part of the array don't contain the rotated part;
        // if must be either of them or even both;
        
        if(direct==1){
            if(A[m]>vl){
                // vl < A[m];  as rotated part are all < vl;  [l,m] don't contain that break point;
                if(vl<target&&target<A[m]){
                    return standardBinarySearch(A,l+1,m-1,target,direct);
                }else{
                    // the rotated part is kinda chaos, we still have chance to find it there;
                    l = m+1;
                }
            }else{
                // A[m]<vl ; A[m] is at behind the roated part;    
                
                if(target<vl&&A[m]<target){
                    return standardBinarySearch(A,m+1,r,target,direct);
                }else{
                    // try chaos part;
                    r = m-1;
                    l = l+1;
                }
            }
        }else{
            // direct is -1, >>>>
            
            if(A[m]<vl){
                if(vl>target&&target>A[m]){
                    return standardBinarySearch(A,l+1,m-1,target,direct);
                }else{
                    l = m+1;
                }
            }else{
                // A[m]>vl; left part is chaos;
                if(A[m]>target&&target>vl){
                    return standardBinarySearch(A,m+1,r,target,direct);
                }else{
                    r = m-1;
                    l=l+1;
                }
            }
        }
        return searchAux(A,l,r,target,direct);
    }
    
    
    
    /**
     * no reversed content in the sub array; 
     */
    private int standardBinarySearch(int[] A,int l,int r,int target,int direct){
    	System.out.println(" standardBinarySearch,  l:"+l+" r:"+r+" d:"+direct);
        int valueInTheArray = (target-A[l])*(target-A[r]);
        if(valueInTheArray>0) return -1; //not in the standard array;
        
        int m;
        while(l<=r){
        	System.out.println(" round,  l:"+l+" r:"+r+" d:"+direct);
        	
        	if(r-l<2){
        		if(A[l]==target) return l;
        		if(A[r]==target) return r;
        		return -1;
        	}
        	
            m = (l+r)/2;
            ///!!!!!!!!!!!!!!!!! use m and right better cause we may have l==m;
            valueInTheArray = (A[m]-target)*(A[r]-target);
            if(valueInTheArray<0){
                //in; 
                l = m+1;
                r = r-1;
            }else if(valueInTheArray>0){
                //no in this part;
                r = m-1;
            }else{
                // valueInTheArray ==0;
                if(A[m]==target) return m;
                return l;
            }
        }
        return -1;
    }
    
}