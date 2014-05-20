package codes;

import java.util.ArrayList;
import java.util.HashMap;

public class PalindromePartition {
	
	
	public void go(){
		String s = "aab";
		s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhaf";
		long l1 = System.currentTimeMillis();
		ArrayList<ArrayList<String>> res = partition(s);
		long l2 = System.currentTimeMillis();
		System.out.println(" res size: "+res.size()+"   cost time: "+(l2-l1));
		
//		for(int i=0;i<res.size();++i){
//			ArrayList<String> al = res.get(i);
//			System.out.print(" I: "+i);
//			for(String str:al){
//				System.out.print("  "+str);
//			}
//			System.out.println("  ");
//		}
		
	}
	

    private HashMap<String,ArrayList<ArrayList<String>>> record = new HashMap<String,ArrayList<ArrayList<String>>>();
    
    /**
     *     //0: each character as a string is a solution;
     * //1,   s = head+ tail.  if head works,   res = head+ fun(tail);
     * // 2 for test cases that from leetcode, we'd better record these "head"/"tail" that worked?
     *   maybe not "head" , cause we can't skip any char, we have to take it, or wait til head is a new "palindrome"
     *  but must "tail", for the case of "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" one milion times.
     * 
     * 
     */
    public ArrayList<ArrayList<String>> partition(String s) {
//    	System.out.println(" partition : "+s);
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if(s==null||s.length()==0){
            return res;
        }
        
        if(record.containsKey(s)){
            return record.get(s);
        }
        
        String head,tail;
        //!!!!!!!!!!!!!!!!!!!!! this loop will not contain string s itself.
        for(int i=1;i<s.length();++i){
            head = s.substring(0,i);
            tail = s.substring(i,s.length());
//            System.out.println(" head : "+head+"  tail: "+tail);
            if(isPalindrome(head)){
                ArrayList<ArrayList<String>> tailRes = partition(tail);
//                System.out.println(" head : "+head+"   is palindrome,  got tai res: "+tail+" "+tailRes.size());
                for(ArrayList<String> al:tailRes){
                    ArrayList<String> one = new ArrayList<String>();
                    one.add(head);
                    for(String str :al){
                        one.add(str);
                    }
                    res.add(one);
                }
            }
        }
        
        
        if(isPalindrome(s)){
//        	System.out.println(" self is palindrome , add : "+s);
            ArrayList<String> one = new ArrayList<String>();
            one.add(s);
            res.add(one);
        }
//        System.out.println("  <<<<<<<<<<<<    string ends : "+s);
        record.put(s,res);
        return res;
    }
    
    
    private boolean isPalindrome(String s){
    	
        if(s==null||s.length()==0){
            return false;
        }
        char[] ca= s.toCharArray();
        int j = ca.length-1;
        for( int i=0;i<j;++i){
            if(ca[i]==ca[j]){
                j--;
            }else{
                return false;
            }
        }
        return true;
    }
    
    
    
}