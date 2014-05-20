package highPassRate;

import java.util.HashMap;

public class RomanToInteger {
    /**
     * 
     * 
     * // String[] nToR = {"","I","II","III","IV","V","VI","VII","VIII","IIX","IX","X"};
        //                1   10  100 1000
        String[] rBase = { "I","X","C","M"};
        String[] rFives = {"V","L","D"};
        //                 5   50  500
     * 
     * 
     */
    public int romanToInt(String s) {
        
        if(s==null||s.length()<1) return 0;
        
        HashMap<Character,Integer> vMap = new HashMap<Character,Integer>();
        vMap.put('I',1);
        vMap.put('V',5);
        vMap.put('X',10);
        vMap.put('L',50);
        vMap.put('C',100);
        vMap.put('D',500);
        vMap.put('M',1000);
        
        // only three pattern in roman
        //PA: (5),1,1,1
        //PB: (1),(1),5            =>  if we don't need to check if it's valid:   (1,1,1,1,5,1,1,1,1) is works too;
        //PC:  (1)(1),10 
        // the rest are from next level;  next level means getting small;
        
        int cur; //cur visit number;
        int last=-1; // last visited number;
        char[] ca = s.toCharArray();
        int n = ca.length;
        int res=0;
        int saved=0; // saved value that not decide it's a plus or a minus;
        for(int i=0;i<n;++i){
            cur = vMap.get(ca[i]);
                
            if(last!=-1){
                if(cur<last){
                    // going next level, saved value can be added;
                    res+=saved;
                    saved = cur;
                }else if(cur==last){
                    // actually not valid if value is 5's , but we don't check here;
                    saved+=cur;
                }else{
                    //cur>last; heihei;
                    res+= cur-saved; // saved value , is for a minus operation;
                    saved = 0;
                }
            }else{
                //if last is -1,cur is  first number, don't need to check actually;
                saved = cur;
            }
            last = cur;
        }
        res+= saved;
        
        return res;
    }
}
