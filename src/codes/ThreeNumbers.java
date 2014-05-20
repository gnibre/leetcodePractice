package codes;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeNumbers {
    
    public void go(){
    	
    	int[] num = {
//    			-1,0, 1, 2, -1, -4
//    			4,0,2,3,-1
//    			0,0,0
//    			1,1,-2
//    			-4,-2,-1
//    			1,2,-2,-1
//    			-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6
    			1,3,5,-7,-8,-9,-19,2,4,5,-8,-9,9,-435,-3,-123,-654,24,45,7,34,2,3123,13,345,47,234
    	};
    	
    	ArrayList<ArrayList<Integer>> res = threeSum(num);
    	
    	System.out.println(" res size: "+res.size());
    	
    	int ind = 0;
    	for(ArrayList<Integer> al: res){
    		System.out.println(" ind: "+ind+"       "+al.get(0)+", "+al.get(1)+", "+al.get(2));
    		
    	}
    	
    	
    }
    
    // sort the array, to get 2 part : array nAL of <0 , array pAL of >0. and we need to konw if there are 0's
    // 1, any in nAL say a and any in pAL say b, that a = -b; so it's a  triplets: {a,0,b}
    // 2, pick any in nAL, say a2 , we try to find all the pairs in pAL {p1,p2} that p1+p2 = -a2;   o(n*n)
    // 3 , pick any in pAL, and find all pairs in nAL this time.
    // the nAL and pAL is ordered, so result easy to order and will not be duplicated.
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        
        ArrayList<ArrayList<Integer>> res  = new ArrayList<ArrayList<Integer>>();
        if(num==null||num.length<3){
            return res;
        }
        
//        int[] sorted = quickSort(num,0,num.length-1);
        //we can use Arrays.sort!!!!  in java.
        Arrays.sort(num);
        int[] sorted = num; 
        for(int i:sorted){
        	System.out.print(" "+i);
        }
        
        int nCount =0;//neg
        int pCount =0 ;//pos
        int zCount =0;//zeros
        
        boolean zAppear = false;
        for(int i=0;i<sorted.length;++i){
            if(sorted[i]==0){
                if(!zAppear){
                    // first time see a zero
                    zAppear = true;
                    nCount = i; // before first zero are all neg.
                }
                zCount++;
            }
            if(sorted[i]>0){
                pCount = sorted.length-i;
                if(!zAppear){
                	// if no zero in the array.
                	nCount = i;
                }
                
                break;
            }
        }
        if(zCount+pCount==0){
        	// all positive. 
        	nCount = sorted.length; 
        }
        
        
        System.out.println("   ncout: "+nCount);
        System.out.println("   zcout: "+zCount);
        System.out.println("   pcout: "+pCount);
        
        int[] nAL = new int[nCount];
        for(int i=0;i<nCount;++i){
            nAL[i] = sorted[i];
        }
        System.out.println("  n array: ");
        for( int n: nAL){
        	System.out.print("  "+n);
        }
        
        int[] pAL = new int[pCount];
        for(int i=0;i<pCount;++i){
            pAL[i] = sorted[i+nCount+zCount];
        }
        
        System.out.println("  p array: ");
        for( int n: pAL){
        	System.out.print("  "+n);
        }
        
        
        System.out.println(" zero count: "+zCount);
        ArrayList<Integer> triplet;
        // now got all the count
        //case 0:  o(1)
        if(zCount>2){
            triplet = new ArrayList<Integer>();
            triplet.add(0);
            triplet.add(0);
            triplet.add(0);
            res.add(triplet);
        }
        
        //case 1:  {n,0,p}
        // o(n)
        //!!!!!!!!!!!!!!!!!!!!!!!!!! we need atleast ONE ZERO!!!!!!!!!!!!
        int saved;
        if(zCount>0){
        	int pPos=pCount-1; //pos scan from end of the array
            // neg scan from head to tail.
        	saved = 1; //save some positive number 
        	//!!!!! make sure we dont' have duplicate case.
            for(int i=0;i<nCount;++i){
            	
            	System.out.println("========> i : "+i+"   "+nAL[i]+"        p: "+pPos);
            	
                while(pPos>-1&&pAL[pPos]>-nAL[i]){
                	System.out.println(" p : "+pPos+"   "+pAL[pPos]);
                    pPos--;
                }
                if(pPos==-1){
                    break; // p array ends.
                }
                
                
                System.out.println(" i : "+i+"   "+nAL[i]+"        p: "+pPos);
                System.out.println(" p : "+pPos+"   "+pAL[pPos]);
                
                
                if(pAL[pPos]==-nAL[i]&&nAL[i]!=saved){
                	System.out.println(" add:  "+nAL[i]+"         saved: "+saved);
                	saved = nAL[i];
                    triplet = new ArrayList<Integer>();
                    triplet.add(nAL[i]);
                    triplet.add(0);
                    triplet.add(pAL[pPos]);
                    res.add(triplet);
                }
                
                // if pal< -nal[i] , move i; so continue for loop;
            }
        }
        
        
        saved = -1; // for positive array
        // case 2; { n1,n2,p} o(n^2)
        for(int i=0;i<pCount;++i){
        	if(saved!=pAL[i]){
        		findTwoNumber(nAL,pAL[i],res,true);
        		saved = pAL[i];
        	}
        }
        
        // case 3:   {n,p1,p2}
        for(int i=0;i<nCount;++i){
        	if(saved!=nAL[i]){
        		findTwoNumber(pAL,nAL[i],res,false);
        		saved = nAL[i];
        	}
        }
        
        return res;
    }
    
    private void findTwoNumber(int[] al, int targetValue, ArrayList<ArrayList<Integer>> res , boolean alNeg){
        for(int i:al){
        	System.out.print(" "+i);
        }
        System.out.println("   to find : "+targetValue+"    in the array   ");
    	
    	int sum = -targetValue; // sum of two number we try to find.
        
        int s = 0;
        int e = al.length-1; // two cursor pos
        int tmp;
        ArrayList<Integer> triplet;
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! duplicate check!!!!!!!!!!!!!!
        int saved = 0; //don't have 0 in both arrays.
        
        while(s<e){
            tmp = al[s]+al[e];
            // we need duplicate check.!!
            if(tmp==sum&&al[s]!=saved){ 
            	saved = al[s];
                triplet = new ArrayList<Integer>();
                triplet.add(al[s]);
                triplet.add(al[e]);
                if(alNeg){ // this array is neg , it's before target,
                    triplet.add(targetValue);
                }else{
                    // this array is pos, target get before it.
                    triplet.add(0,targetValue);
                }
                res.add(triplet);
            }
            
            
            if(tmp>sum){
                // too big, move e,
                e--;
            }else{
                // tmp<sum , too small, move s
                s++;
            }
        }
    }
    
    
    private int[] quickSort(int[] num,int s,int e){
        int tmp;
        if(e==s+1){
            if(num[s]>num[e]){
                tmp = num[s];
                num[s] = num[e];
                num[e] = tmp;
            }
        }
        if(e-s<2){
            return num;
        }
        
        
        int piv = num[(s+e)/2];
        int c1=s;
        int c2 = e; //scan head and end.
        
        while(c1<c2){
            // find one taht >= piv at c1,.
            while(num[c1]<piv&&c1<c2){
                c1++;
            }
            //find one that <= piv at c2;
            while(num[c2]>piv&&c1<c2){
                c2--;
            }
            if(c1==c2){
                break;
            }
            //swap 
            tmp = num[c1];
            num[c1]=num[c2];
            num[c2]=tmp;
            
            c1++;
            c2--;
        }
        quickSort(num,s,c2);
        return quickSort(num,c1,e);
    }
    
}