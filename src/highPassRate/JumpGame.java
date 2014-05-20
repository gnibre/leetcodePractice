package highPassRate;

public class JumpGame {
    
    /**
     * create a reachable array to record.
     * one scan from the beginning to the end.
     * 
     * , if find position can be reached, add mark to reachable array.
     *  if nomore reachable availabe, quit.
     * 
     * if reach end . gj.
     * 
     * /// there maybe aggresive strategy to move faster to try reach the end.; may save some time. but for the case below, 
     * we'd rather do it step by step;
     * 
     * if we skip to 5, or 10 when we find 5,10, will be a dead end.
     * [5,10,0,1,10000,0,0,0,0,0,00,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
     * 
     * 
     * //ok. short code, but got TLE from oj.
     * // do something aggrasive.
     * 
     * new rules found:  at end,  the reach table will not be like  111100011111,why?  check the first 1 after 0?  where this 1 from..
     * so we don't need a array list to save the mark.
     * 
     */ 
    public boolean canJump(int[] A) {
        if(A==null||A.length==0){
            return false;
        }
        int len = A.length;
        
        int max = 0;
        int cur =0; // scan from cur.
        int jump;
        int reach;
        while(cur<=max){
            // this one can be reached. so get what it can reach.
            jump = A[cur];
            reach = cur+jump;
            if(max<reach){
                max = reach;
            }
            if(max>=len-1){
                //reach end.
                return true;
            }
            cur++;
        }
        
        if(max>=len-1) return true;
        //after all, no reach.
        return false;
    }
}