package codes;

public class WildCard {
	
	public void go(){
		//go test 
		
		String s;
		String p;
		
//		String s = "abc*?kjk*adk*????*akdp*bbbc";
//		String p = "abcdkakjkadkdkeiakdkdkakdpebbbc";
		
		s= "aa";p="a";
		s= "aa";p="aa";
		s= "aaa";p="aa";
		s= "aa";p="*";
		s= "ab";p="?";
		s= "aab";p="c*a*b";
		s= "";p="a";
		s= "abbbb";p="?*b**";
		s= "aaba";p="***";
		s= "bbaaaa";p="a**b";
		s= "ab";p="ab*";
		s= "";p="*";
		s= "a";p="a*";
		s= "hi";p="*?";
		s= "b";p="?*?";
		s= "abc";p="abc*defghijk";
		s= "aaaabbaa";p="*b*a*aa";
		
		boolean res = isMatch(s,p);
				System.out.println(s+"   ?   "+p+"        :"+res);
	}
	
	
    public boolean isMatch(String s, String p) {
        if(isEmptyString(s)){
        	if(isEmptyString(p)){
        		return true;
        	}else{
        		p = p.replaceAll("\\*", "");
        		if(isEmptyString(p)){
        			return true;
        		}
        		return false;
            }
        }
        if(isEmptyString(p)){
        	s = s.replaceAll("\\*", "");
    		if(isEmptyString(s)){
    			return true;
    		}
            return false;
        }
        
        int indexs = s.indexOf("*");
        int indexp = p.indexOf("*");
        
        if(indexs<0&&indexp<0){
            return isMatchPlainCompare(s,p);
        }
        
        // make sure s and p are not null. we can accept "", but not null
        return isMatchWithStar(s,p);
    }
    
    private boolean isEmptyString(String s){
    	if(s==null||s.length()==0){
    		return true;
    	}
    	return false;
    }
    
    private boolean isMatchPlainCompare(String s,String p){
    	//		System.out.println(" isMatchPlainCompare? ");
    	//		System.out.println(s);
    	//		System.out.println(p);
        if(s==null){
            return p==null;
        }
        if(p==null){
            return false;
        }
        if(s.length()!=p.length()){
                return false;
        }
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        for(int i=0;i<cs.length;++i){
            if(cs[i]=='?'||cp[i]=='?') continue;
            if(cs[i]==cp[i]) continue;
            return false;
        }
        return true;
    }
    
    /**
     *found star in at least on string.  we find the position of the star, cut the head and tail with plain compare,
     * 
     */
    private boolean isMatchWithStar(String s,String p){
//    			System.out.println("isMatchWithStar");
//    			System.out.println(s);
//    			System.out.println(p);
        int indexsf = s.indexOf('*');
        int indexpf = p.indexOf('*');
//        		System.out.println(" sf and pf : "+indexsf+"   "+indexpf);
        int indexsl = s.lastIndexOf('*');
        int indexpl = p.lastIndexOf('*');
        
        int countBeforeStar= 0;
        
        int headPosition=-1;
        int tailLengthFromEnd=-1;
        if(indexsf>-1||indexpf>-1){
            if(indexsf==-1){
                headPosition = indexpf;
            }else if(indexpf==-1){
                headPosition=indexsf;
            }else{
                headPosition = indexsf<indexpf?indexsf:indexpf;
            }
        }
        
        
        if(s.length()<headPosition){
        	return false;//length problem
        }
        if(p.length()<headPosition){
        	return false;//length problem
        }
        // find head done. cut head.
        String sHead = s.substring(0,headPosition);
        String pHead = p.substring(0,headPosition);
        boolean headMatch = isMatchPlainCompare(sHead,pHead);
        if(!headMatch){
            return false; //head not match, string not match
        }
        
        		System.out.println("len"+s.length()+"  index,s,l  "+indexsl);
        
        // check if do have tail,
        //!!!!!!!!!!!!!!!!!!!!!!!!!!1 head and tail can be divided by only one "star", think of case "abc*kkde" and "jdbcworks", head is 3, tail is 4,devided by one star
        if(indexsl>=headPosition){
            
        	tailLengthFromEnd = s.length()-1-indexsl;//candidate
        }
        System.out.println("tailLengthFromEnd: "+tailLengthFromEnd);
        
        System.out.println("indexpl "+indexpl);
        System.out.println("headPosition "+headPosition);
        
        if(indexpl>=headPosition){
            int pFromEnd = p.length()-1-indexpl;
            if(pFromEnd>-1){
            	if(tailLengthFromEnd==-1){
            		tailLengthFromEnd = pFromEnd;
            	}
                if(tailLengthFromEnd>pFromEnd){
                	tailLengthFromEnd = pFromEnd; //pick smaller one, more close to the end.
                }
            }
        }
        		System.out.println("tailLengthFromEnd: "+tailLengthFromEnd);
		if(headPosition+tailLengthFromEnd>s.length()||headPosition+tailLengthFromEnd>p.length()){
        	return false;
        }
		
		
        		
        if(tailLengthFromEnd>0){
            //cut tail.
            // find tail done, cut tail.
        	
        	if(tailLengthFromEnd>s.length()||tailLengthFromEnd>p.length()){
        		return false;
        	}
        	
            String sTail = s.substring(s.length()-tailLengthFromEnd);
            String pTail = p.substring(p.length()-tailLengthFromEnd);
            boolean tailMatch = isMatchPlainCompare(sTail,pTail);
            if(!tailMatch){
                return false; //tail not match, string not match
            }
        }else{
            // don't need to cut tail.
        	tailLengthFromEnd = 0;
        }
        //		System.out.println("tailLengthFromEnd: "+tailLengthFromEnd);
        // see what's between head and tail
        
        
        
        String sBetween = s.substring(headPosition,s.length()-tailLengthFromEnd);
        String pBetween = p.substring(headPosition,p.length()-tailLengthFromEnd);
        //accept "" but not null
        return isMatchStartAndEndWithStar(sBetween,pBetween);
    }
    
    /**
     * string s/p starts/ends with * 
     *  s or p or both start with star.
     *  s or p or both ends with star.
     *  or s="*" or p="*" 
     * 
     * 
     * if start star at s and end star at p ,auto win, match
     * if start star at p and end star at s, auto win, same as above
     * 
     * if both start star and end star at s,  and we have a star at anywhere in p.  win, match.
     * same if both start and end star at p and a star at s;
     * 
     * 
     * only thing we have to code is : start star at s, end star at s, maybe star inside s, and NO star at p.
     */
    private boolean isMatchStartAndEndWithStar(String s,String p){
        		System.out.println("isMatchStartAndEndWithStar : ");
    			System.out.println(s);
    			System.out.println(p);
    	
    	boolean ss = false,se = false,ps = false,pe = false;
    	
    	if(s!=null&&s.length()>0){
    		ss = (s.charAt(0)=='*');// string s starts with *
    		se = (s.charAt(s.length()-1)=='*');// string s ends with *
    	}
    	if(p!=null&&p.length()>0){
    		ps = (p.charAt(0)=='*');// string p starts with *
    		pe = (p.charAt(p.length()-1)=='*');// string p ends with *
    	}
        
        if(ss&&pe){
            return true;
        }
        if(se&&ps){
            return true;
        }
        
        if(ss&&se){
            if(p.indexOf('*')>0){
                //contains a '*';
                return true;
            }
            return isMatchGreedyCompare(s,p);
        }
        
        if(ps&&pe){
            if(s.indexOf('*')>0){
                //contains a '*';
                return true;
            }
            return isMatchGreedyCompare(p,s);
        }
        // if code came here, my code sucks..
        return false;
        
    }
    
    
    /**
     *we garantee when this function is called, 
     * 1 string "sWithStar" starts with '*', ends with '*', maybe have star inside string
     * 2 string "pWithoutStar" with no star in this string.
     * 
     * s  :      *-----*--*--------*
     * p :       adfjklajdlfkjalkjflakjlfjaldjflaoiewjao
     * 
     * algorithm: if we find each char not * from s also in string p, we keep scanning till s ends,
     * if its does find all the match in p, it's a match, else, no match.
     * for char '?', add index with one.
     */
    private boolean isMatchGreedyCompare(String sWithStar,String pWithoutStar){
//    			System.out.println("isMatchGreedyCompare : ");
//    			System.out.println(sWithStar);
//    			System.out.println(pWithoutStar);
//    	sWithStar = sWithStar.substring(1,sWithStar.length()-1);
//    	System.out.println(sWithStar);
        String[] splits = sWithStar.split("\\*"); // !!!!!!!!!!!!!! split function, can't be used to string   *abc*, 
        										// !!!!!!!!!!!!!!   error PatternSyntaxException: Dangling meta character '*' near index 0 ,  
        										// !!!!!!! cause string like "*" and "?", have special meaning, we have to use "\\?" or "\\*" to get the char it self
        int index = 0;
        int commonCharCount =0;
        boolean found = false;
        String p = pWithoutStar;
        for(String s:splits){
        			System.out.println("search string: "+s);
        	if(s==null||s.length()==0){
        		// for the 
        		continue;
        	}
            //s: each string to find a match in pWithoutStar
            commonCharCount = 0;
            index = 0;
            while(commonCharCount<s.length()&&s.charAt(commonCharCount)=='?'){
                commonCharCount++;
            }
            //		System.out.println("commonCharCount : "+commonCharCount);
            if(p.length()<s.length()){
            	return false;//length not match.
            }
            s = s.substring(commonCharCount); //discard the heading '?' chars.
            p = p.substring(commonCharCount); //discarding anyheading char, just like it's a match.
            
            //		System.out.println("search string-: "+s);
            if(s==null||s.length()==0){
                //this one matchs, go on matching.
            	
                continue;
            }
            
            		System.out.println("p string: "+p);
            
            char fc = s.charAt(0);
            		System.out.println("fc: "+fc);
            int charPositionMatch = p.indexOf(fc,index);//find char fc in string p, from position index.
            found = false;
            while(charPositionMatch>-1){
            	found = true;
            			System.out.println("pos : "+charPositionMatch);
            	//		System.out.println("same in p?  "+p.substring(charPositionMatch));
                //if we find a match, we continue searching, if this while loop breaks, no match.
                if(p.length()<s.length()+charPositionMatch){
                	//		System.out.println(" length error");
                    return false; //length can't match , and no chance for further searching.
                }
                for(int i=0;i<s.length();++i){
                    if(s.charAt(i)=='?') continue;
                    if(p.charAt(i+charPositionMatch)=='?') continue;
                    if(p.charAt(i+charPositionMatch)==s.charAt(i)){
                        continue;
                    }else{
                        found = false;
                        //		System.out.println("not same:  s:  "+ s.charAt(i));
                        //		System.out.println("not same:  p:  "+ p.charAt(i+charPositionMatch));
                        break;
                    }
                }
                		System.out.println("res : "+found+"    charPositionMatch--- "+charPositionMatch);
                //		System.out.println("p string: "+p);
                if(found){
                    p = p.substring(charPositionMatch+s.length()); // heading matched with string s
                    		System.out.println("p left string after one win: "+p);
                    //break to next string search
                    break;
                }else{
                    // this match fails, try next.
                    index+=1;
                    //		System.out.println("index : "+index);
                    charPositionMatch = p.indexOf(fc,index); //try start from another position.
                }
            }
            
            if(!found){
                return false;
            }
            //else found, go next s.
        }
        return true;
    }
    
    
    
    /**
     * 
     * 
     * star: strings after star  of String A, can match any substring of string B, it's a match.

case 1
A= *-------
B= asdlfadf*
,
match.

case 2
A= *--*-   *--*
B= ad*slfj*al


if star at each side, it's a match,
only if two stars at the same side, have to check substring.
	if more stars inside string A. o shit, it's a match, becase patern  *-  matchs  -*, so  *-----* matchs    ~~~*~~~~



so the only case left is A= *~~~~~**~~*, B=lkajfdljaldkjf;

is there still a greedy strategy? I think yes, ALWAYS USE FIRST INDEX.FOR STRINGS IN A, FIND THEM IN B.
     * 
     * 
     * 
     */
}