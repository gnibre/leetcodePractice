package data;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * create this just to recall that , in java , a heap is PriorityQueue
 * 
 * @author Yubing
 *
 */
public class Heap {
	
	
	
	Comparator<Integer> cmp = new Comparator<Integer>(){
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
	};
	PriorityQueue<Integer> p = new PriorityQueue<Integer>(10,cmp);
	
	
	public void go(){
		p.peek();
		
	}
	
	public void add(Object o){
//		p.add(o);
		
	}
	
	//!=================== poll!!!
	public int pull(){
		return p.poll();
	}
	
	public int peek(){
		return p.peek();
	}

}
