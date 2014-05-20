package codes;

import ulti.Printer;

public class KMP_STRSTR {
    public void go(){
    	
    	
    	String input = "lakdjfoiasdufijasaaaaaaaaadbdaaaaaaaaaaaadlfjlaksdf";
    	String target = "aaaaaaaaadbdaaaaaaaaaaaa";
    	
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<10000;++i) sb.append("a");
    	sb.append("b");
    	target = sb.toString();
    	
    	target = "abcabcabcdddddabcabcabcaa";
    	target = "aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    	target = "aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabb";
    	target = "acacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacbacacacacacac";
    	
    	target = "aabbcdaaabbcdkkkk";
    	target = "aaaaabbcdaaaaabaaaaaaacaaaa";
    	target = "abcabcabcdddddabcabcabcaabd";
    	String res = strStr(input,target);
    	System.out.println(" res : "+res);
    }
	
	
    /**
     * KMP algorithm;
     * hard part is to calc the offset array of target(needle) string
     * 
     * though  the target string maybe small, but, still , o(k^2) is not good , k is the length of target string;
     * 
     */ 
    public String strStr(String haystack, String needle) {
        
        // java libary function works, follow line accepted by oj to understand what this problem is doing;
        // int ind = haystack.indexOf(needle);
        // if(ind<0) return null;
        // return haystack.substring(ind);
    	if(needle==null||needle.length()<1){
            return null;
        }
        
        int len = needle.length();
//        int[] shift = new int[len]; // for each position from 0 to len-1;  actually don't need for index 0, but just do this to ease the array set up and index;
//        shift = getKMPShift(needle);
//        
//        
//        int[] kmp2 = getKMPShiftInOK(needle);
//        Printer.pArray(kmp2);
        // compare these, if same;
        
//        for(int i=0;i<shift.length;++i){
//        	if(kmp2[i]==shift[i]){
//        		continue;
//        	}else{
//        		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!different!!!!!!");
//        	}
//        }
        
        
        // not easy to get o(k) kmp array here;
        int[] kmp = getKMPShiftInOK(needle);
        
        
        char[] hay = haystack.toCharArray();
        int cur=0; /// this cursor move in char array of input;
        char[] k = needle.toCharArray();
        int matchLen = k.length;
        int curk =0; // this cursor moves in target string char array;
//        System.out.println("matchLen : "+matchLen);
        while(cur<hay.length){
//        	System.out.println(" cur in input: "+cur+"  curk:"+curk+"     dis: "+(matchLen-curk));
        	
            if(hay[cur]==k[curk]){
                curk++;
                if(curk==matchLen){
                    //found; break the loop; 
                    break;
                }
                //keep comparing next in input and in target;
            }else{
                //failed at this position; where shall we go?
                
                    // omg, we got a while loop in another while loop ; how to prove that KMP jumps will not exceed complexit of O(N)?  
                while(curk>-1){
                    curk = kmp[curk];
                    if(curk>-1&&hay[cur]==k[curk]){
                        //keep going from here;
                        break;
                    }else{
                        // if fails, will jump to next again, with curk as index to the shift array;
                        // if fails continue, curk will finally fell into -1; as we start from head of target string;
                    }
                }
                // if matched, move cursor in the target string 1 char forward; if not matched, curk must be -1; also works as start from 0;
                curk++;
            }
            // cur aways move foward one step; // so complexity is O(N+K); N is lenght of input; K is length of target string;
            cur++;
        }
        
        if(cur!=hay.length){
            // found;  or can use curk==matchLen
            //so the matched string end at cur;
            //start at 
            int start = cur-matchLen+1;
            return haystack.substring(start);
        }
        // not found;
        return null;
    }
    
    /**
     * for target string, we get shift array; for each position match fail, position where shall we continue the search; 
     * this one is actually harder and more important in this algorithm, as you can see the main part of compare is very easy;
     * 
     * what shift means?   the longest substring of target have matched before this fail/current position; so we can continue from that position and don't need to try from head of target string everytime, and more importantly don't need to set cursor backward;
     * 
     *  for example, the target string:   aabbcdaaabbcdkkkk
     *                                    01234567890123456
     *  k-res                             --1000--210006000
     * - means -1,  skip, to next char, nothing can match this char;
     * i means match start at position i;  i : [0,len-2] len-1 means you already have a match;
     * 
     * 
     * is it possible that kmp worse jump,  which means that k-res is like  -012345678; which means at position x, you may jump x-1 times 
     * it just like compare every word;
     * 
     *      what will the string be look like if kmp res array is  :        
     *  index    0123456                                                                
     *  chars    ax
     *  kmp-     -012343
     *  for chars[1], if it is char x;     now the comming char c, not match x; but jump to 0 to compare again;
     *  so x is not 'a', assume x = b; keep going;
     *  index    0123456                                                                
     *  chars    abx
     *  kmp-     -012343
     *  now at position 2,  coming char ?, not match x; so coming string is ab?,  
     * but jump to 1 to continue compare, 
     * which means if ? is chars[1] , we have a chance to have 'ab'; but bullshit, b? can't be ab; 
     * so it seems that kmp will not jump that bad; 
     * but i don't know how to prove it; 
     * 
     * let's try another one, leetcode oj must use this as one of the test cases...
     * target string:         
     * 0123456789012345678901
     * aaaaaaaaaabaaaaaaacaaaa
     * ----------9-------7----
     * 
     * 
     * 
     * 
     * description: the longest string that ends before the failed position and this string is the substring of target, same length and start from head;  and one more thing, the char at failed position can't be the same as the longest string ends;
     * 
     * for each position, to calc it seems need o(k) time; so totally o(k^2);
     * but as we are going to get all of them, we reuse the info we get ;
     * 
     * say at position p,match failed ,   the longest prefix found is of length q,    so the string is like     xxxxxxxqqqqqqqqpk; 
     * , now for the next position k: k=p+1; what's  the best it can do? ofcause if p matchs the q+1th element of the target string;
     * then max length can be matched is q+1; match one more char;
     * what if p failed to match that one;?  the result must be in qqqqqqqqq, cause if we got a match longer than qqqqqqqqqqq, then qqqqqqqqqq is not teh longest substring at position p;
     * 
     * 
     */ 
    private int[] getKMPShift(String t){
    	System.out.println(" calc kmp array for this : ");
    	Printer.pArray(t.toCharArray());
    	System.out.println();
        // char[] ca = new 
        char[] ca = t.toCharArray();
        int[] kmp = new int[ca.length];
        
        int step= 0;
        for(int fp=0;fp<kmp.length;++fp){
            // if compare fails at position fp; where shall we try again?
            // find a substring;
            
            int end = fp-1;
            while(end>-1){
            	step++;
                if(ca[end]==ca[fp]){
                    // this one can't be the end, cause why we fail? cause coming char is not ca[fp]; thus coming char can't use this 'end'
                    end--;
                    continue;
                }
                // if from end-1 to 0 , matchs chars from [x,fp-1] ; then end position is the best candidate for match;
                boolean match = true;
                for(int i=0;i<end;++i){
                	step++;
                    if(ca[end-1-i]!=ca[fp-1-i]){
                        match = false;
                    }
                }
                
                if(match){
                    // this end , is it
                    break;
                }
                end--;
            }
            kmp[fp] = end;
        }
        Printer.pArray(kmp);
        System.out.println("total steps to calc : "+step);
        return kmp;
    }
    
    
    
    /*******
     * 
     * case 1
     * 012345678901234567890123456
     * aaaaabbcdaaaaabaaaaaaacaaaa
     * -----4000-----46-----4
     * 
     * case 2
     * 0123456789012345678901234
     * abcabcabcdddddabcabcabcaa
     * -00-00-0060000-00-00-0097
     * 
     * case 3
     * 
     * 0123456789
     * abcadkabcadkx
     * -00-10-00-106
     * 
     * 0123456789
     * abcadkabcamno
     * -00-10-00-400
     * 
     * case 4
     * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
     * ----------------------------------------------------------------------------------------------93
     * 
     * 929190
     * 
     * 
     * case 5
     * 01234567
     * aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
     * ------5------66666666666666666666666666666666666666666666666666666666666666666666666666666666666
     * 
     * case 6
     * aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabb
     * ------5------666666666666666666666665------6666666666666666 36  666666666666666666666666666666666666666666660
     * ~54321.66666666666666666666666 54321.6666666666666666666666666666666666666666666666666666666666666666654321..
     * 
     *  
     *  
     *  
     *                               |36
     * 
     * 
     * case 7
     * acacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacacbacacacacacac
     * -0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0 -0-0-0-0-0-0
     * 																								   |94
     * 
     * 
     * -0
     *  10                             
     *  210                             
     *                               
     *                                                            
     * 
     * - or (-1) means this char is the same as t[0];  first char case, and only case for result -1; other wise, the next compare will be start from at least 0; only when it is the same char with [0], then we don't need to compare it with head;
     * but does that means same char with t[0] will always return -1? of cause not; see that 6 of above case 1; 
     * 
     * can we use this already build up kmp array to get the rest of the array? i guess so;
     * but why that sudden 6, sudden 9 and 7 came up after 0s; i feel we need another array to record something missed;
     * cause to save 0 in the kmp array as the final res, is not informative to help the rest of the array;
     * 
     * aux array to record compared? what to record?
     * before we get another -, we don't need record anything; because no chance to get the head char to start; so will all be 0 before next -;
     * 
     * focus that case 2/3 , the t[4];  
     * when we see 1s or something that >0; the record before this one seems not important anymore; don't know if it's true or I can prove it;
     * 
     * >0 meas, we do have something the same before this char, and not the same at this char;
     * see the case below, char x, kmp res is 5; t[5] is ?;  what do this 5 mean? 
     *  012345 
     *  __foo?_____barx_________
     *                5
     *  this 5 means, char at t[5], to the closest - before it, can be a valid start, of length L:   
     *    1, L chars before x, must be teh same as start chars of length l => t.substring(0,L);
     *    2, x must not the same as t[5]; or it can't be 5;
     *    3, same substring ENDS at this x; it's the longest substring find for the latest -;
     *    this is important, cause for the later cases, we don't need to check no more before this -;
     * but this is not enough, look at case 4 and 5; when we don't have much - or we have too much -; how to calc this;
     * 
     * ===========================================================================================================================
     * im gonna calc max length of substring for every char that same as heading char;
     * for case like 4,5,6,7; we got too many heading chars, what to do ? try to use heading pattern
     * heading pattern:  heading char is t[0]; next char that same as t[0] is next[i]; define pattern as t.substring(0,i)
     * so for case 4,5,6, pattern is "a" of length 1;
     * for case 7, pattern is "ac", of length 2;
     * 
     * for case we have repeating patterns, what to do ?
     * case4: pattern is n; repeating 93 times, 
     * 
     * 01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234
     * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
     * 
     * the kmp array in the pattern must be -0000000; cause there's no heading char in the pattern;
     * for the repeating; part,
     * 
     * p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**p**?       if ? is heading, it's - ; if not, it's patternlen*patterncount;
     * -00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00        
     * 
     * for the rest part, 
     * 1, find head,  find longest substring;
     * 2, find pattern..
     * 
     * 
     * ===============================================================================================================================
     * 
     * The idea is use the already done part of the kmp array to help calc the rest part, in O(1) for each position;
     * maybe all the above kmp array are over calculated, ill try this one: ( the array only keeps match fail position and wanted char position)
     * but not the real jump position( same char case);
     * 
     * like case 0;
     * 012345678901234567890123456              index
     * aaaaabbcdaaaaabaaaaaaacaaaa				target
	   -----4000-----46----555----				jump-array
       *----4000           4440123              next-char-to-compare(wanted-char)
       *                     -
       <-1->2<3>< 1  >2< 1>224< 1>                stage;
       
       
       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CONGRATULATIONS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       seems i got it, check the above state;
       we do need kmp array;  and also, we need to record the comparing stage, and compare position,:
       p1: compare char in target string
       p2: start char in finding a longest substring; 
       p3; comparing char in finding a longest substring;
       
       <1>stage: 1 comparing: chose a start and try find longest substring start from here;
       this stage happens at the beginning or after stage 3;   
       p2 is the first char when stage 1 begin; p3 is moving char by char in stage 1;
       p1 move to t[0] when stage 1 begin; move one step forward each time success compare
       
       once you find the same char at p3 that same as p1;    we can set kmp[p3] = kmp[p1]; cause it's the same case; we have same heading string;
       this saves time;
       
       <2>stage 2: fail char found;  when the compare failed;
       again , we can use the kmp array to help going;
       when failed to get t[p1]; the next position to try is kmp[p1],
       but we still set kmp[p3] as p1; cause it's the value of t[p1] we want at this position;
       but to keep searching(keep the substring running, we must find a match)!!!!!!!!!!!!!!
        so char is: t[kmp[p1]]; and keep the p2 still,p3 still
       until we found the right char, we set kmp[p3] as that char; still kmp[p3] = kmp[p1]; cause we assigns p1 with new values;
                    
       <3> stage 3: no start char after fail; 
       this stage happens after stage 4; or at the beginning;
       can't start compare; reset p1 to 0; p2,p3 meaning less;
       result all 0; kmp[p3] = 0;
       
       <4> stage 4: badly fail;  when we fail, we use kmp array to find next p1; but when p1 fell into -1; this do happen;
       go to stage 3; and wait 
       how ever, the 
       
       stages: (3)->1->(2)->(4)->3;   til p3 reachs end of the string;
       
       rules: 
       I,set kmp[0] = -1;
       II, before find compare string start, stage 3,  just set to 0; 
       III,when compare starts, if same, stage 1, just set as that value of kmp ; 
       IV, if not; stage 2/4 set index value first, cause it's what we want;
       and, we shall keep going, jump to next according to the kmp res; til find a match char or -1; 
       
       try these rules on the next case:
                               
012345678901234567890123456       index
abcabcabcdddddabcabcabcaabd       string
-00-00-0060000-00-00-009702       res-kmp 
333111111433331111111112212                                state
***0123456    0123456789                            compares
         -             67
                        012
0123456789
abcadkabcadkx	
-00-10-00-106


0123456789
abcadkabcamno
-00-10-00-400


aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
----------------------------------------------------------------------------------------------93


01234567
aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab
------5------66666666666666666666666666666666666666666666666666666666666666666666666666666666666


aaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabb
------5------666666666666666666666665------6666666666666666 36  666666666666666666666666666666666666666666660
     * 
     * 
     * @param t
     * @return
     */
    private int[] getKMPShiftInOK(String t){
    	
//    	int step2 = 0;
//    	I,set kmp[0] = -1;
//        II, before find compare string start, stage 3,  just set to 0; 
//        III,when compare starts, if same, stage 1, just set as that value of kmp ; 
//        IV, if not; stage 2/4 set index value first, cause it's what we want;
//        and, we shall keep going, jump to next according to the kmp res; til find a match char or -1;
    	
    	char[] ca = t.toCharArray();
    	int[] kmp = new int[ca.length];
    	
    	
    	int p0=0; //cursor position at original string;
    	int p1=1; // cursor start position of finding a longest substring;
    	int p2 =1; // cursor comparing/end position of finding a longest substring;
    	int stage = 3; // 
    	
    	kmp[0] =-1; // init; rule I:
    	
    	// moving p2;
    	
    	while(p2<ca.length){
    		// p2 will only move forward, not back;  will be o(k); 
//    		System.out.println(" kmp calc p2, : "+p2+"   stage: "+stage);
//    		step2++;
    		if(stage==3){
    			if(ca[p2]==ca[p0]){
    				// find a start here;
    				p1 = p2;
    				kmp[p2]=-1; // set kmp[p2] := kmp[0]; as we know kmp[0] is alwasy -1;
    				stage = 1; //compare starts;
    				p0=1; // next compare p0 position is 0++;
    			}else{
    				// not found; 
    				kmp[p2] = 0; // rule II;
    				// next compare p0 is still 0;
    			}
    		}else{
    			// stage is 1/2/4; comparing; fail or not;
    			if(ca[p2]==ca[p0]){
    				//same; stage1;
    				kmp[p2] = kmp[p0]; //cause same,  so same kmp;
    				// keep compare going;
    				p0++;
    			}else{
    				// compare failed; stage 2/4; 
    				kmp[p2] = p0; // set it first; then we need to find next compare position;
    				
    				int next = kmp[p0];
    				while(next>-1){
//    					step2++;
    					if(ca[next]==ca[p2]){
    						p0=next; //continue compare from here;
    						break;
    					}else{
    						// still not match; try find an earlier one;
    						// where's next one? kmp array is for compare fails;
    						next = kmp[next];
    					}
    				}
    				
    				if(next==-1){
    					// failed to find a same one; / it's stage 4;
    					//going to stage 3;
    					stage =3;
    					p0=0;
    				}else{
    					//find compare position for p0 and p2; good;
    					p0++;
    				}
    			}
    		}	
    		// each case, we do
    		p2++;
    	}
//    	System.out.println(" steps to calc: "+step2);
    	return kmp;
    }
    
}
