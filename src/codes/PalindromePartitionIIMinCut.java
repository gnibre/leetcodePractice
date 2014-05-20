package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class PalindromePartitionIIMinCut {
    
	public void go(){
		
		
		
		String s = " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//		s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		s = "fifgbe";
		s = "fifgbeacccfee";
		s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		
		s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		s = "ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk";
		s = "apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp";
		
		
		
		long st = System.currentTimeMillis();
		
//		ArrayList<String> res = bestCut(s,Integer.MAX_VALUE);
//		
		int res = minCut(s);
//		System.out.println(" res size: "+ res);
		long e = System.currentTimeMillis();
//		
		System.out.println(" res size: "+ res+"      , time:      "+(e-st));
		System.out.println(" rec size:"+rec.size());
		System.out.println(" pset size:"+pSet.size());
//		
//		
		
		
		
		ArrayList<String> ares = bestCut(s,Integer.MAX_VALUE);
		System.out.println(" right answer's words count: "+ares.size()+"       cut(-1):  ");
		String l,r; // right one is right.
		for(int i=0;i<debug.size();++i){
			l = debug.get(i);
			r = ares.get(i);
			if(!l.equals(r)){
				System.out.println("==============!!!!!!!!!!!!!!!!!========");
			}
			System.out.println("("+l+")"+"  ["+r+"]"     );
		}
		
		System.out.println("   last one: "+ares.get(ares.size()-1));
		
//		System.out.println(" size is: "+ares.size());
//		for(String ss: ares){
//			System.out.println(" "+ss+"("+ss.length()+")");
//		}
//		
//		
//		System.out.println(" size debug is: "+debug.size());
//		for(String ss: debug){
//			System.out.println(" "+ss+"["+ss.length()+"]");
//		}
		
		
		
	}
    
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// this implementation is cool, fine , works, fast ,
	// but it can't pass leetcode online judge,
	// i guess it's cause memory useage problem , it's not good to save everything as a ArrayLIst<string> record,  we may try to save this.
	// new function uses bestCut2. 
	
	ArrayList<String> debug = new ArrayList<String>();
	
	private int[][] isPalindrome;
	private int[][] recMinCut;
	
    /**
     *  min cut ?   least words in the result array?
     * 
     * 0, we already did Palindrome Partition II and find all, we can just find the min cut from that
     * 1, how to optimize ( ofcause we don't need to find all, and we only record the best cut)
     * 
     */
    public int minCut(String s) {
    	if(s==null||s.length()==0){
            return -1;
        }
    	
    	//init.
    	int len = s.length();
    	System.out.println(" s, len : "+len);
    	isPalindrome = new int[len][len];
    	recMinCut = new int[len][len];
    	for(int i=0;i<len;++i){
    		for(int j=0;j<len;++j){
    			recMinCut[i][j] = -1; // not found.
    		}
    	}
    	char[] ca = s.toCharArray();
        int res = bestCut2(ca,0,len-1,Integer.MAX_VALUE);
        return res;
    }
    
    int hit= 0;
    
    
    
    
    /**
     * almost same function from bestCut,  ; this time , we don't save a path( combine of palindrome strings) , we only save a result
     * 
     * 
     * @param s
     * @param cutLimit : if the cut of this string is going to exceed cutLimit, we just return.
     * @return
     * 
     *  return -1 as not work.
     *  return 0 as this word is a palindrome
     *  return 1 as this word is made of two palindromes with one cut. 
     * 
     */
    private int bestCut2(char[] ca,int s,int e, int cutLimit){
    	System.out.println("  bestcut2:   "+s+" ~ "+e);
    	if(cutLimit<0){
        	return -1;
        }
    	if(recMinCut[s][e]>-1){
//            System.out.println(""+recMinCut[s][e]+ "       hit!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  "+hit+++"    ");
            int cut = recMinCut[s][e];
            if(cut>cutLimit){
            	// though this string have a cut saved, we don't need this , cause we must already have better one
            	return -1;
            }
            return cut;
        }
        // ofcz we try from NO cut.
        if(isPalindrome(ca,s,e)){
        	return 0;
        }
        
        if(cutLimit==0){
        	// but word is not a palindrome,
            return -1;
        }
        
        String head,tail;
        //!!!!!!!!!!!!!!!!!!!!! this loop will not contain string s itself.
        
        //!!!!! this limit is the request of the result, 
        // we can't init it here. cause we dont' have NO result yet.  
        // we will compare the result with the limit, if it exceed limit, it will giveup and set to 0.
        int bestTilNow = cutLimit;
        int bestKeep= 0;
        System.out.println(" s: "+s+" e :"+e);
        boolean gotRes = false;
        for(int i=e-1;i>=s;--i){
//        for(int i=1;i<s.length();++i){
            // we try to have more words in head first.
//            head = s.substring(0,i);
//            tail = s.substring(i,s.length());
//             System.out.println(" head : "+head+"  tail: "+tail);
            if(isPalindrome(ca,s,i)){
                int tailCutCount = bestCut2(ca,i+1,e,bestTilNow-1);
                System.out.println(" get res of :  s: "+(i+1)+"  e: "+e+"     : "+tailCutCount);
                if(tailCutCount>-1){
                	//find a cut. this tail works.
                	if(tailCutCount+1<bestTilNow){
                    	bestTilNow = tailCutCount+1;
                    	bestKeep = i;
                    	gotRes = true;
                    	System.out.println(" best cut for  s: "+s+"  e: "+e+"     : "+bestKeep);
                    }
                }
            }
        }
        if(!gotRes){
        	return -1;
        }
        
        
        if(bestKeep>0){
        	String h = String.valueOf(ca, s, bestKeep-s+1);
//        	System.out.println(" "+h+"("+h.length());
        	
        	if(h.equals("eye")||h.equals("yey")||h.equals("ee")||s==288||s==287){
        		System.out.println(" H : "+h+"      bestKeep,"+ bestKeep+"         s:"+s+" e:"+e);
        		for(int i=0;i<10;++i){
        			System.out.print(""+ca[s+i]);
        		}
        		System.out.println("");
        	}
        	
        	debug.add(0,h);
        }else{
        	
        }
        
        recMinCut[s][e] = bestTilNow;
        return bestTilNow;
    }
    
    /**
     * as all the words are sub string of same source, 
     * it's easier to record the palindrome-info of substring, and use them to help calc if it's a palindrome.
     * @param ca
     * @param s
     * @param e
     * @return
     */
    private boolean isPalindrome(char[] ca, int s, int e){
//    	System.out.println(" isPalindrome ? "+s+" ~ "+e);
    	if(ca==null){
    		return false;
    	}
    	if(e>=ca.length){
    		return false;
    	}
    	
    	if(isPalindrome[s][e]>0){
    		//have record.
    		return true;
    	}
        if(s==e){
        	return true;
        }
        
        boolean res;
        if(ca[s]==ca[e]){
        	if(e-s<3){
        		//1 or 2,  true
        		res  = true;
        	}else{
        		res = isPalindrome(ca,s+1,e-1);
        	}
        }else{
        	// head and tail don't match, return false;
        	res = false;
        }
        
        if(res){
        	isPalindrome[s][e] = 1;
        }else{
        	isPalindrome[s][e] = 0;
        }
        
//        for(int i=s;i<=e;++i){
//        	System.out.print(""+ca[i]);
//        }
//        System.out.println(" is pa ? : "+res);
        return res;
    }
    
    

    private HashMap<String,ArrayList<String>> record = new HashMap<String,ArrayList<String>>();
//  
  private HashMap<String,Integer> rec = new HashMap<String,Integer>();
  private HashSet<String> pSet = new HashSet<String>();
    /**
     *  if  this  string works for a palindrome partition( cut ), return the best cut( least cut)
     * 
     * 
     * para; cutLimit;  as we just wanna find the min cut.  we limit the cut chance 
     *   cutLimit = 0:  just chance to try itself 
     * maybe we can find one cut for this string , but if it's > cutlimit,  we return [];
     */
    private ArrayList<String> bestCut(String s,int cutLimit){
        ArrayList<String> res = new ArrayList<String>();
        if(s==null||s.length()==0){
            return res;
        }
        
        if(cutLimit<0){
        	return res;
        }
        
//        System.out.println(" bestcut:  "+s.length()+"  < "+cutLimit+"  "+s);
        
        if(record.containsKey(s)){
//            System.out.println(s+ "       hit!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  "+hit++);
            int size = record.get(s).size();
            if(size-1>cutLimit){
                // though this string have a cut saved, we don't need this , cause we must already have better one
                // return []
                return res;
            }
            return record.get(s);
        }
        
        // ofcz we try from NO cut.
        if(isPalindrome(s)){
//        	System.out.println(" self is palindrome , add : "+s);
            //best one find;
            res.add(s);
            return res;
        }
        
        if(cutLimit==0){
            return res;
        }
        
        String head,tail;
        //!!!!!!!!!!!!!!!!!!!!! this loop will not contain string s itself.
        
        int bestTilNow = cutLimit;
        for(int i=s.length()-1;i>0;--i){
//        for(int i=1;i<s.length();++i){
            // we try to have more words in head first.
            head = s.substring(0,i);
            tail = s.substring(i,s.length());
//             System.out.println(" head : "+head+"  tail: "+tail);
            if(isPalindrome(head)){
                ArrayList<String> tailRes = bestCut(tail,bestTilNow-1);
                int tsize = tailRes.size();
                if(tsize>0){
                	//find a cut.
                	if(tsize<bestTilNow){
                    	bestTilNow = tsize;
                        res.clear();
                        res.add(head);
                        for(String str:tailRes){
                            res.add(str);
                        }
                    }
                }
            }
        }
        record.put(s, res);
        return res;
    }
    
//    private HashMap<String,Integer> palindromeMap = new HashMap<String,Integer>();
    
    private boolean isPalindrome(String s){
    	
        if(s==null||s.length()==0){
            return false;
        }
        char[] ca= s.toCharArray();
        int j = ca.length-1;
        for( int i=0;i<j;++i){
            if(ca[i]==ca[j]){
                j--;
            }else{
                return false;
            }
        }
        return true;
    }
}