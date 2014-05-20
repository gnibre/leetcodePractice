package highPassRate;

import java.util.ArrayList;

public class PascalsTriangle {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(numRows==0){
            return res;
        }
        
        ArrayList<Integer> firstRow = new ArrayList<Integer>();
        firstRow.add(1);
        res.add(firstRow);
        if(numRows == 1){
            return res;
        }
        ArrayList<Integer> lastRow;
        ArrayList<Integer> thisRow;
        //i is the level count;
        for(int i=2;i<=numRows;++i){
            lastRow = res.get(i-2); //-2 cause first level named level 1, but saved as index 0 in array;
            thisRow = new ArrayList<Integer>();
            int size = lastRow.size();
            thisRow.add(1);
            for(int s=0;s<size-1;++s){
                thisRow.add(lastRow.get(s)+lastRow.get(s+1));
            }
            thisRow.add(1);
            res.add(thisRow);
        }
        return res;
    }
}