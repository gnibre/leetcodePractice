package highPassRate;

public class CountAndSay {
    public String countAndSay(int n) {
        // the init string is always 1, not n; read from the test case;
        String s = String.valueOf(1);
        for(int i=1;i<n;++i){
            s = readIt(s);
        }
        return s;
    }
    
    /**
     * found it easier with string;
     */ 
    private String readIt(String s){
        // finally we don't need to check this string if valid; thanks god;
        char[] ca = s.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        
        char last = (char)(ca[0]+1); //jsut make sure it's not same as ca[0]; we can't set a char to be null;
        int sameCount =0;
        char c;
        for(int i=0;i<ca.length;++i){
            c = ca[i];
            if(c==last){
                sameCount++;
                continue;
            }
            
            //else, c is not like last; handle last one first please;
            if(sameCount>0){
                //read it;  222 read as : 3 twos;
                sb.append(sameCount) ;//  count first;
                sb.append(last);
            }
            last = c;
            sameCount = 1;
        }
        
        // out the loop, add last one;
        if(sameCount>0){
                sb.append(sameCount) ;//  count first;
                sb.append(last);
        }
        return sb.toString();
    }
}