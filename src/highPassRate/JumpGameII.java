package highPassRate;


public class JumpGameII {
    
    
    /****
     * 
     * sure, this is what we shall do.
     * be aggrasive but don't miss any case at the same time.     * 
     * 
     * not the one closer to the end must have least jumps to reach it.
     * 
     * divide array to levels. ( level is same as the number of jumps ;  can be reached in the first jum, in the first level.)
     * 
     * so we can make sure the scan algorithm will not exceed o(len)
     * 
     */
    public int jump(int[] A) {
        if(A==null||A.length==0){
            return 0;
        }
        
        int len = A.length;
        int cur =0; // scan from cur.
        int curLevelEnd = 0;
        int curLevel = 0; //level 0 is auto reach, the first one.;  level 1 is taht can be reached in one step.
        int nextLevelEnd = 0;
        int jump;
        int reach;
        
        if(len==1){
            return 0;
        }
        
        while(cur<=curLevelEnd){
            jump = A[cur];
            reach = cur+jump;
            if(reach>curLevelEnd){
                //jumps to next level.
                if(reach>nextLevelEnd){
                    nextLevelEnd = reach;
                }
            }else{
                // this jump don't reach next level, kinda meaningless
            }
            
            if(reach>=len-1){
                return curLevel+1;
            }
            
            if(cur==curLevelEnd){
                //reach end. so , prepare to go next level.
                // cur = curLevelEnd+1; // next level..
                curLevelEnd = nextLevelEnd; 
                curLevel++;
            }
            cur++;
        }
        return 0;
    }
}