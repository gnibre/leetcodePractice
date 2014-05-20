package highPassRate;

import ulti.Printer;

public class BestTimeToSellStockIII {
	
	public void go(){
		
		int[] pr = {
				5,2,3,0,3,5,6,8,1,5
		};
		
		Printer.pArray(pr);
		
		int res = best(pr);
	}
	
	
	/***
	 * 
	 * can buy and sell twice;
	 * 
	 * I,  if we can only do once.       record the min, each time we see a price, we record if we sell, how much can we get.
	 * 
	 * II if we can sell as many times as we can.  whenever price over last one, buy/sell it. 
	 * 
	 * II we can only do it twice.;    after we do once. we find the result from the rest of the array for doing once.
	 * 
	 * 
	 * find all the peaks, that's the best chance for the first sell. and for the later cases, we use already exsit sellonce algorithm.
	 * 
	 * 
	 * @param pr
	 */
	public int best(int[] pr){
		if(pr==null||pr.length==0){
	        return 0;
	    }
		int low = pr[0];
		int buy = 0;
		int len = pr.length;
		boolean rising = false;
		int maxSellTwice = 0;
		// greedy find peaks(and also lowest before peak), and make it the position to do first sell. 
		for(int i=1;i<pr.length;++i){
			System.out.println(" scan to : "+pr[i]);
			if(pr[i]>pr[i-1]){
				// we can have profit here. it's rising.
				rising = true;
				continue;
			}else if(pr[i]<pr[i-1]){
				// it's going down. maybe sell it when the price was pr[i-1] is a good idead.? 
				// only if it's on rising stage;  if not rising, we can't even find a lower price to buy it, how to sell..
				if(rising){
					//was rising and now about to fall,  this is the one we can try do first sell.
					// take care, sell price is pr[i-1]
					
					int profFirstSell = pr[i-1]-low;
					int profSecondSell = maxProfitOnce(pr,i,len-1);
					int res = profFirstSell+profSecondSell;
					System.out.println(" try sell when price is : "+ pr[i-1]+"   f p: "+profFirstSell+"  sp: "+profSecondSell+"   res: "+res);
					
					if(res>maxSellTwice){
						maxSellTwice = res;
					}
				}
				
				// getting lower even from the beginning.
				if(pr[i]<low){
					low = pr[i];
				}
				rising = false;
			}
			// equal case just, continued..
		}
		// till the end day, still rising. 
		if(rising){
			int profFirstSell = pr[len-1]-low;
			// no second sell.
			if(profFirstSell>maxSellTwice){
				maxSellTwice = profFirstSell;
			}
		}
		return maxSellTwice;
	}
	
	
	
	
	public int maxProfitOnce(int[] prices,int start,int end) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(prices==null||prices.length<2){
            return 0;
        }
        
        int min=Integer.MAX_VALUE;
        int maxP = 0;
        int got =0;
//        int l = prices.length;
        System.out.println(" for array: ");
        for(int i=start;i<=end;++i){
            System.out.print(" "+prices[i]);
        	if(prices[i]<min){
                min = prices[i];
                continue;
            }
            
            got = prices[i]-min;
            
            if(got>maxP){
                maxP = got;
            }
            
        }
        System.out.println(" for sell once max prof: : "+maxP);
        return maxP;
    }
	

}
