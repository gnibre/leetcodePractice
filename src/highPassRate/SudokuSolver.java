package highPassRate;

import java.util.HashSet;

/****
 * 
 * Error @ line 160 and NOT FIXED.          check SudokuSolverII for right answer.
 *   failed to fix the problems that we have in this strategy. seems we'd better add an row set. 
 * 
 * 
 * @author Yubing
 *
 */
public class SudokuSolver {
    
	
	public void go(){
		
		char[][] board = {
				{'.','.','9','7','4','8','.','.','.'},
				{'7','.','.','.','.','.','.','.','.'},
				{'.','2','.','1','.','9','.','.','.'},
				{'.','.','7','.','.','.','2','4','.'},
				{'.','6','4','.','1','.','5','9','.'},
				{'.','9','8','.','.','.','3','.','.'},
				{'.','.','.','8','.','3','.','2','.'},
				{'.','.','.','.','.','.','.','.','6'},
				{'.','.','.','2','7','5','9','.','.'}
		};
		
		
		solveSudoku(board);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;++j){
				System.out.print(" "+board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	
	private void printB(int[][] board){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;++j){
				System.out.print(" "+board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
    HashSet<Integer>[] colSets;
    HashSet<Integer>[] cubeSet;
    int[][] bValue;
    /**
     * 
     * solve a sudoku,  how? by guess,
     * one by one, check if it works. til the end.
     * it's a depth first search.
     * when we get one, just return. cause one solution is enough.
     * 
     */
    public void solveSudoku(char[][] board) {
        if(board==null){
            return;
        }
        if(board.length!=9){
            return;
        }
        if(board[0].length!=9){
            return;
        }
        init(board);
        
        
        HashSet<Integer> rSet = new HashSet<Integer>(); //set for row.
        // when this function reachs end, result came.
        solveSudokuByGuess(0,0,rSet);
        
        // after this. values get
        
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                board[i][j] = (char) ('0'+bValue[i][j]);
            }
        }
    }
    
    private void init(char[][] board){
        colSets = new HashSet[9];
        for(int i=0;i<9;++i){
        	colSets[i] = new HashSet<Integer>();
        }
        cubeSet = new HashSet[9];
        for(int i=0;i<9;++i){
        	cubeSet[i] = new HashSet<Integer>();
        }
        bValue = new int[9][9];
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                if(board[i][j]=='.'){
                    bValue[i][j] = 0;
                }else{
                    bValue[i][j] = board[i][j]-'0';
                    
                    colSets[j].add(bValue[i][j]);
                    int cubIndex = (i/3)*3+(j/3);
                    cubeSet[cubIndex].add(bValue[i][j]);
                }
            }
        }
    }
    
    int ided = 0;
    /**
     * row conflict are handled in the function. so don't forget to maintain rowSet.
     * 
     * 
     */ 
    private boolean solveSudokuByGuess(int row,int col,HashSet<Integer> rSet){
    	System.out.println(" go : "+row+" - "+col+"     debug:"+ided++);
    	
    	if(row==0&&col==8){
    		printB(bValue);
    	}
    	
        int val = bValue[row][col];
        if(ided==1006||ided==1007){
        	System.out.println("value: "+val);
        }
        if(val!=0){
        	if(rSet.contains(val)){
                // this value, conflict with guesses before.
        		
        		if(ided>1004){
        			System.out.println(" happens: "+row+"-"+col+"  return false"+val);
        			printB(bValue);
        			System.out.println(" rset contains: ");
        			for(int in:rSet){
        				System.out.print(" "+in);
        			}
        			System.out.println("");
        		}
        		
        		
                return false;
            }
        	
            if(row==8 && col==8){
                //found.  res is saved in the res array. so just return.
                return true;
            }else{
                // go next. ; before go next, handle set.
                if(col==8){
                	HashSet<Integer> newSet = new HashSet<Integer>();
                    return solveSudokuByGuess(row+1,0,newSet);
                }else{
                	//!!!!!!!!!!!!!!!!!!!!!!!!!1 when you added here, and later deadend happens and we wanna back
                	/// the value added to set will not be removed. so it affects later judge.
                	// we'd rather added it from beginning.     and do it separately /
                    rSet.add(val);
                    return solveSudokuByGuess(row,col+1,rSet);
                }
            }
        }
        
        if(ided==1006||ided==1007){
        	System.out.println("goes here?:   row,col: "+row+" - "+col+"   with val:"+val);
        }
        
        //val!=0; we need to find a value for this position.
        
        int guess;
        for(guess=1;guess<10;++guess){
        	if(ided>1004){        		
        		System.out.println(" ======================================================guess :"+guess);
        	}
            // check row.
            if(rSet.contains(guess)){
            	if(col==0&&row==0){
            		System.out.println(" guess in it r: "+guess);
            	}
                continue;
            }
            // check column,
            if(colSets[col].contains(guess)){
            	if(col==0&&row==0){
            		System.out.println(" guess in it  c: "+guess);
            	}
                continue;
            }
            
            // check cube.
            int cubIndex = (row/3)*3+(col/3);
            if(cubeSet[cubIndex].contains(guess)){
            	
            	if(col==0&&row==0){
            		System.out.println(" guess in it cub: "+guess);
            	}
            	
                continue;
            }
            
            
            
            // ok, let's try.
            bValue[row][col] = guess;
            rSet.add(guess);
            colSets[col].add(guess);
            cubeSet[cubIndex].add(guess);
            
            boolean res = false;
         // so, values passed here, can have a try.
            if(row==0&&col==0){
            	System.out.println("===============================try  row,col: "+row+" - "+col+"   with guess:"+guess);
            	printB(bValue);
            }

            if(row==8 && col==8){
                //found.  res is saved in the res array. so just return.
                return true;
            }
            
            
            
            // go next slot.
            if(col==8){
                HashSet<Integer> newRowSet = new HashSet<Integer>();
                res = solveSudokuByGuess(row+1,0,newRowSet);
            }else{
                res = solveSudokuByGuess(row,col+1,rSet);
            }   
            
            if(!res){
                // not found, keep searching.
                // after  this , we  return state and keep trying next
            	
//            	System.out.println("------  give up     row,col: "+row+" - "+col+"   with guess: "+bValue[row][col]+"---  guess:"+guess);
            	
            	if(row==0&&col==8){
            		printB(bValue);
            	}
            	
                bValue[row][col] = 0;
                rSet.remove(guess);
                colSets[col].remove(guess);
                cubeSet[cubIndex].remove(guess);
            }else{
                // already found, don't change values
            	System.out.println(" final : true");
                return true; 
            }
        }
        // not return true after all guess. no result.
        return false;
    }
    
}
