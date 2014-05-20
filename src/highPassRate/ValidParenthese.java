package highPassRate;

import java.util.Stack;

public class ValidParenthese {
    
    /**
     * as from experience of other problems, harder ones;  use stacks to match, not try to find a pair by search;
     *  by using a stack, we know which pair shall match;
     * 
     */ 
    public boolean isValid(String s) {
        if(s==null||s.length()<1) return false;
        
        char[] ca = s.toCharArray();
        Stack<Character> cst = new Stack<Character>();
        
        char c;
        char pop;
        for(int i=0;i<ca.length;++i){
            c = ca[i];
            if(c=='('||c=='{'||c=='['){
                cst.push(c);
            }else{
                if(cst.isEmpty()){
                    return false;
                }
                pop = cst.pop();
                
                // funnything is that :  ')'-'(' = 1; but  ']'-'[' =2;  '}'-'{'=2;
                if(pop=='('){
                    if(c==')')continue;
                    return false;
                }
                if(pop!=c-2){
                    return false;
                }
            }
        }
        return cst.isEmpty();
    }
}