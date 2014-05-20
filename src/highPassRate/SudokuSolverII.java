package highPassRate;

import java.util.HashSet;

/****
 * 
 * fixed bug of old version. 
 * 
 * @author Yubing
 *
 */
public class SudokuSolverII {
    
	
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
	    HashSet<Integer>[] rowSets;
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
	        
	        
	        // HashSet<Integer> rSet = new HashSet<Integer>(); //set for row.
	        // when this function reachs end, result came.
	        solveSudokuByGuess(0,0);
	        
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
	        rowSets = new HashSet[9];
	        for(int i=0;i<9;++i){
	        	rowSets[i] = new HashSet<Integer>();
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
	                    
	                    rowSets[i].add(bValue[i][j]);
	                    colSets[j].add(bValue[i][j]);
	                    int cubIndex = (i/3)*3+(j/3);
	                    cubeSet[cubIndex].add(bValue[i][j]);
	                }
	            }
	        }
	    }
	    
	    /**
	     * row conflict are handled in the function. so don't forget to maintain rowSet.
	     * 
	     * 
	     */ 
	    private boolean solveSudokuByGuess(int row,int col){
	        int val = bValue[row][col];
	        if(val!=0){
	            if(row==8 && col==8){
	                //found.  res is saved in the res array. so just return.
	                return true;
	            }else{
	                // go next. ; before go next, handle set.
	                if(col==8){
	                    return solveSudokuByGuess(row+1,0);
	                }else{
	                    return solveSudokuByGuess(row,col+1);
	                }
	            }
	        }
	        
	        //val!=0; we need to find a value for this position.
	        
	        int guess;
	        for(guess=1;guess<10;++guess){
	            // check row.
	            if(rowSets[row].contains(guess)){
	                continue;
	            }
	            // check column,
	            if(colSets[col].contains(guess)){
	                continue;
	            }
	            
	            // check cube.
	            int cubIndex = (row/3)*3+(col/3);
	            if(cubeSet[cubIndex].contains(guess)){
	                continue;
	            }
	            
	            // so, values passed here, can have a try.
	            
	            // ok, let's try.
	            bValue[row][col] = guess;
	            rowSets[row].add(guess);
	            colSets[col].add(guess);
	            cubeSet[cubIndex].add(guess);
	            
	            boolean res = false;
	            
	            if(row==8 && col==8){
	                //found.  res is saved in the res array. so just return.
	                return true;
	            }
	            
	            
	            //if not the end, go next slot.
	            if(col==8){
	                res = solveSudokuByGuess(row+1,0);
	            }else{
	                res = solveSudokuByGuess(row,col+1);
	            }   
	            
	            if(!res){
	                // not found, keep searching.
	                // after  this , we  return state and keep trying next
	                bValue[row][col] = 0;
	                rowSets[row].remove(guess);
	                colSets[col].remove(guess);
	                cubeSet[cubIndex].remove(guess);
	            }else{
	                // already found, don't change values
	                return true; 
	            }
	        }
	        // not return true after all guess. no result.
	        return false;
	    }
	    
	}
