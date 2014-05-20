package highPassRate;

import java.util.HashSet;


public class ValidSudoku {
    
    
    /**
     * 
     * 
     * !!!! thanks,  some one in the discuss form explained that :
     * this one, is not a soduku solver! 
     * if it looks good, it's goood.  no matter if there is a solution for this soduku or not 
     *
    //  * 
    //  * 
    //  * 
    //  * find the solution of the Sudoku,
    //  * if find, it's valid;
    //  * if not find , it's not valid.
     * 
     * // to find the solution. do depth first search.
     * 
     */
    public boolean isValidSudoku(char[][] board) {
        if(board==null){
            return false;
        }
        if(board.length!=9){
            return false;
        }
        if(board[0].length!=9){
            return false;
        }
        
        // x from  0 to 8,
        // y from 0 to 8,
        
        //  check each row,
        // check each column,
        // check each 3*3 box.
        
        // 1 no duplicate, no out of '1-9' just use set to check.
        
        HashSet<Character> set = new HashSet<Character>(); // row check.
        HashSet<Character> set2 = new HashSet<Character>(); // col check.
        char ch;
        for(int r=0;r<9;++r){
            set = new HashSet<Character>();
            set2 = new HashSet<Character>();
            for(int c = 0;c<9;++c){
               ch = board[r][c];
               if(ch>='0'&&ch<='9'){
                   //check
                   if(set.contains(ch)){
                       return false;
                   }else{
                       set.add(ch);
                   }
               }else if(ch!='.'){
                   // not valid char.
                   return false;
               }
               
               // check column,
               ch = board[c][r];
               if(ch=='.'){
                   continue;
               }else if(ch>='0'&&ch<='9'){
                   if(set2.contains(ch)){
                       return false;
                   }else{
                       set2.add(ch);
                   }
               }else{
                   return false;
               }
            }
            //this round clean, try next row.
        }
        
        
        // check each 3*3 as a block
        
        for(int r=0;r<9;r+=3){
            for(int c=0;c<9;c+=3){
                // 3*3 start from [r][c]
                set = new HashSet<Character>();
                for(int i=0;i<3;++i){
                    for(int j=0;j<3;++j){
                        ch = board[r+i][c+j];
                        if(ch=='.'){
                            continue;
                        }
                        //have checked, no invalid char.
                        if(set.contains(ch)){
                            return false;
                        }else{
                            set.add(ch);
                        }
                    }
                }
            }
        }
        return true;
    }
    
}