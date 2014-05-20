package highPassRate;

public class ClimbStairs {
    
    /****
     * 
     * dp, either take 1 or 2;
     * something fun is that for the last 1 step, you don't have a choice.
     * and we still save the res in a array for later case.
     */
    public int climbStairs(int n) {
        int[] rec = new int[n+1];
        return csAux(n,rec);
    }
    
    /**
     * name: climb stairs auxiliary
     */
    private int csAux(int n, int[] rec){
        if(n ==1 ){
            return 1;
        }
        if(n==0){
            return 1;
        }
        if(rec[n]>0){
            return rec[n];
        }
        int res = csAux(n-1,rec)+csAux(n-2,rec);
        rec[n] = res;
        return res;
    }
    
    
}
