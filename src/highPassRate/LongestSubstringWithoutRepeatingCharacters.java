package highPassRate;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    
    
    /***
     * 
     * longest substring without duplicate characters.
     * 
     * scan with a window of lenght x;?  x-1 is the length of already found longest substring without repeat chars.
     * if this window works, change the max to this, and expand the window size +1;
     * if this window fails,shift window to the right, cause focusing on a window length smaller than this is meaningless, can't find
     * more result.
     * 
     */ 
    public int lengthOfLongestSubstring(String s) {
        
        if(s==null||s.length()==0){
            return 0;
        }
        char[] ca = s.toCharArray();
        int len = ca.length;
        
        HashMap<Character,Integer> cCountMap = new HashMap<Character,Integer>();
        int max = 0; //max found default is 0, window size is 1;
        int start=0;
        int end = 0;
        
        cCountMap.put(ca[0],1); //init.
        int distance = 0;   //when distance is 0, it has no repeat char,  when one repeat added, distance +1;
        int curLen;
        char c;
        while(end<len){
            // curlen, 
            curLen = end-start+1;
            if(distance==0&&curLen>max){
                //new max found; 
                max = curLen;
                //expand window.
                end = end+1;
                if(end>=len){
                    break;
                }
                c = ca[end];
                if(cCountMap.containsKey(c)){
                    int oldV = cCountMap.get(c);
                    oldV++;
                    cCountMap.put(c,oldV);
                    distance++;
                }else{
                    cCountMap.put(c,1);
                }
            }else{
                //distance>1(have duplicate) or curLen<=max. move the window, keep searching.
                
                
               c = ca[start]; //c to remove
               int count = cCountMap.get(c);
               if(count==1){
                   cCountMap.remove(c);
               }else {
                   // saved cout must be >1;
                   cCountMap.put(c,count-1);
                   distance--;
               }
               start++;
               end++;
               if(end>=len){
                   break;
               }
               c = ca[end];
               if(cCountMap.containsKey(c)){
                    int oldV = cCountMap.get(c);
                    oldV++;
                    cCountMap.put(c,oldV);
                    distance++;
                }else{
                    cCountMap.put(c,1);
                }
            }
        }
        
        return max;
        
    }
}