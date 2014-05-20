package highPassRate;

public class UniquePath {
    
    /**
     * 
     * if start-> (x,y) have fun(x,y) UniquePaths
     * and  (x,y)-> end have fun( m-x, n-y) UniquePaths
     * then start -> end have fun(x,y)*fun( m-x, n-y) UniquePaths that walk through position (x,y)
     * 
     * as start can only move to (0,1) or (1,0); total = UniquePaths from (0,1) to end  plus UniquePaths from (1,0) to end
     * and also, we can save basic result, it's classic dp.
     * 
     */
    public int uniquePaths(int m, int n) {
        int[][] rec = new int[m][n]; //ease to use.
        return upAux(m-1,n-1,rec); //start from (1,1)
    }
    
    /**
     * name: uniquePathAuxiliary
     *  x,y : distance from the end.
     */
    private int upAux(int x, int y, int[][] rec){
        if(x==0||y==0){
            //same row/column
            return 1;
        }
        if(rec[x][y]>0){
            return rec[x][y]; //have saved recrod
        }
        int res = upAux(x-1,y,rec)+upAux(x,y-1,rec);
        rec[x][y] = res;
        return res;
    }
}