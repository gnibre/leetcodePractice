package highPassRate;

import ulti.Printer;

public class NextPermutation {
    
    
	public void go(){
		
		int[] num={
				1,1
		};
		
		nextPermutation(num);
		
		Printer.pArray(num);
	}
    
    /**
     * a  number,permutation, that bigger than this one (except last one); 
     * pick the smallest of it;
     * 
     * // 1 find how many number can still move; ( any number that left < right )
     * // 2 find the first one; and we get the range ; name it firstSmall;
     * // 3 pick the smallest number that to right of firstSmall,bigger than firstSmall ,  name it pickSmall; 
     * // now, put pickSmall at the position where firstSmall is; 
     * // reorder the numbers from smallest to biggest ; ( don't need to sort, cause it is ordered  >>>> )
     * 
     * example.       58732
     * firstSmall = 5 (5<8); pickSmall = 7 ( 7>5 and 7 is the smallest one >5);
     * order the rest swap 5 and 7:  78532, 
     * reverse rest:  72358
     * 
     * 
     * /// sure, test case do have duplicate;     the given example also have dup;;;;; i forget to do so;..     * 
     * first small, still, we need fs<xx; or we can't make space;
     * ps, ps have to >fs;;
     * 
     * 5887755532; 
     * fs  =5; ps = 7;
     * swap:   7887555532
     * rev:   7235555788
     * it seems still works for dup case?
     * 
     */
    public void nextPermutation(int[] num) {
        if(num==null||num.length<2) return;
        // so, just do as the example step by step;
        // add some boundary cases;
        
        int len = num.length;
        
        int findFirstSmall = len-2;
        while(findFirstSmall>-1){
            if(num[findFirstSmall]<num[findFirstSmall+1]){
                break;
            }
            findFirstSmall--;
        }
        
        if(findFirstSmall==-1){
            // no found; 
            reverseArray(num,0,len-1);
            return;
        }
        
        String s;
        
        
        // ok, find firstSmall; first step done;
        int fs = num[findFirstSmall];
        int ps =len-1;
        for(int i=findFirstSmall+1;i<len;++i){
        	//!!!!!!!!!!!!! equal case added here, for the dup cases!!!!
            if(num[i]<=fs){
               ps = i-1;
               break;
            }
        }
        
        // find and second small;  at position ps;
        //swap;
        int swap = num[ps];
        num[ps] = num[findFirstSmall];
        num[findFirstSmall] = swap;
        
        // reverse
        reverseArray(num,findFirstSmall+1,len-1);
    }
    
    private void reverseArray(int[] num,int f,int t){
        int swap;
        while(f<t){
            swap = num[f];
            num[f] = num[t];
            num[t] = swap;
            f++;
            t--;
        }
    }
}