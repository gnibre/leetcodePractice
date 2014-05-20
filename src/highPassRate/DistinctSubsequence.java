package highPassRate;

public class DistinctSubsequence {
    
    /**
     * the only hard part is, the problem is long and not easy to understand...
     * 
     * 1, we can understand what subsequence is;
     * 
     * 2,  in this one; we are given a string S ;  
     * 
     * AND the give T is one of the subsequence of S;
     * 
     * and S may have many ways to get THIS subsequence T; 
     * count the number of different/distinct T;
     * 
     * ; so it's a DP
     * ; to make dp smooth, we'd better save result;    how to hash two string to a value? use index, cause they are all substrings;
     * 
     */ 
    public int numDistinct(String S, String T) {
        
        
        boolean nospace = true;
        
        if(nospace){
            return ONSpaceDPNumDistinct(S,T);
        }
        
        char[] cs = S.toCharArray();
        char[] ct = T.toCharArray();
        
        //dp results saved;
        int[][] res = new int[cs.length][ct.length];
        for(int i=0;i<cs.length;++i){
            for(int j=0;j<ct.length;++j){
                res[i][j] = -1;
            }
        }
        return dpNumDistinct(cs,0,ct,0,res);
    }
    
    
    
    /**
     *as basic knowledge of dp, if we do it from top down, it's a recursive and may use o(n^2) space to save result;
     * but if we build it up from base cases, we can use O(n) space to get this done;
     *  build it up; 
     * 
     * calc all the (x,0) cases first; use that to get (x,1) ; til we got (m,n) at last
     * good thing is that we don't need recursive function call
     * 
     * easy idea; but not easy for the boudnary condition; shall have clear idea of matrix building and matrix boundary meaning;
     * 
     */ 
    private int ONSpaceDPNumDistinct(String S,String T){
        
        char[] cs = S.toCharArray();
        char[] ct = T.toCharArray();
        
        int ls = cs.length;
        int lt = ct.length;
        
        int[] res=  new int[cs.length+1];
        for(int i=0;i<res.length;++i){
            res[i] = 0;
        }
        
        //define f(x,y) : last x element in s; last y elements in T; value is num distinct;
        //when y=0; done state; special for everything came here, it's a yes; but with value 0;
        // when y=1, each time find a match S[x] == T[tLen-1] , f(x,1) =f(x-1,0)+1 = 1 
        // for y = i, each time find a match S[x]==T[tLen-i]; f(x,i)==f(x-1,i-1)+.... +;
        
        // whtat in the res array?  f(x,y) for current y;
        // s[  lastY-element]   t[  last-X-element]
         int savedLastResX = 1;
        
        
        
        
        for(int x=1;x<=ls;++x){
            // as we don't have "saved" value, we init it manully, first level;
            if(cs[ls-x]==ct[lt-1]){
                res[x] = res[x-1]+1;
            }else{
                res[x] = res[x-1];
            }
        }
        
        for(int y=2;y<=lt;++y){
            savedLastResX = 0;
            for(int x=1;x<=ls;++x){    
                int toSave = res[x];//
                if(cs[ls-x]==ct[lt-y]){
                              // last level  ,  this level;  ; value of last level is overwriten ; but we just need one each time,save in saved
                    // res[x] = res[x-1,y-1]+res[x-1,y]
                    res[x] = res[x-1]+savedLastResX;
                }else{
                    // res[x] = res[x-1]; last level;
                    res[x] = res[x-1];
                }
                savedLastResX = toSave;
            }
        }
        return res[ls];
    }
    
    
    private int dpNumDistinct(char[] cs,int sFrom, char[] ct, int tFrom,int[][] res){
        int ls = cs.length;
        int lt = ct.length;
        int sRest = ls-sFrom;
        int tRest = lt-tFrom;
        if(tRest==0){
            //end of t;  
            return 1;
        }
        if(sRest == 0 || sRest<tRest){
            return 0; //dead end;
        }
        
        if(res[sFrom][tFrom]!=-1){
            // have result for this case already;
            return res[sFrom][tFrom];
        }
        
        char c1 = cs[sFrom];
        char c2 = ct[tFrom];
        
        // if c1!=c2; have to keep searching till we find one;
        // we can recursively try find the result from  SFrom+1; but all hte recursive function just doing the same thing: find the first math
        // if no match; actually no result (count := 0)
        
        // so we'd rather find the math here;
        int ps = sFrom+1;
        while(c1!=c2&&ps<ls){
            c1 = cs[ps];
            ps++;
        }
        // ps is now one position ahead where char c1 is;
        if(c1==c2){
            // when two char match, we cna have two choice; take it or not take it;
            
            
            // if take; 
            int resTake = dpNumDistinct(cs,ps,ct,tFrom+1,res);
            int resNotTake = dpNumDistinct(cs,ps,ct,tFrom,res); // s moved but t not;
            int total = resTake+resNotTake;
            res[sFrom][tFrom] = total; //save it after we calc once;
            return total;
        }else{
            // even reached end , but still can't match ; it's a dead end;
            // don't forget to save the value; even it's a 0;
            res[sFrom][tFrom] = 0;
            return 0;
        }
    }
}