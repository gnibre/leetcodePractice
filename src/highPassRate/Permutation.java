package highPassRate;

public class Permutation {

	public void go(){
		permutation(3);
		permutation(4);
		permutation(5);
	}
	
	public void permutation(int n){
		int[] ar = new int[n];
		for(int i=0;i<n;++i){
			ar[i] = i+1;
		}
		String head = "";
		pAux(head,ar);
	}
	
	
	private void pAux(String head,int[] num){
		
		if(num.length==1){
			System.out.println(head+num[0]);
			return;
		}
		
		for(int i:num){
			int[] left = new int[num.length-1];
			int ind = 0;
			// for things left.
			for(int n:num){
				if(n!=i){
					left[ind++] = n;
				}
			}
			pAux(head+i,left);
		}
	}
	
	
	
}
