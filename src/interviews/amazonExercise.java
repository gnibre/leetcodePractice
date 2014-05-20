package interviews;

public class amazonExercise {

	public void test() {
		int[] inputArray = { 
				4, 3, 2, 8 // example case;
//				0,0,0,0 //zeros..
//				0,1,2,3   // zeros 2
//				0,15,0,24 //zeros 3
//				888888,99999999,777777777,66666,555,4444,333// exceed
//				888,999,777,666// normal
//				888,999,777,666,3 // almost exceed Integer.max
//				-777,666,888,-333,2// almost, with neg
		};

		int[] res = getProducts(inputArray);

		if (res == null)
			return;
		for (int i = 0; i < res.length; ++i) {
			System.out.print(" " + res[i]);
		}
	}

	/**
	 * 
	 * say the product of all the member in the list is productAll, if
	 * productAll do not exceed range of long(or even int), it will be sweet.
	 * just fill the result array with value productAll/array[i], but i guess
	 * there will be some test cases will big members. Take care of 0s too.
	 * 
	 * ======================================================== Here I assume
	 * the return type is int array: int[] if the input array is of size 0,
	 * return null, size 1, return array with one element 0; And also assume the
	 * return values for these exceeds the range of int, i will just return
	 * Integer.MAX_VALUE.
	 * ========================================================
	 * 
	 * complexity
	 * 1, to find out what case the input, we scan the list, get productAll and some other statistics
	 *  time O(n), space o(1)
	 * 2 create return array;  time and space O(n)
	 * 3, return result. 
	 * for case 1, case 2 , O(1) as we only modify on element of the result array, or don't modify the array
	 * for case 3,4 O(n), as we modify n elements of the array and for each element we use O(1) step to do that.
	 * 
	 * 
	 * The average runtime for this program will be O(n) and for worst case still O(n), both time complexity and space complexity.
	 * 
	 * ============================
	 */
	public int[] getProducts(int[] inputArray) {

		if (inputArray == null) {
			return inputArray;
		}
		if (inputArray.length == 1) {
			int[] res = new int[1];
			res[0] = 0;
			return res;
		}

		/*
		 * case id: 0 as init, 
		 * case 1: contains 0 , 0s count >1 
		 * case 2: contains  0, 0 count: 1 
		 * case 3: productAll is bigger than Long.maxvalue(or  smaller than min), well,return everyone as max_values as defined;
		 * case 4: productAll <Long.MAX_VALUE, and no zeros, check if it's
		 * exceeds max;
		 */
		int caseId = 0;

		int countZeros = 0;
		boolean exceedLong = false;
		long productAll = 1;
		long tmp;
		for (int i : inputArray) {
			if (i == 0) {
				countZeros++;
				if (countZeros > 1) {
					// break for case 1,
					break;
				}
			} else {
				// only for these i not 0;
				tmp = Long.MAX_VALUE / Math.abs(i);
				if (tmp >= Math.abs(productAll)) {
					// not exceed yet; will get result for case 2 and case 4;
					productAll = productAll * i;
				} else {
					// case 3
					exceedLong = true;
					break;
				}
			}
		}

		// init return result array;
		int[] resArray = new int[inputArray.length];
		for (int i = 0; i < resArray.length; ++i) {
			resArray[i] = 0;
		}

		// case 1
		if (countZeros > 1) {
			// all 0;
			return resArray;
		}

		// case 2, only 1 zero in the array; we will need the product array;
		if (countZeros > 0) {
			for (int i = 0; i < inputArray.length; ++i) {
				if (inputArray[i] == 0) {
					// zero as input
					if (exceedLong) {
						resArray[i] = Integer.MAX_VALUE; // as defined, return
															// this.
					} else {
						resArray[i] = getIntProduct(productAll); // still shall
																	// check
																	// from
																	// long->int;
					}
				}
			}

			return resArray;
		}

		// case 3 , great pre defined value for everyone.
		if (exceedLong) {
			for (int i = 0; i < resArray.length; ++i) {
				resArray[i] = Integer.MAX_VALUE;
			}
			return resArray;
		}

		// case 4, normal case; sweet case; no zeros.
		for (int i = 0; i < resArray.length; ++i) {
			resArray[i] = getIntProduct(productAll / inputArray[i]);
		}
		return resArray;

	}

	private int getIntProduct(long l) {
		if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
			return Integer.MAX_VALUE; // return this as defined..by me
		}
		return (int) l;
	}

}
