package codes;

import java.util.ArrayList;

public class LongestValidParentheses {
	
	public void go(){
		
		String s;
		
		s = "(()()(()(()))()((()))((()(()())()(()))())))()(()()))())))))))()()()()))(((()())((()()(((())))()(()()(())((()))))))(()(()))(((()())()))(()))((((()(()()()())()()(()))(()()(())()((()()())))(())()())()(";
		
		s="()()(()(()))";
		s = ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())";
		s="((())())(()))(()()(()(()))(()((((()))))))((()())()))()()(()(((((()()()())))()())(()()))((((((())))((()))()()))))(()))())))()))()())((()()))))(()(((((())))))()((()(()(())((((())(())((()()(()())))())(()(())()()))())(()()()))()(((()())(((()()())))(((()()()))(()()))()))()))))))())()()((()(())(()))()((()()()((())))()(((()())(()))())())))(((()))))())))()(())))()())))())()((()))((()))()))(((())((()()()(()((()((())))((()()))())(()()(()))))())((())))(()))()))))))()(()))())(()())))))(()))((())(()((())(((((()()()(()()())))(()())()((()(()()))(()(())((()((()))))))))(()(())()())()(()(()(()))()()()(()()())))(())(()((((()()))())))(())((()(())())))))())()()))(((())))())((()(()))(()()))((())(())))))(()(()((()((()()))))))(()()()(()()()(()(())()))()))(((()(())()())(()))())))(((()))())(()((()))(()((()()()(())()(()())()(())(()(()((((())()))(((()()(((()())(()()()(())()())())(()(()()((()))))()(()))))(((())))()()))(()))((()))))()()))))((((()(())()()()((()))((()))())())(()((()()())))))))()))(((()))))))(()())))(((()))((()))())))(((()(((())))())(()))))(((()(((((((((((((())(((()))((((())())()))())((((())(((())))())(((()))))()())()(())())(()))))()))()()()))(((((())()()((()))())(()))()()(()()))(())(()()))()))))(((())))))((()()(()()()()((())((((())())))))((((((()((()((())())(()((()))(()())())())(()(())(())(()((())((())))(())())))(()()())((((()))))((()(())(()(()())))))))))((()())()()))((()(((()((()))(((((()()()()()(()(()((()(()))(()(()((()()))))()(()()((((((()((()())()))((())()()(((((()(()))))()()((()())((()())()(())((()))()()(()))";
		long t1 = System.currentTimeMillis();
    	int res = longestValidParentheses(s);
    	long t2 = System.currentTimeMillis();
    	System.out.println(" longestValidParentheses? : "+res+"  time: "+(t2-t1)+"     input len: "+s.length());
    	
    	
    	int cal =0;
    	for(int i=0;i<s.length();++i){
    		for(int j=0;j<s.length();++j){
    			if(rec[i][j]!=0){
    				cal++;
    			}
    		}
    	}
    	
    	System.out.println(" total calc: "+cal);
    	
	}
	
	
	
    private int[][] rec;
    /**
     *  if si is valid, 
     * 
     * 
     *   (s1), (s1 s2) , s1 () s2 , are valid.
     * s1 = (s2)s3    , s2 could be null, s3 could be null , if s1 is valid, s2 and s3 is valid 
     * it's like matching palindrome. mid part of string works, the whole may work.
     * 
     * so to check if s is a valid,
     * 1, find heading '('
     * 2, find ')' that pair with heading '(',   and we get s = (s2)s3,  check s2 and s3, if s2 and s3 are valid, s is valid, 
     * 
     * it's a dp, to speed up, we same base conditions,   with string char start/end in array record[start][end]
     * 
     * 
     * 
     *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * as LTE is the final result of this code. had to find a better way.
     * and then i find out that  , what?  we can have a greedy strategy.?
     * 
     *        say s is:   s1,'(',')',s2 ; so for that ')', if it's don't are with that '(',  any string contain string s can't be a valid,
     * 		in brevity, brief,  if sub string "()" found, it's a pair and we can't separate it. 
     * 
     * for example, string is 0(1(2(3(4)5)6(7(8(9)10(11(12(13(14)15)16)17)18)19))))))))))))))
     * 
     * when process, we combine the inner most () pair.  in greedy strategy, and save the count.
     * 
     * 0(1(2(3( when 4) came :    0(1(2([2] 5) 
     * 0(1([4] 6(7(8(  when 9) came : 0(1([4] 6(7([2]  
     * 0(1([4] 6(7([2] 10(11(12([2]   
     */
    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0) return 0;
        int len = s.length();
        
        rec = new int[len][len];
        
        for(int i=0;i<len;++i){
            for(int j=0;j<len;++j){
                rec[i][j] = 0; //init as 0 : unknown ( -1: not valid,  other, length of the string)
            }
        }
        
        char[] ca = s.toCharArray();
        int longest = 0;
        int val;
        for(int i=0;i<len;++i){
        	for(int j=len-1;j>i;--j){
        		if(j-i+1<longest){
        			//cant get longest with this string.
        			continue;
        		}
        		
        		val = longestValidParenthesesAux(ca,i,j);
        		if(val>-1){
        			if(val>longest){
        				longest = val;
        				System.out.println("============================ longest now is :"+longest+"   from : i"+i+" j:"+j);
        			}
        		}
        	}
        	System.out.println(" start from : "+i+" over, res longest: "+longest);
        	
        }
        
        System.out.println(" longest: "+longest);
        
        int ret = greedyLongestValidParenthesesAux(s);
        return ret;
        
    }
    
    private int greedyLongestValidParenthesesAux(String s){
    	char[] ca = s.toCharArray();
    	
    	// keep a stack of (())) and number also in a stack ( arraylist)
    	
    	// define:  ( as  -1 , ) as -2;   if saved value is a value, it's the length of a valid match.
    	
    	ArrayList<Integer> resStack = new ArrayList<Integer>();
    	
    	int top;
    	int topChar = -1;
    	int commingChar;
    	for(int i=0;i<ca.length;++i){
//			showStack(resStack);
//			System.out.println("===char to add,   index : "+i+"   :   "+ca[i]);
    		
    		
    		if(ca[i]=='('){
    			commingChar = -1;
    			// no chance to match with this guy. we have to use ')' to match a existing string.
    			resStack.add(0,commingChar);
    			continue;
    		}
    		commingChar = -2;
    		if(resStack.size()==0){
    			// stack is now, empty, just push chars.
    			//everything add to head, as a stack did.
    			resStack.add(0,commingChar);
    			continue;
    		}
    		
    		// stack is not empty.
    		// check top element
			top = resStack.get(0);
			if(top>-1){ // not ( or ), it's a number
				if(resStack.size()>1){
					topChar =resStack.get(1); // it's a char now.
				}else{
					topChar = -3; //no top char found.
				}
			}else{
				// top is a char.
				topChar = top;
			}
			
			if(topChar+commingChar==-3){
//				System.out.println(" match found");
				// match ;  -1 + -2;
				int sum = 0;
				if(top>-1){ // top is number
					sum = top+2;
					resStack.remove(0); // remove top
					resStack.remove(0); // remove top char that matched.
				}else{
					// top is the matched char.
					resStack.remove(0); //remove top/topchar.
					sum = 2;
				}
				
				// we may want to push the sum back, but take care, as we removed one char from stack, the one next pop maybe another number.
				// we get two number together.
				
				if(resStack.size()>0){
					int nextTop = resStack.get(0);
//					System.out.println(" next top check: "+nextTop);
					if(nextTop>-1){
						// next is a number
						sum+= nextTop;
						resStack.remove(0); //remove this number, we save the sum.
					}
				}
				
				resStack.add(0,sum);
				
			}else{
				// not match, oshit. just add one more char.
				resStack.add(0,commingChar);
			}
			
			// go match next char.
			
			
			
    	}
    	
    	int maxRec = 0;
    	for(int i=0;i<resStack.size();++i){
    		int v = resStack.get(i); 
    		if(v>maxRec){
				maxRec = v;
			}
    	}
    	
    	
    	return maxRec;
    }
    
    private void showStack(ArrayList<Integer> resStack){
//    	System.out.println(" =============  show stack =========>");
    	int size = resStack.size();
    	
    	for(int i=0;i<size;++i){
    		int v = resStack.get(i); 
    		if(v==-1){
    			System.out.print("(");
    		}else if(v==-2){
    			System.out.print(")");
    		} else{
    			System.out.print(" "+v);
    		}
    	}
    	System.out.println("");
    }
    
    
    int ind =0;
    private int longestValidParenthesesAux(char[] ca,int s,int e){
    	
    	
        if(e==s){
            // one char is not valid.
            return -1;
        }
        
        if(e==s-1){
         // WTF, e<s?  and return 0??
         //sure, cause caller is a e=s+1, we pretend to be the "";
         return 0;
        }
        
        
        if(rec[s][e]!=0){
            //already have value. it's already cooked.
//        	System.out.println("longestValidParenthesesAux ,     hit");
            return rec[s][e];
        }
//        System.out.println("longestValidParenthesesAux , ind: "+ind+++"         s,e: "+s+" ~ "+e+"   len:"+(e-s));
        
        // heading '('  , can we find it.
        if(ca[s]!='('){
        	rec[s][e] = -1;
            return -1;
        }
        if(ca[e]=='('){
        	rec[s][e] = -1;
            return -1;
        }
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!TLE , this step is too slow if the input for check is long string , and we have to find the every ')'  and try to pair.
        // find ')' to pair  with heading '('
        for(int i=s+1;i<=e;++i){
            if(ca[i]==')'){
                //try pair with this one,      ( strIn ) strOut
                
            	int inside = longestValidParenthesesAux(ca,s+1,i-1);
                if(inside>-1){
                    //inside is avlid 
                	
                	int outside = longestValidParenthesesAux(ca,i+1,e);
                    if(outside>-1){
                    	rec[s][e] = inside+outside+2; 
                        return rec[s][e];
                    }
                }
            }
        }
        // no good pair for a valid.
        rec[s][e] = -1;
        return -1;
    }
    
}