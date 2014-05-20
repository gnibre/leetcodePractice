package codes;

import java.util.ArrayList;

import ulti.Printer;

public class RestoreIPAddress {
    
	
	public void go(){
		
		
		String in = "0000";
		
		 ArrayList<String> res = restoreIpAddresses(in);
		Printer.pArray(res);
		
		System.out.println("  check validIpSubstring, of str 0 : "+validIpSubstring("0"));
	}
	
    
    /***
     * valid ones can be of lenght 4 to 12;   0.0.0.0 (4)  255.255.255.254 (12)
     * 
     * 
     */ 
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if(s==null||s.length()<4||s.length()>12){
            return res;
        }
        resstoreIpAddressesAux(res,new ArrayList<String>(),s,4);
        return res;
    }
    
    /**
     * the original one is calling this one by resstoreIpAddressesAux(res, newListNoPre, s_original, 4 )  need 4 number;
     * so it's easy to under stand this one;'
     * 
     * pre is the path of the depth first tree that is visiting;
     */
    private void resstoreIpAddressesAux(ArrayList<String> res, ArrayList<String> pre, String s,int togo){
        System.out.println("   resstoreIpAddressesAux      ,  "+s+" to go: "+togo);

        if(togo<0){
            return;
        }
        
        if(togo==0){
        	if(s==null||s.length()==0){
                StringBuilder sb = new StringBuilder();
                for(String p:pre){
                    sb.append(p);
                    sb.append(".");
                }
                sb.deleteCharAt(sb.length()-1);
                res.add(sb.toString());
            }
        }
        
        
        String sub;
        // one number of length 1 to 3;
        //!!!!!!!!!!!!!!!!!!!!!&&i<=s.length() check boundary!!!
        for(int i=1;i<=3&&i<=s.length();++i){
            sub = s.substring(0,i);
            if(validIpSubstring(sub)>-1){
            	//!!!!!!!!!!!!!!!!!!!!!!!!!! pre.add(sub) is a boolean!!!!!!!!!! for if it's added;
            	pre.add(sub);
            	resstoreIpAddressesAux(res,pre,s.substring(i),togo-1);
                pre.remove(pre.size()-1);//back state;
            }
        }
    }
    
    
    /**
     * we don't know, we just try;
     * accept strings of length<=3;
     * if returns -1, it's not valid;
     * 
     */
    private int validIpSubstring(String sub){
        
        int l = sub.length();
        
        char c;
        int v = 0;
        int b = 0;
        for(int i=0;i<l;++i){
            c = sub.charAt(i);
            if(c>='0'&&c<='9'){
                b = c-'0';
            }else{
            	//not valid char;
                return -1;
            }
            //!!!!!!!!!!!!!!!!  01 as not valid, but 0 self is valid!!!
            if(b==0&&i==0&&l>1){ // 01,02 cheat as not valid?
                return -1;
            }
            v = v*10+b;
        }
        if(v>255){
            return -1;
        }
        return v;
    }
}