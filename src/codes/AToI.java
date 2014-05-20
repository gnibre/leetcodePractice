package codes;

public class AToI {
	public void go(){
		String s = "";
		s = "  -0012a42";
		s= "2147483648";
		int res = atoi(s);
		System.out.println("res : "+s);
		System.out.println("i s : "+res);
		
		System.out.println(""+charToInt('a'));
	}
	
	
    public int atoi(String str) {
        boolean neg = false;
        boolean max = false;
        if(str==null||str.length()==0){
            return 0;
        }
        //!!!!!!!!!!!need trim()
        
        str = str.trim();
        if(str.startsWith("+")){
            str = str.substring(1,str.length());
        }
        if(str.startsWith("-")){
            str = str.substring(1,str.length());
            neg = true;
        }
        
        char[] ca = str.toCharArray();
        
//        int ret=0;
        int get=0;
        long ret = 0;
        
        
        for(int i=0;i<ca.length;++i){
        	get = charToInt(ca[i]);
        	System.out.println(" get: "+get);
        	//!!!!!!!!! need break when wrong input.
        	
        	//!!!!!!!!!!!!!  if exceed max, we shall break... INT_MAX (2147483647) or INT_MIN (-2147483648)
        	
        	if(get==-1){
        		break;
        	}else{
        		ret = ret*10;
        		ret+=get;
        		if(ret>Integer.MAX_VALUE){
        			max = true;
        			break;
        		}
        	}
        	
        	
        }
        
        
        if(max){
        	if(neg){
        		return Integer.MIN_VALUE;
        	}else{
        		return Integer.MAX_VALUE;
        	}
        }
        if(neg){
            ret = -ret;
        }
        return (int)ret;
    }
    
    private int charToInt(char c){
        int v = c-'0';
        if(v>-1&&v<10){
            return v;
        }
        return -1;
    }
    
}