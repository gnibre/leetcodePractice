package interviews;

public class amazonExerciseSizeFormat {

	public void test() {
		
		long l = Long.MIN_VALUE;
		long mm = Integer.MAX_VALUE;
		long mn = Integer.MIN_VALUE;
		long rem = l/mm;
		long m1 = mm*mn;
		long m2 = mm*(-mn);
		System.out.println(" rem "+rem);
		
		System.out.println(" m1 "+m1);
		System.out.println(" m1 "+m2);
		
		int[] sizeArray = {341,34200,5910000,0,34000,7200000,54123};
		int size;
		size = 341; // 341B
		size = 34200; // 34.2K;
		size = 1000000000 ; //1G
		
		String res;
		Object lock = new Object();
		for(int s:sizeArray){
			synchronized(lock){
				
			}
			res = sizeFormat(s);
			System.out.println(" ==size: "+s+"   => "+res);
		}
		
		size = 1000000000 ; //1G
		while(size>0){
			res = sizeFormat(size);
			System.out.println(" ==size: "+size+"   => "+res);
			size/=10;
		}
		
	}

	/**
	 * complexity:
	 * space complexity O(1). Cause did not use any array or other data structure that of size related to input. 
	 * time complexityO(1), a few steps of calculation for each digit till we get the result.
	 */
	public String sizeFormat(int size) {
		if (size < 0||size>1000000000)
			return "";

		int[] threeDigAndZeroCount = getThreeDigAndCount(size);
//		int threeDig = threeDigAndZeroCount[0];
//		int zeros = threeDigAndZeroCount[1];
		
		String ret= getFormatFromThreeDigAndCount(threeDigAndZeroCount[0],threeDigAndZeroCount[1]);
		
		return ret;
	}

	private int[] getThreeDigAndCount(int size) {
		int[] ret = new int[2];
		// convert to 3 digit;
		int threeDig = size;
		int count = 0;
		while (threeDig >= 10000) {
			threeDig /= 10;
			count++;
		}
		// now from 0 to 9999
		if (threeDig >= 1000) {
			// 1000 to 9999
			// still have 4 digits, need to calculate carry;
			int tail = threeDig % 10;
			int carry = 0;
			if (tail >= 5) {
				carry = 1;
			}
			threeDig = threeDig / 10 + carry;
			count++;

			// now from 100 to 1000(case 999+1)
			if (threeDig == 1000) {
				threeDig = 100;
				count++;
			}
		}
		
//		System.out.print(" for size: "+size);
//		System.out.println("  "+threeDig+"  , "+count);
		
		ret[0] = threeDig;
		ret[1] = count;
		return ret;
	}
	
	/**
	 * three digit and zero counts;
	 */
	public String getFormatFromThreeDigAndCount(int threeDig, int count){
		int shift = 0;
		char[] letters={'B','K','M','G'}; // K,B,M,G.
		
		// count 0 , level 0,  B
		// count 1,2,3, level 1, K
		// count 4,5,6, level 2, M;
		int level=count/3;
		count-= level*3;
		if(count>0){
			level++;
			count-=3;
		}
		shift = count; // shift: -2,-1,0        
		
		// we will not have heading 0 , but will still have tailing 0;
		StringBuilder sb = new StringBuilder();
		int tail;
		if(shift==-2){
			// a.bc
			sb.append(threeDig/100);
			tail=threeDig%100;
			if(tail%10==0){
				tail = tail/10;
				if(tail%10==0){
					//case 100;
					tail = tail/10;
				}
			}		
			if(tail>0){
				sb.append('.');
				sb.append(tail);
			}
		}else if(shift==-1){
			//ab.c
			sb.append(threeDig/10);
			
			tail = threeDig%10;
			if(tail>0){
				sb.append('.');
				sb.append(tail);
			}else{
				// nothing append;
			}
		}else{
			sb.append(threeDig);
		}
		
	    sb.append(letters[level]);
		return sb.toString();
	}
}
