package highPassRate;

public class MergeSortArray {
    public void merge(int A[], int m, int B[], int n) {
        
        int totalLen = m+n;
        // ends at p: m+n-1; of array A;
        
        int e = totalLen-1;
        // merge from back(end pos,A[e]) to A[0]
        
        
        int curA = m-1;
        int curB = n-1;
        
        while(curB>-1&&curA>-1){
            // when curB == -1; no more element from B to insert; it's done;  and the same for A, if A is done, add b to the front;
            if(A[curA]<B[curB]){
                A[e] = B[curB];
                curB--;
            }else{
                A[e] = A[curA];
                curA--;
            }
            e--;
        }
        
        // add the rest of B/
        while(curB>-1){
            A[e--]=B[curB--];
        }
    }
}