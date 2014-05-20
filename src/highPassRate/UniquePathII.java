package highPassRate;

public class UniquePathII {
    
    /**
     * dp. 
     * of cause, tyr to do this with only o(n) space cost and no recursive calls;
     * 
     * res is f(0,0)
     * f(x,y) = f(x+1,y) + f(x,y+1); 
     * 
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if(obstacleGrid==null) return 0;
        
        int h = obstacleGrid.length;
        int w = obstacleGrid[0].length;
        int[] res = new int[w+1]; // never mind if h>w; we just, make it easy, build the case from bot up;
        
        //as f(x,y) is related to f(x+1,y) and f(x,y+1);
        //init build bot lane;
        res[w] = 1;  // for the init grid; we can have 1 as a choice;
        for(int i=w-1;i>-1;--i){
            if(obstacleGrid[h-1][i]==1){
                res[i] = 0; //no way , count =0;
            }else{
                res[i] = res[i+1];
            }
        }
        res[w] = 0; // for later level, each row; case from res[w] is from right side out of matrix; add 0 means no way;
        
        
        for(int j=h-2;j>-1;--j){
            //from last line to top line;
            for(int i=w-1;i>-1;--i){
                //each line, build from right to left;
                //res[i] = res[i+1] (just builded, this level value ,means grom grid to right of it )
                //           + res[i] , from last level, builded last level, means from grid below;
                
                if(obstacleGrid[j][i]==0){
                    res[i] +=res[i+1];
                }else{
                    res[i] = 0;
                }
            }
        }
        
        //builded all;
        return res[0];
    }
}
