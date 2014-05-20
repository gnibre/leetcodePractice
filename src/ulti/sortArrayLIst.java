package ulti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.Interval;

public class sortArrayLIst {
	public void go(){
		
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		
		
		Comparator<Interval> com = new Comparator<Interval>(){
			@Override
			public int compare(Interval i1, Interval i2) {
				return i1.start-i2.start;
			}
		};
		
		
		Collections.sort(intervals, com);
		

		
		
		
	}
}
