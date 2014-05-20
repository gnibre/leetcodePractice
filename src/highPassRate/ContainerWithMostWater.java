package highPassRate;

public class ContainerWithMostWater {
    
    
    /*****
     * 
     * it's actually not tath simple;   it's even harder to understand what this problem is , than doing maximal rectangle in a histogram;
     * the pass rate is hight maybe because it accepts O(N^2) brute force;
     * 
     * 
     * what this problem doing is that, : find two height( ai,ak ), that min one * distance is size of container; find the max container size;
     * 
     * idea is like doing maximal rectangle in a histogram;
     * 
     *  1, do with one scan : o(N) ; we have data structure( thought not know what it is now ) to do the following: 
     *  example height:   ..... 5  3(here)  1  8  9 .................
     *  so when we scanned to  value 3;  
     *  if 3 is made to be one of the board of the container;     
     *  if value 3 is right board, left board is at L ( with height >=3)     [L, 3]
     * and later if we find anyvalue >3 like 8;   [L,8] is a better container than the "best" one;
     * 
     * so conclusion 1:  right board don't have any value bigger than it that appears to right of it;
     * conclusion 2:  left board is likewise; left board don't have value bigger than it at left;
     * 
     * applying these conclusion, we got condidate board like that:
     *      ~~
     *    /   \
     *   /     \ 
     *  /        \      .... 
     *    
     * so, two cursor ,greedy algorithm, let's go;
     * 
     * 
     */
    public int maxArea(int[] height) {
        if(height.length<1) return 0;
        int max =0;
        int size;
        // two cursor;
        int l = 0;
        int r = height.length-1;
        
        
        
        while(l<r){
            int lh = height[l];
            int rh = height[r];
            
            if(lh<rh){
                //we already kown the fate of smaller one;
                size = lh*(r-l);
                
                //move this cursor;
                l++;
                while(height[l]<=lh&&l<r){
                    l++; // skip these height even below the cursor; just ignore them;
                }
            }else{
                // lh>=rh; rh can be done
                size = rh*(r-l);
                //move right cursor;
                r--;
                while(height[r]<=rh&&l<r){
                    r--; // skip these height even below the cursor; just ignore them;
                }
            }
            if(size>max){
                 max = size;
            }
        }
        
        return max;
    }
}