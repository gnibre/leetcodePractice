package highPassRate;

import java.util.ArrayList;

public class SpiralMatrix {
    
    
    
    /***
     * 
     * for a 3*3: 
     * 123
     * 456   =>    456     =>        45       =>             45     up =>      5
     * 789         789               78
     * 
     * for a 4*4:
     * 1234
     * 5678          5678                567          567              67
     * 90AB  =>      90AB       =>       90A    =>    90A      =>      09         =>   09    => 0              
     * CDEF          CDEF                cDE
     * 
     *      *  each time  in the direction you go, do have count and do have a rule. but not that easy to discrible;
     *  3*3 :        3,2,2,1,1
     *  4*4:     4,3,3,2,2,1,1
     *  5*5:  5,4,4,3,3,2,2,1,1 
     * 
     * 
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        if(matrix==null||matrix.length==0){
            return res;
        }
        
        int r = matrix.length;
        
        //  current board is (0,0) ~ (n-1,n-1)
        //1   traversal top from left to right    [1,2,3], 
        //    count is 3:  (0,0) ~ (0,n-1);  rest board  (1,0)~(n-1,n-1) ,r:n-1, col:n;;; rule:  top row cut.
        //2  traversal from (1,n-1)~(n-1,n-1); rest : (1,0)~(n-1,n-2);  r:n-1, col:n-1 ;  rule : right most column cut;
        //3  from (n-1,n-2)~ (n-1,0) ;  rule: bot row cut;
        //4 left most column cut;
        
        
        //it's a state machine. stops when there's exactly nothing left.
        
        
        //!!!!!!!!!!!!!! as from the test case,we have to find col, cause no guarantee col == h;
        int col = matrix[0].length;
        //start from state 1,
        addToResByState(matrix,1,0,0,col-1,r-1,res);
        return res;
    }
    
    
    /**
     * state 1  ->
     * state 2  down
     * state 3  <=
     * staet 4   up.
     * 
     * 
     * //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! from the  test case , we got to know that,
     * the matrix don't guarantee a  w = h; or row = col;
     * matrix maybe a 2*3 or a n*m
     * 
     * so the quit strategy, is not always when l=t &&r==b;
     * at the end, their will be no more element left in the range. 
     * so that means l>r or t>b
     * 
     * 
     * 
     */ 
    private void addToResByState(int[][] matrix,int state,int l,int t,int r, int b,ArrayList<Integer> res){
        if(l>r||t>b){
            return;
        }
        
        //->
        if(state==1){
            // if(l==r&&t==b){
            //     return; //end
            // }
            for(int c = l;c<=r;++c){
                res.add(matrix[t][c]); // add all the content of top row
            }
            // after add all. fix new bound
            addToResByState(matrix,2,l,t+1,r,b,res);
        }else if(state==2){
            // if(l==r&&t==b){
            //     return; //end, no content.
            // }
            for(int row=t;row<=b;++row){
                res.add(matrix[row][r]); // add all the right most column; 
            }
            //after this. 
            addToResByState(matrix,3,l,t,r-1,b,res);
        }else if(state==3){
            // <=
            // if(l==r&&t==b){
            //     return; //end;
            // }
            for(int c = r;c>=l;--c){
                res.add(matrix[b][c]); // add all the content of bot row
            }
            // after add all. fix new bound
            addToResByState(matrix,4,l,t,r,b-1,res);
        }else{
            //state = 4 , up;
            // if(l==r&&t==b){
            //     return; //end, no content.
            // }
            for(int row=b;row>=t;--row){
                res.add(matrix[row][l]); // add all the left most column; 
            }
            //after this. 
            addToResByState(matrix,1,l+1,t,r,b,res);
        }
    }
    
    
    
}