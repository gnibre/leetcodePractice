package highPassRate;

public class RotateImage {
    
    
	public void go(){
		
		int[][] matrix = {
				{1,2},
				{3,4}
		};
		
		showMatrix(matrix);
		rotate(matrix);
		showMatrix(matrix);
		
		
		int[][] answer ={
				{3,1},
				{4,2}
		};
		showMatrix(answer);
		
	}
	
	private void showMatrix(int[][] m){
		System.out.println(" m is : ");
		
		int h = m.length;
		for(int i=0;i<h;++i){
			for(int j=0;j<h;++j){
				System.out.print(" "+m[i][j]);
			}
			System.out.println();
		}
		
	}
	
    /**
     *  we need a formula, to find 4 pixel as a group, and rotate inplace.
     * say the size is n*n;         
     * 
     * 
     * or we can do this from the outsize of the matrix, to the inner most. 
     * we define this by the left-top and right-bot possition.
     * we define each value outmost of the matrix as a certain level.
     *     (0,0)  a
     *      -----------------
     *      |               |
     *      |               |b
     *      |               |
     *    d |               |
     *      -----------------
     *                  c    (n-1,n-1)
     * 
     */
    public void rotate(int[][] matrix) {
    	if(matrix==null||matrix.length<1){
            return ;
        }
        int n = matrix.length;
        int left_top = 0;
        int right_bot = n-1;
        while(left_top<right_bot){
            rotateCertainLevel(matrix,left_top,right_bot);
            left_top++;
            right_bot--;
        }
        
        
        
    }
    
    
    private void rotateCertainLevel(int[][] matrix, int left_top,int right_bot){
        if(left_top>=right_bot){
            // hte center of the matrix is a single pixel(but not 4 as a group); n is a odd number.
            // don't need to rotate.
            return;
        }
        
        // move everyone top row , to the right most column,  as clockwise.
        // move right => bot,  bot=> left , left=> top;
        // as we when to make this happen inplace. we do this by moving 4 numbers as a group.
        
        int swap;
        // ind<right_bot, cause we dont' want to include the right-top in the loop, it will be included in right most array.
        for(int ind=left_top;ind<right_bot;ind++){
            // group of four,
            // a = (ind,left_top) ; b=(right_bot,ind) ; c=(left_top+right_bot-ind,right_bot ); d=(left_top, left_top+right_bot-ind  )
            
//            swap = matrix[ind][left_top]; //swap = a; save it.
//            matrix[ind][left_top] = matrix[left_top][left_top+right_bot-ind]; // a = d; 
//            matrix[left_top][left_top+right_bot-ind] = matrix[left_top+right_bot-ind][right_bot] ; // d=c;
//            matrix[left_top+right_bot-ind][right_bot] = matrix[right_bot][ind]; // c= b;
//            matrix[right_bot][ind] = swap; // b = saved a.
            
            
            
            

            
            
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // i was wrong about the x and y.
            //   x, y in a matrix is like: 
            
            //      (0,0) , (0,1) , (0,2), (0,3)
            //      (1,0)  , (1,1),  (1,2), 
            //    
            //     
            //      (n-1,0) ................(n-1,n-1)
            
            
            //so , a = (left_top,ind) ; b =(ind,right_bot);c=(right_bot, left_top+right_bot-ind);d= (left_top+right_bot-ind,left_top)
            
            swap = matrix[left_top][ind]; // swap = a; save it.
            matrix[left_top][ind] = matrix[left_top+right_bot-ind][left_top]; //a= d;
            matrix[left_top+right_bot-ind][left_top] = matrix[right_bot][left_top+right_bot-ind]; //d=c;
            matrix[right_bot][left_top+right_bot-ind] =  matrix[ind][right_bot];// c= b;
            matrix[ind][right_bot] = swap; //b = saved.
            
            
            
        }
    }
    
}