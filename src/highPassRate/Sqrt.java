package highPassRate;

public class Sqrt {
	
	
	public void go(){
		
		int x = 1024;
//		System.out.println("max; "+Integer.MAX_VALUE);
		x = 904239234;
		x = 2;
		x = 1579205274;
		
		int res = sqrt(x);
		System.out.println(" res:   "+res+"        x:"+x);
	}
	
	
	
	
//	/**
//	 * at least you can do binary search, as a programmer....
//	 * 
//	 * @param x
//	 * @return
//	 */
//	public int sqrt(int x) {
//		if(x<0){
//            return 0;
//        }
//		
//		int left =0; 
//		int right = x;
//		int mid = (right+left)/2;
//		int d;
//		while(mid<right){			
//			d = x/mid; // use devide is cool, if use square. we may exceed Integer.MAX_VALUE;
//			if(d<mid){
//				//mid try too big.
//				right = mid;
//				mid = (left+right)/2;
//			}else if(d>mid){
//				//mid try too small
//				if(left==mid){
//					// if left already is mid. we know that right = left+1; 
//					// we shall break the while loop cause left/mid/right will not change anymore.
//					break;
//				}
//				left = mid;
//				mid = (left+right)/2;
//			}else{
//				return mid;
//			}
//		}
//		return mid;
//	}
	
	
    /**
     * 
     * like we just guess what the result is and try.
     * 
     * for example,      1055^2 < x < 1056^2
     * so we konw it's  > 1,000,000 and <4,000,000
     * so we can guess    x is something like 1---; so we got the first bit.
     * we try get it bit by bit.
     */ 
    public int sqrt_fail(int x) {
        if(x<0){
            return 0;
        }
        
        int len = 1;
        int copy = x;
        int base = 1;
        while(copy>100){
            len++; //got one bit.
            copy/=100;
            base*=10;
        }
        // how many bits do we have.
        int[] bits = new int[len];
        
        
        // for loop to get each bit, each time , just guess from 0 ot 9;
        int head = 0;
        int toTry = 0;
        int sq;
        int goodGuess =0;
        for(int i=0;i<len;++i){
            for(int guess=9;guess>-1;--guess){
                toTry = (head*10+guess)*base;
                sq = toTry*toTry;
                System.out.println(" to try: "+toTry+"  it's sq : "+sq);
                
                
                //!!!!!!!!!!!!!!!!!! problem is that, the sq value will not always <0 when it exceeds Integer.MAX_VALUE;
                if(sq<0||sq>x){
                    //exceed max. or too big.
                    continue;
                }
                //else, it's good,
                if(sq<=x){
                    goodGuess = guess;
                    break;
                }
            }
            head = head*10+goodGuess;
            base/=10;
        }
        return head;
    }
    
    
    /**
     * 
     * like we just guess what the result is and try.
     * 
     * for example,      1055^2 < x < 1056^2
     * so we konw it's  > 1,000,000 and <4,000,000
     * so we can guess    x is something like 1---; so we got the first bit.
     * we try get it bit by bit.
     * 
     * 
     * when check , we don't use square, as it may exceed Integer.MAX_VALUE;
     * when trying, we use binary search, as a programmer....
     * 
     */ 
    public int sqrt(int x) {
        if(x<0){
            return 0;
        }
        
        int len = 1;
        int copy = x;
        int base = 1;
        while(copy>100){
            len++; //got one bit.
            copy/=100;
            base*=10;
        }
        // how many bits do we have.
        int[] bits = new int[len];
        
        
        // for loop to get each bit, each time , just guess from 0 ot 9;
        int head = 0;
        int toTry = 0;
        for(int i=0;i<len;++i){
        	int left= 0; int right = 10; //as we try mid = (left+right)/2; we can never reach right; right start at 10;
        	int guess =4;
        	// we break this while loop when mid = left. and at that time , we have:
        	// square(left)<= x;  square(right)>x;
        	while(guess>left){
        		toTry = (head*10+guess)*base;
        		
        		int d = x/toTry;
        		if(d<toTry){
        			//to big.  right bound ;
        			System.out.println(" toTry: "+toTry+"  too big");
        			right = guess;
        		}else if(d>toTry){
        			// to small, left bound;
        			System.out.println(" toTry: "+toTry+"  too small");
        			left = guess;
        		}else{
        			// d == toTry?  toTry^2 = x;
        			return toTry;
        		}
        		//next guess.
        		guess = (left+right)/2;
        		System.out.println(" left:"+left+"  right:"+right+"   next guess: "+guess);
        	}
        	//this guess breaks.
            head = head*10+guess;
            System.out.println(" guess res: "+head);
            base/=10;
        }
        return head;
    }
    
}