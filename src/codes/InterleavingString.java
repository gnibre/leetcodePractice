package codes;

public class InterleavingString {
    
    public void go(){
    	
    	String s1,s2,s3;
    	
    	s1="abbbbbbcabbacaacccababaabcccabcacbcaabbbacccaaaaaababbbacbb";
    	s2="ccaacabbacaccacababbbbabbcacccacccccaabaababacbbacabbbbabc";
    	s3="cacbabbacbbbabcbaacbbaccacaacaacccabababbbababcccbabcabbaccabcccacccaabbcbcaccccaaaaabaaaaababbbbacbbabacbbacabbbbabc";
    	s1="aa";
    	s2="ab";
    	s3="aaba";
    	
    	
    	long t1 = System.currentTimeMillis();
    	boolean res = isInterleave(s1,s2,s3);
    	long t2 = System.currentTimeMillis();
    	System.out.println(" is interleave? : "+res+"  time: "+(t2-t1));
    }
	
	int cind = 0;
    
    /**
     * only thing that is hard:  when string left of s1 and s2 , with same heading, say s1-left="aaaadbcad"; s2-left="aaakkc"
     * 
     * and s3 = aaaaak,  we can't decide which 'a' to pick when we see 5 'a's from s3. til when we see 'k', we know 3 'a' from s2, 2'a' from s1.
     * 
     * so if charAtHead(s1) = charAtHead(s2), we have to get the next special char from the s3. 
     * if holly shit, this char still have this condition, like   s1 = "aaakkcccx" s2 = "aakkkkkkccd"
     * we may have to do ? dynamic programing...
     * 
     * !!!!!!!!!!!!!!! TLE 
     * when we find  s1 and s2 with same heading, if we just split into two case  (s1-1,s2,s3-1) && (s1,s2-1,s3-1), easy to program, but there are some nodes that with 
     * same condition are created many times.. and oj of leetcode just returned TLE.
     * 
     * for example
     * s1 = "aaab"
     * s2 =" aac"
     * s3 = "aaaacab"
     * 
     *  we may try 1 {aab,aac,aaacab} 2 {aaab,ac,aaacab}
     *             11{ab,aac,aacab} 12{aab,ac,aacab}
     *             21{aaab,c,aacab} 22{aab,ac,aacab}
     *             
     *             111{b,aac,acab}, 112{ ab,ac,acab},      121{ab,ac,aacab} = 112,   122{aab,c,acab} 
     *             211{aab,c,acab}, NO 212 ,      221{ab,ac,acab}=112   222{aab,c,acab}=122
     *  so here, case 12 and case 22 is actually same case, and later 121 and 112, 
     *  and even worse this if we don't be smart, we can see dead end:  212
     * 
     * 
     */    
    public boolean isInterleave(String s1, String s2, String s3) {
    	System.out.println(" isInterleave , ind: "+cind+++"    size: "+s3.length());
//    	System.out.println("  s1 :"+s1);
//    	System.out.println("  s2 :"+s2);
//    	System.out.println("  s3 :"+s3);
        if(isEmptyString(s1)){
            if(isEmptyString(s2)){
                return isEmptyString(s3);
            }else{
                return s2.equals(s3);
            }
        }else{
            if(isEmptyString(s2)){
                return s1.equals(s3);
            }
        }
        
        // normal case, not null. so at least we can get length
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if(l3!=l1+l2){
            return false;
        }
        
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);
        char c3 = s3.charAt(0);
        
        
        /// example ,  s1 = "aabb" , s2 ="aaakd"  s3="aaabakdb" 
        //   next not 'a' char of s1,s2,s3 is 'b','k','b';  so we know next 'b' is from same 'b' of s1,  
        // so the next case to check is s1 cut 'aab', s3 cut 'aaab', s2 cut'a', =>  {"b","aakd","akdb"}
        
        //  another example,   s1 = "aacc" s2="aaacc" s3="aaaacaaccc"
        // we still have to keep two condition, but much easier: case1 'c' from s1 => { "c","acc", "aacc"}
        //														case 2 'c' from s2 => {"acc","c",""aacc"}	
        
        // hard problem coming.
        if(c1==c2){
        	if(c3!=c1){
        		return false;
        	}
        	
        	
        	//1 find how many are there.
        	
        	int nextDiffChar1 = nextDifferentCharIndex(s1,c1);
        	int samec1Count = nextDiffChar1;
        	char nextCharC1 = c1;
        	if(nextDiffChar1<s1.length()){
        		nextCharC1 = s1.charAt(nextDiffChar1);
        	}
        	
        	int nextDiffChar2 = nextDifferentCharIndex(s2,c2);
        	int samec2Count = nextDiffChar2;
        	char nextCharC2 = c2;
        	if(nextDiffChar2<s2.length()){
        		nextCharC2 = s2.charAt(nextDiffChar2);
        	}
        	
        	int nextDiffChar3 = nextDifferentCharIndex(s3,c3);
        	int samec3Count = nextDiffChar3;
        	char nextCharC3 = c3;
        	if(nextDiffChar3<s3.length()){
        		nextCharC3 = s3.charAt(nextDiffChar3);
        	}
        	
        	
        	
        	if(nextCharC3==c3){
        		//fun,  s3 is all of same char: c3;
        		if(samec3Count==samec1Count+samec2Count){
        			return true;
        		}else{
        			return false;
        		}
        	}
        	
        	// nextCharC3 is not c3,{ not c1 c2}
        	boolean dpCase1 = false;
        	// next3 = next1,  one choice, get c1 till this next char, get c2 of the rest.
        	if(nextCharC3==nextCharC1){
        		// next char from s1, 
        		// same count c1 all used.
        		
        		int s1cutcount = samec1Count+1; // cut all the same c1, and one different char (next char)
        		int s3cutcount = samec3Count+1;  // cut same char c1(=c2=c3), and one different char( nextchar)
        		int s2cutcount = samec3Count-samec1Count;
        		
        		if(s2cutcount<=samec2Count&&s2cutcount>-1){
        			//things cut from s2 are all same char: c2;   we make sure there's enough there.
        			dpCase1 = isInterleave(s1.substring(s1cutcount),s2.substring(s2cutcount),s3.substring(s3cutcount));
        		}
        	}
        	
        	// same for s2, if next c2 = next s3.
        	
        	boolean dpCase2 = false;
        	// next3 = next1,  one choice, get c1 till this next char, get c2 of the rest.
        	if(nextCharC3==nextCharC2){
        		// next char from s1, 
        		// same count c1 all used.
        		
        		int s2cutcount = samec2Count+1; // cut all the same c2, and one different char (next char)
        		int s3cutcount = samec3Count+1;  // cut same char c1(=c2=c3), and one different char( nextchar)
        		int s1cutcount = samec3Count-samec2Count;
        		
        		if(s1cutcount<=samec1Count&&s1cutcount>-1){
        			//things cut from s2 are all same char: c2;   we make sure there's enough there.
        			dpCase2 = isInterleave(s1.substring(s1cutcount),s2.substring(s2cutcount),s3.substring(s3cutcount));
        		}else{
        		}
        	}
        	return dpCase1||dpCase2;
        }else{
        	// s1 and s2 have different heading char, easy to do.
            String s3left = s3.substring(1);
            if(c1==c3){
                return isInterleave(s1.substring(1),s2,s3left) ; // cut one char from s1 and s3.
            }
            if(c2==c3){
                return isInterleave(s1, s2.substring(1),s3left) ; // cut one char from s2 and s3.
            }
        	return false;
        }
    }
    
    private int nextDifferentCharIndex(String s1,char c1){
    	int ind = 1;
    	while(ind<s1.length()&&s1.charAt(ind)==c1){
    		ind++;
    	}
    	return ind;
    }
    
    private boolean isEmptyString(String s ){
    	if(s==null) return true;
    	if(s.length()==0) return true;
    	return false;
    }
}