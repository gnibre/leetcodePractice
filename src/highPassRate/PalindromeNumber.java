package highPassRate;

public class PalindromeNumber {
    
	public void go(){
		
		int n = 1000110001;
		
		int c = ']'-'[';
		c = ')'-'(';
		c ='}'-'{';
		System.out.println("  c: "+c);
		
		System.out.println(" res:  "+isPalindrome(n));
	}

    
    /***
     * 
     * no extra space, can't use string, can't save value to list;
     * what can do is just check first value and last;
     *   
     * 10002344320001 
     *  000234432000      
     * ==> first step:   remain: 1000234432 (info:  zero count: 3, tail 1)  
     * ==> second step:  234432
     * 
     * case 123000000321; => 30000003  total number 8 bit; 6 zeros; 
     * 
     * ; no it's not; 
     *  seems algorithm automaticlly works for this case... cause there do have same number of zeros after head 3; though these 0s are counted twice;
     */ 
    public boolean isPalindrome(int x) {
        
        
        //!!!!!!!!!!!!!!!added x<0 case ; after see the test case;
        if(x<0){
            //!!!!!!!!!!!!!!!added  this case;  anytime you want do this  x = -x for an int , shall check ;
            if(x==Integer.MIN_VALUE) return false;
            // return isPalindrome(-x);
            // test case said -1 is not, so negtive ones are not
            return false;
        }
        
        if(x<10){
            return true;
        }
        if(9<x&&x<100){
            if(x%10==x/10){
                return true;
            }
            return false;
        }
        
        int tail = x%10;
        //!!!!!!!!!!!!!!!added tail==0 case;
        if(tail==0){
            return false;
        }
        
        int zerosBeforeTail =0;
        int remain = x/10;
        while(remain%10==0){
            remain = remain/10;
            zerosBeforeTail++;
        }
        int cutTail = remain; //things left cut tail;   10002344320001 =>1000234432
        
        if(cutTail<10){ // like number is 100001; remove zeros makes things left after cut very fun;
            if(cutTail==tail) return true;
            return false;
        }
        
        int head=0;
        int numberS = 1;
        int numberOfZerosAfterHead =0;
        remain = cutTail;
        
        //count numbers;
        while(remain>9){
            numberS++;
            if(remain%10==0){
                numberOfZerosAfterHead++;
            }else{
                numberOfZerosAfterHead = 0;
            }
            remain/=10;
        }
        head = remain;
        
        if(numberOfZerosAfterHead!=zerosBeforeTail){
            return false;
        }
        if(head!=tail){
            return false;
        }
        
        int midCount = numberS-numberOfZerosAfterHead-1; // mid is (things cut tail) - zeros - head;
        
        int MidModer = 1;
        for(int i=0;i<midCount;++i){
            MidModer*=10;
        }
        
        int mid = cutTail%MidModer;
        return isPalindrome(mid);
        
    }
}