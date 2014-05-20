package highPassRate;

import java.util.Stack;


public class TrappingRainWater {
    /***
     *  ty, the picture helps;
     *  can be done with one scan;; use stack of size n at most;
     * 
     * as the rain container is even; we can know the left boundary very early, though don't konw the end time;
     * 
     * 
     * 
     * 1, find first number;  we got a recorded height Hf;
     * 2,  comming number :
     *     <Hf,  we got container candidate;  but we shall record the height, to calc amount rain water;
     *           add new height to the stack; the water amount in this row is not sure yet;
     *         content recorded in the stack is like : \          
     *                                                  \ 
     * * *                                                        \
     *     =Hf,  can be ignored? seems not, cause we need to use this to  calc rain water amount;
     *          can be ignored if we calc it line by line ( by row not by column, though by column is more intuitive)
     *          
     *     >Hf, good time came; time to clear the stack and get rain water calced;
     *     
     *   
     *  COOL, this one passed very fast, with out much fix and before debug;
     *     
     */
    public int trap(int[] A) {
        if(A==null||A.length<1) return 0;
        
        // boundary height and index;
        Stack<Integer> leftBHeight = new  Stack<Integer>();
        Stack<Integer> leftBIndex = new  Stack<Integer>(); 
        
        int containerBot = -1;
        int sum = 0;
        int h;
        for(int i=0;i<A.length;++i){
            h=A[i];
            if(leftBHeight.isEmpty()||leftBHeight.peek()>h){
                leftBHeight.push(h);
                leftBIndex.push(i);
                continue;
            }
            
            // check out;
                // check out lines that below this one;
            // boundary again, same height; 
            while(!leftBHeight.isEmpty()&&leftBHeight.peek()<=h){
                int popH = leftBHeight.pop(); //
                int popP = leftBIndex.pop();
                if(containerBot ==-1 ){
                    // sorry, you are bottom of the container; important but no value;
                    containerBot = popH;
                }else{
                    sum+= (popH-containerBot)*(i-popP-1);
                    containerBot = popH; //cause below this row is calulated; take popH as new bot;
                }
            }
            
            if(!leftBHeight.isEmpty()){
                int leftBP = leftBIndex.peek();
                sum+=(h-containerBot)*(i-leftBP-1);
            }
            
            containerBot = -1; //left size is done;
            //now your turn, you are the lowest now;
            leftBHeight.push(h);
            leftBIndex.push(i);
        }
        
        // many in the stack; but we can't hold water anymore.  you are not a stack, you are steps.of.stairs..... 
        return sum;
    }
}