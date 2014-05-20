package data;

import java.util.Comparator;

public class Interval {

	public int start;
	public int end;

	public Interval() {
		start = 0;
		end = 0;
	}

	public Interval(int s, int e) {
		start = s;
		end = e;
	}
	
	
	/**
	 * 
	 * added comparator to save time for compare;
	 */
	Comparator<Interval> compStart = new Comparator<Interval>(){
		@Override
		public int compare(Interval i1, Interval i2) {
			return i1.start-i2.start;
		}
	};
}
