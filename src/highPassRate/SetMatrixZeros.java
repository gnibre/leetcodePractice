package highPassRate;


public class SetMatrixZeros {
    /**
     * control the generated 0s not to recursively do this again, we will be fine;
     * 
     * if the matrix is of chars, we can mark that row/column with something like '#' when we find a '0'
     * and finally change all '#'s to '0's;
     * but for array, try do something different;
     * 
     * 1, first line and first column will be marked as all 0 with special record ;
     * 2, one row: row h is marked zero if and only if [h][0] ==0;
     * 3 , one column w is marked zero if and only if [0][w]==0;
     * 
     */ 
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
        
        int h = matrix.length;
        int w = matrix[0].length;
        for(int i=0;i<h;++i){
            if(matrix[i][0]==0){
                firstColumnZero=true;
                break;
            }
        }
        
        for(int j=0;j<w;++j){
            if(matrix[0][j]==0){
                firstRowZero = true;
                break;
            }
        }
        
        // now we can overwrite any value of first row and first column as we want;
        
        for(int i=1;i<h;++i){
            for(int j=1;j<w;++j){
                if(matrix[i][j]==0){
                    matrix[i][0] = 0; // this row marked;
                    matrix[0][j] = 0; // this column marked;
                }
            }
        }
        
        // final result 
        for(int i=1;i<h;++i){
            if(matrix[i][0]==0){ // row;
                for(int j=1;j<w;++j) matrix[i][j] = 0;
            }
        }
        
        for(int j=1;j<w;++j){
            if(matrix[0][j]==0){ // col;
                for(int i=1;i<h;++i) matrix[i][j] = 0;
            }
        }
        
        // don't forget the first row and first column;
        if(firstRowZero){
            for(int j=0;j<w;++j){
                matrix[0][j] = 0;
            }
        }
        
        if(firstColumnZero){
            for(int i=0;i<h;++i){
                matrix[i][0] = 0;
            }
        }
    }
}