package codes;

import ulti.Printer;



/****
 *
 * second try, shall I be much better? 
 * 
 * also please read LongestPalindrome.jave, to checkout what i was thinking when i first see this.;
 * 
 * 
 * holly *t, it's in the reading section... why we finished coding it...
 * http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 * 
 * @author Yubing
 * 
 * after reading:
 * 
 * 1:  insert # ; pound sign;      actually easy to under stand:  mid of string can now be indexed, (will not locate somewhere like 2.5 , 3.5)
 *    from the artical: (both palindromes of odd and even lengths are handled graciously)
 *
 */
public class LongestPalindrome_sec {
    
	
	public void go(){
		
		String s = "abadfllkjdflakjl";
		
//		s = "bb";
		s = "ccc";
		s = "babcbabcbaccba";
		s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		String res = longestPalindrome(s);
		
		System.out.println("  s: "+s);
		System.out.println("  res: "+res);
		
		
	}
	
    public String longestPalindrome(String s) {
        if(s==null||s.length()<2){
            return s;
        }
        return ONSolution(s);
    }
    
    
    
    /**
     * 1, add pound, : # , to get both odd and even size string well done;
     * 2, scan array only once, make sure reuse the case we have scanned to help
     * 3, what to record when we are at position x, 
     * 
     * 
     *      p # a # b # c # k # c # b # a # b # s # t # t # s # k
     *      0 0 1 0 1 0 1 0 7 0 1 0 1 0 3 0 1 0 1 0 1 4 1 0 1 0 0
     *        --|--               ------|------   --|--   --|--
     *            --|--           --|--     | --|--   --|--  
     *        --------------|--------------    -------|--------
     *        
     *    move central each time, 
     *    hard part is to prove it's o(n)..
     *    
     *    make sure each char compare out of the symmetric pushes one of the following forward:
     *      I) current position II) center position and current position;
     *    as current position and center position moves forward, 
     *    we can make sure char compare time is under 2*N
     *      
     *  
     *  rules :
     *  we have ccp, currentCenterPosition;
     *  we have r, radius of the center, radiance /
     *     thus we have rightEdge( ccp + r)
     *  we have cp, current scan position( candidate of next center)
     * 
     *  we do:
     *  	case 1, if cp >=r;(out of radius)
     *      don't use ccp, all on it's own; do char compare, see how far it can go;
     *          case 1.a compare fail, no progress for center( cause new center of radius 0 is meaning less)
     *          case 1.b compare do find some match and have radius: update center, right, cp; 
     *                  
     *      case 2, if cp<r; (in radius)
     *       check mp, mirror position:    2*ccp-cp; 
     *       check radius of mirror position;   res[mp]
     *          
     *           case 2.a. 
     *            if(res[mp]+cp)< r; in radius, take result of res[mp], we know your condition better than yourself.       
     *  		case 2.b.
     *            if(res[mp]+cp)>=r;(can be out of this radius, but the out part we don't have a control, have to compare again) 
     *            like case 1; we do further compare,
     *                case 2.b.I,  do get new radius,  update center, right, cp
     *                case 2.b.II, failed , keep center not move, move cp;
     *  
     *    
     * if i get this done, i do make progress and did it much faster than last try:)
     * @param s
     * @return
     */
    public String ONSolution(String s){
    	
    	//init
    	//char arry;  before and after pounded 
    	char[] cao = s.toCharArray();
//    	insert #, pounded array
    	char[] cap = new char[cao.length*2-1];
    	cap[0] = cao[0];
    	for(int i=1;i<cao.length;i++){
    		cap[2*i-1] = '#';
    		cap[2*i] = cao[i];
    	}
    	
    	int capLength = cap.length;
    	
    	// current position scan;
    	int cp = 0;
    	// current center record taht can be used;
    	int ccp = 0;
    	// radius related to center ; radius is covered, means it's a match at edge;
    	int radius =0;
    	
    	// result, the radius of palindrome centered at this position; (# counted in)
    	int[] res = new int[capLength];
    	for(int i=0;i<capLength;++i){
    		res[i] = 0;
    	}
    	
    	int right =0; //right position;
    	int mp; //mirror position;
    	int compareFrom = 0; // compare from for new center and new radius;
    	// for new radius adn new center;
    	int endPosition ; //endPosition included as a match;; so it start from it self;
    	
    	// push cp and ccp forward so we can get 
    	while(cp<capLength){
    		right = ccp+radius;
    		
    		// cp of coz shall be always >= ccp;
    		if(cp<right&&cp>ccp){
    			// can check mirror;
    			mp = 2*ccp-cp;
    			
    			int mirrorRadious = res[mp];
    			
    			if(cp+mirrorRadious<right){
    				//case 2.0; we got it right now, cool, no more compare;
    				//cp move forward; ccp don't change; r don't chang;e
    				res[cp] = res[mp];
    				cp++;
    				continue;
    			}else{
    				// equal or bigger case, we shall compare from out of right;
    				compareFrom = right+1;
    				endPosition = right;
    			}
    		}else{
    			// not in mirror;  all on your own;
    			compareFrom = cp+1;
    			endPosition = cp;
    		}
    		
    		// do compare, to find new center, new r,
    		int compareWith=0; //= cp*2-compareFrom
    		for(int cmp = compareFrom;cmp<capLength;++cmp){
    			compareWith = cp*2-cmp;
//    			System.out.println(" center: "+cp+" compare  "+cmp+" -with- "+compareWith);
    			if(compareWith>-1){
    				if(cap[cmp]==cap[compareWith]){
    					//go on;
    					endPosition=cmp;
    				}else{
//    					System.out.println(" center: "+cp+" comapre fail,  cmp:"+cmp+cap[cmp]+", "+compareWith+cap[compareWith]);
//    					if(endPosition<cmp-1){
//    						endPosition = cmp-1;
//    					}
    					break; 
    				}
    			}else{
//    				if(endPosition<cmp-1){
//						endPosition = cmp-1;
//					}
    				break;
    			}
    		}
    		// new successful compare radius;
    		int r = endPosition-cp;
    		res[cp] = r;
//    		System.out.println(" calc, res at position: "+cp+" :  "+res[cp]+"    endposition:"+endPosition);
    		
    		//new center, radius got;
    		if(r>0){
    			ccp = cp;
    			radius = r;
    		}
    		//move cp forward;
    		cp++;
    	}
    	
    	System.out.println("check array and radius calced");
//    	Printer.pArray(cap);
    	for(int i:res){
    		System.out.print(" :"+i);
    	}
    	
    	Printer.pArray(res);
    	
    	//reduce result;
    	// case center at 'x',  contains ((r)/2)*2+1  : this don't always = r+1;  
    	//case center at '#', contains ((r+1)/2)*2; again, this don't always = r+1;
    	
    	int maxPosition = 0;
    	int maxLength = 0;
    	
    	for(int i=0;i<capLength;++i){
    		int tmp = 0; 
    		if(cap[i]=='#'){
    			tmp = (res[i]+1)/2;
    			tmp*=2;
    			
    		}else{
    			tmp = res[i]/2;
    			tmp = 2*tmp+1;
    		}
    		
    		if(tmp>maxLength){
				maxLength = tmp;
				maxPosition = i;
			}
    	}
    	
    	//find it; at position maxPosition; string with maxLength;
    	//start from maxPosition-res[maxPosition] , to maxPosition+res[maxPosition]
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i=maxPosition-res[maxPosition];i<=maxPosition+res[maxPosition];++i){
//    		if(cap[i]!='#'){
    		if(i%2==0){
    			sb.append(cap[i]);
    		}
    	}
    	
    	return sb.toString();
    }
    
}
