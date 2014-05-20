package codes;

public class ISNumber {
	
	public void go(){
		
		String[] sl = {
//				"0"," 0.1 ","abc","1 a","2e10",".",".3",".0"
				"2e0","+ 1","46.e3","6e6.5"
		};
		
		//46.e3 true
		//1e. false
		
		for(int i=0;i<sl.length;++i){
			boolean res = isNumber(sl[i]);
			System.out.println("==   "+sl[i]+"   "+res);
			
		}
		
//		ArrayList<String> al = new ArrayList<String>();
		
		
	}
	
	
    public boolean isNumber(String s) {
        
        // numbers |.| e from the sample.
        // 1 if start with 0, we must have : .
        // 2 start with + or -
        
    	s = s.trim();
    	if(s.startsWith("+") || s.startsWith("-")){
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean gotValue =false;
        boolean gotDot = false;
        boolean efirstShow = true;
        for(int i=0;i<len;++i){
//        	System.out.println(""+i+" : "+chars[i]);
//        	if(chars[i]==' '||chars[i]=='\r'||chars[i]=='\n'){
//            	continue;
//            }
//        	if(chars[i]=='0'&&i==0){
//        		gotValue = true;
//                if(i+1<len){
//                	if(chars[i+1]!='.'){
////                		System.out.println(" no dot after 0");
//                		return false;
//                	}else{
////                		System.out.println(" got dot");
//                		gotDot = true;
//                		i++;
//                		continue;
//                	}
//                }
//            }
        	
        	
        	
        	if(chars[i]=='.'){	
        		if(!gotDot){
        			gotDot = true;
        			if(!efirstShow){
        		        //no dot after e?
        		        return false;
        		    }
        			continue;
        		}else{
        			System.out.println(" wrong position .");
        			return false;
        		}
        	}
        	
            if(chars[i]>='0'&&chars[i]<='9'){
            	gotValue = true;
                continue;
            }
            	
            
            if(chars[i]=='e'&&efirstShow){
            	if(!gotValue){
            		//no value before e
            		return false;
            	}
            	
            	
            	efirstShow = false;
                // can have +- after e
                if(i+1==len){
//                	System.out.println(" nothing after e");
                    return false;
                }
                if(chars[i+1]=='+'||chars[i+1]=='-'){
                	if(i+2==len){
                        //no content after e
                        return false;
                    }
                    i++;
                }
                
                gotValue=false;
                gotDot = false;
                continue;
            }
            
            
            if(!efirstShow&&gotDot){
            	return false;
            }
            
            System.out.println(" char not good: "+chars[i]);
            return false;
        }
        
//        if(gotDot&&chars[len-1]=='0'){
//        	System.out.println("end 0 not good");
//            return false;
//        }
        
        if(!gotValue){
        	return false;
        }
        
        return true;
        
    }
}