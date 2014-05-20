package highPassRate;

public class UniqueBinarySearchTrees {
    
    
    /**
     * 
     * complex and no idea...
     * 
     * so, try to setup root with a value, 
     * say, root value is x,  (1<=x<=n)
     * as it's a binary search tree, problem became:
     * 
     * numTrees(n) = sum{for each x,  numTrees(x-1)*numTrees(n-x) }
     * 
     * so, we don't need to image what all the cases will be like and problem have done.
     * 
     * for a dp problem, we save small cases and it will speedup.
     */
    public int numTrees(int n) {
        int[] rec = new int[n+1];
        int sum = 0;
        return numTreesAux(n,rec);
    }
    
    private int numTreesAux(int n,int[] rec){
        if(rec[n]!=0){
            return rec[n];
        }
        if(n==1){
            rec[1] = 1;
            return 1;
        }
        if(n==0){
            return 1; //no node, only 1 case. not 0;
        }
        
        int left,right; // left and right subtree size, if make i the root of the tree of size n.
        int sum = 0;
        for(int i=1;i<=n;++i){
            left = i-1; //<i
            right = n-i; // >i and <=n
            sum+=numTreesAux(left,rec)*numTreesAux(right,rec);
        }
        rec[n] = sum;
        return sum;
    }
    
}
