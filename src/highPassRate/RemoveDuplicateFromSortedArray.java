package highPassRate;

public class RemoveDuplicateFromSortedArray {
    /***
     * 
     *
     * sorted? orz.
     * 
     * two pointer/cursor,  
     * one point to current scan,
     * another point to current saved.
     * 
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
        while(cur<len){
            v = A[cur];
            cur++;
            while(cur<len&&A[cur]==v){
                cur++;
            }
            //cur moved to next different element.
            A[sav] = v;
            sav++;
        }
        return sav;
    }
}