package highPassRate;

import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    
	public void go(){
		
		int[] num = {
				100,4,200,1,3,2
		};
		
		int res = longestConsecutive(num);
		System.out.println(" out: "+res);
		
	}
	
    
    private HashMap<Integer,Integer> conBorderMap = new HashMap<Integer,Integer>();
    private HashSet<Integer> headSet = new HashSet<Integer>(); //second scan for heads to save time.
    /**
     * 
     * i remember i've see this before. but i can't recall it.
     * 
     * o(n) means one/two/three scans. and no more.
     * we group everything together.
     * 
     * so each time wo get a number, we connect them together.
     * 
     * leetcode don't have memory limit most of time, we try use O(n) memeory to save group info.
     * 
     * we need something like a "heap":
     * 1, when a number came, we shall know where shall this number go
     * 2, when we added this number to the "heap", heap self changes. and (longest) consecutive elements info updates.
     * 3, heap need o(nlogn) to add all the element, how can we guarantee o(n)?
     * 
     * update:  connection means  connenct   num-1 and num+1 for num, so it's O(1) time check, just mark each value we got.
     * and for the second scan for the groups, we have all. 
     * 
     * and the array size is N, and maybe numbers in the array from [-M,M] and N<<<<M.
     * so we need hashmap<> to find M->N , the size of hashmap is N.
     * 
     * which value do i save? min of the group? or max of the group?  the other side of the consecutive number.. i guess.
     * 
     * for example given by leetcode [100, 4, 200, 1, 3, 2],
     * map(100,100); map(4,4); map(200,200); map(1,1) ;  
     * map(3,4) -> find 4 so saved right most if you find your right in the map;  !! and save map(4,3)
     * map(2,4 or 1 anything), cause the most important thing is :  map(1,4), map(4,1)
     * 
     * 
     */
    public int longestConsecutive(int[] num) {
        
        for(int n:num){
            // add num to map.
            
            if(conBorderMap.containsKey(n)){
                //not first time for this number. save time
                continue;
            }
            
            boolean left = conBorderMap.containsKey(n-1);
            boolean right = conBorderMap.containsKey(n+1);
            int leftMost;
            int rightMost;
            if(left&&right){
                //connect both side.
                leftMost = conBorderMap.get(n-1);
                rightMost = conBorderMap.get(n+1);
                conBorderMap.put(leftMost,rightMost);
                conBorderMap.put(rightMost,leftMost);
                conBorderMap.put(n,leftMost); //save a record.
                //!!!!!!!!!!!!update head.
                headSet.remove(n+1);
                headSet.add(leftMost); // new head of this group is now leftMost;
                
                continue;
            }
            
            
            
            if(left){
                leftMost = conBorderMap.get(n-1);
                conBorderMap.put(leftMost,n); // connect left.
                conBorderMap.put(n,leftMost);
                headSet.add(leftMost); // new head of a new group. add to set for later index.
            }
            
            if(right){
                rightMost = conBorderMap.get(n+1);
                conBorderMap.put(rightMost,n); // connect rightMost.
                conBorderMap.put(n,rightMost);
                headSet.remove(n+1);
                headSet.add(n); // new head of this group is now n;
            }
            
            if(left||right){
            	continue;
            }
            
            // 	coool, you are along.
            
            conBorderMap.put(n,n);
            
            if(headSet.size()==0){
                headSet.add(n); /// at least add one record to head. cause the max result maybe just 1...
            }
        }
        
        
        //reduce phase
        
        int maxLength = 0;
        int len;
        for(int head:headSet){
System.out.println(" go head: "+head+"     got its rightmost : "+conBorderMap.get(head));
            len = conBorderMap.get(head)-head+1;  
            if(maxLength<len){
                maxLength = len;
            }
        }
        return maxLength;
    }
}
