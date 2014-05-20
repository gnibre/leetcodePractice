package codes;

import java.util.HashMap;

public class DecodeWays {
	
	public void go(){
		String s;
		s = "9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253";
		s = "10";
		s = "00";
		int res = numDecodings(s);
		System.out.println("res : "+s);
		System.out.println("i s : "+res);
		
		for(int i=0;i<11;++i){
			String ss = String.valueOf(i);
			if(resMap.containsKey(String.valueOf(ss))){
				System.out.println(" "+ss+"  => "+resMap.get(ss));
			}
		}
		
	}
	
	int count=0;
	HashMap<String,Integer> resMap = new HashMap<String,Integer>();
    public int numDecodings(String s) {
    	System.out.println("  "+count+++"    : "+s);
        if(s==null||s.length()==0){
            return 0;
        }
        s = s.trim();
        if(s.length()==1){
//        	!!!!!!! wrong!! if input is 0, as A->1,  we have no way to decode. 
        	//   and if input is 10, as A->1, 10->J, 0->nothing, we have only 1 way to decode.
//            return 1;
        	int v = s.charAt(0)-'0';
            if(v>0&&v<10){
                return 1;
            }
            return 0;
        }
        if(resMap.containsKey(s)){
        	return resMap.get(s);
        }
        int res;
        char c1 = s.charAt(0);
        
        
        //!!! for case 01, return 0;
        if(c1<'1'){
            return 0;
        }
        
        if(c1>'2'){
        	res = numDecodings(s.substring(1));
        	resMap.put(s, res);
            return res;
        }
        
        char c2 = s.charAt(1);
        int decade = c1-'0';
        int num = c2-'0';
        int startValue = decade*10+num;
        
        System.out.println(" startValue: "+startValue);
        //!!!!!!!!!!!!! when the start value is 0!!!!!!!!!! we can't find the real one.
        if(startValue==0){
        	return 0;
        }
        if(startValue<27){
        	//!!!!!!!!!!!!!!!!!!!! when the string lenth is 2, the left string cant' use this function to do recursive call, as ""input returns 0.
        	if(s.length()==2){
        		res = numDecodings(s.substring(1))+1; 
        	}else{
        		res = numDecodings(s.substring(1))+numDecodings(s.substring(2));
        	}
        	resMap.put(s, res);
            return res;
        }
        
        res = numDecodings(s.substring(1));
    	resMap.put(s, res);
        return res;
    }
}