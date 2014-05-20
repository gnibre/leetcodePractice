package codes;

import java.util.HashMap;
import java.util.HashSet;

public class MinWindowSubstring {
    
	
	public void go(){
		
		String S,T;
		
		S = "ADOBECODEBANC";
		T = "ABC";
		
		S ="of_characters_and_as";
		T = "aas";
				
		
		S="ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country";
		T="ask_country";
		
		String res = minWindow(S,T);
		System.out.println(" res: "+res);
		
		
	}
	
    // scan once? // char one by one, 
    // 
    //
    
	//set is not enough, we also need count of each word in the string T.
//    private HashSet<Character> nappearSet = new HashSet<Character>();
    
    private HashMap<Character,Integer> tCharMap = new HashMap<Character,Integer>(); //chars in T.
    private HashMap<Character,Integer> cIndexMap = new HashMap<Character,Integer>(); //chars index in the array. 
    int cIndex = 0;
    int[] countMap; // we got upper case , lower case, and maybe other chars. 
    int distance; //when distance is 0, we got what we need.
    /***
     * 
     * 
     * 1, save chars of T in a set/map , appearMap
     * 2, scan from front to ? til find first position p that S[0]-S[p] contains all the char.
     *    thus we got is = 0, ie = p;
     *    try push is++, if the reduced window still contain all the char, we can reduce the size of the window.
     *    thus we got a init window size, is(ie-is+1)
     * 3, move is and ie by one each time and til ie reachs end.
     * 
     * 
     */
    public String minWindow(String S, String T) {
        
        // init everything.
//        System.out.println(T);
//        System.out.println(S);
        char[] ta = T.toCharArray();
        for(char c:ta){
        	if(tCharMap.containsKey(c)){
        		int ct= tCharMap.get(c);
        		ct++;
        		tCharMap.put(c,ct);
        	}else{
        		tCharMap.put(c,1);
        		// first appear, 
        		cIndexMap.put(c, cIndex);
        		cIndex++;
        	}
        }
        
        countMap = new int[cIndex];
        for(int c:countMap){
            c =0;
        }
        distance = T.length();
        int is=0; int ie =0;   //scan cursor start/end.
        
        char[] sa = S.toCharArray();
        char  c;
        // firt scan to find the first window.
        for(ie=0;ie<sa.length;++ie){
            c = sa[ie];
//            System.out.println(" char : "+c);
            if(tCharMap.containsKey(c)){
            	int countNeeded = tCharMap.get(c);
            	int cur = ++countMap[cIndexMap.get(c)];
            	if(cur<=countNeeded){
            		// we need this one,  so we got distance -1
            		distance--;
            	}
            	
//            	System.out.println(" char : "+c+"  cur: "+cur+"     dis: "+distance);
            	if(distance==0){
            		// first time got everything we need.
            		break;
            	}
            }
        }
        
        if(distance>0){
            // no result, even the whole string of s don't contain T.
            return "";
        }
//        printSaved(is,ie,sa);
        
        // ok, first window go.   try reduce it.
        boolean canDeleteFirstChar = true;
        int count;
        while(canDeleteFirstChar){
            c = sa[is];
            if(!tCharMap.containsKey(c)){
            	is++;
            	// don't have count for this.
            }else{
            	count = countMap[cIndexMap.get(c)];
            	if(count>tCharMap.get(c)){
            		// true, can do ; we have many  we can delete one.
            		is++;
            		countMap[cIndexMap.get(c)]--; //cause we gonna move window and delete first char of this window.
            	}else{
            		canDeleteFirstChar = false; // this is done.
            	}
            }
        }
        
        int[] saved =new int[2];
        saved[0]= is;
        saved[1] = ie;
//        printSaved(saved[0],saved[1],sa);
        int winLenRequest = ie-is-1; // scan and see if window of this size can be find. Of cause start from saved_length-1
        
        // ok , first reduced window got. try find a smaller one in the later scan.
        
        // scan the rest of the string, and this time, we keep an eye on the size of the window, 
        // we discard ones that larger then saved.
        
        // takecare, all the conditions (count/appear) in the array and map are for sa[is] to sa[ie]
        while(ie<sa.length&&winLenRequest>0){
            while(ie-is>winLenRequest){
                //move is to get a smaller window.
                c = sa[is];
                removeAChar(c);
                is++;
            }
            
            if(distance==0){
                // this window works, 
                saved[0] = is;
                saved[1] = ie;
//                printSaved(saved[0],saved[1],sa);
                winLenRequest--;
                continue;  // try remove from head when size reduced.
            }
            
            
            // else, what we get is a small window, but don't contain all hte char in string T.
            // but we don't giveup, we move on.
            
            // window move on, is++ and ie ++;
            
            // during this moving, conditions changed.
            c = sa[is];
            removeAChar(c);
            is++;
            ie++;
            if(ie==sa.length){
            	break;
            }
            c = sa[ie];
            //add a char.
            if(!tCharMap.containsKey(c)){
            	/// useless char added.
            }else{
            	int countNeeded = tCharMap.get(c);
            	int cur = ++countMap[cIndexMap.get(c)];
            	if(cur<=countNeeded){
            		// we need this one,  so we got distance -1
            		distance--;
            	}
            }
        }

        // when while loop break, res is saved.
        return S.substring(saved[0],saved[1]+1);
    }
    
    
    private void removeAChar(char c){
    	if(!tCharMap.containsKey(c)){
    		// safe to remove , don't even have count record for this one.
    	}else{
    		int countNeeded = tCharMap.get(c);
    		int cur = --countMap[cIndexMap.get(c)];
    		if(cur<countNeeded){
    			// not enough char to get String T now, distance change. 
    			distance++; 
    		}
    	}
    }
    
    private void printSaved(int a,int b,char[] sa){
        System.out.println("   printSaved  from : "+a+"    to  "+b+"             len: "+sa.length);
      for(int i=a;i<=b;++i){
      	System.out.print(" "+sa[i]);
      }
      System.out.println("<====== saved");
    	
    }
    
}