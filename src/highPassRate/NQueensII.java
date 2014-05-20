package highPassRate;

import java.util.HashSet;

public class NQueensII {
    /***
     *  OMG, this one shall came before the N-Queens one, seems easier.
     * 
     * // but the test case must be even larger.  for example, n=100;
     */
    public int totalNQueens(int n) {
        
        total = 0;
        
        
        // for the first row.
        totalNQueuesByRow(n,0,true);
        return total;
        
    }
    
    
    private HashSet<Integer> col = new HashSet<Integer>(); // col taken set.
    private HashSet<Integer> ld = new HashSet<Integer>();// left diagonal taken set;
    private HashSet<Integer> rd = new HashSet<Integer>();// right diagonal taken set;
    int total=0;
    /**
     * other conditions except the row info are in hashsets.
     * r: row. to check
     */
    private void totalNQueuesByRow(int n, int r, boolean mirrorExist){
        boolean me = mirrorExist;//mirror exist.
        for(int i=0;i<n;++i){
            // we need only half. save half time.
            if(r==0){
                // if n is even like 4, 6,  we need to check only [0,1] no need to check[2,3]
                // if n is odd, like 5, we need to check [0,1] with mirror. and [2], without mirror.
                if(n%2==0){
                    //even. 
                    if(i>=n/2){
                        continue;
                    }
                }else{
                    // odd.
                    if(i==n/2){
                        me = false; // only this case and last case, we dont' have mirror/
                    }else if(i>n/2){
                        continue;
                    }
                }
            }
            
            if(col.contains(i)||ld.contains(r-i)||rd.contains(r+i)){
                // not valid position.
                continue;
            }
            
            if(r==n-1){
                // find one. for last row.
                total++;
                if(me){
                    total++; // mirror.
                }
            }else{
                // keep searching for other row.
                col.add(i);
                ld.add(r-i);
                rd.add(r+i);
                totalNQueuesByRow(n,r+1,me);
                // return state after this.
                col.remove(i);
                ld.remove(r-i);
                rd.remove(r+i);
            }
        }
    }
    
}