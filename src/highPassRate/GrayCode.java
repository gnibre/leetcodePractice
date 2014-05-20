package highPassRate;

import java.util.ArrayList;

public class GrayCode {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(n<1){
            res.add(n);
            return res;
        }
        if(n==1){
            res.add(0);
            res.add(1);
            return res;
        }
        // we only want one instance? make one...
        // if we know the graycode of g(n); how to get g(n+1)?
        // add content of g(n) onebyone from front to back, then add 2^n to each element from back to front;
        
        ArrayList<Integer> rec = grayCode(n-1);
        int l = rec.size();
        for(int i:rec){
            res.add(i);
        }
        
        int twoToN =1; // 2 to n-1 actually
        for(int i=1;i<n;++i){
            twoToN*=2;
        }
        for(int i=l-1;i>-1;--i){
            res.add(rec.get(i)+twoToN);
        }
        return res;
    }
}