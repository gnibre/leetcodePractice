package codes;

import java.util.ArrayList;

import data.Interval;


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class InsertInterval {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        
        int toInsertFrom = newInterval.start;
        int toInsertTo = newInterval.end;
        
        int size = intervals.size();
        
        
        
        // seems simple, cases complex; boundary check need patient;
        // to makes things simple, scan twince;  
        // first one here, just to find two things: 
        // 1 the FIRST interval with right boundary >  startOfNewInsert;    name it I1 ( itervals before I1 are guaranteed to be < new)
        // 2  the FIRST interval that with left boundary > endOfNewInsert;  name it I2 ( itervals after I2 are guaranteed to be >new )
        //after doing 1,and 2,
        //cases :  no I1, do have I2 ==> res:   you program fails, or the input helps you fail
        //cases : no I1 , no T2...... ==> whole list is < new; no overlap;
        //cases:  do have I1, no I2;   I1 and every one behind i1, is overlap with new;
        //cases :  do have I1, do have I2,  [I1,I2) are overlaps; ( specially, when I1 is I2, no over lap actually; and more special:  I1 and I2 are the same and are the first element in the array, means the whole array is > new )
        
        Interval int1 = null;
        Interval int2 = null;
        int p1=0,p2=0;
        Interval tmp;
        for(int i=0;i<size;++i){
            tmp  = intervals.get(i);
            //!!!!!!!!!!!!!!!!!11 added equal case after i see test case;
            if(tmp.end>=toInsertFrom&&int1==null){
                int1 = tmp;
                p1 = i;
            }
            // int2 can also be the same one as int1;
            if(tmp.start>toInsertTo&&int2==null){
                int2 = tmp;
                p2 = i;
                break;
            }
        }
        
        if(int1==null){
            // must be no int2 too;  
            for(Interval inter:intervals){
                res.add(inter);
            }
            res.add(newInterval);
            return res;
        }
        
        // int1 do exist;  so p1 do exist;
        int overlapEnd = 0; // overlap end position;
        if(int2==null){
            // nodes from int1 are all overlap with newone;
            overlapEnd = size;
        }else{
            overlapEnd = p2; // position of int2;
        }
        
        
        int overlapCount = overlapEnd-p1; //[p1,p2)
        
        
        //add stuff before where overlap happens,
        for(int i=0;i<p1;++i){
                res.add(intervals.get(i));
        }
        
        //actually.. no overlap
        if(overlapCount==0){
            res.add(newInterval);
            
            for(int i=overlapEnd;i<size;++i){
                res.add(intervals.get(i));
            }
            return res;
        }
        
        
        // do have overlap;
        
        int overlapLeft,overlapRight;
        
        overlapLeft= Math.min(intervals.get(p1).start,toInsertFrom);
        overlapRight = Math.max(intervals.get(overlapEnd-1).end, toInsertTo);
        
        Interval overLapInterval = new Interval(overlapLeft,overlapRight);
        res.add(overLapInterval);
    
    // add rest
        for(int i=overlapEnd;i<size;++i){
            res.add(intervals.get(i));
        }
        return res;
        
    }
}