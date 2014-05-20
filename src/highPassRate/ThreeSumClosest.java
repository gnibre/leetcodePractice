package highPassRate;

import java.util.Arrays;

public class ThreeSumClosest {
    
    /**
     * 3 sum can be done with o(n^2) ; so to sort it is ok;
     * 
     * =========passed oj, but it's like o(n^3),  can be o(n^2), 
     * if we pick one number first; and search the next two number in o(N)
     * 
     * 
     */ 
    public int threeSumClosest(int[] num, int target) {
       if(num==null||num.length<3) return 0;
       
       // sort will not exceed this total complexity; 
       Arrays.sort(num);
       
       
       //1 pick 2 numbers;
       //2 pick 1 number in the rest of the array; see how close it can be with the target;
       
       int len = num.length;
       int ts; //two sum;
       int sum;
       int closestDistance = Integer.MAX_VALUE;
       int dis;
       int closest = num[0]+num[1]+num[2];
       for(int f=0;f<len-2;++f){
           for(int s=f+1;s<len-1;++s){
               // first number and second number are choosen;
               ts = num[f]+num[s];
               for(int t =s+1;t<len;++t){
                   sum = ts+num[t];
                   // value is going >
                   
                   if(sum>target){
                       dis = sum-target;
                       if(dis<closestDistance){
                            closestDistance = dis;
                            closest = sum;
                       }
                       // sum is going bigger; so , not closer, but farther; rather break;
                       break;
                   }
                   
                   dis = target-sum;
                   if(dis<closestDistance){
                        closestDistance = dis;
                        closest = sum;
                        
                        if(target==sum){
                            return sum;
                        }
                   }
               }
           }
       }
       
       return closest;
        
    }
}