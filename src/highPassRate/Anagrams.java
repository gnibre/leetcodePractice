package highPassRate;

import java.util.ArrayList;
import java.util.HashMap;

public class Anagrams {
    /**
     * 
     * not palindrom;
     * searched, i guess anagram is something , same string, but not same order;
     * 
     * find a signature for each of them?like char count?
     * 
     * 
     * ============= this one passes the oj super fast; 
     * remember two things that i can't recall before use an IDE;
     */ 
    public ArrayList<String> anagrams(String[] strs) {
        
        ArrayList<String> res = new ArrayList<String>();
        if(strs==null||strs.length<1){
            return res;
        }
        
        //map
        /// string sigature-> result array;;  
        // so strings with same signature will be saved together;
        HashMap<String,ArrayList<String>> sigResMap = new HashMap<String,ArrayList<String>>();
        for(int i=0;i<strs.length;++i){
            String sig = getSignature(strs[i]);
            mapAddPair(sigResMap,sig,strs[i]);
        }
        
        
        //reduce;
        //!!!!!!!!!!!!!!!!!!!!!!!!! it's hashMap.values(). get all the values as a collection;
        for(ArrayList<String> value:sigResMap.values()){
            if(value.size()>1){
            	
                //!!!!!!!!!!!!!!!!!!!!!!!!! it's addAll, to add another collection;
                res.addAll(value);
            }
        }
        return res;
    }
    
    private void mapAddPair(HashMap<String,ArrayList<String>> sigResMap,String sig,String s){
        ArrayList<String> alThisSig = null;
        if(sigResMap.containsKey(sig)){
            alThisSig = sigResMap.get(sig);
        }else{
            alThisSig = new ArrayList<String>();
        }
        alThisSig.add(s);
        sigResMap.put(sig,alThisSig);
    }
    
    private String getSignature(String s){
        
        //count;
        int[] countA = new int[26];
        for(int i:countA){
            i = 0;
        }
        char[] charA = s.toCharArray();
        for(char c:charA){
            countA[c-'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for(char c='a';c<='z';++c){
            int count=countA[c-'a'];
            if(count>0){
                sb.append(c);
                sb.append(count);
            }
        }
        return sb.toString();
    }
    
}
