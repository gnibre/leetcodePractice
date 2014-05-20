package highPassRate;


public class EditDistance {
    
    
    
    /**
     * 
     * edit distance and longest common sequence?
     * 
     *    gjh      xxxxxabc  xxx  defx
     *             xxxxx  ad xxx df  x  lakjdfl      
     * if xxxxxxxxxxxxxxxx is the longest common sequence that we can find;
     * 
     * what's the edit distance?  are they related?
     * 
     * 
     * focusing length only,
     * 1, insert +1
     * 2, delete -1
     * 3, replace  both -1?
     * 
     * cause replace operation rules, we don't really need to find common sequnce?
     * will common sequence makes things bad like, the match cause  1 edit => 1 insert+ 1 delete?  
     * 
     * s:    xxxabcdxxx
     * t:  abxxxxxxtttt ;
     *  if we do edit and insert,  2insert, 7 edit; 
     * if we match common sequence:         xxxabcdxxx         
     *                                    abxxx    xxxtttt  insert 10;
     * thought 6 x is longest common sequence, if we try match that, takes too much steps;
     * ======================================================================================================================
     * 
     * dp, rules, edit rules.
     * 
     * as delete/insert is kinda same : what we can do with inserting a char at word1, we can do that by deleting a char at w2;
     * 
     * so we use only : replace / delete;
     */ 
    public int minDistance(String word1, String word2) {
        if(word1==null&&word2==null) return 0;
        if(word1==null||word1.length()<1) return word2.length();
        if(word2==null||word2.length()<1)return word1.length();
        
        if(word1.length()<word2.length()){
            return minDistance(word2,word1);
        }
        
        
        int[][] res = new int[word1.length()][word2.length()];
        
        char[] ca = word1.toCharArray();
        char[] ca2= word2.toCharArray();
        
        return minDistanceAux(ca,0,ca2,0,res);
        
    }
    
    
    
    private int minDistanceAux(char[] ca,int from, char[] ca2,int from2, int[][] res){
        
        int left1 = ca.length-from;
        int left2 = ca2.length-from2;
        if(left2==0){
            return left1;
        }
        if(left1==0){
            return left2;
        }
        
        if(res[from][from2]!=0){
            return res[from][from2];
        }
        
        //w1 longer than w2;   operation : delete and replace;
        //cause edit rules, we don't need to delete any char in w2;  delete will only happen at w1; edit = delete both
        //!!!!!!!!!!!!!!!!!!! this deduction is terribly wrong :  
        // got an example from test case:   prosperity       pro   sper i ty
        //                                  properties       pro    perti es ;  if we delete t in second string, it actually save much;
        
        int min;
        if(ca[from]==ca2[from2]){
            int takeRes = minDistanceAux(ca,from+1,ca2,from2+1,res);
            int deleteRes = 1+minDistanceAux(ca,from+1,ca2,from2,res);
            int deleteRes2 = 1+minDistanceAux(ca,from,ca2,from2+1,res);
            
            min = Math.min(takeRes,deleteRes);
            min = Math.min(min,deleteRes2);
            // int edit res = 1+minDistance(word1.substring(1),word2.substring(2)); so was ignored;
            res[from][from2] = min;
            return min;
        }else{
            int editRes = 1+minDistanceAux(ca,from+1,ca2,from2+1,res);
            int deleteRes = 1+minDistanceAux(ca,from+1,ca2,from2,res);
            int deleteRes2 = 1+minDistanceAux(ca,from,ca2,from2+1,res);
            min = Math.min(editRes,deleteRes);
            min = Math.min(min,deleteRes2);
            res[from][from2] = min;
            return min;
        }
        
        
        //============================TODO: build from bot, and use only o(n) sapce?   maybe complex;
    }
    
}