package codes;

import java.util.ArrayList;

public class PermutationSequence {
	
	
	public void go(){
		int n = 2;
		int k = 2;
		String res = getPermutation(n,k);
		System.out.println(" res : "+res);
		
	}
	
	/***********
	 * 
	 * get the kth of n permutation;
	 * 
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
    public String getPermutation(int n, int k) {
        if(n==1){
            return "1";
        }
        
        int[] countArray = new int[n+1]; // count of permutation of each number;
        countArray[1] = 1;
        for(int i=2;i<n;++i){
            countArray[i] = countArray[i-1]*i;
        }
        
        int[] indexArray = new int[n]; // for each position of the final result, it's the  x-th number of the rest of the array;
        
        int remain = k-1; // the second one is the one with index = 1;  we are coders.
        
        for(int i=0;i<n-1;++i){          //don't need the index of position n-1; the last one is the only one that left;
            //each loop to find the [i]th value of the final result (array)
            int round = remain/countArray[n-i-1];
            
            System.out.println(" pos : "+i+"     the rest part have variety : "+countArray[n-i-1]);
            
            indexArray[i] = round;
            remain = remain-round*countArray[n-i-1];
            
            System.out.println(" pos : "+i+"    choose : "+round+" -th smallest number availabe ");
        }
        
        //got all the index; now find numbers;
        
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i=1;i<=n;++i){
            nums.add(i);
        }
        
        StringBuilder sb = new StringBuilder();
        // add the indexed number to the final
        int ind;
        for(int i=0;i<n-1;++i){
            ind = indexArray[i];
            sb.append(nums.get(ind));
            // have to be no mistakes above, or maybe error here;
            nums.remove(ind);
        }
        sb.append(nums.get(0));
        return sb.toString();
    }
}