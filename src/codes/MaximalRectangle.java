package codes;

import java.util.ArrayList;

public class MaximalRectangle {

	public void go(){
		char[][] matrix = {
				{'0','1'}
		};
		
		int res = maximalRectangle(matrix);
		System.out.println(" res is :"+res);
		
		
	}
	
	
/****
 * 
 *  dp?  find a clever dp that can use already scanned info;
 * 
 * 
 *   when scan to (x,y) and we find (a,b)~(x,y) is the max rectangle yet;  then (a,b)~ (x-1,y) or (a,b)~(x,y-1) must also in 
 * the lsit of big rectangles;  
 *  so , one scan; check biggest was;
 * 
 * for a line 1D, we can do one scan and record the max length to each position.
 * for a 2D. record the right-bot of maxRectangle found at each position is not good enough;
 * for example.  the max found at (20,20) is a 4*6 of size 24;  at the same time there is a 2*11 at this position. of size 22;
 * and at position (21,20) the max maybe that 3*11, not 5*6 from last max;  so only record the max we'll miss cases;
 * 
 * if we do things like in 1-D. the complexity will be O(n^3); n^2 for scan; and o(n) to check each node above it, to find the max;
 * 
 * 
 * 
 * ///////////////////////////////////////////////
 * worked one day on the question:  largestRectangleInAHistogram; 
 * as we can get  Histogram by scan the matrix, n*n
 *  and for each line of Histogram, we can get largestRectangleInAHistogram in O(N)
 * total is still O(N^2)
 * 
 * so this strategy is welldone;
 * things to do:
 * http://hi.baidu.com/mzry1992/item/030f9740e0475ef7dc0f6cba  ;
 * so, how to slove it with hangle lane?  or with bad spot?  
 * 
 * 
 * 
 */
public int maximalRectangle(char[][] matrix) {
    if(matrix==null||matrix.length<1){
        return 0;
    }
    
    LargestRectangleInAHistogram lr = new LargestRectangleInAHistogram();
    int h = matrix.length;
    int w = matrix[0].length;
    
    
    int threshold = Math.max(w,(h+w)/2); // if htere are number of bad points overthis, we give up algorithm focus on badpoints.
    int[] badH = new int[threshold];
    int[] badW = new int[threshold]; // save width and height of bad points.
    
    int[][] vHistogramMatrix = new int[h][w];
    int count =0;
    
    for(int j=0;j<w;++j){
        if(matrix[0][j]=='1'){
            vHistogramMatrix[0][j] = 1;
        }else{
        	badH[count] = 0;
        	badW[count] = j;
        	count++;
        }
    }
    
    for(int i=1;i<h;++i){
        for(int j=0;j<w;++j){
            if(matrix[i][j]=='1'){
                vHistogramMatrix[i][j] = vHistogramMatrix[i-1][j]+1;
            }else{
                vHistogramMatrix[i][j] = 0;
                // count the bad spot, in case we can use other strategy to handle this.
                
                if(count<threshold){
                	// save the bad point;
                	badH[count] = i;
                	badW[count] = j;
                }
                count++;
            }
        }
    }
    
    
    
    if(count<=threshold){
    	// use strategy based on badpoints;
    	return maximalRectangleBadPoints(h,w,badH,badW,count);
    }
    
    
    int maxx=  0;
    for(int i=0;i<h;++i){
        int maxThisLine =  lr.largestRectangleInAHistogram(vHistogramMatrix[i]);
        if(maxThisLine>maxx){
            maxx = maxThisLine;
        }
    }
    
    return maxx;
}




/**
 * from zhikun's 
 * 
 * max Rectangle must be srround by bad points , or the boundary of matrix
 * find bad points , find results.
 * 
 *  do this by one scan from top to bot, line by line. from left to right in each line;
 *  
 *  1 take the top boundary as the top of maximal Rectangle,
 *  what we want to do is find the max rectangle take this boundary as top;
 *  
 *  	 left boundary( 1 point w< top or left boundary of matrix)
 *  	right boundary ( 1 point w> top or right boundary )
 *  	bot boundary ( 1 point or bot boundary)
 *  
 *   so we have to scan all the bad points for this top boundary
 *   
 *   when top is confirmed, we scan the rest points one by one,
 *    A : if the scanned point is as the same height with the top boundary; add to the top boundary selection; and go next point
 *    B : if the scanned point is lower than the top boundary; 
 *    		we MUST add this point to all the cases; and cant escape no point;
 *    		B.1   take this point as the bot; (do not end) we have more choices for this top boundary
 *    				        (if already have left or right; check if it's valid)
 *    		B.2	  if top boundary have point that to right of this point;  
 *    					if no left boundary yet, take this as left boundary, add this case;
 *    					if already have left boundary, check if this to right of left boundary, take this one as the new left boundary;
 *    		B.3   if top boundary have point that to left of this point; take this as right boundary ; add this case;
 *    					if no right boundary yet, take this as the right boundary
 *    					if already have right boundary
 * 2 scan all the points from top down again, and this time take next height as top boundary;   
 *   
 * algorithm:
 *  int len = badpoints.length;
 *  for( int i=-1;i<len;++i){
 *  	if(i==-1) height-1 as top
 *  	else height is badpoints[i].height;
 *  
 *  	for(int j=i+1, i<len;++i){
 *  		do the B part;
 *  
 *  	}
 *  
 *  
 * 
 * 
 * @param vM
 * @return
 */
private int maximalRectangleBadPoints(int h,int w,int[] badH,int[] badW,int count){
	System.out.println(" maximalRectangleBadPoints,  h"+h+"  w"+w+"    count:"+count);
	// use bad points to find the maxRectangle;
	//points are ordered level by level from top to bot, then left to right in the same level; 
	// a maximal rectangle is surrounded by bad points ( or the boundary of matrix);
	
	// do this by one scan;
	
	//EVERY POINT IN THE BOUNDARY IS EXCLUSIVE; [0,0] means this one takes this point, of length 1;  [2,-1] means don't exist; [2,3] is of length 2;
	ArrayList<LRBoundaryPair> sol = new ArrayList<LRBoundaryPair>();
//	int size = badH.length; //size of bads.
	int size = count; // size of bad is not the size of array; cause when it inited, it is takes too much space;
	
	if(size==0){
		//no bad guy;
		return h*w;
		
	}
	
	
	// i is the index to top bad points position of maximal rectangle. 
	// i=-1 means no top boundary bad points. just take top matrix
	// otherwise,  top bad pints is:  (badH[i],badW[i]) , top is badH[i]
	int currentFocusingTop= -1;
	int ph = 0,pw;
	int curSize;
	int maxRecord = 0;
	for(int i=-1;i<size;++i){
		// this loop for find the focusing top of maximal rectangle;
		if(i==-1){
			currentFocusingTop= 0;
		}else{
			// find next focusing top;
			if(badH[i]<currentFocusingTop){
				continue;
			}else{
				currentFocusingTop = badH[i]+1;
			}
		}
		sol = new ArrayList<LRBoundaryPair>();
		LRBoundaryPair wholeW = new LRBoundaryPair(0,w-1); // take the screen from left to right;
		sol.add(wholeW);
		// for each top; we do visit all the points after that;
		for(int j=i+1;j<size;++j){
			// this is the point for visit;
			ph = badH[j];
			pw = badW[j];
			if(ph<currentFocusingTop){
				//!!!!!!!!!!!!!!boundary conditions!!
				continue; //this one is as high as current focusing top; we can skip this point;
			}
			
			
			int conditionSize = sol.size();
			for(int k=0;k<conditionSize;++k){
				LRBoundaryPair lrp = sol.get(k);				
				//1 take this one as the bot? apply to each record; so will gen a result, don't need to change the condition of boundary array; 
				curSize = (ph-1-currentFocusingTop+1)*lrp.getWidth();
				System.out.println("condition idnex:"+k+" cursize : "+curSize+"        for : "+ph+"       l:"+lrp.l+" r:"+lrp.r);
				if(curSize>maxRecord){
					maxRecord = curSize;
				}
				
				// 2 don't wanna stop here.  but we can't skip this one; because it's bad;  if we wanna pass this point, we have to change current l-r 
				
				if(lrp.l<=pw&&lrp.r>=pw){
//					split this one;
					// cause pw is bad, the new l-r boundary is from pw-1], or [pw+1
					
					if(lrp.l<pw){
						LRBoundaryPair lrp1 = new LRBoundaryPair(lrp.l,pw-1);
						sol.add(lrp1);
					}
					if(lrp.r>pw){
						LRBoundaryPair lrp2 = new LRBoundaryPair(pw+1,lrp.r);
						sol.add(lrp2);
					}
					sol.remove(k);
					break; //and have to break as the list itself changed;
				}
			}
			//got next point;
			
		}//end of visit each point focusing some hight for top of rectangle;
		
		//after all the points done;  we may still find rectangle that don't have bot boundary; through the focusing top to the bot of matrix without bad poitn;
		int conditionSize = sol.size();
		for(int k=0;k<conditionSize;++k){
			LRBoundaryPair lrp = sol.get(k);
			curSize = (h-1-currentFocusingTop+1)*lrp.getWidth();
			System.out.println(" cursize : "+curSize+"        for h: "+h+"cutop:"+currentFocusingTop+"       l:"+lrp.l+" r:"+lrp.r);
			if(curSize>maxRecord){
				maxRecord = curSize;
			}
		}
		
	}//each loop focus a top;
	
	return maxRecord;
}

private class LRBoundaryPair{ 
	int l;
	int r;
	public LRBoundaryPair(int left,int right){
		l = left;
		r = right; // right boundary
	}
	public int getWidth(){
		return (r-l+1);
	}
}


}