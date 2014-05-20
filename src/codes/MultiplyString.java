package codes;

public class MultiplyString {
    public void go(){
    	String s = "";
    	String t = "";
    	
    	//!! 
    	  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // when we use Integer.valueOf(Char c),
        // the result may not what we expect.!!!   like Integer.valueOf('0') = 48!!!
        
    	s  = "0";
    	t = "0";

    	s = "9";
    	t = "99";
    	
    	
    	
    	String res = multiply(s,t);
    	System.out.println(" multi: "+s);
    	System.out.println(" multi: "+t);
    	System.out.println(" res: "+res);
    	
    	
    }
	
    /**
     * kinda like problem of multiply two large int
     *         a-b-c
     *      x  j-k-l
     * -------------
     *        al bl cl
     *     ak bk ck
     *  aj bj cj
     *Array[5][4][3][2][1]
     * 
     * so, 
     * 1,  string to char[]
     * 2,  char to int
     * 3,  multiply each int, and save it at good location.
     * 4,  addup values in each location of an array
     */
    public String multiply(String num1, String num2) {
        if(num1==null||num2==null||num1.length()==0||num2.length()==0){
            return "0";
        }
        boolean positive = true;
        if(num1.charAt(0)=='+'){
            num1 = num1.substring(1);
        }
        if(num2.charAt(0)=='+'){
            num2 = num2.substring(1);
        }
        
        if(num1==null||num2==null||num1.length()==0||num2.length()==0){
            return "0";
        }
        
        if(num1.charAt(0)=='-'){
            num1 = num1.substring(1);
            positive = !positive;
        }
        if(num2.charAt(0)=='-'){
            num2 = num2.substring(1);
            positive = !positive;
        }
        
        if(num1==null||num2==null||num1.length()==0||num2.length()==0){
            return "0";
        }
        
        
        char[] ca1 = num1.toCharArray();
        char[] ca2 = num2.toCharArray();
        int len1 = ca1.length;
        int len2 = ca2.length;
        
        // in this result arry, we just save tmp value, we can save a value like 36 in the array,
        // we dont' care carry problem of a decimal number first, we will handle that later.
        int[] res = new int[len1+len2+2]; //like len1+len2+1 will be enough? but we use +2 so no need to check if it's enough,it must be enough...
        for(int v:res){
            v = 0; //init.
        }
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // when we use Integer.valueOf(Char c),
        // the result may not what we expect.!!!   like Integer.valueOf('0') = 48!!!
        
        int[] vl1 = new int[len1];
        for(int i=0;i<len1;++i){
            //reverse the char array, so vl[0] will contain lower digits ,vl[1] contains tens , vl[2] hundreds.  easier to cal. 
            vl1[i] = ca1[len1-i-1]-'0';
        }
        int[] vl2 = new int[len2];
        for(int i=0;i<len2;++i){
            //reverse the char array, so vl[0] will contain lower digits ,vl[1] contains tens , vl[2] hundreds.  easier to cal. 
            vl2[i] = ca2[len2-i-1]-'0'; 
        }
        
        
        System.out.println(" "+num1);
        System.out.println("*"+num2);
        
        System.out.println(" char to int:  num1 ");
        for(int i:vl1){
        	System.out.print(i);
        }
        System.out.println(" ");
        System.out.println(" char to int:  num2 ");
        for(int i:vl1){
        	System.out.print(i);
        }
        System.out.println(" ");
        int base;
        for(int i=0;i<len1;++i){
            for(int j=0;j<len2;++j){
                base = i+j; //  a 30 * 500, base is 1+2 = 3, 15000, a 3* 50 base is 0+1 = 1;
                res[base]+= vl1[i]*vl2[j];
                System.out.println(" i: "+i+"  j: "+j+"  res:  "+res[base]);
            }
        }
        //everything in res array. we take care of carry problem and heading 0?
        
        int v;
        int carry = 0 ;
        int remain;
        for(int i=0;i<res.length;++i){
            v = res[i];
            v+=carry; //got carry from lower 
            System.out.println(" i: "+i+"  v :"+v);
            carry = v/10;
            res[i] = v%10; // remain saved in the array, carry move on.
            System.out.println(" i: "+i+"  res:"+res[i]+"  carryon: "+carry);
        }
    
        //handle heading zeros, delete heading zero don't delete the last zero, if it's there.
        int p = 0;
        for(int i=res.length-1;i>0;--i){
            if(res[i]>0){
                p = i;
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if(!positive){
            sb.append("-");
        }
        for(int i=p;i>=0;--i){
            sb.append(res[i]);
        }
        return sb.toString();
    }
}