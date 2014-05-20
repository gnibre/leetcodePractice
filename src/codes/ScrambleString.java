package codes;

import java.util.Arrays;


public class ScrambleString {
	
	
	
	
	
	
	
    public void go(){
    	String s1 = "xstjzkfpkggnhjzkpfjoguxvkbuopi";
    	String s2 = "xbouipkvxugojfpkzjhnggkpfkzjts";
    	boolean res = isScramble(s1,s2);
    	System.out.println(" res is : "+res);
    }
    
	
	
	
	
    /**
     * 
     * what's the variety of "scramble" string? no '
     * 
     *      abc
     *    ab,c    a,bc
     *   abc     acb
     *   bac 
     * 
     * can't have  cba or cab or bca, cause the substring shall be non-empty;
     *
     *                                  abcd
     *      ab,cd         abc,d          a, bcd 
     *  abcd             bac,d     a,bdc
     *  abdc                       a,cbd
     *  bacd                acb,d     
     *  badc 
     *  no much;
     *                                                 from abcd
     *  if we want         cabd; can we get that? what we want is c**d;  we have to include c for the left half abc,d so this can be done;
     *  one choice is greedy, to find the boudary;
     *   
     *  do we get every one after we make sure the boundary two?
     * 
     *  how about axxd? can;
     *  how about dxxa?  no can't swap d to first ;
     * how about dxxx? no can't swap d'
     * 
     * so all the cant's are :  d*** or ***a;  for the len =4 case; 
     * 
     * 
     * if A can't => B ;  B can't => A; 
     * 
     * can we prove that a string  "a*****b", the only can't reach by scramble is "b*****" and "******a"
     * 
     *=================================================== wrong understanding before this;/
     *       for abc , we can get everything; 
     *     ab,c c,ab  a,bc bc,a
     *     ba,c c,ba, a,cb cb,a
     * 
     *  for abcd, we can get
     * abc,d      ab,cd       a, bcd
     * d,abc      ba,dc       bcd,a
     *            cd,ab
     *  12          8          12 
     *  totally  32? ; remove duplicates;  we get everything starts with a and d;  a*** ,d***
     *  badc bcda bcad
     *  bacd bdca    no (bdac)
     *  cdab cbda  cabd
     *  cdba cbad  no (cadb)
     *  
     *  so the idea is that, each cut   make a string  s like [a,b,c|| d] , it can't be back;  the result is (abc)||d or d||(abc)
     * 
     */
    public boolean isScramble(String s1, String s2) {
        if(s1==null||s2==null) return false;
        int len = s1.length();
        //!!!!!!!!!!! len = 1; no tail no head; im myself
        if(len==1){
            if(s1.equals(s2)){
                return true;
            }else{
                return false;
            }
        }
        

        
        if(s2.length()!=len){
            return false;
        }
        
        char[] ca = s1.toCharArray();
        char[] ca2 = s2.toCharArray();
        
        if(!sameCharSet(ca,ca2)){
            return false;
        }
        if(s1.equals(s2)){
            return true;
        }
        
        StringBuilder sb = new StringBuilder(s1);
        if(sb.reverse().toString().equals(s2)){
        	return true;
        }
        
        
      //!!!!!!!!!!!oshit, just missed len-1 and will get TLE
        for(int i=1;i<len;++i){
        	
        	if(isScramble(s1.substring(0,i),s2.substring(0,i)) && 
        			isScramble(s1.substring(i),s2.substring(i)) ){
        		return true;
        	}
        	
        	if(isScramble(s1.substring(0,i),s2.substring(len-i)) && 
        			isScramble(s1.substring(i),s2.substring(0,len-i)) ){
        		return true;
        	}
        }

        return false;
        
    }
    

    
    
    // don't need to checkfor char pool;
    private boolean isScrambleAux(String s1,String s2){
        
       
        
        
        char[] ca = s1.toCharArray();
        char[] ca2 = s2.toCharArray();
        String left,right;
        String left2,right2;
        boolean match = false;
        int res;
        
        int len = s1.length();
        for(int i=1;i<len-1;++i){
            // [0,i)  [i,len)
            
            //focus the shorter array;
            if(i<len/2){
                res = sameCharSet(ca,0,i-1,ca2);
            }else{
                res = sameCharSet(ca,i,len-1,ca2);         // 10-3 return 1 , means 1 same as top3;  2 means from back; 10 same from bot;
                res = 3-res;
            }
            
            if(res==1){
                left = s1.substring(0,i);
                right = s1.substring(i);
                
                left2 = s2.substring(0,i);
                right2 = s2.substring(i);
                
                match = (isScrambleAux(left,left2)&&isScrambleAux(right,right2));
                if(match) return true;
            }else if(res==2){
                left = s1.substring(0,i);
                right = s1.substring(i);
                
                left2 = s2.substring(0,len-i);
                right2 = s2.substring(len-i);
                
                match = (isScrambleAux(left,right2)&&isScrambleAux(right,left2));
                if(match) return true;
            }
        }
        
        //tried every cut still not return true;
        return false;
        
    }
    
    
    
    
    
    /**
     * will sort the array 
     */
    private boolean sameCharSet(char[] ca,char[] ca2){
        int len = ca.length;
        Arrays.sort(ca);
        Arrays.sort(ca2);
        for(int i=0;i<len;++i){
            if(ca[i]!=ca2[i])
                return false; //different char set;
        }
        return true;
    }
    
    
    // if match from front, return 1, if match from end , return 2; other wise return -1;
    private int sameCharSet(char[] ca,int s,int e,char[] ca2){
        
        int len = e-s+1;
        
        char[] c1 = new char[len];
        for(int i=0;i<len;++i){
            c1[i] = ca[i+s];
        }
        
        char[] c2 = new char[len];
        for(int i=0;i<len;++i){
            c2[i] = ca2[i];
        }
        
        if(sameCharSet(c1,c2)) return 1;
        
        int end = ca2.length-1;
        int start = end-e+s;
        for(int i=0;i<len;++i){
            c2[i] = ca2[i+start];
        }
        
        if(sameCharSet(c1,c2)) return 2;
        
        return -1;
    }
    
    
    
}