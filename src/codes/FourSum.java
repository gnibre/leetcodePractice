package codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FourSum {
    
	
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
	
	
	
	
	
    /***
     * proofed best algorithm is O(N^CEIL(k/2)); in this one, k = 4.  best algorithm is O(N^2);
     * while when K = 3, best algorithm is also O(N^2);  ceil(k/2) =2;
     * 
     * how can k = 4 the same as k = 3? don't know'
     * but atleast we know that, sorting, which takes O(NLOGN) is ok to use.
     * 
     * after sort.
     * 1: 3 of 4 from <0?       pick 1 and subquestion of pick3(O(N^2)) , each number o(N^2) , it's O(N^3)
     * 
     * pick any 2 number out of 4, already cost O(N^2), how to do the rest liek compare in O(1)? 
     * O(1) ,only hashtable can do that. if we dont' care about space we use.
     * 
     * try use O(N^2) space for a hashmap, see if oj warns me..
     * 
     * 
     * 
     * 
     * 
     *  learned from some online code:  for the final result dup check
     *  you can change res into string like "a","b","c","d"; so the string shall be unique;
     * 
     */ 
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null||num.length<4){
            return res;
        }
        
        HashMap<Integer,ArrayList<Pair>> mp = new HashMap<Integer,ArrayList<Pair>>(); //save sum -> pair.
        // use another map to save count; all the cases makes it so many cases to manage.
        HashMap<Integer,Integer> countMap = new HashMap<Integer,Integer>(); 
        
        Arrays.sort(num);
        // only sort is not enough, for the duplicate cases, we still need to know count of each number;
        
        int v = num[0];
        int count = 1;
        int[] newNum = new int[num.length];
        int ind = 0;
        for(int i=1;i<num.length;++i){
            if(v==num[i]){
                // dup value;
                count++;
                continue;
            }else{
                // new value came, hanle old case frist;
                newNum[ind++] = v;
                if(count>1){
                    countMap.put(v,count);
                }
                // new values here
                v = num[i];
                count = 1;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IF FORGET THIS CASE!
        //////////////////after quit for loop( case the last element is dup) or not;
        newNum[ind++] = v; //last v;
        if(count>1){
            countMap.put(v,count);
        }
        
        
        // new size of the array;
        // saved int newNum[]
        int size = ind;
        int sum;
        
        System.out.println(" size of new: "+size);
        
        // for a common case,  for number a<=b<=c<=d is the result; 
        // when focusing pairs, we will get (a,b)(a,c)(a,d) (b,c)(b,d) (c,d);
        // if a==b; it's like   :  (a,b)(a,c)(a,d) (c,d) when we delete dup pairs(b,c) and (b,d) ; target = a*2+c+d;
        //      so we will find case I (a,b)(c,d) ; or case II(a,c)(a,d);  for the second case, (a,c) and(a,d) have 'a' of same index;
        // so we delete case II; 
        
        // if b==c, it's like a==b;   (a,b)(a,d) (b,c)(b,d) ,deleted (a,c) and (c,d);  
        //   case (a,d)(b,c) or case (a,b) (b,d) ; and we will delete (a,b) and (b,d) case cause b are from same index;
        // i think same for c==d; as a==b
        // so for a==b==c?   anti dup map result is like: (a,b) (a,d)  ; others are all delete; 
        // we get reuslt (a,b),(a,d) but cause we got a from fxxking same index; we failed this one;
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! pair check sucks, we had to back and take care of 3 number same case....
        // so at last, i giveup and start to count number of each element;...
        
        
        // O(N^2) , saved in a big.. hashmap;
        for(int i=0;i<size;++i){
            if(countMap.containsKey(newNum[i])){
                // for duplicate. in the map means do have dup;
                sum = newNum[i]*2;
                Pair newP = new Pair(newNum[i],newNum[i]);
//                mp.put(sum,newP);
                mapAdd(mp,sum,newP);
            }
            
            for(int j=i+1;j<size;++j){
                sum = newNum[i]+newNum[j];
                Pair newP = new Pair(newNum[i],newNum[j]);
                // in which case do we need duplicate number? like (2,3) (2,3) from different index of cause.
            // only case is the target is 10, 10 = 5*2; so we need pairs of summon 5 ;like (1,4);(2,3); even same pair.
            // array is 222222223334, we got a 2233 as result. for another result, like 2224, it's not same pair.
            // so it's ok to delete pairs with duplicate content, only except case that target is 2 times pair sum;
            // and another thing we found is that, when (2,3),(2,3) is a pair of result,  (2,2),(3,3) is too;
            // so it's safe to delete duplicate (2,3),(2,3); 
            //!!!!!!!!!!!!!!!!!!!!!! but only thing to take care is that like ,  array is : 22222222223333566778 and target is 8;
            // if we delete dup (2,2) pair, we get no result;
            // but to keep the dup pair is not good, hard to manage and makes mistakes,  
            //so we had to take care of this specail number :  4*x = target;(not only x = target/4)            // 
            // we use other method to check taht; for the other case:
            // (for case of k=4) pick 4 numbers.
            // we just proved we can delete all the duplicate pairs( same number/value, no matter what index/position is)
            
            //! later i found out that duplicate thing ; for case that three numbers are same...
                // mapAntiDupAdd(mp,sum,newP);
                mapAdd(mp,sum,newP);
//                mp.add(sum,newP); // no duplicate any more, list is specially handled.
            }
        }
        
        int half = target/2;
        for(int value:mp.keySet()){
            // only pick value<0 we can make sure that we will not pick duplicate pair. ( like first pick sum=7 and sum=)
            // no duplicate pair means no duplicate index;
            // but no duplicate index don't guarantee no duplicate number;
            if(value<half&&mp.containsKey(target-value)){
            	System.out.println("try add pair of :  "+value+"   and "+(target-value));
                addRes(res,mp.get(value),mp.get(target-value),countMap);
            }
        }
        
        // for the value is half the target, it's different to the above one;
        if(target==half*2&&mp.containsKey(half)){
            addResEqualCase(res,mp.get(half),countMap);
        }
        
        return res;
    }
    
    /**
     *  shall take care duplicate numbers( different index, but same value);
     * 
     * as pairs in al1 sum < pairs in al2;  there maybe duplicate element in al1 and al2, but will not add duplicate case twice;
     * 
     * say abcd is the result,  a+b< half, c+d>half;   a+c<half ; b+d>half;  we will get (a,b)(c,d) and will get(a,c) (b,d) later;
     * 
     * we know in each pair,  f1<s1 as we added (index i and index j) , and so f2<s2;
     * and also al1 are in the lower half and al2 are in the higher half
     * 
     * it maybe I  f1<s1<f2<s2 ; or  II f1<f2<s1<s2;   or  III f2<f1<s1<s2; 
     * for case III, we can also found him in (f2<f1), (s1<s2) pair;
     * for example,  it's 1,2,5,9;   we may get (1,5) and (2,9) and later (2,5)and(1,9); we accept only (1,2) (5,9)
     * we only accept  1<2; just that easy;
     * 
     * if it's 1 4 5 9;  we may get(1,4) +(5,9) and later (1,5)+(4,9); we only accept (1,4) +(5,9)
     * 
     * of cause, we can't escape the duplicate cases... we can never escape that;
     * shall we just give up and later do that in the result array?...
     * 
     * abcd, ;             if two number are same;  say it's aabc; order as aabc, baac , or bcaa; 
     *  if we still keep the f1<=s1<=f2<=s2 rule, we will add (aa)(bc); (ba)(ac); (bc)(aa); no duplicate still, good;
     * 
     * if three number are same; it's aaab or abbb; still , with the rule, we get first half (ab)+second half(bb) ; or (aa)+(ab);
     * 
     * so damn coooooooooooooooooool, it's the best idea of today..... let's do this and check;
     * 
     */
    private void addRes(ArrayList<ArrayList<Integer>> res,ArrayList<Pair> al1,ArrayList<Pair>al2,HashMap<Integer,Integer> countMap){
    	
        // got two array, contains values; 
        int a,b,c,d;
        // Pair p1,p2;
        for(int i=0;i<al1.size();++i){
            for(int j=0;j<al2.size();++j){
                a = al1.get(i).f;
                b = al1.get(i).s;
                c = al2.get(j).f;
                d = al2.get(j).s;
                
                
                
                // p1 = al1.get(i);
                // p2 = al2.get(j);
                // p1.f=a,  p1.s=b;  p2.f =c  p2.s = d
                // if(p1.s>p2.f){   // if b> c; 
                if(b>c){
                    //just give up this result, cooool. read the record above.
                	System.out.println("======================================================== skipped:  abcd "+a+" "+b+" "+c+" "+d);
                    continue;
                }else{
                    // check result for dup,  and add result;
                    // now we have a<=b<=c<=d
                	System.out.println("try add:  abcd "+a+" "+b+" "+c+" "+d);
                    if(a==b){
                        // ab;
                        if(b==c){
                            //abc;
                            if(c==d){
                                //abcd;
                                if(countMap.get(a)>3){
                                    addResABCD(res,a,b,c,d);
                                }
                            }else{
                                //abc , d
                                if(countMap.get(a)>2){
                                    addResABCD(res,a,b,c,d);
                                }
                            }
                        }else{
                            //ab ; don't need to check cause if we got ab, ab must in the map;
                            // if(c==d){
                                // ab,cd; don't need to check count cause it must be in the map or we will not add this;
                            // }else{
                                // c!=d; no need to cehck for this.
                            // }
                        	
//                        	!!!!!!!!!!!!!!!!!!!!
                        	//!!!!!!!!!!!!!!!!!!ADD RESULTS IF NO NEED TO CHECK!!!!!!
                        	addResABCD(res,a,b,c,d);
                        }
                    }else{
                        //a,b
                        if(b==c){
                            //a,bc
                            if(c==d){
                                //a,bcd
                                if(countMap.get(b)>2){
                                    addResABCD(res,a,b,c,d);
                                }
                            }else{
                                //a,bc,d
                            	//!!!!!!!!!!!! can't use countMap.get(b) here. b may not in the count array;
//                            	System.out.println( "get b: "+b+"       "+countMap.get(b));
                                if(countMap.containsKey(b)){
                                    addResABCD(res,a,b,c,d);
                                }
                            }
                        }else{
                            //a,b,c?d ; no need to check;
//                        	!!!!!!!!!!!!!!!!!!!!
                        	//!!!!!!!!!!!!!!!!!!ADD RESULTS IF NO NEED TO CHECK!!!!!!
                        	addResABCD(res,a,b,c,d);
                        }
                    }
                }
            }
        }
        
    }
    
    /**
     * array of pair of zero sum; 
     * 
     * abcd, so what? a+b == c+d; 
     * cause if (a,c)==(b,d);
     * we already added( a,b) and (c,d); 
     * so the only case, equal case, 
     * is a==b==c==d; 
     */
    private void addResEqualCase(ArrayList<ArrayList<Integer>> res,ArrayList<Pair> al0,HashMap<Integer,Integer> countMap){
        int f,s;
        for(int i=0;i<al0.size();++i){
            f = al0.get(i).f;
            s = al0.get(i).s;
            if(f==s){
                if(countMap.get(f)>3){
                    addResABCD(res,f,f,f,f);
                }
            }
        }
    }
    
    
    private void addResABCD(ArrayList<ArrayList<Integer>> res,int a,int b, int c, int d){
        ArrayList<Integer> sol = new ArrayList<Integer>();
        sol.add(a);
        sol.add(b);
        sol.add(c);
        sol.add(d);
        res.add(sol);
    }
    
    private void mapAdd(HashMap<Integer,ArrayList<Pair>> mp,int sum,Pair p){
    	if(!mp.containsKey(sum)){
            ArrayList<Pair> nl = new ArrayList<Pair>();
            nl.add(p);
            mp.put(sum,nl);
            return;
    	}
    	ArrayList<Pair> savedList = mp.get(sum);
    	savedList.add(p);
    	mp.put(sum, savedList);
    }
    
    //don't use nomore.
    private void mapAntiDupAdd(HashMap<Integer,ArrayList<Pair>> mp,int sum,Pair p){
                    // we may do the duplicate element check here. ;  
                    // for example teh list is  2,2,3,3,4,5,6
                    // mpa: (2,2)(2,3)(2,3)<-one level earlier in the stack,(2,4),(2,5),(2,6),(2,3)<_here again,(2,3)same level,(2,4)
        if(!mp.containsKey(sum)){
                    ArrayList<Pair> nl = new ArrayList<Pair>();
                    nl.add(p);
                    mp.put(sum,nl);
                    return;
        }
        
        ArrayList<Pair> savedList = mp.get(sum);
        //shall check dup;
        
        int first = p.f;
        int size  = savedList.size();
        Pair pair;
        for(int i=size-1;i>-1;--i){
            pair = savedList.get(i);
            if(pair.f==first&&pair.s==p.s){
                //found duplicate, don't need to add;
                //add done.
                return;
            }
            
            if(pair.f<first){
                // check done, no more dup below this; can safely add.
                savedList.add(p);
                break;
            }
        }
        mp.put(sum,savedList);
    }
    
    private class Pair{
        int f;
        int s;
        public Pair(int o, int w){
            f = o;
            s = w;
        }
    }
}
