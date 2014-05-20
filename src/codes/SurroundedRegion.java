package codes;

import java.util.HashMap;
import java.util.HashSet;

public class SurroundedRegion {
    
	public void go(){
//		char[][] board={
//				{'o','X','o','o','X','X'},
//				{'o','X','X','X','o','X'},
//				{'X','o','o','X','o','o'},
//				{'X','o','X','X','X','X'},
//				{'o','o','X','o','X','X'},
//				{'X','X','o','o','o','o'}
//	};
//		char[][] board={
//				{'X','X','X','X'},
//				{'X','o','o','X'},
//				{'X','X','o','X'},
//				{'X','o','X','X'}
//			};
		
//		char[][] board={
//				{'X','o','X'},
//				{'X','o','X'},
//				{'X','o','X'}
//			};
//		String[] sa = {"XOOXXXOXXOOOOOOOOOOO","XOOXXOOXOOOXOXOXOOXO","OOOXXXXOXOXXOOOOXOXO","OOOXXOOXOOOXXXOOXOOX","OOOOOOOXXXOOOOOOOOOO","XOOOOXOXOXXOOOOOOXOX","OOOXOOOXOXOXOXOXOXOX","OOOXOXOOXXOXOXXOXXXO","OOOOXOOXXOOOOXOOOXOX","OOXOOXOOOOOXOOXOOOXO","XOOXOOOOOOOXOOXOXOXO","OXOOOXOXOXXOXXXOXXOO","XXOXOOOOXOOOOOOXOOOX","OXOOXXXOOOXXXXXOXOOO","OOXXXOOOXXOOOXOXOOOO","XOOXOXOOOOXOOOXOXOXX","XOXOOOOOOXOOOXOXOOOO","OXXOOOXXXOXOXOXXXXOO","OXOOOOXXOOXOXOOXOOXX","OOOOOOXXXXOXOOOXXOOO"};
//		String[] sa = {"OXOOXXXOOOOOXOOOOXOX","XOXOOXXOOXOXOXOXXOOO","OXOOOXXXXOOOOOXXXXOX","XXOOOXXOOOXXXOOXOXXO","OXOXXOXOOOXOOXOOOOOX","XOOXOXOOOXXOXOOXOOOO","XOOOXXOOOOOXOOXOOOOX","XOOOXOXXXOXOXXXXOOOX","XOOXOOOXOOOOOOOOOXOX","OOOXOXXXXXXXXXOOOOXO","XOXOXOOXXXOXXOOXXOOO","OXOOXOOOOOOXXXXOOOXO","XOOOXXXOXOOOXOXOXOOX","OOOOXOXXOXOXOXXXXOOO","OXXOOOOXOOXXXOOXXOXO","XOXXXXXXOXXOXOOXOOOX","XOOOXOXOOXOXOOXOOXXX","OOXOOOOXOOXXOXXXOOOO","OOXOOOOOOXXOXOXOOOXX","XOOOXOXXXOOXOXOXXOOO"};
		String[] sa = {"OXOOXXXOOOOOXOOOOXOX","XOXOOXXOOXOXOXOXXOOO","OXOOOXXXXOOOOOXXXXOX","XXOOOXXOOOXXXOOXOXXO","OXOXXOXOOOXOOXOOOOOX","XOOXOXOOOXXOXOOXOOOO","XOOOXXOOOOOXOOXOOOOX","XOOOXOXXXOXOXXXXOOOX","XOOXOOOXOOOOOOOOOXOX","OOOXOXXXXXXXXXOOOOXO","XOXOXOOXXXOXXOOXXOOO","OXOOXOOOOOOXXXXOOOXO","XOOOXXXOXOOOXOXOXOOX","OOOOXOXXOXOXOXXXXOOO","OXXOOOOXOOXXXOOXXOXO","XOXXXXXXOXXOXOOXOOOX","XOOOXOXOOXOXOOXOOXXX","OOXOOOOXOOXXOXXXOOOO","OOXOOOOOOXXOXOXOOOXX","XOOOXOXXXOOXOXOXXOOO"};
		int l= sa.length;
		char[][] board = new char[l][l];
		for(int i=0;i<l;++i){
			board[i] = sa[i].toCharArray();
		}
		
		
		
		solve(board);
		System.out.println("res: ");
		for(int h=0;h<board.length;++h){
			for(int w=0;w<board[0].length;++w){
				System.out.print(" "+board[h][w]);
			}
			System.out.println();
		}
		
		
//		String[] answer ={"XOOXXXOXXOOOOOOOOOOO","XOOXXOOXOOOXOXOXOOXO","OOOXXXXXXOXXOOOOXOXO","OOOXXOOXOOOXXXOOXOOX","OOOOOOOXXXOOOOOOOOOO","XOOOOXOXXXXOOOOOOXOX","OOOXOOOXXXOXOXOXOXOX","OOOXOXOOXXOXOXXXXXXO","OOOOXOOXXOOOOXXXXXXX","OOXOOXOOOOOXOOXXXXXO","XOOXOOOOOOOXOOXXXXXO","OXOOOXOXOXXOXXXXXXOO","XXOXOOOOXOOOOOOXOOOX","OXOOXXXOOOXXXXXXXOOO","OOXXXOOOXXXXXXXXOOOO","XOOXOXOOOOXXXXXXXOXX","XOXOOOOOOXXXXXXXOOOO","OXXOOOXXXXXXXOXXXXOO","OXOOOOXXXXXXXOOXOOXX","OOOOOOXXXXOXOOOXXOOO"};
		String[] answer ={"OXOOXXXOOOOOXOOOOXOX","XXXOOXXOOXOXOXOXXOOO","OXOOOXXXXOOOOOXXXXOX","XXOOOXXOOOXXXOOXOXXO","OXOXXXXOOOXXXXOOOOOX","XOOXXXOOOXXXXOOXOOOO","XOOOXXOOOOOXOOXOOOOX","XOOOXXXXXOXOXXXXOOOX","XOOXXXXXOOOOOOOOOXOX","OOOXXXXXXXXXXXOOOOXO","XOXOXXXXXXXXXOOXXOOO","OXOOXXXXXXXXXXXOOOXO","XOOOXXXXXXXXXXXOXOOX","OOOOXOXXOXXXXXXXXOOO","OXXOOOOXOOXXXXXXXOXO","XOXXXXXXOXXXXXXXOOOX","XOOOXOXOOXXXXXXOOXXX","OOXOOOOXOOXXXXXXOOOO","OOXOOOOOOXXXXXXOOOXX","XOOOXOXXXOOXOXOXXOOO"};
		char[] arry;
		System.out.println("DIFF: ");
		for(int i=0;i<l;++i){
			arry = answer[i].toCharArray();
			for(int j=0;j<arry.length;++j){
				if(board[i][j]==arry[j]){
					System.out.print(" "+board[i][j]);
//					System.out.print("  ");
				}else{
					if(board[i][j]=='X'){
						System.out.print(" A");
					}else{
						System.out.print(" B");
					}
				}
			}
			System.out.println();
		}
		
		
		
		String[] outp = {"OXOOXXXOOOOOXOOOOXOX","XXXOOXXOOXOXOXOXXOOO","OXOOOXXXXOOOOOXXXXOX","XXOOOXXOOOXXXOOXOXXO","OXOXXXXOOOXXXXOOOOOX","XOOXXXOOOXXXXOOXOOOO","XOOOXXOOOOOXOOXOOOOX","XOOOXXXXXOXOXXXXOOOX","XOOXXXXXOOOOOOOOOXOX","OOOXXXXXXXXXXXOOOOXO","XOXOXXXXXXXXXOOXXOOO","OXXOXXXXXXXXXXXOOOXO","XXOOXXXXXXXXXXXOXOOX","OOOOXOXXOXXXXXXXXOOO","OXXOOOOXOOXXXXXXXOXO","XOXXXXXXOXXXXXXXOOOX","XOOOXOXOOXXXXXXOOXXX","OOXOOOOXOOXXXXXXOOOO","OOXOOOOOOXXXXXXOOOXX","XOOOXOXXXOOXOXOXXOOO"};
		String[] exp = {"OXOOXXXOOOOOXOOOOXOX","XXXOOXXOOXOXOXOXXOOO","OXOOOXXXXOOOOOXXXXOX","XXOOOXXOOOXXXOOXOXXO","OXOXXXXOOOXXXXOOOOOX","XOOXXXOOOXXXXOOXOOOO","XOOOXXOOOOOXOOXOOOOX","XOOOXXXXXOXOXXXXOOOX","XOOXXXXXOOOOOOOOOXOX","OOOXXXXXXXXXXXOOOOXO","XOXOXXXXXXXXXOOXXOOO","OXOOXXXXXXXXXXXOOOXO","XOOOXXXXXXXXXXXOXOOX","OOOOXOXXOXXXXXXXXOOO","OXXOOOOXOOXXXXXXXOXO","XOXXXXXXOXXXXXXXOOOX","XOOOXOXOOXXXXXXOOXXX","OOXOOOOXOOXXXXXXOOOO","OOXOOOOOOXXXXXXOOOXX","XOOOXOXXXOOXOXOXXOOO"};
		for(int i=0;i<outp.length;++i){
			String s1 = outp[i];
			String s2 = exp[i];
			if(!s1.equals(s2)){
				System.out.println("====> diff: "+i);
				System.out.println(s1);
				System.out.println(s2);
			}
			
			
			arry = outp[i].toCharArray();
			for(int j=0;j<arry.length;++j){
				if(board[i][j]!=arry[j]){
					System.out.print(" N");
				}else{
					System.out.print("  ");
				}
			}
			System.out.println();
			
		}
	}
	
    int boardW,boardH;
    
    /**
     * visit once from left to right, top to bot.
     * code each content with position :  hashcode = boardW*h+w;
     * 
     * do: create and make each '0' we visit join groups for '0's connected.. 
     * so for each group, or called a region ,share the same fate, either captured by 'x's or alive.
     * each group is set as "captured" as default.
     * for any of the group member became alive(board border reached), this group is marked as alive.
     *
     * 
     * at the end, change group of captured '0's into 'x'
     * 
     * 
     * for more complex situation, two group of different id, may became one group( joined later),  
     * for convenient, these positions are grouped to different group ids, but we also mark these groups as connected.
     * mark same group rule:  hashmap<groupid,markedToSameGroupSmallestGroupId>;
     * 
     */ 
    public void solve(char[][] board) {
        if(board==null||board.length==0||board[0]==null||board[0].length==0){
            return;
        }
        
        boardW = board[0].length;
        boardH = board.length;
        
        
        int groupIdIndex = 0;
        HashSet<Integer> aliveGroupSet = new HashSet<Integer>(); //in this set, survived group ids.
        HashMap<Integer,Integer> positionToGroupId = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> sameGroup = new HashMap<Integer,Integer>();
        
        //map stage, visit every position.
        int code; //position code.
        int gid;
        // h0 w0w1w2w3
        // h1 -------
        // h2
        for(int h=0;h<boardH;++h){
            for(int w=0;w<boardW;++w){
                if(board[h][w]=='X'){
                    //skipped.
                    continue;
                }
                
                // group. according to it's top and it's left.
                //which group do it belong?  find left one and topone.
                gid = -1;
                if(w!=0){ // have left one.
                    code =boardW*h+ w-1; 
                    if(positionToGroupId.containsKey(code)){
                        gid = positionToGroupId.get(code);
                    }
                }
                
                if(h!=0){  //have one above it, same group?
                    code =boardW*(h-1)+ w;  // code of the one above
                    if(positionToGroupId.containsKey(code)){
                        int anothergid = positionToGroupId.get(code);
                        if(gid>-1&&gid!=anothergid){
                            //find two gid, which group do you like? smaller one. and we mark bigger one same group as smaller one.
                        	
                        	//!!!!!!!!!!!!!1 as another not guaranteed to be the smalllest one. we mark it to the smallest one. and don't recover the old one
                        	//!!!!!!!!! for example, was 22->20 (mark to smaller) , later we found 22->0, we shall not save 22->0 directly, because that will make 20->dead/null 
                        	//!!!!!!!! what shall i do?
                        	
//                        	System.out.println(" same group found and marked: "+gid+" - "+anothergid);
                        	
                        	
                        	int smaller = gid<anothergid?gid:anothergid;
                        	
                        	while(sameGroup.containsKey(smaller)){
                        		if(smaller>sameGroup.get(smaller)){
                        			smaller = sameGroup.get(smaller);
                        		}else{
                        			break;
                        		}
                        	}
                        	
                        	
                        	if(sameGroup.containsKey(gid)){
                        		int sameGroupOne = sameGroup.get(gid);
                        		if(sameGroupOne<smaller){
                        			//group again, we all change to smaller one.
                        			smaller = sameGroupOne;
                        		}
                        	}
                        	if(sameGroup.containsKey(anothergid)){
                        		int sameGroupOne = sameGroup.get(anothergid);
                        		if(sameGroupOne<smaller){
                        			//group again, we all change to smaller one.
                        			smaller = sameGroupOne;
                        		}
                        	}
                        	
                        	//smaller is now smallest.
                        	
                        	if(sameGroup.containsKey(gid)){
                        		int sameGroupOne = sameGroup.get(gid);
                        		if(sameGroupOne!=smaller){
                        			// change to smallerone
//                        			System.out.println(" put same group: "+sameGroupOne+"->"+smaller);
                        			sameGroup.put(sameGroupOne, smaller);
                        		}
                        	}
                        	if(sameGroup.containsKey(anothergid)){
                        		int sameGroupOne = sameGroup.get(anothergid);
                        		if(sameGroupOne!=smaller){
                        			// change to smallerone
//                        			System.out.println(" put same group: "+sameGroupOne+"->"+smaller);
                        			sameGroup.put(sameGroupOne, smaller);
                        		}
                        	}
//                        	System.out.println(" put same group: "+gid+"->"+smaller);
//                        	System.out.println(" put same group: "+anothergid+"->"+smaller);
                        	sameGroup.put(gid,smaller);
                        	sameGroup.put(anothergid,smaller);
                        	
                        	// after same group done, we use the smallest one as the group id.
                        	gid = smaller;
                        }else{
                        	gid = anothergid;
                        }
                    }
                }
                
                // no sibling with groupid found, boy you are the first one in a new group...
                if(gid==-1){
                    gid = groupIdIndex++;
                }
                
                code = boardW*h+w;
                
//                System.out.println(" boy : "+h+"-"+w+"  got gid: "+gid);
                
                positionToGroupId.put(code,gid);
                
                
                // check if it's alive.
                
                // it's a surviver and can save a whole group.
                if(h==0||h==boardH-1||w==0||w==boardW-1){
                    aliveGroupSet.add(gid);//this group lives.
//                    System.out.println(" group id : "+gid+"     alive oooooooooooooooo");
                }else{
//                	System.out.println(" group id : "+gid+"     DEAD xxxx");
                }
            }
        }
        
        
        //reduce the result.
        //we got alot of groups, and we know some group combines.
        
        // for all the group id, find it's same group as the other
        int sameGid;
        for(int i = groupIdIndex;i>0;--i){
            if(sameGroup.containsKey(i)){
                sameGid = sameGroup.get(i);
                if(aliveGroupSet.contains(sameGid)){
                    aliveGroupSet.add(i); // this group also alive.
                }
                if(aliveGroupSet.contains(i)){
                    aliveGroupSet.add(sameGid); // this group also alive.
                }
            }
        }
        
        //!!!! do this twice, cause in the first time , we can only make sure now the smaller number group id is now safe( alive linked to this one) 
        //!!!!  only for the second time here we can make sure every one in this same group, know sure the same safety.            
        // for example,  we got group 1, later gid 3=>group1,  and later gid5=>1 , gid7=>1, gid9=>7,gid11=>5, and finally, gid 99 alives! and gid99=>7;
        // for the first loop, we got alive group is : 99, 7,
        // !!!!! for real that bug from the test case, happens in order:
        // 1, got group 0, alive
        // 2, got group 20, dead,
        // 3, got group 22, dead,
        // 4, found group 22 and 20 linked.  22->20
        // 5, found group 22 and 0 linked:  22->0 , the set content covers old value 22->20, and now 22->0, so group 20 is dead as not liked to nothing.
        // for the final, when searching alive group, we got gid=22, link to 20, still found dead.
        
        
        
        for(int i = groupIdIndex;i>0;--i){
            if(sameGroup.containsKey(i)){
                sameGid = sameGroup.get(i);
                if(aliveGroupSet.contains(sameGid)){
                    aliveGroupSet.add(i); // this group also alive.
                }
            }
        }
        
//        System.out.println(" alive group id: ");
//        for(int alive:aliveGroupSet){
//        	System.out.print(" , "+alive);
//        }
//        System.out.println("");
        
        
        
//        for(int h=0;h<board.length;++h){
//			for(int w=0;w<board[0].length;++w){
//				System.out.print(" "+board[h][w]);
//			}
//			System.out.println();
//		}
        
        // finally. 
        for(int h=0;h<boardH;++h){
            for(int w=0;w<boardW;++w){
                if(board[h][w]=='X'){
                    //skipped.
                    continue;
                }
                code = boardW*h+w;
                if(positionToGroupId.containsKey(code)){
                    gid = positionToGroupId.get(code);
                    if(aliveGroupSet.contains(gid)){
                        continue;
                    }else{
                        // no survive. flip it.
                        board[h][w]='X';
//                        System.out.println(" change to XXXXXX : pos: "+h+"-"+w);
                    }
                }else{
                	
                    //error, a '0' without a group...
                }
            }
        }
    }
    
}