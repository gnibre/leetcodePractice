package codes;

import java.util.Stack;

public class LargestRectangleInAHistogram {
	
	
	
	
	
	/*****
	 * 
	 * this problem, seems simple, but cost me one whole day doing this.
	 * maybe today is not a good day for algorithm; but at least, i still shall understand this one;
	 *           x
	 *           x x
	 *           x x x
	 *         x xxx x
	 *       x x xxxxx
	 *       x xxxxxxx
	 *       xxxxxxxxx     
	 *       314274635    ; <=  histogram;
	 *          
	 *
	 *  think strait foward :
	 *  0, consider for loop from min heiht to max height  ;  or from max height to min height;
	 *     but we need to know the left bound and right bound of each height;
	 *     it's like for each height, we need to scan the array to find the boundary;
	 *      and the boundary maybe have many of them;  like histogram   42434243424342435253; if you try height3, you will find a lot of ; 
	 *     seems an algorithm of O(N^2)
	 *  
	 *  
	 *  1, consider scan from left to right , if the spot scanned is the right-bot corner of a rectangle; 
	 *      we may need to calc many heights; 
	 *      for example,  when scanned to 6, 
	 *       we may need to try  6*1( one column);     we if we choose height of 4;  4*3
	 *       we may need to try height2,   2*[from4274to6]=2*5
	 *       and we may need to try height of 1; 
	 *       we need to keep all the height that < current scanned number, and also it's position;
	 *       it's terrible;like we are doing O(n) steps for each number we scanned , and result in a O(N^2) algorithm;
	 *       
	 *  omg;
	 *  why a problem seems so easy can't be done by a straight forward algorithm;?
	 *  
	 *  think carefully with this 1;
	 *  when we use DP, we shall understand the resources we scanned very well;
	 *  A: there do have many choice to try,each case can be described as that:
	 *     for each position {ind} , with height{hei} that smaller than currrent scanned height;
	 *     we try: curHeight*(cur-ind0}
	 *      then   hei0*(cur-ind1}
	 *      then   hei1*(cur-ind2}
	 *      til   heiN*(cur-0);
	 *  
	 *  seems , waste some of the checks;  as we need cur is the max length of the array for doing some of the checks;
	 *  to save time;
	 *  
	 *  
	 * 
	 * 2; after scan the height( save the height in a way that when we need it, it can show me what we want; don't know the format yet)
	 *    takes time O(N)
	 *    so , when going for the Rectangle size, we scan from end of the array, to the beginning;
	 *    take the scanned position as the right-bot corner of rectangle(s)
	 *    for the case  314274635;
	 *    first;, scan where 5 is ,(pos 9)   
	 *    try height 5 :   5*1     ( 3 smaller than 5 at pos 8) , 1= 9-8;   8 : the first position that height < choosen height 5;
	 *    try height 3:   3*5      (2 smaller than 3 at pos 4)   5 = 9-4;   4: the first position that height < choosen height 3;
	 *    try height 2:   2*7     ( 1 smaller than 2 at pos 2)    7=9-2;   2 : the first position that height< choosen height 2;   
	 *    try height 1:   1*9    (non smaller than 1)     9 = 9 ;   non smaller than choosen height 1;
	 *    
	 *    so we can see that [[what we want]] is :   from the back to front,   the first position P that some height H is smaller than cur;
	 *    
	 *    we create a function P(H) that can return the first position P that height < input H; 
	 *    
	 *    next, scan where 3 is ; pos 8;
	 *    try height3? no need, height 3 is already tried; do we miss any cases?
	 *     yes; if another height 3 before pos 8;
	 *    deadend again?
	 *    
	 *    at least from this we can see that scan from back don't help save time ;
	 *    we'd rather try each position from beginning and take this as the end of the rectangle;
	 *    
	 *    and what we want from the function keep still; we want every height that < current height; 
	 *    
	 *   ok, design the P(H) function
	 *   sure, not a design, i have done some reading and konw we can do this with stack(s); one stack for height; one stack for index; 
	 *  
	 *  still the case of    314274635 :              position  1,2,3,4,5,6,7,8,9
	 *                                                height    3,1,4,2,7,4,6,3,5
	 *  
	 *  p1, height3 , stack save 3;      	 hstack: 3   
	 *  			   						 pstack: 1
	 *  	we don't there's no hurry to caculate height of 3 or keep the max;
	 *  cause we don't know the best case to calc this height 3;
	 *        
	 *  p2, height1,       top of stack is 3, current 1< 3;  ; 
	 *      !!!!!!!!!! ok, as1<3, height of 3 is over. cause we don't have to try height3 after this. it will be blocked by 1; look from right;
	 *      !!!!!!!!!!! this is how we reduce the complexity to O(N) cause we pop elements out of stack;        

	 *        x |
	 *        x |....unknown coming.  but does't matter; for the height = 3; the best it can do is connect to the begining of the array;                                    
	 *        xx|                         it can't reach any further after this 1;              
	 *   h    31 ??????? 			   so for postion 1, we  don't calc it til now; 
	 *   p    12                        AND, after this, we pop it from the stack;
	 *   
	 *   	 *              max      3                      hstack : 1
	 *                   		                            pstack : 2 
	 *                   when do we calc 1? later when we find something < 1;  
	 *                   (of cause nothing <1, so we just remember this, we will do this after array scan is over)
	 *   
	 *   
	 *  p3, height4,   top of stack is 1, current 4>1;  push to stack! cause we need both;
	 *  	calc things? not yet. cause we not sure what's the best case for this height 4;
	 *              
	 *      and try height 1 in the stack?   ( NO; cause keep doing this will cause complexity to O(N^2))
	 *      wait for the height 1 ;
	 *      
	 *      									hstack : 1,4
	 *                   		                pstack : 2,3
	 *      
	 *      
	 * p4 , height 2,      stack top is 4;
	 *         x
	 *         x 
	 * 		   xx
	 *        xxx
	 *     h ?142
	 *     p 1234
	 *             as cur = 2< topStack =4;    calc 4,  and end of 4; height 4 can do?  4*1;
	 *             so max =4; after this, remove 4 from stack; push 2 to stack;
	 *     
	 *     hstack: 1,2
	 *     pstack: 2,4
	 *            
	 * p5 , height7, cur 7> top 2;  add
	 * 
	 *     hstack: 1,2,7
	 *     pstack: 2,4,5
	 * 
	 * 
	 * p6, height4, pos:6            top7<4; work to do , end 7;
	 *                 size of 7: 7 , max now 7;
	 *     hstack: 1,2,4
	 *     pstack: 2,4,6
	 * 
	 * 						position  1,2,3,4,5,6,7,8,9
	 * 						height    3,1,4,2,7,4,6,3,5
	 * p7, height 6,            top 4<6; add
	 *     hstack: 1,2,4,6
	 *     pstack: 2,4,6,7
	 * 
	 * p8, height3;				top6>3; end of 6;
	 * 							second top4 >3; end of 4 too
	 *           x
	 *           x x
	 *           x x 
	 *         x xxx 
	 *       x x xxxx
	 *       x xxxxxx
	 *       xxxxxxxx 
	 *  h:   31427463
	 *  p:   12345678
	 * what's the best this  height 6 can do?        from posh6: 7 to posh4:6          6*1= 6;
	 * what this  4 can do? from pos 6 or from pos7? i guess from pos=77(before posh3); to 5(after posh2) ;   4*3=12; max til now
	 * 
	 * pop both 6 and 4 out; 
	 * 
	 * hstack: 1,2,3
	 * pstack: 2,4,8
	 * 
	 * 
	 * p9,height 5 ; add
	 * 
	 * hstack: 1,2,3,5
	 * pstack: 2,4,8,9
	 * 
	 * scan done; after done, it's like the next is coming something with p 10; and height =0;
	 * 
	 * as height is zero; it's the end of all the elements remaining in the stack ( include 1 that can't find something shorter before)
	 * 
	 * so, this height5,position9; best it can do? from  9 to position of 8( where 3 is)  5*1;
	 * 
	 *     height3, from position9 to where height2 is    (5~9)*3=  15;  new max;
	 *     height 2, from p9 to p3; 2*7;
	 *     height 1, from p9 to p0(empty stack)  9;       
	 * 
	 * res is  15;
	 * 
	 * ===============================================================================
	 * RULES FOR LARGEST RECTANGLE IN HISTOGRAM
	 * 
	 * 1, create a Height stack  , and a position stack
	 * 2, scan the array from left to right; once;
	 * 3,   for comming element curH at position curP;
	 *      top of stack is element sTopH , and at position sP
	 * 	   A: if( stackIsEmpty || sTopH<curH ) ;   do add curP and curH to two stacks;
	 *     B:  if ( sTopH>curH);       
	 *          for every element below curH;  calc the rectange size it can make; and pop it out from the stack;
	 *                can name it as  stack.popRes( curH , curP) // curH for pop min element dead line, curP for start from;
	 * 4,  at the end, when scan finished,
	 *     clear the stack; 
	 *     just call function stack.popRes( 0 , end_of_array_position) ; add a 0 ; array cut;
	 *     
	 * o. remember to keep the max during this...    
	 * ===============================================================================================
	 *       
	 *  thanks to : 
	 *  http://wansishuang.appspot.com/?p=3002
	 *  http://www.youtube.com/watch?v=E5C5W6waHlo
	 *  http://blog.csdn.net/havenoidea/article/details/11854723
	 * @param histo
	 * @return
	 */
	public int largestRectangleInAHistogram(int[] histo){
		System.out.println(" histo size: "+histo.length);
		if(histo==null||histo.length<1){
			return 0;
		}
		System.out.println("histogram:  ");
		for(int i=0;i<histo.length;++i){
			System.out.print(" "+histo[i]);
		}
		System.out.println("");
		
		for(int i=0;i<histo.length;++i){
			newHeightCame(histo[i],i);
		}
		
		newHeightCame(0,histo.length); //end it;
		return max;
	}
	
	int max = 0;
	Stack<Integer> hStack = new Stack<Integer>();
	Stack<Integer> pStack = new Stack<Integer>();
	
	private void newHeightCame(int height,int p){
		// for equal case? same as if height <= peek; why, cause it brings end to this height(at that position) ; though actully teh result is no used( guaranteed not a best )
		// so just ignore the same case..
		if(hStack.isEmpty()||hStack.peek()<height){
			//just add
		}else if(hStack.peek()>height){
			//pop and calc first, then at last , also add ;
			stackPopElements(height,p);	
		}
		
		hStack.push(height);
		pStack.push(p);
//		System.out.println(" ++++++++++++++ push height"+height+"  position: "+p+"   after thsi , : "+pStack.peek()+"   size: "+pStack.size());
	}
	
	private void stackPopElements(int height,int p){
//		System.out.println("  pop til elment of height: "+height);
		// p is the end position taht can't reach;
		int recEnd = p-1;
		int size;
		//again, for the equal case; we pop element that equal to height in the stack;  cause it's not useful anymore.
		// and also, we don't need to calc the value for it, cause it's guaranteed no the best value yet;
		while(!hStack.isEmpty()&&hStack.peek()>=height){
			int sh = hStack.pop();
			int sp = pStack.pop();
//			System.out.println("------------- sh and sp is: "+sh+"   sp:"+sp+"      size left is: "+pStack.size());
			
			int start;
			if(sh==height){
				//ignore;
			}else{
				if(pStack.isEmpty()){
					start = 0;
				}else{
					start = pStack.peek()+1; // element smaller than this height is at this position;
				}
				size = (p-start)*sh;  // size is width*height;
//				System.out.println(" height : "+sh+"   size: "+size);
//				System.out.println(" p and start : "+p+"    start:"+start);
				if(size>max){
					// also can record other things if we want;
					max = size; 
				}
			}
		}
	}
	


	
	
	public void go(){
//		int[] h = {3,1,4,2,7,4,6,3,5};
//		int[] h = {5,1,7,4,10,6,8,3};
//		int[] h = {5,1,7,0,9,5,0,3};
//		int[] h = {4};
		
		int[] height = new int[100000000];
		for(int i=0;i<height.length;++i){
			height[i] = 1;
		}
		
		largestRectangleInAHistogram(height);
		System.out.println(" max is : "+max);
	}
	
}
