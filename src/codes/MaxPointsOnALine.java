package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MaxPointsOnALine {
    
	/**
	 * Definition for a point.
	 */
	 private class Point {
	     int x;
	     int y;
	     Point() { x = 0; y = 0; }
	     Point(int a, int b) { x = a; y = b; }
	     public void print(){
	    	 System.out.print(toString());
	     }
	     public String toString(){
	    	 return "["+x+","+y+"]";
	     }
	 }
	 
	 
	 private Point[] parseStringToPl(String s){
		 String[] sp = s.split("AAA");
		 int len = sp.length;
		 Point[] res = new Point[len];
		 for(int i=0;i<len;++i){
//			 System.out.print(" [ "+sp[i]+" ]");
			 res[i] = toPoint(sp[i]);
		 }
		 return res;
	 }
	 
	 public void go(){
		 
		 Point p1 = new Point(1,1);
		 Point p2 = new Point(1,1);
		 Point p3 = new Point(3,3);
		 Point p4 = new Point(3,3);
		 Point p5 = new Point(3,3);
//		 Point p1 = new Point(3,1);
//		 Point p2 = new Point(12,3);
//		 Point p3 = new Point(3,1);
//		 Point p4 = new Point(-6,-1);
		 
//		 Point[] pl = {p1,p2,p3,p4,p5};
		 
		 String s = "-240,-657AAA-27,-188AAA-616,-247AAA-264,-311AAA-352,-393AAA-270,-748AAA3,4AAA-308,-87AAA150,526AAA0,-13AAA-7,-40AAA-3,-10AAA-531,-892AAA-88,-147AAA4,-3AAA-873,-555AAA-582,-360AAA-539,-207AAA-118,-206AAA970,680AAA-231,-47AAA352,263AAA510,143AAA295,480AAA-590,-990AAA-236,-402AAA308,233AAA-60,-111AAA462,313AAA-270,-748AAA-352,-393AAA-35,-148AAA-7,-40AAA440,345AAA388,290AAA270,890AAA10,-7AAA60,253AAA-531,-892AAA388,290AAA-388,-230AAA340,85AAA0,-13AAA770,473AAA0,73AAA873,615AAA-42,-175AAA-6,-8AAA49,176AAA308,222AAA170,27AAA-485,-295AAA170,27AAA510,143AAA-18,-156AAA-63,-316AAA-28,-121AAA396,304AAA472,774AAA-14,-67AAA-5,7AAA-485,-295AAA118,186AAA-154,-7AAA-7,-40AAA-97,-35AAA4,-9AAA-18,-156AAA0,-31AAA-9,-124AAA-300,-839AAA-308,-352AAA-425,-176AAA-194,-100AAA873,615AAA413,676AAA-90,-202AAA220,140AAA77,113AAA-236,-402AAA-9,-124AAA63,230AAA-255,-118AAA472,774AAA-56,-229AAA90,228AAA3,-8AAA81,196AAA970,680AAA485,355AAA-354,-598AAA-385,-127AAA-2,7AAA531,872AAA-680,-263AAA-21,-94AAA-118,-206AAA616,393AAA291,225AAA-240,-657AAA-5,-4AAA1,-2AAA485,355AAA231,193AAA-88,-147AAA-291,-165AAA-176,-229AAA154,153AAA-970,-620AAA-77,33AAA-60,-111AAA30,162AAA-18,-156AAA425,114AAA-177,-304AAA-21,-94AAA-10,9AAA-352,-393AAA154,153AAA-220,-270AAA44,-24AAA-291,-165AAA0,-31AAA240,799AAA-5,-9AAA-70,-283AAA-176,-229AAA3,8AAA-679,-425AAA-385,-127AAA396,304AAA-308,-352AAA-595,-234AAA42,149AAA-220,-270AAA385,273AAA-308,-87AAA-54,-284AAA680,201AAA-154,-7AAA-440,-475AAA-531,-892AAA-42,-175AAA770,473AAA118,186AAA-385,-127AAA154,153AAA56,203AAA-616,-247";
		 Point[] pl = parseStringToPl(s);
		 
		 
//		 [(-435,-347|-435,-347|609,613|-348,-267|-174,-107|87,133|-87,-27),
//		  (-609,-507|435,453|-870,-747|-783,-667|0,53|-174,-107|783,773),
//		  (-261,-187|-609,-507|-261,-187|-87,-27|87,133|783,773|-783,-667),
//		  (-609,-507|-435,-347|783,773|-870,-747|87,133|87,133|870,853),
//		  (696,693|0,53|174,213|-783,-667|-609,-507|261,293|435,453|261,293|435,453)]
//		 Point p1 = new Point(0,0);
//		 Point p2 = new Point(0,0);
//		 Point[] pl = {p1,p2};
		 
		 int m = maxPoints(pl);
		 System.out.println(" res : "+m);
	 }
	 
	
	
    //find an angle, add points to this angle.;  points are prsented in string format :  "x,y" 
	 //because two points of same position can't be found by a hashset.
    private HashMap<Float,HashSet<String>> anglePointMap = new HashMap<Float,HashSet<String>>();
    // <x , < y, count>> // String of the location : "x,y" is the key, count is the 
    private HashMap<String,Integer> samePointCountMap = new HashMap<String,Integer>();
    
    
    /**
     * mistakes i made:   
     * 
     * 1, point can be at teh same possition
     * 2, hashset<Point> can't tell if two points are at the same position. no content check of two points. we shall get hash value of points.
     * 3, possibility of each angle, shall count point number but not only the hashset size.
     * 
     * @param points
     * @return
     */
    public int maxPoints(Point[] points) {
        
    	
    	
        //try each angle, 
        //n^2, we got at most this much angles,cost n^2
        // and we calc location to the original point of this angle? o(n^3) to calculate each point. and o(n) space(hashmap) to save the result.
        
        // save result in the hashset.  // map stage.
        mapAllThePossibleAngles(points);
        
        //no point /only one point or same possition for every point.
        if(anglePointMap==null||anglePointMap.size()==0){
        	return points.length;
        }
        
        // no point or all the same point.
        
        
        // for all the possible angles, calc distance of each point with this angle, from the saved angles.
        // reduce stage.
        int ret = reduceEachAngle();
        return ret;
    }
    
    private String toString(Point p){
		 return p.x+","+p.y;
	 }
    private Point toPoint(String s){
    	String[] ss = s.split(",");
    	int x = Integer.valueOf(ss[0]);
    	int y = Integer.valueOf(ss[1]);
    	Point p = new Point(x,y);
    	return p;
    }
    
    private void mapAllThePossibleAngles(Point[] points){
        int len = points.length;
        HashSet<Integer> alreadyCountSet = new HashSet<Integer>();
        for(int i=0;i<len;++i){
            for(int j=i+1;j<len;++j){
                //got a pair, j always j>i;
                float angle = getAngle(points[i],points[j]);
                
                if(angle==Float.MAX_VALUE&&points[i].y==points[j].y&&!alreadyCountSet.contains(i)){
                	//two points have same position, don't add to map.
                	if(samePointCountMap==null){
                		samePointCountMap = new HashMap<String,Integer>();
                	}
                	String pstring = toString(points[i]);
                	int count;
                	if(samePointCountMap.containsKey(pstring)){
                		count = samePointCountMap.get(pstring);
                		count++;
                		samePointCountMap.put(pstring, count);
                	}else{
                		samePointCountMap.put(pstring, 2);//start from 2;
                	}
                	alreadyCountSet.add(j);
//                	if(samePointCountMap.containsKey(points[i].x)){
//                		HashMap map = samePointCountMap.get(points[i].x);
//                		if(map.containsKey(points[i].y)){
//                			int count = (int) map.get(points[i].y);
//                			count++;
//                			map.put(points[i].y, count);
//                		}else{
//                			map.put(points[i].y, 2);
//                		}
//                	}else{
//                		HashMap record = new HashMap<Integer,Integer>();
//                		record.put(points[i].y, 2);
//                		samePointCountMap.put(points[i].x, record);
//                	}
                }else{
                	
                	addToMap(angle,points[i],points[j]);
                }
            }
        }
    }
    
    
    
    private int samePointCount(Point p){
    	if(samePointCountMap==null){
    		return 1;
    	}
    	String ps = toString(p);
    	if(samePointCountMap.containsKey(ps)){
    		int ret = samePointCountMap.get(ps);
//    		System.out.println(" this point: "+p.toString()+" got cout: "+ret);
    		return ret;
    	}
    	return 1;
    }
    
    private int samePointCount(String ps){
    	if(samePointCountMap==null){
    		return 1;
    	}
    	if(samePointCountMap.containsKey(ps)){
    		int ret = samePointCountMap.get(ps);
//    		System.out.println(" this point:[ "+ps+" ]got cout: "+ret);
    		return ret;
    	}
    	return 1;
    }
    
    
    private void addToMap(float angle,Point p1,Point p2){
    	if(angle==Float.MAX_VALUE)
    	System.out.println("[[[[[[MMMMMAX]]]]]]  add to map ,  angle: "+angle+" points:  "+p1.toString()+"  "+p2.toString());
        if(anglePointMap==null){
            anglePointMap = new HashMap<Float,HashSet<String>>();
        }
        HashSet<String> pSet = null;
        if(anglePointMap.containsKey(angle)){
            pSet = anglePointMap.get(angle);
        }else{
            pSet = new HashSet<String>();
            anglePointMap.put(angle,pSet);
        }
        pSet.add(toString(p1));
        pSet.add(toString(p2));
    }
    
    private float getAngle(Point p1,Point p2){
        if(p1.x==p2.x){
            return Float.MAX_VALUE;
        }
        // if(p1.y==p2.y){
        //     return 0;
        // }
        float f = (float)(p1.y-p2.y)/(p1.x-p2.x);
        return f;
    }
    
    // y- position
    // float is not enough for this method, cause angle of float don't guarantee it.
//    private float getDistance(Point p,float angle){
//        if(angle==Float.MAX_VALUE){
//            return Float.MAX_VALUE;
//        }
//        return (p.y - angle*p.x);
//    }
    
    private int reduceEachAngle(){
        if(anglePointMap==null){
            return 0;
        }
        Set<Map.Entry<Float, HashSet<String>>> set = anglePointMap.entrySet();
        int best = 0;
        float bestAngle;
        for(Map.Entry entry:set){
            Float key = (Float)entry.getKey();
            HashSet<String> hs = anglePointMap.get(key);
            int count = reduceFromHashSet(hs,key,best);
            if(count>best){
                best = count;
                bestAngle=key;
            }
        }
        return best;
    }
    
    private int reduceFromHashSet(HashSet<String> hs,float angle,int best){
//    	if(hs.size()>19)
//    	System.out.println("============= reducing anlge: "+angle+"  hashset of points size: "+hs.size());
//        if(hs.size()<=best){
            // this can't be the best one.
        	//mistake, there are many points at the same position. we can't decide from only the size, as if the hashset is only size 3, but each point have 10 brothers.
//            return 0;
//        }
    	
    	int maxp = 0;
        for(String ps:hs){
            maxp+=samePointCount(ps);
        }
        if(maxp<=best){
            // this can't be the best one.
            return 0;
        }
        
        // points of a line are grouped and marked count of one point.
        HashMap<String,Integer> pointGroupCountMap = new HashMap<String,Integer>();
        ArrayList<Point> pal = new ArrayList<Point>();
        ArrayList<Integer> countal = new ArrayList<Integer>();
        boolean findGroup = false;
        Point pg;
        int bestInSet =1;
        float ag;
        
        Point p;
        for(String ps:hs){
        	System.out.println("  --> "+ps+" <--    from angle: "+angle);
        	p = toPoint(ps);
        	findGroup = false;
        	for(int i=0;i<pal.size();++i){
        		pg = pal.get(i);
        		if(pg.x==p.x&&pg.y==p.y){
        			System.out.println("============== set of point failed, same point appear twice in one set");
        		}
        		ag = getAngle(pg,p);
        		System.out.println(" got angle of two points : "+ag+"     "+pg.toString()+"  "+p.toString());
        		if(ag==angle){
        			findGroup = true;
        			int count = countal.get(i)+samePointCount(ps);
        			countal.set(i,count);
        			if(count>bestInSet){
        				bestInSet = count;
        			}
        			System.out.println(" this point group with : "+pg.toString()+"   group count: "+count);
        			break;
        		}
        	}
        	if(!findGroup){
        		pal.add(p);
        		int c = samePointCount(ps);
        		countal.add(c);
        		if(c>bestInSet){
        			bestInSet = c;
        		}
        		System.out.println(" this point create new group, cout: "+c);
        	}
        }
//        System.out.println("\n count result for this angle:  "+bestInSet);
        return bestInSet;
    }
    
    
    
}