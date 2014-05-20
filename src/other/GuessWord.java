package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import data.DirectedAcyclicGraph;
import data.GraphNode;

public class GuessWord {


	public void go(){
		
		
		String target = "passwo"; // for the very first example we then find that, if duplicate words appear close to each other
		//						 "ss" here. if it don't appear "xss" or "ss_" in the part, we can never tell  which one is repeated.
		
		// make it much easier here.  add "ssw" to part;  or try other words like       paswos
		
		target = "paswos";
		String[] part ={
//				"pas", "pwo", "swo", "aso", "psw","asw","pso","ssw" 		//"passwo"      passwo,     spaswo,psaswo
//				"pas", "pwo", "swo", "aso", "psw","asw","pso","pws","wos","sws"            //this one for "paswos" ;   calc res: pswaos,  spawos,paswos,spwaos,psawos                 
				
				"psp","pap","ssp","aso","spo","ass" // this one for "passpo" ; calc res: psasop, psaspo
				
				
				
		};
		
		
		
		
		
		
		String res = guessWord(part,target.length(),part[0].length());
		
		System.out.println(" res is : "+res+"           equal? "+res.equals(target));
	}
	
	
	/**
	 * 
	 * a word of string length 6;
	 * each time you request, function will return a result of size 3; it's part of word, keep the order
	 * 
	 * for example, the word is "passwo" which you don't know, it's of length 6;
	 * each time you request, result came as a list of string of length 3 as following
	 * pas, pwo, swo,aso, psw   like these
	 * return the guess result.
	 * 
	 * ======================================================================================
	 * for first attempt, try to combine two words and make them a list of term format;
	 * 
	 * for example, 
	 * when i get  "pas" and "pwo", i will try to combile them and make new terms for all the possibility
	 * 
	 *  0123456
	 *   p a s
	 * 
	 * we insert "pwo" to it;
	 * insert p,  if p is the same p as p in "pas",  no insert need to be done, but after this, "wo"insert after "p":1 
	 * isnert p , if p is NOT the same p as p in"pas".  insert position can be 0,2,4,6 , choose one, and the rest word "wo" follows; 
	 * so we get:
	 * p|as, wo       ; vertical bar is for where "wo" insert begin;
	 * p|pas, wo
	 * pp|as , wo
	 * ppa|s, wo
	 * ppas|, wo
	 * 
	 * it's dynamic programming for this kind of insert
	 * 
	 * ;
	 * and for there contains same char, like
	 * "asw","pso"
	 * if these "s" are for the same "s",  need to  separate as    insert p to "asw" before s,  insert  o to "asw" after "s" 
	 * 
	 * 
	 * so topological order in a graph is better than this. cause many causes result in one graph.
	 * =============================================================================
	 * ideas about graph/
	 * 
	 * put all the same char "s" in one node is not good; i've proved this.    
	 * in case "psp", "pap","ssp"   in this example, when draw on graph , with one node for "s" and one node for "p"
	 * we can get result like "spapso", that have on the graph p->s,s->p, p-a,a->p, s->s;  but no p->s->p;
	 * better separate it. 
	 * 
	 * so , for each that one char for two or more nodes;
	 * if the char is 's' for example, nodes are listed as s0,s1,s2,s3 of topological order:  s0->s1->s2->s3 
	 * when  we have something like "pas" come, we just add to the graph p->a->s3; cause any "s" in "pas" will result p->a->s3; and this also works.   
	 * same way we can add "swo" as s0->w->o to get graph
	 * but sadly, for "aso", we have to try all the case a->s0->o, a->s1->o, a->s2->o, a->s3->o.   any of them can be done, we are done.
	 * 
	 * finally, use topology order, get the result in the graph.
	 * 
	 * @param guess
	 * @return
	 */
	public String guessWord(String[] part,int resLen,int partLen){
		ArrayList<String> termFormat = new ArrayList<String>();
		
		//1, check chars, better get count for each char;
			// maybe need a graph to get count info: 
//				A->B and B->A,   C->B,B->C, and D,E;  we got A,B,C,D,E already, so the only possible thing is that count(B) =2;
			//thus   B0->A->B1 ,  B0->C->B1 , and D,E;
			// to make this easy, add BAB or BXB to part, thus we know we have two Bs....
		//2, for each char, create a GraphNode; ready for DAG, and get topology order
		//3, for each dup char, create a node for each dup, and also order them;
		//4. for dup char in the mid; all node are used for possible case;
			// each possible case mean a whole new graph; 
			// cause a graph stand for a solution;
		
		
		
		HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
		
		HashMap<String,GraphNode> nodeMap = new HashMap<String,GraphNode>(); // map from node.name to node object 
		for(String ps:part){
			HashMap<Character,Integer> charCountOneString = new HashMap<Character,Integer>();
			char[] ca = ps.toCharArray();
			for(char c:ca){
				if(charCountOneString.containsKey(c)){
					int was = charCountOneString.get(c);
					charCountOneString.put(c, was+1);
				}else{
					charCountOneString.put(c, 1);
				}
			}
			
			for(char c:charCountOneString.keySet()){
				int countInString = charCountOneString.get(c);
				if(charCount.containsKey(c)){
					int wasMax = charCount.get(c);
					if(wasMax<countInString){
						charCount.put(c, countInString);
					}
				}else{
					charCount.put(c, 1);
				}
			}
		}
		System.out.println(" after input parts, get count(step1 done) : ");
		String name;
		int id= 0;
		GraphNode[] nodeListForGraph = new GraphNode[resLen];
		ArrayList<Edge_GW> edgesToAddForDupChar= new ArrayList<Edge_GW>();
		for(char c:charCount.keySet()){
			int count = charCount.get(c);
			System.out.println(" char : "+c+"  , count: "+ charCount.get(c));
			name = String.valueOf(c);
			
//			GraphNode gn = new GraphNode(name,id);
//			nodeListForGraph[id] = gn;
//			id++;
//			nodeMap.put(name,gn);
			for(int i=0;i<count;++i){
				name = String.valueOf(c)+i;
				GraphNode gn = new GraphNode(name,id);
				nodeListForGraph[id] = gn;
				
				//dup chars add order edge
				if(i>0){
					Edge_GW edg = new Edge_GW();
					edg.from = id-1;
					edg.to = id;
					edgesToAddForDupChar.add(edg);
				}
				
				
				id++;
				nodeMap.put(name,gn);
			}
		}
		
		if(nodeListForGraph[resLen-1]==null){
			System.out.println("========= not enough chars,  add any char to result maybe still goood guess====     quit here in case null point");
			return null;
		}
		

		//////////////////////////////////////step 2;                    use graph
		//add edges to graph; if we have many choice; duplicate; graphs;
		int len = part.length;
		 
		
		
		// basic graph with edges added, with out duplicate case; this one will be reused many times;
		DirectedAcyclicGraph baseDAG = new DirectedAcyclicGraph(nodeListForGraph);
		
//		add order edge;
		try {
				for(Edge_GW edg:edgesToAddForDupChar){
					baseDAG.addEdge(edg.from, edg.to);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		System.out.println(" graph node list index :");
		for(int i =0;i<nodeListForGraph.length;++i){
			System.out.println("  node  "+i+"   :   "+nodeListForGraph[i].name);
		}
		
		//all hte possible result;
		HashSet<String> resultSet = new HashSet<String>();
		ArrayList<ArrayList<Edge_GW>> allDupChoicePart = new ArrayList<ArrayList<Edge_GW>>();
		ArrayList<Edge_GW> dummy = new ArrayList<Edge_GW>();
		allDupChoicePart.add(dummy);
//		add edge  from part string;
		int a,b,c;
		String nn;
		for(String ps:part){
			System.out.println("---------- string is : "+ps);
			// from string to node;
			//from node to index of graph node ,  get id: (a,b,c)
			char[] ca = ps.toCharArray();
			int count;
			
			// we foudn that no matter first char is dup or not, use +"0" always works
//			count = charCount.get(ca[0]);
//			if(count>1){
//				//dup first char, take first one;
//				nn = String.valueOf(ca[0])+"0";
//			}else{
//				// else , only one char, still same; name;
//				nn = String.valueOf(ca[0])+"0";
//			}
			nn = String.valueOf(ca[0])+"0";
			a = nodeMap.get(nn).id; //get node id; it's also id in the graph 
			
			
			// last char
			count = charCount.get(ca[2]);
			if(count>1){
				//dup last char, take last one;
				nn = String.valueOf(ca[2])+String.valueOf(count-1);
			}else{
				nn = String.valueOf(ca[2])+"0";
			}
			c = nodeMap.get(nn).id; //get node id; it's also id in the graph
			
				if(charCount.get(ca[1])>1){
					
					System.out.println(" *2 : "+ps+"        have to dup cases");
					// need duplicate current cases, we may need *2 current graph to get all the cases;
					// add these cases to dup edges first so we don't handle these now. we do this later;
					count = charCount.get(ca[1]);
					int sizeOfCurrent = allDupChoicePart.size();
					System.out.println(" size of current dup choice: "+sizeOfCurrent);
					for(int choice=0;choice<sizeOfCurrent;++choice){
						ArrayList<Edge_GW> edges = allDupChoicePart.get(choice);
						//have many choices;
						for(int i=1;i<count;++i){
							ArrayList<Edge_GW> copy = new ArrayList<Edge_GW>();
							for(int k=0;k<edges.size();++k){
								//reuse edges, as their value will not be changed;
								copy.add(edges.get(k));
							}
							//add to the end;
							nn = String.valueOf(ca[1])+String.valueOf(i);
							b = nodeMap.get(nn).id;
							
							System.out.println(" a,b,c for this part;   "+a+" - "+b+" - "+c);
							
							if(a==b||b==c){
								System.out.println(" actually is dup, don't add "+a+" - "+b+" - "+c);
							}else{
								Edge_GW ed = new Edge_GW();
								ed.from = a;
								ed.to = b;
								copy.add(ed);
								Edge_GW ed2 = new Edge_GW();
								ed2.from = b;
								ed2.to = c;
								copy.add(ed2);
								allDupChoicePart.add(copy);
							}
						}
						
						//case 0;
						nn = String.valueOf(ca[1])+"0";
						b = nodeMap.get(nn).id;
						
						Edge_GW ed = new Edge_GW();
						ed.from = a;
						ed.to = b;
						edges.add(ed);
						
						Edge_GW ed2 = new Edge_GW();
						ed2.from = b;
						ed2.to = c;
						edges.add(ed2);
					}
					
				}else{
					// mid not dup, good, just add to baseGraph, cause it will always be added;
					nn = String.valueOf(ca[1])+"0";
					b = nodeMap.get(nn).id; //get node id; it's also id in the graph
					
					try {
						System.out.println(" add edge to basic graph");
						System.out.print(" ,  "+nodeListForGraph[a].name+"->"+nodeListForGraph[b].name);
						System.out.print(" ,  "+nodeListForGraph[b].name+"->"+nodeListForGraph[c].name);
						System.out.println(" ");
						baseDAG.addEdge(a,b);
						baseDAG.addEdge(b,c);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
		
		
		// now we get basegraph, and some edges of multichoice;
		System.out.println("choice size: "+allDupChoicePart.size());
		
		
		System.out.println(" print base dag: ");
		baseDAG.printAllEdges();
		
//		baseDAG
		//don't change base dag; use copy for each case;
		for(ArrayList<Edge_GW> edges:allDupChoicePart){
			//
			DirectedAcyclicGraph copyDAG = baseDAG.clone();
			System.out.println(" base graph copy, check it's edges? , now add edges : ");
			
			try {
				for(Edge_GW ed:edges){
//					System.out.print(" ,  "+ed.from+"->"+ed.to);
					System.out.print(" ,  "+nodeListForGraph[ed.from].name+"->"+nodeListForGraph[ed.to].name);
					
						copyDAG.addEdge(ed.from,ed.to);
						
				}
				
				
				//get result for this case; ONLY WHEN NOTHING WRONG DURING ADD EDGE
				ArrayList<String> resOneCase = copyDAG.getTopologicalOrderedPairs();
				
				long l = Integer.MAX_VALUE;
				l*=l;
				System.out.println(" show res of this case:  "+l);
				if(resOneCase!=null){
					for(String resStr:resOneCase){
						System.out.println(" ===> "+resStr);
						resultSet.add(resStr);
					}
					System.out.println("");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("");
			
			
		}
		
		System.out.println(" final ressss ; ");
		String sample = "";
		for(String s:resultSet){
			System.out.println(s);
			sample = s;
		}
		
		return sample;
	}
	
	private class Edge_GW{
		int from;
		int to;
	}

}
