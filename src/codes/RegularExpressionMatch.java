package codes;

import java.util.ArrayList;

public class RegularExpressionMatch {
	
	public void go(){
		//go test 
		
		String s;
		String p;
		
//		String s = "abc*.kjk*adk*....*akdp*bbbc";
//		String p = "abcdkakjkadkdkeiakdkdkakdpebbbc";
		
		s= "aa";p="a";
		s= "aa";p="aa";
		s= "aaa";p="aa";
		s= "aa";p="*";
		s= "ab";p=".";
		s= "aab";p="c*a*b";
		s= "";p="a";
		s= "abbbb";p=".*b**";
		s= "aaba";p="***";
		s= "bbaaaa";p="a**b";
		s= "ab";p="ab*";
		s= "";p="*";
		s= "a";p="a*";
		s= "hi";p="*.";
		s= "b";p=".*.";
		s= "abc";p="abc*defghijk";
		s= "aaaabbaa";p="*b*a*aa";
		
		boolean res = isMatch(s,p);
				System.out.println(s+"   ?   "+p+"        :"+res);
	}
	    
	    /**
	     * almost the same one as the WILDCARD match??
	     * 
	     * i paste code from wildcard match to here.... replace ? to .
	     * 
	     * NO, this is not wild card.  though . is the same as ? ,  * in regular express is not the same as * in wild card.
	     * 
	     * 
	     * 
	     * if only p can have stars and . , it's quite easy, we can go one scan on two strings with a cursor moving from head to end.
	     * 
	     * 
	     * easy cases first,  only p can have stars
	     * e.x.   s = "abcdefghhhiijijllll" p="abcd*e*f*g*h*i*j.j*l*" what will happen here
	     * 
	     * use p to compare all the chars in s. cause p contains * 
	     * 1, plain compre and remove same heading chars. we got :   s = "defghhhiijijllll" p="d*e*f*g*h*i*.i*j*l*"
	     * 2 ,  for s ="d---" p="d*----", shall we make d* in p = d? not yet, maybe p = "d*d" , better save it till next solid came.
	     * 
	     *  next solid of s is "e", and next solid of p is "."
	     * 
	     *   so we got 2 subcases:
	     * 1 d* as d,      * s ="efghhhiijijllll" p ="e*f*g*h*i*j.j*l*" 
	     * 2 d* as nothing,'.' as d  p ="i*j*l*" s= "efghhhiijijllll"
	     * either of two cases is a match, it's a match;
	     * dp; goes on.
	     * 
	     * 
	     * ===============================================================
	     * if both string can have "*" and "." notation.
	     * 
	     * for case  s1:  ab*c*d*e*.f*ghi
	     *           s2:  aed*e*k*f*ghi*, match? 
	     * 
	     *   star pool s1 ( b,c,d,e)with order.  next solid char is : .
	     *  star pool s2 ( d,e,k,f, ) with order    next solid  char is g
	     * 
	     * . case is kinda suck,  "." can match any in the star pool, or match next solid,
	     * case1, . of s1 , to match solid g, we got   s1  pool(bcdef),ghi  s2: (dekf) h . next solid not match , 
	     *          and not in any of the star pool  , fail
	     * case2,  . of s1, to match any of star pool of s2,  got  s1 pool(bcdef)ghi   s2(dekf)gh(i) got
	     *          a g for g, h for h, and pool i for i.
	     * works.
	     * 
	     * save the pools, and focus next solid char.
	     */ 
	     
	     // this implementation is for case only p can have "*" only.
	    public boolean isMatch(String s, String p) {
	        if(p==null||p.length()==0){
	            if(s.length()==0) return true;
	            return false;
	        }
	        return isMatch(s,p,new ArrayList<Character>());
	        
	    }
	    
	    private boolean isMatch(String s, String p, ArrayList<Character> starPool) {
	        //remove heading *s, so the next char is solid.
	        p = getStarPool(p,starPool);
	        if(p==null){
	            return false;
	        }
	        
	        if(s.length()==0){
	            if(p.length()==0){
	                // p lenth is 0 means everything in p is in star pool, make them match on char, and p matchs "";
	                return true;
	            }else{
	                // p is not empty, matchs at least one char, so empty s cna't match p;
	                return false;
	            }
	        }
	        //solid char from s, guaranteed by the assumption that s don't have "*"s
	        char headS = s.charAt(0);
	        if(headS=='*'){
	            //wrong assumption.
	            return false;
	        }
	        
	        boolean isHeadSInThePool = false;
	        for(char c:starPool){
	        	// .* maybe in the pool too!!!!!
	            if(c==headS||c=='.'){
	                isHeadSInThePool = true;
	                break;
	            }
	        }
	        
	        
	        boolean case1 =false;
	        // case 1, headS in the star pool of p, match it with the one in the star pool.
	        if(isHeadSInThePool){
	            // so after this match,  other chars before this headS in star pool , will auto match ""
	            // for example, s="akke", headS ='a',  starPool of p is {b*c*k*a*e*k*}
	            // after this match,  s="kke", starPool of p is {a*e*k*}, that b*c*k* in pool before a is gone.
	            int size = starPool.size();
	            for(int i=0;i<size;++i){
	                if(starPool.get(0)!=headS&&starPool.get(0)!='.'){
	                    starPool.remove(0);
	                }else{
	                    break;
	                }
	            }
	            case1 = isMatch(s.substring(1),p,starPool);
	        }
	        
	        // case 2 , don't care if headS in the starPool of p, we just ignore the pool.
	        boolean case2 = false;
	        ArrayList<Character> newPool= new ArrayList<Character>();
	        
	        if(p.length()>0){
	            char headP = p.charAt(0);
	            if(headP=='*'){
	                // wrong input expression.
	                case2 = false;
	            }else if(headS==headP){
	                //cool, skip all the pool and go one, you two are a pair.
	                case2 = isMatch(s.substring(1),p.substring(1),newPool);
	            }
	        }
	        return case1||case2;
	    }
	    
	    /***
	     * input will not be null. don't need to check.'
	     */
	    private String getStarPool(String p,ArrayList<Character> pool){
	        if(p.length()<2){
	            return p;
	        }
	        char c0 = p.charAt(0);
	        if(c0=='*'){
	            return null; /// null for wrong input!! we don't wanna continue.
	        }
	        char c1 = p.charAt(1);
	        if(c1=='*'){
	            //find *, add to star pool,
	            pool.add(c0); 
	            p = p.substring(2);
	            return getStarPool(p,pool); //continue doing this.
	        }
	        // don't need to change. heading solid.
	        return p;
	    }
	
    
}