package highPassRate;

import java.util.Arrays;

public class RotatedMatrixSearch {
    
    /**
     * 
     * AS I ACTUALLY MISUNDERSTAND THE PROBLEM,  this piece of code works for no problem that on oj...
     * 
     * 
     * 
     * 
     * normal one goes liek    
     *    -> |
     *    -> v
     * 
     *  which means there is a source(was left  top)  to a target (was right bot)
     * 
     * first thing is find the directing, 
     * 
     * 
     */
    public boolean search(int[][] A, int target) {
        if(A==null||A.length<1) return false;
        int h = A.length;
        int w = A[0].length;
        
        if(h<3||w<3){
            for(int i=0;i<h;++i){
                for(int j=0;j<w;++j){
                    if(A[i][j]==target){
                        return true;
                    }
                }
            }
            return false;
        }
        
        // normal matrix, of size >2;     at least we have corner, thus direction for the order;
        
        int a = A[0][0];
        int b = A[0][w-1];
        int c = A[h-1][0];
        int d = A[h-1][w-1];
        int min = Math.min(a,b);
        min = Math.min(min,Math.min(c,d));
        int max = Math.max(a,b);
        max = Math.max(max,Math.max(c,d));
        
        if(min>target||max<target){
            return false;
        }
        
        boolean revH = false; 
        boolean revW = false;
        int hgo = 0, wgo=0;
        int startH,startW;
        if(a==min){
            startH = 0;
            startW = 0;
            hgo=1;wgo=1;
            // revH = false; revW = false;
        }else if(b==min){
            startH = 0;
            startW = w-1;
            wgo = -1;hgo = 1;
            // revW = true;  //<- and \v
        }else if(c==min){
            startH = h-1;
            startW = 0;
            hgo = -1; wgo = 1;
            // revH = true   //-> and ^|
        }else{
            startH = h-1;
            startW = w-1;
            hgo = -1; wgo =-1;
            // revH = true;
            // revW = true; // <- and ^|
        }
        
        int curh,curw;
        for(int steph=0;steph<h;++steph){
            curh = startH+steph*hgo;
            for(int stepw =0;stepw<w;++stepw){
                curw = startW+stepw*wgo;
                //search this level following the direction; from small searching for bigger
                
                if(A[curh][curw] ==target){
                    return true;
                }
                if(A[curh][curw]<target){
                    continue;
                }else{
                    break;
                }
            }
            if(A[curh][startW]>target){
                return false;
            }
        }
        
        return false;
        
        
    }
}