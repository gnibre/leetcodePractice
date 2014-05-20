package highPassRate;

public class SearchMatrix {
    int h;
    int w;
    /**
     * 
     * for an sorted array, we will use binary search.
     * for this one, same id.
     * just don't copy values to an ordered array.
     * 
     * nothign but binarysearch.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0){
            return false;
        }
        h = matrix.length;
        w = matrix[0].length;
        if(w==0){
            return false;
        }
        // for  0<=x<h;  0<=y<w;   code(x,y) = x*w+y;   content in matrix[x][y] is the same as in array[x*w+y];
        
        int start =0;
        int end = w*(h-1)+(w-1)+1;// last one. last one +1 actually, cause we try (start+end)/2; can never reach end, [start,end)
        int cur = (start+end)/2;
        
        // while loop breaks, we have matrix[start] <= target < matrix[end] and cur = start, end = start+1;  
        //   or matrix[cur] is the target.
        while(cur>start){
            int geth = cur/w;
            int getw = cur-geth*w;
            if(matrix[geth][getw]<target){
                // too small.
                start = cur;
            }else if(matrix[geth][getw]>target){
                // too big.
                end = cur;
            }else{
                return true;
            }
            //next try
            cur = (start+end)/2;
        }
        
     // last try, incase matrix too small and cur = start as init.
        int geth = cur/w;
        int getw = cur-geth*w;
        return matrix[geth][getw]==target;
        
    }
    
    
    
}