package codes;

import java.util.ArrayList;

public class DevideTwoInteger {

	
	public void go(){
		
		int a = 2147483647;
		int b = 2;
		a = -1010369383;
		b = -2147483648;
		a = 5;
		b = 2;
		int res = divide(a,b);
		System.out.println("  a: "+a+"   b: "+b);
		System.out.println("  res: "+res);
		System.out.println("  sys: "+(a/b));
		
	}
	
	private int longToint(long l){
		if(l>0&&l>Integer.MAX_VALUE){
			return Integer.MAX_VALUE;
		}
		if(l<0&&l<Integer.MIN_VALUE){
			return Integer.MIN_VALUE;
		}
		return (int)l;
	}
	
	public int divide(int dividend, int divisor) {

		boolean neg = false;

		long dd = dividend;
		if (dd < 0) {
			neg = !neg;
			dd = -dd;
		}

		long ds = divisor;
		if (ds < 0) {
			neg = !neg;
			ds = -ds;
		}

		if (ds == 1) {
			if (neg) {
				return longToint(-dd);
			} else {
				return longToint(dd);
			}
		}

		ArrayList<Long> timesValue = new ArrayList<Long>();
		// res in Decimal
		// ArrayList<String> res = new ArrayList<String>();
		String res = "";
		long tenTimes = ds;

		long tDS = ds;
		// timesValue = ten
		long rem = dd - tDS;
		// int time=0;
		int index = 0;
		long base;
		timesValue.add(ds); // for timesValue(0), it's ds it self.
		while (rem > 0) {
			// while rem>0, we shall try bigger number to divide this dd.
			// our strategy is easy to understand, we use decimal numbers, so we
			// try "multip" ds with 10 each time.
			base = timesValue.get(index); // it's ds for the first time, and
											// 10*ds for second, 100*ds third
											// and ..
			tenTimes = base;
			for (int i = 0; i < 9; ++i) {
				tenTimes += base;
			}

			System.out.println(" tenTimes is : "+tenTimes);
			if(tenTimes>Integer.MAX_VALUE){
				//exceed max int value.
				break;
			}else{
				timesValue.add(tenTimes);
				index++;
				rem = dd - tenTimes;
			}
		}

		// so we got the maxone needed in the array, we can do minus from here.

		rem = dd;
		long m;
		for (int i = timesValue.size() - 1; i > -1; --i) {
			m = timesValue.get(i);
			int count = 0;
			if (m > rem) {
				// m too big, got an 0 as div res.
				res += "0";
				continue;
			} else {
				while (rem >= m) {
					rem = rem - m;
					count++;
				}
				res = res + "" + count;
			}
		}

		int vres = Integer.valueOf(res);

		if (neg) {
			return -vres;
		} else {
			return vres;
		}

	}
}
