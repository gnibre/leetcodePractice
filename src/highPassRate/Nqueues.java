package highPassRate;

import java.util.ArrayList;
import java.util.HashSet;

public class Nqueues {
	
	
	public void go(){
		
		solveNQueens(4);
		
	}
	
	
	
    /***
     * so classic,
     *  what to say? don't know..
     *  
     *  1, scan by row, so no queue will attack each other since we put at most 1 queue in a row.
     *  2, save column that already have a queue, so when place a queue in this row, make sure no attack this column 0~n-1
     *  3, queue can also attack from deferent column : left top or right top , save it's (x-y) and it's ( y-x); roughly(1-n~ n-1);
     * 
     *  4, as we need to get all, we have to back to parent node as a depth first tree tranverse.
     * 
     */
    public ArrayList<String[]> solveNQueens(int n) {
    	
    	//init for output,...   leetcode output for java is sometimes time consuming, and don't be a dumbass..
    	// actually, this helps to pass the oj, and dont' get a TLE..
    	initOutputString(n);
    	
    	
        ArrayList<String[]> res = new ArrayList<String[]>();
        ArrayList<Integer> prev = new ArrayList<Integer>();
        HashSet<Integer> co = new HashSet<Integer>(); // check attack from column(top) 
        HashSet<Integer> lr = new HashSet<Integer>(); // check attack from left-top      //same diagonal
        HashSet<Integer> rl = new HashSet<Integer>(); // check attack from right-top     // same diagonal
        solveNQueenesByRow(n,res,0,prev,co,lr,rl);
        return res;
    }
    
    private String[] rowSample;
    
    private void initOutputString(int n){
    	rowSample = new String[n];
    	StringBuffer sb;
    	// string for queen at ith column.
    	for(int i=0;i<n;++i){
    		sb = new StringBuffer();
    		for(int c= 0;c<n;++c){
    			if(c==i){
    				sb.append('Q');
    			}else{
    				sb.append('.');
    			}
    		}
    		rowSample[i] = sb.toString();
    	}
    }
    
    
    /**
     *  save result found as a String[] and add to res. 
     * 
     *  r: current row,
     *  prev: prevPosition of queues that saved.
     * 
     */ 
    private void solveNQueenesByRow(int n, ArrayList<String[]> res, int r,ArrayList<Integer> prev, HashSet<Integer> co,HashSet<Integer> lr, HashSet<Integer> rl){
    	
//    	System.out.print(" run  ,  r: "+r+"     prvious:");
//    	for(int pr:prev){
//    		System.out.print(" "+pr);
//    	}
//    	System.out.println();
    	
        int cur;
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!1 as time limit exceed helps imporve again.
        // we only need to know half, the other we can use mirror the current result and find it out. || how about 90degree clockwise.
        for(int i=0;i<n;++i){
        	// we need only half. save half time.
            if(r==0){
            	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 boundary conditions!!! take care ok?
                if(i>=(n+1)/2){
                    continue;
                }
            }
        	System.out.println("r"+r+" i:"+i);
        	
        	
            // next possition try to place a queue is :   (r,i); // as we already know the row.
            // check attacks
            if(co.contains(i)){
                // this column is not available.
                continue;
            }
            
            
          //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 it's r-i and r+i!!!!!!
            // for two diagonals.
            int vlr = r-i; //left_top to right_bot diagonal
            if(lr.contains(vlr)){
                continue;
            }
            int vrl = i+r; //left_bot to right_top diagonal
            if(rl.contains(vrl)){
                continue;
            }
            
            
            
            // can do;  but we don't break ; as we want to find all the cases.
            if(r==n-1){
                // last row actually,  we get results.
                
            	System.out.println(" r: "+r+", i: "+i+"   vlr: "+vlr+"  vrl: "+vrl+"    lrsize: "+lr.size()+"   rlsize: "+rl.size());
            	
                addSolutionToRes(n,prev,i,res);
                continue; // continue we try next available one.
            }
            
            
            
            //not the last row, if we place here. we change condition values.
            
            co.add(i);
            lr.add(vlr);
            rl.add(vrl);
            prev.add(i);
            // go next.
            solveNQueenesByRow(n,res,r+1,prev,co,lr,rl);
            
            // and, when we returned from the function. it's not over, we still have to try , the left cases of the same row
            // also recognized as the "parent" level of this runned subfunction,  it's the time of back to parent node of depth treversal
            //
            // so we are back. we give up enter ith node, we regret to put queue at i.
            co.remove(i);
            lr.remove(vlr);
            rl.remove(vrl);
            prev.remove(prev.size()-1);
            // continue;
        }
    }
    
    
    private int iii =0;
    private void addSolutionToRes(int n, ArrayList<Integer> prev,int q,ArrayList<String[]> res){
//    	System.out.print("=");
//    	System.out.println("========= find a solution: "+iii++);

    	System.out.print(" run  ,  last: "+q+"     prvious:");
    	for(int pr:prev){
    		System.out.print(" "+pr);
    	}
    	
        String[] ret = new String[n];
        
        for(int r=0;r<n-1;++r){
            int p = prev.get(r);
            ret[r] = rowSample[p];
        }
        // for last row.
        ret[n-1] = rowSample[q];
        res.add(ret);
        
        if(prev.size()<1){
            return;
        }
        int p2 = n-1-prev.get(0);
        if(p2==prev.get(0)){
            //if first element the same, don't need to do the mirror.
            return;
        }
        
        String[] ret2 = new String[n];
        for(int r=0;r<n-1;++r){
        	p2 = n-1-prev.get(r); 
            ret2[r] = rowSample[p2];
        }
        ret2[n-1] = rowSample[n-1-q];
        res.add(ret2);
        
        
        System.out.println("========= find a solution: "+res.size());
    }
    
}