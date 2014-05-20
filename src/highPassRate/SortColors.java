package highPassRate;

import ulti.Printer;

public class SortColors {
	
	public void go(){
		
		int[] A = {
//				1,0,1
				1
		};
		
		sortColors(A);
		System.out.println("------------------");
		Printer.pArray(A);
	}
	
	
    
    /**
     * only three colors, so can swap?
     * sort from chaos to 000111222;  so from the end, if it's 2, easy to swap with 0 from front;
     * so if we get 1? wait til next 0 or 2;
     * 
     * why don't we just count numbers...
     * 
     */ 
    public void sortColors(int[] A) {
        
        // two cursor that we are still not sure about the content; 
        // but for stuff before start, all 0's (or nothing)
        //     for things after end?  all 2's, or end of array;
        // so let's go;
        int s = 0;
        int e = A.length-1; 
        // int cur = s;
        
        // when they meet, scan is done; 
        // s and e are index for filling 0 and 2's 
        // we use another TWO... yes, two cursor for searching... maybe one is enough, but one cursor from one side makes me feel not safe...
        int ss,se; //search start and search from end;
        ss = s;
        se = e;
        while(ss<=se){
            System.out.println(" N.  "+ss+"   "+se+"      ;    s:"+s+"  e: "+e);
            Printer.pArray(A);
            // from front,  
            // if 0 skip;  if 1 wait ; if 2 swap to waiting end;
            
            // fron end 
            // if 2 skip; if 1 wait; if 0 swap to wating start;
            
            
            // if both waiting at 1? move on so we need a fxxk third cursor??
            
            // skip is easy; these blanks are filled;
            while(s<=e&&A[s]==0){     s++;  ss++;         }
            while(e>=s&&A[e]==2){      e--;     se--;       }
            
            System.out.println(" s: "+s+"  e: "+e);
            
            if(s>e){
            	System.out.println(" break here");
                break;
            }
            
            
            // active, s must swap to end when get 2; no matter what you have at end;
            if(A[s]==2){
                A[s]=A[e]; // as A[s] don't know what it is ,maybe 0, may 1, don't move s yet
                A[e] = 2;
                e--;
                continue; // to make logic easy.. just go next round;
            }
            
            // 0 is something for safe swap; must swap actually
            if(A[e]==0){
                A[e] = A[s];
                A[s]=0;
                s++;
                continue; // to make logic easy.. just go next round;
            }
            
            // how to take care of 1?  
            // IGNORE it, like it's just left blank in the array; anytime got an effect number, fill the blank;
            if(ss<=s){
            	ss = s+1;
            }
            if(se>=e){
            	se = e-1;
            }
            
            /// meanings again:  s & e, position to write/fill next 0/2; 
            //                  ss& se, position with values not checked;
            // so ss==s can happen, when came that the next slot to fill is at ss, but because it's value not checked yet,
            // we'd better check it first; so we kinda blocked at this position;
            
            
            
            // ss and se are the content that you can't overwrite before read;
            // but it's guaranteed that things between [s,ss) and (se,e] are all 1s, can be easy overwrite and don't need to check;
            
            // so if it goes here. we can use these 1s to make things faster; 
            // we don't swap 1s , we ignore 1s , we just pull 0 and 2 s and pill them to the right position;
            
            
            while(ss<=se){ // with 1's for the blank space, no more swap! at least try to do that;
            	System.out.println(" SEC .  "+ss+"   "+se+"  s:"+s+" e:"+e);
                while(ss>s&&ss<=se){
                	System.out.println(" SEC I .  "+ss+"   "+se);
                    //have 1s between ss and s; so it's kinda free for the right side to do what they want;
                    if(A[se]==2){
                        //skip, and value well filled;
                        if(se!=e){
                            // fill e with se's 2;
                            A[e]=2;
                            //actually we need to swap a 1 to A[se] to keep the array the same content with old one; but we just ignore 1;
                        }else{
                            // no need to do;
                        }
                        se--;
                        e--;
                    }else if(A[se]==1){
                        //skip, and no need to fill value;
                        se--;
                    }else{
                        //  A[se]==0;  as we already know in this while loop; there's 1s between s and ss; we can just insert;no swap;
                        //basically we are doning counting one by one, but this problem don't suggest us to count...
                        A[s] = 0;
                        s++;// here ss don't add one cause value still not checked;
                        se--;
                    }
                }
                
                
                while(se<e&&ss<=se){
                	System.out.println(" SEC II .  "+ss+"   "+se);
                    // like ss>s ; there's 1 s at the end part; time to check search head;
                    if(A[ss]==0){
                        // fill it to s;
                        if(ss!=s){
                            A[s] =0;
                        }
                        s++;
                        ss++;
                        
                    }else if(A[ss]==1){
                        ss++;
                    }else{
                        //A[ss]==2; 
                        A[e] = 2;
                        e--;
                        ss++;
                    }
                }
            }
            
        }
        
        
        // when while loops are over; we need to fill the rest;
        //!______  i form [s ,e]
        System.out.println(" final s: "+s+"  e:"+e);
        for(int i=s;i<=e;++i){
            A[i] = 1;
        }
    }
}