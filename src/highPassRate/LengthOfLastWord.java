package highPassRate;

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s==null||s.length()<1) return 0;
        
        
        boolean dontWannaUseSplit = true;
        if(dontWannaUseSplit){
            return lengthOfLastWordManual(s);
        }
        
        // this code works ; but we may not allowed to use this;
        String[] split = s.split(" ");
        if(split==null||split.length<1){
            return 0;
        }
        return split[split.length-1].length();
    }
    
    
    private int lengthOfLastWordManual(String s){
        // can we use this?..
        
        
        // what?  for the input  , "a " , the last word is "a"? that's what split do?
        // s.trim()
        while(s.length()>0&&s.charAt(s.length()-1)==' '){
            s = s.substring(0,s.length()-1);
        }
        int ind = s.lastIndexOf(" ");
        if(ind<0) return s.length();
        
        return s.length()-ind-1;
    }
}