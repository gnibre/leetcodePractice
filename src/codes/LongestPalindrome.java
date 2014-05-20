package codes;



/****
 * 
 * holly *t, it's in the reading section... why we finished coding it...
 * http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 * 
 * @author Yubing
 * 
 * after reading:
 * 
 * 1:  insert # ; pound sign;      actually easy to under stand:  mid of string can now be indexed, (will not locate somewhere like 2.5 , 3.5)
 *    from the artical: (both palindromes of odd and even lengths are handled graciously)
 *
 */
public class LongestPalindrome {
    
	
	public void go(){
		
		String s = "abadfllkjdflakjl";
		
		s = "bb";
		
		
		String res = longestPalindrome(s);
		
		System.out.println("  s: "+s);
		System.out.println("  res: "+res);
		
		
	}
	
    /***
     * 
     * 
     * there exsits a o(n) algorithm. I shall recall it. or do deep thinking and find it out.
     * the key is to reuse cases we have meet properly and don't let any infomation leak.
     * 
     * 
     *  [       x       y  ]
     * 
     * 
     */
    public String longestPalindrome(String s) {
        if(s==null||s.length()<2){
            return s;
        }
        
        return ONSolution(s);
        
    }
    
    
    

    /***
     * of cause we can find o(n) solution. cause i've seen the answer before, 
     * i just need to recall it.....though i don't remember nothing'
     * 
     * 
     * 1, one scan can do this.
     * 2, so, what we can get is info from the already scanned chars.
     *       ==>s = a:b ,  longestPalindrome(s) can be get from :  longestPalindrome(a) info + scan b;
     *     specially, s = s1+ charA; 
     *     longestPalindrome(s) = longestPalindrome(s1) or take charA.
     *      and at the same time, if longestPalindrome(s) is of length lenS, and contains charA,    
     *      there must be a palindromes at the end of String s1, and with len: lenS-2;
     * 
     * 3 , so for each char c we scan, record: what's the longestPalindrome ends with this char.
     * 
     * 
     */
    public String ONSolution(String s){
        int len = s.length();
        //each position, record the length of the longest palindrome end at this position.
        int[] rec = new int[len]; 
        rec[0] = 1; //init.
        
        char[] ca = s.toCharArray();
        String res = s;
        int maxOfMax = 1;
        // length of longest palindrome of last char.
        int lastCharPLen;
        int lastFrom;
        for(int scan =1;scan<len;++scan){
            
            lastCharPLen = rec[scan-1];
            lastFrom = scan-lastCharPLen;
            
            if(lastFrom-1>-1){
                if(ca[lastFrom-1]==ca[scan]){
                    //it's a new palindrom to include this char.
                    // record it.
                    rec[scan] = lastCharPLen+2;
                }else{
                    // no, this is not... holy shit, so how to get this one.?
                    //         ------------kabccbaxabccbax, scan is at second 'x',  
                    // longest before x is  abccbaxabccba,  'x'!='k' the longest at x is xabccbax, but there's no record for 'abccba'
                    //                      
                    // what if we start search from first 'a'? what the compexity will be.
                    // if the scan position is always pushing ahead/ moving forward, the complexity will still be O(N);
                    //the answer is YES.  
                    // cause each time we scan and get a char, the lastFrom will be moved backward 1 position if char match.
                    // and the lastFrom will move foward at least 1 position if it fails.
                    // so during the sacn, the LastFrom char( equal to check time and  time complexity), will only move at most 2*N positions.
                    // so it's still O(N)
                    //glad that we can just find the position with a for loop. ( cause we are looking for palindrome end at scan. )
                    
                    int lenMaxMatch = maxMatchWithEndIncluded(ca,lastFrom,scan);
                    rec[scan]  = lenMaxMatch;
                }
            }else{
                // last one already reach the bound.
                // have to do search from the last one.
                int lenMaxMatch = maxMatchWithEndIncluded(ca,lastFrom,scan);
                    rec[scan]  = lenMaxMatch;
                
            }
            
            if(rec[scan]>maxOfMax){
            	maxOfMax = rec[scan];
            	System.out.println(" scan position is : "+scan+"   : "+ca[scan]);
            	System.out.println(" len max is : "+rec[scan]);
            	
            	System.out.println(" start from is  : "+(scan-rec[scan]+1));
            	
//            	from start to scan, len = scanposition - startposition + 1;
            	res = s.substring(scan-rec[scan]+1,scan+1);
            }
        }
        return res;
    }
    
    /**
     * return the lens of the max palindrome that MUST contain end.
     */
    private int maxMatchWithEndIncluded(char[] ca,int from,int end){
        for(int i = from;i<=end;++i){
            boolean match = true;
            if(ca[i]==ca[end]){
                int s = i;
                int e = end;
                while(s<e){
                    if(ca[s]==ca[e]){
                        s++;
                        e--;
                    }else{
                        match = false;
                        break;
                    }
                }
                if(match){
                    // the first one found is the longest one.
                    //position is i,
                    //len is 
                    return (end-i)+1;
                }
            }
        }
        return 1;
    }
    
    
    /**
     * one solution that easy to understand and easy to code.  take o(n^2) time, o(n) space,  n is the length of the string.
     * 
     * scan each position as the center of a palindrome,
     * 
     * positions we shall scan are  0.5, 1, 1.5, 2, til.... len-1,len-0.5 
     * position count: 2n
     * for each position, check the longest Palindrome substring start from that,   find that ,and keep the longest.
     * each position takes o(n) time at most.
     * 
     * total is o(n^2)
     * 
     * 
     * 
     */
    private String solutionOne(String s){
        
        //init value as palidrome of length 1, so we dont' have to check string of length 1 in the late case.
        String longest = s.substring(0,1);
        int max = 1;
        
        int len = s.length();
        char[] ca = s.toCharArray();
        //first scan, for palindrome substrings of lens odd numbers. (2x+1) , so the centers of palindromes are 0,1,2,3,4,5,..
        int rad; //radiance of palindrome. so len is rad*2+1
        for(int i=0;i<len;++i){
            
            int maxPossible = Math.min(2*i+1,2*(len-i-1)+1);
            if(maxPossible<max){
                // if best case of this position i is wrose than max exist. we can continue;
                continue;
            }
            
            rad = 1;
            boolean keepChecking = true;
            while(keepChecking){
                // try is this a palindrome.
                if(i-rad<0||i+rad>len-1){
                    // exceed size of array, break.
                    break;
                }
                if(ca[i-rad]==ca[i+rad]){
                    rad++;
                    //true;
                }else{
                    keepChecking = false;
                    // and rad is the longest it can do.
                }
            }
            // result of from this position as center
            int longestThisPosition = 2*(rad-1)+1;
            if(longestThisPosition>max){
                max  = longestThisPosition;
                longest = s.substring(i-rad+1,i+rad);
            }
        }
        
        System.out.println(" longets: "+longest);
        
        /// second scan, for palindromes that of even length, like "AA" or "DDDD". center of palindrome is between positions.
        
        for(int centerToRightOf=0;centerToRightOf<len;++centerToRightOf){
            int maxPossible = Math.min(2*(centerToRightOf+1), 2*(len-centerToRightOf-1));
            System.out.println("    centerToRightOf : "+centerToRightOf);
            if(maxPossible<max){
            	System.out.println(" max possible: "+maxPossible+"     giveup on:   "+centerToRightOf);
                // if best case of this position i is wrose than max exist. we can continue;
                continue;
            }
            
            rad = 1;
            boolean keepChecking = true;
            while(keepChecking){
                //left is i-rad+1;
                //right is i+rad;
                // for rad =2,  i = 1 it's [0,1(i),(center)2,3]; 
                
                int left = centerToRightOf-rad+1;
                int right = centerToRightOf+rad;
                
                
                System.out.println("    left : "+left+"    right : "+right);
                
                
                if(left<0||right>len-1){
                    //exceed size of array.
                    break;
                }
                if(ca[left]==ca[right]){
                    rad++;
                    //true;
                }else{
                    keepChecking = false;
                    // and rad is the longest it can do.
                }
            }
            // result of from this position as center
            // left is i-rad+2; right is i+rad-1;  
            int longestThisPosition = 2*(rad-1);
            if(longestThisPosition>max){
                max  = longestThisPosition;
                longest = s.substring(centerToRightOf-rad+2,centerToRightOf+rad);
            }
        }
        return longest;
        
    }
}
