package codes;

import java.util.HashSet;
import java.util.Set;

public class WordBreaker {
    public void go(){
    	
//    	String[] ar = {"cbc","bcda","adb","ddca","bad","bbb","dad","dac","ba","aa","bd","abab","bb","dbda","cb","caccc","d","dd","aadb","cc","b","bcc","bcd","cd","cbca","bbd","ddd","dabb","ab","acd","a","bbcc","cdcbd","cada","dbca","ac","abacd","cba","cdb","dbac","aada","cdcda","cdc","dbc","dbcb","bdb","ddbdd","cadaa","ddbc","babb"};
    	String[] ar = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
    	HashSet<String> d = new HashSet<String>();
    	for(String s:ar){
    		d.add(s);
    	}
    	
    	
    	String s;
    	
    	//we shall always remember this shit case, 
    	//like in a breadth first search, if the level of the final result is really deep/high, bfs is not a good choice/ easy to code though.
    	// and dfs can't find it too, cause there's no result, but we kept trying.
    	// here this simple case cause more than 70million function call but still no result, time limit will reach. 
    	s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//    	s = "aaaaaaaaaaaaaaaaaaaaaaaaaaab";
//    	s = "bccdbacdbdacddabbaaaadababadad";
    	boolean res = wordBreak(s,d);
    	System.out.println("res : "+res);
    }
    int shit =0;
	
    //I , find one, 
    //II , find all.
    
    
    //memory consuming but really fast, we save the whole tree.
    HashSet<String> resSet = new HashSet<String>();
    HashSet<String> deadSet = new HashSet<String>();
    public boolean wordBreak(String s, Set<String> dict) {
        for(String d:dict){
        	resSet.add(d);
        }
        return wordBreakSaved(s);
    }
    
    int good = 0;
//    int shit =0;
    public boolean wordBreakSaved(String s) {
    	
    	if(resSet.contains(s)){
    		System.out.println("[good]"+good++);
    		return true;
    	}
    	if(deadSet.contains(s)){
    		System.out.println("[shit]"+shit++);
    		return false;
    	}
        int l = s.length();
        String sub;
        String left;
        boolean part;
        
        for(int i=1;i<l;++i){
        	sub = s.substring(0,i);
        	left = s.substring(i);
        	if(wordBreakSaved(sub)&&wordBreakSaved(left)){
//        		System.out.println("")
        		if(!resSet.contains(s)){
//        			System.out.println("new word added : "+l+"    : "+s);
        		}
        		resSet.add(s);
        		return true;
        	}
        }
        System.out.println("DEAD   : "+l+"    : "+s);
        deadSet.add(s);
        return false;
    }
}