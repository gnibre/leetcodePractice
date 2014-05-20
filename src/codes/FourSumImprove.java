package codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FourSumImprove {
    
	
	public void go(){
		
		int target = 1;
		
		int[] num = {
//				-3,-1,0,2,4,5
//				0,0,0,0
//				2,1,0,-1
				-1,0,1,2,-1,-4
		};
		target = -1;
		
		ArrayList<ArrayList<Integer>> res = fourSum(num,target);
		ArrayList<Integer> one;
		for(int i=0;i<res.size();++i){
			System.out.print("  ind: "+i+"      : ");
			one = res.get(i);
			for(int j=0;j<one.size();++j){
				System.out.print(" "+one.get(j));
			}
			System.out.println();
		}
	}
	
	
	/**
	 * feel so, complex,  can the old one be improved?
	 * how about just save everything, and scan everything?
	 * 
	 * check FourSumImprove
	 * 
	 */
	 
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null||num.length<4){
            return res;
        }
        
        HashSet<String> dupResChecker = new HashSet<String>();
        
        Arrays.sort(num);
        
        // key(sum of a pair) -> value(the pair object)
        HashMap<Integer,ArrayList<Pair>> pMap = new HashMap<Integer,ArrayList<Pair>>();
        
        // add all, o(n^2)
        ArrayList<Pair> al;
        for(int i=0;i<num.length;++i){
        	for(int j=i+1;j<num.length;++j){
        		Pair p = new Pair(i,j);
        		int k = num[i]+num[j];
        		if(pMap.containsKey(k)){
        			al = pMap.get(k);
        		}else{
        			al = new ArrayList<Pair>();
        		}
        		al.add(p);
        		pMap.put(k, al);
        	}
        }
        
        // check all;
        
        int half = target/2;
        for(int k:pMap.keySet()){
        	if(k>half){
        		// focus key <=half; so we can be sure first pair with smaller sum;
        		continue;
        	}
        	int aok = target - k;
        	if(aok==k){
        		//handle same k case here;
        		// worst case for here; all the pair we get are in the same slot;
        		// same the case is target=0; the array milions of 0;
        		// optimize: keep at most 4 of the same number;
        		
        		
        		// still bad case for most of numbers fell in to here;
        		
        		ArrayList<Pair> pl = pMap.get(k);
        		for(int i=0;i<pl.size()-1;++i){
        			for(int j=i+1;j<pl.size();++j){
        				OneResult or = getResultFromTwoPairs(num,pl.get(i),pl.get(j));
        				if(or!=null){
        					String dupCheckStr = or.toString();
        					if(dupResChecker.contains(dupCheckStr)){
        						//dup;
        					}else{
        						dupResChecker.add(dupCheckStr);
        						res.add(or.toArray());
        					}
        				}
        			}
        		}
        		
        		
        	}else{
        		// handle two pair;
        		ArrayList<Pair> pl1 = pMap.get(k);
        		ArrayList<Pair> pl2 = pMap.get(aok);
        		if(pl2==null){
        			continue;
        		}
//        		System.out.println("-------  k :"+k+"   anotherk: "+aok);
        		for(Pair p1:pl1){
        			for(Pair p2:pl2){
        				OneResult or = getResultFromTwoPairs(num,p1,p2);
        				if(or!=null){
        					String dupCheckStr = or.toString();
//        					System.out.println(dupCheckStr);
        					if(dupResChecker.contains(dupCheckStr)){
        						//dup;
        					}else{
        						dupResChecker.add(dupCheckStr);
        						res.add(or.toArray());
        					}
        				}
        			}
        		}
        	}
        }
        
        
        // worst case analysis;
        // we got at most n^2 result in the hashmap; if we need to compare each one with each
        // it will be a disaster;
        //same the array is 1,2,3,4,5,6,....100; here target is 200;
        // there are 50(n/2) pairs that with pair.sum = 100;         
        // compare all these pair cost n/2^2 => n^2;      will that be too much;?
        
        
        return res;
    }
    
    /**
     * value is index in the array; and f<s as it's index;
     */
    private class Pair{
        int f;
        int s;
        public Pair(int o, int w){
            f = o;
            s = w;
        }
    }
    
    private class OneResult{
    	int[] ary = new int[4];
    	public String toString(){
    		String s= "";
    		for(int v:ary){
    			s+=v;
    		}
    		return s;
    	}
    	public ArrayList<Integer> toArray(){
    		ArrayList<Integer> al = new ArrayList();
    		for(int i=0;i<ary.length;++i){
    			al.add(ary[i]);
    		}
    		return al;
    	}
    	public OneResult(int x,int y,int z,int w){
    		ary = new int[4];
    		ary[0] =x;
    		ary[1] =y;
    		ary[2] =z;
    		ary[3] =w;
    		Arrays.sort(ary);
    	}
    }
    
    private OneResult getResultFromTwoPairs(int[] num,Pair p1,Pair p2){
    	if(p1.f==p2.f||p1.f==p2.s||p1.s==p2.f||p1.s==p2.s){
    		return null;
    	}
    	OneResult or = new OneResult(num[p1.f],num[p1.s],num[p2.f],num[p2.s]);
    	return or;
    }
}
