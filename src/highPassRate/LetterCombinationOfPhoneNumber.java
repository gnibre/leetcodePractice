package highPassRate;

import java.util.ArrayList;
import java.util.HashMap;


public class LetterCombinationOfPhoneNumber {
    /**
     * easy math? choose 1 out of 3 or 4;  math arrange problem;
     * 
     * this passed with almost no fix... oshit, good;
     * but i think this one takes too much memory;
     * 
     */ 
    public ArrayList<String> letterCombinations(String digits) {
        
        HashMap<Character,String> cToS = new HashMap<Character,String>();
        cToS.put('2',"abc");
        cToS.put('3',"def");
        cToS.put('4',"ghi");
        cToS.put('5',"jkl");
        cToS.put('6',"mno");
        cToS.put('7',"pqrs");
        cToS.put('8',"tuv");
        cToS.put('9',"wxyz");
        
        ArrayList<String> pre = new ArrayList<String>();
        pre.add(""); // don't be empty for the pre array;
        return letterCombinationsAux(digits,pre,cToS);
    }
    
    
    private ArrayList<String> letterCombinationsAux(String digits,ArrayList<String> pre, HashMap<Character,String> cToS){
        if(digits==null||digits.length()<1){
            return pre;
        }
        char c = digits.charAt(0);
        char[] pool;
        if(cToS.containsKey(c)){
                pool= cToS.get(c).toCharArray();
        }else{
                pool = new char[1];
                pool[0]=  c;
        }
        
        ArrayList<String> appendOneChar = new ArrayList<String>();
        for(String p:pre){
            for(char c1:pool){
                String append = p+c1;
                appendOneChar.add(append);
            }
        }
        return letterCombinationsAux(digits.substring(1),appendOneChar,cToS);
    }
    
}