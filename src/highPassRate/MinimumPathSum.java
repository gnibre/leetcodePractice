package highPassRate;


public class MinimumPathSum {
    /**
     * 
     * get more familiar with dp;  boundary condition is the most important thing in this one;
     * 
     * dp? 
     *  cause you have only two choice, when you are at position (x,y) ; next step is (x+1,y) or (x,y+1); 
     * 
     * save result in a big array the same size of grid; 
     * but we can also try to save some space, build from bottom up; 
     * cause every slot of grid will be calculated; in this problem,
     * build from bottom and calc all the values can save space/ memory but not cost too much extra time;
     */ 
    public int minPathSum(int[][] grid) {
        
        //build from bot; so we don't need recursive function call;
        // we use only o(n) space . so last level value will be overwrite by this level; we shall take care;
        
        // f(x,y) = min( grid[x][y]+f(x+1,y) ,  grid[x][y]+ f(x,y+1)) ;
        
        int h = grid.length;
        int w = grid[0].length;
                
        
        // dp res;
        int[] res;
        
        if(h<w){
            res = new int[h+1];
            res[h] = 0;
            //init column : col w-1;
            for(int i=h-1;i>-1;--i){
                res[i] = res[i+1]+grid[i][w-1];
            }
            res[h] = Integer.MAX_VALUE; // boundary, so will not be choosed by min function;
            // build from right , to left;
            for(int i=w-2;i>-1;--i){
                //each loop build a column;
                for(int j=h-1;j>-1;--j){
                    // res[j] is min res[j+1](this level newly calced) or  res[j] lastLevel;
                    // choose right column already exist one. or chose newly caled one below;
                    res[j] = Math.min(res[j+1],res[j])+grid[j][i];
                }
            }
            //after all, return;
            return res[0]; // it's res[0][0] actually for dp;
        }else{
            res = new int[w+1];
            res[w] = 0;
            // init bot line/row;  
            for(int i=w-1;i>-1;--i){
                res[i] = grid[h-1][i]+res[i+1];
            }
            res[w] = Integer.MAX_VALUE;
            
            // build from bot to top; 
            for(int i=h-2;i>-1;--i){
                //each loop build a line;
                for(int j=w-1;j>-1;--j){
                    // choose last line or chose new calced right one;
                    res[j] = Math.min(res[j+1],res[j])+grid[i][j];
                }
            }
            return res[0];
        }
    }
}