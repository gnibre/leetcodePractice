package other;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import ulti.Printer;

public class BitOperation {
	
	
	
	public void go(){
		
		int count = 6; //4 bit, so max is 1111 =15;
		// numbers are 0 to 15;
		
		int base =1;
		for(int i=0;i<count;++i) base*=2;
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<base;++i){
			al.add(i);
//			System.out.println(Integer.valueOf(i).byteValue());
		}
		int toFind = 38;
		al.add(toFind);
		int find = findExtraNumber(al,count);
		
		System.out.println(" find the number : "+find+"     same? : "+(find==toFind));
	}
	
	private int findExtraNumber(ArrayList<Integer> al,int count){
		
		// only count 1, and 0 is where not 1;
		int[] bitOneCount = new int[count]; // if no count given, use 32; as integer size; 
		//bit operation for bit count;
		
		System.out.println(" and value :  "+(16&1));
		
		for(int v:al){
			int p =0;
			System.out.println(" v added : "+v);
			while(v>0){
				bitOneCount[p] += v&1;
				v = v>>1;
				p++;
			}
			Printer.pArray(bitOneCount);
		}
		
		System.out.println(" got count 1 result: ");
		Printer.pArray(bitOneCount);
		
		//count of 1s shall be 2^(n-1) at each position;
		// the one more than 2^(n-1) and must be 2^(n-1)+1;  is the extra;
		int base = 1;
		for(int i=0;i<count-1;++i){
			base*=2;
		}
		int res =0;
		for(int p=bitOneCount.length-1;p>-1;--p){
			res*=2;
			if(bitOneCount[p]>base){
				res+=1;
//				System.out.println(" res is :"+)
			}
		}
		return res;
	}

}
