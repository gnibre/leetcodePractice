package codes;

public class FirstMissingPositive {

	public void go() {

		int[] A = { 1, 3, 4 };

		int res = firstMissingPositive(A);
		System.out.println(" res: " + res);
	}

	/**
	 * 
	 * 1, A is of size n; 2, remove items > n; 3, no matter how many items are
	 * removed in process2, it's not important, the most important this is that:
	 * 4: the number we consern is <n (of cause count also <n); devide and
	 * counquer : we can all ways cut the list size to at most n/2; picking some
	 * pivot;
	 * 
	 * -======= read steps in the Aux function; so will recall why this one is
	 * so simple;
	 */
	public int firstMissingPositive(int[] A) {
		if (A == null || A.length < 1)
			return 1;
		int n = A.length;
		int v;
		int replaced;
		for (int i = 0; i < n; ++i) {
			v = A[i];
			// valid number, put it at rigth position; other numbers
			// automaticlly ignored;
			while (v > 0 && v <= n) {
				// going to index v-1; the old value is replaced;
				replaced = A[v - 1];
				if (v == replaced) {
					// already at the right position;
					// or do have duplicate; but ok we know you are in the list;
					// we can go next;
					break;
				}
				// else, need to find a position for this one who is replaced
				// and now homeless
				A[v - 1] = v;
				v = replaced;
			}
		}

		for (int i = 0; i < n; ++i) {
			if (A[i] != i + 1) {
				return i + 1;
			}
		}
		return n + 1; // we got all the number from 1 to n ; you must be
						// kidding;
	}

	/**
	 * aux so we don't need to copy list, just operate the old one;
	 */
	// private int firstMissingPositiveAux(int[] A, int s,int e, int
	// numberFrom){
	// int piv = numberFrom+ n/2; //unlike quick sort, with random position to
	// get piv; we can choose a good piv, no matter there are this number int
	// the array or not;

	// numbers can be the result [1,n];
	// save numbers<=piv in position[0,piv-1]
	// save numbers>piv in pos [piv,n-1] //care boundary;
	// ignore other numbers( or maybe overwrited at each round)

	// boundary care; total number n; from : numberFrom; piv is numberFrom+n/2;
	// can be [numberFrom,numberFrom+n/2] total 1+n/2 numbers in the first half;
	// index from s to s+n/2;
	// the rest ( value> piv and value<=numberFrom+n-1 ) are saved at position
	// from [numberFrom+n/2+1, e];
	// value >numberFrom+n-1 are ignored;

	// !!!!!!!!!!! ok, if we can have duplicate numbers in the array? what if we
	// can't save all the number in the first half?
	// for example. if we got a test case that all the content is 1...
	// as I don't miss this practice, ill finish coding of non-duplicate first;

	// practice sucks, and failed.;
	// got a better way to do this: bracket sort;
	// array A of size n;
	// rule and only rule that very simple: if you see number 1<=x<=n; put it at
	// index x-1;
	// one scan and works done;
	// }

	int ff = 0;

	/**
	 * aux so we don't need to copy list, just operate the old one;
	 */
	private int firstMissingPositiveAux(int[] A, int s, int e, int numberFrom) {
		System.out.println(" from s: " + s + " end: " + e + "   number:"
				+ numberFrom);
		if (ff++ > 100)
			return 0;
		int n = e + 1 - s;
		if (n == 1) {
			if (A[s] == numberFrom) {
				return numberFrom + 1;
			} else {
				return numberFrom;
			}
		}
		int piv = numberFrom + n / 2; // unlike quick sort, with random position
										// to get piv; we can choose a good piv,
										// no matter there are this number int
										// the array or not;

		// numbers can be the result [1,n];
		// save numbers<=piv in position[0,piv-1]
		// save numbers>piv in pos [piv,n-1] //care boundary;
		// ignore other numbers( or maybe overwrited at each round)

		// boundary care; total number n; from : numberFrom; piv is
		// numberFrom+n/2;
		// can be [numberFrom,numberFrom+n/2] total 1+n/2 numbers in the first
		// half; index from s to s+n/2;
		// the rest ( value> piv and value<=numberFrom+n-1 ) are saved at
		// position from [numberFrom+n/2+1, e];
		// value >numberFrom+n-1 are ignored;

		// !!!!!!!!!!! ok, if we can have duplicate numbers in the array? what
		// if we can't save all the number in the first half?
		// for example. if we got a test case that all the content is 1...
		// as I don't miss this practice, ill finish coding of non-duplicate
		// first;
		//

		// cursor to scan all the number;
		int lCur = s;
		int rCur = e;
		// cursor to save values;
		int lSaved = s;
		int rSaved = e;
		// scan all the array;
		while (lCur <= rCur) {
			while (lCur <= rCur && A[lCur] <= piv) {
				// auto saved; even lSaved==lCur; we dont' care;
				A[lSaved] = A[lCur];
				lCur++;
				lSaved++;
			}
			while (lCur <= rCur && A[lCur] > numberFrom + n - 1) {
				// ignore useless number ; skip
				lCur++;
			}
			// value at lCur now ready to be swapped; for the second half;

			while (lCur <= rCur && A[rCur] > piv && A[rCur] < numberFrom + n) {
				A[rSaved] = A[rCur];
				rCur--;
				rSaved--;
			}
			while (lCur <= rCur && A[rCur] > numberFrom + n - 1) {
				// ignore useless number ; skip
				rCur--;
			}
			// value at rCur now ready to be swapped;

			if (lCur < rCur) {
				// swap when we have two numbers cause maybe
				// lCur==lSaved&&rCur==rSaved; we don't have extra space for
				// numbers to buff;
				int swap = A[lCur];
				A[lSaved] = A[rCur];
				A[rSaved] = swap;
				lSaved++;
				rSaved--;
				lCur++;
				rCur--;
			}
		}

		if (lSaved == s + n / 2) {
			// first half full. first missing in the second half;
			return firstMissingPositiveAux(A, rSaved + 1, e, piv + 1);
		} else {
			return firstMissingPositiveAux(A, s, lSaved - 1, numberFrom);
		}
	}

}