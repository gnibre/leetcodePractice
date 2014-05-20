package highPassRate;

public class RemoveDuplicateFromSortedArrayII {
	    final int MAX_ALLOWED = 2;
	    /***
	     * 
	     * still sorted.
	     * max allowed is two. kinda fun,
	     * but still shall be easy and can be done in o(n)
	     * 
	     */
	    public int removeDuplicates(int[] A) {
	         if(A==null||A.length==0){
	            return 0;
	        }
	        
	        int cur = 0;
	        int sav = 0;
	        int len = A.length;
	        int v;
	        int savedBegin;
	        while(cur<len){
	            v = A[cur];
	            savedBegin = cur;
	            cur++;
	            while(cur<len&&A[cur]==v){
	                cur++;
	            }
	            //cur moved to next different element.
	            int count = cur-savedBegin;
	            count = Math.min(count,MAX_ALLOWED);
	            // max count allowed to be saved in A ;  of cause, based on max exist. ; count will not exceeed max_allowed.
	            for(int c=0;c<count;++c){
	                A[sav++] = v;
	            }
	        }
	        return sav;
	    }
	}