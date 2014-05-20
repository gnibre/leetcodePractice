package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * 
 * created for WordGuess, need topological order for direct acyclic graph;     maybe used for other problems too;
 * 
 * 
 * 
 * @author gnibrE
 *
 *
 */
public class DirectedAcyclicGraph extends Graph{

	int sizeOfGraph;
	
	boolean useAdj = false;
	// either use direct , full, or use adj
	int[][] direct;
	
	ArrayList<ArrayList<Integer>> adj;
	
	GraphNode[] nList;
	
	public DirectedAcyclicGraph(GraphNode[] list){
		initWithNodeList(list);
	}
	
	public DirectedAcyclicGraph(int sizeOfNodes){
		GraphNode[] list = new GraphNode[sizeOfNodes];
		for(int i=0;i<sizeOfNodes;++i){
			GraphNode gn = new GraphNode("n"+i);
			list[i] = gn;
		}
		initWithNodeList(list);
	}
	
	//-----------------------graph operation ; public interface;
	
	public DirectedAcyclicGraph clone(){
		DirectedAcyclicGraph clone = new DirectedAcyclicGraph(nList);
		clone.useAdj = false;
		try {
			for(int i=0;i<nList.length;++i){
				for(int j=0;j<nList.length;++j){
					if(direct[i][j]==1){
						clone.addEdge(i, j);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	public void printAllEdges(){
		System.out.println(" print all edges in this graph: ");
		for(int i=0;i<nList.length;++i){
			for(int j=0;j<nList.length;++j){
				if(direct[i][j]==1){
					System.out.print(" "+nList[i].name+"->"+nList[j].name);
				}
			}
		}
		System.out.println(" ");
	}
	
	
	
	private void initWithNodeList(GraphNode[] list){
		if(list==null||list.length<1){
			return;
		}
		sizeOfGraph = list.length;
		nList = new GraphNode[sizeOfGraph];
		for(int i=0;i<sizeOfGraph;++i){
			nList[i] = list[i];
			//ref;
		}

		useAdj = false;
		adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<sizeOfGraph;++i){
			ArrayList<Integer> adjForNodei = new ArrayList<Integer>();
			adj.add(adjForNodei);
		}
		direct = new int[sizeOfGraph][sizeOfGraph];
	}
	
	public void addEdge(int a,int b) throws Exception{
//		System.out.println("addEdge,  "+a+"->"+b+"       useAdj: "+useAdj);
		if(useAdj){
			addEdgeAdj(a,b);
		}else{
			addEdgeDirect(a,b);
		}
	}
	
	
	//=================================================graph functions
		
	private void addEdgeDirect(int a,int b) throws Exception{
//		System.out.println("addEdgeDirect,  "+a+"->"+b);
		if(a<0||a>=sizeOfGraph||b<0||b>=sizeOfGraph){
			System.out.println("bad node index ,  "+a+"->"+b);
			Exception e = new Exception(" bad node index");
			throw e;
		}
		
		if(a==b){
			System.out.println("BAD EDGE(toself) FOR A DAG FIND CYCLE,  "+a+"->"+b);
			Exception e = new Exception("BAD edge(toself) for a DAG ; DIRECTED ACYNCLIC GRAPH!  ");
			throw e;
		}
		
		if(direct[b][a]==1){
			System.out.println("BAD EDGE FOR A DAG FIND CYCLE,  "+a+"->"+b);
			Exception e = new Exception("BAD edge for a DAG ; DIRECTED ACYNCLIC GRAPH!  ");
			throw e;
		}
		
		direct[a][b] = 1;
		System.out.println(" added: "+a+"->"+b+"        "+nList[a]+"->"+nList[b]);
	}
	
	/**
	 * add edge from a -> b; when graph is using adj;
	 * @param a
	 * @param b
	 */
	private void addEdgeAdj(int a,int b){
		if(a<0||a>=sizeOfGraph||b<0||b>=sizeOfGraph){
			return;
		}
		ArrayList<Integer> adjForNodeA = adj.get(a);
		
		for(int i=0;i<adjForNodeA.size();++i){
			int e = adjForNodeA.get(i);
			if(e<b){
				continue;
			}else if(e==b){
				break;// already in.
			}else{
				// almost mis it; // add b here;
				adjForNodeA.add(i, b);
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * return all the possibilities, with pairs; 
	 * 
	 * say  A->B , A->C,       B->D, C->D    D->F,D->E
	 * return will be [A],[B,C],[D],[E,F]; 
	 * KINDA NOT ALL OF THEM ......... SAVE THIS  ONE/BUG FOR LATER...........
	 * 
	 * 
	 * @return
	 */
	public ArrayList<String> getTopologicalOrderedPairs(){
		if(useAdj){
			System.out.println(" ---------- not implemented yet; for adj to run topology order");
			return null;
		}
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		
		//find nodes with no income;  it is good for 
		
		HashSet<Integer> already = new HashSet<Integer>();
		int[] incomeCount = new int[sizeOfGraph];
		for(int i=0;i<sizeOfGraph;++i){
			incomeCount[i] = 0;
		}
		
		
		for(int i=0;i<sizeOfGraph;++i){
			//check income for each node;
			for(int node=0;node<sizeOfGraph;++node){
				if(direct[i][node]==1){
					incomeCount[node]++; 
				}	
			}
		}
		
		////////////////only works when it is a good DAG;  but not guaranteed at add edge stage...take care;
		
		int lastTime;
		System.out.println("get topology order : ");
		while(already.size()<sizeOfGraph){
			lastTime = already.size();
			// this round maybe one or more nodes;
			ArrayList<Integer> thisRoundNoIncome = new ArrayList<Integer>();
			System.out.print("[");
			for(int i=0;i<sizeOfGraph;++i){
				if(incomeCount[i]==0&&!already.contains(i)){
					thisRoundNoIncome.add(i);
					already.add(i);
					System.out.print(", "+nList[i]);
				}
			}
			System.out.println("]");
			res.add(thisRoundNoIncome);
			//update count map;
			for( int node:thisRoundNoIncome){
				for(int out=0;out<sizeOfGraph;++out){
					if(direct[node][out]==1){
						incomeCount[out]--;
					}
				}
			}
			if(already.size()==lastTime){
				//no change;
				break;
			}
		}
		
		int allSize =0;
		ArrayList<String> strRes = new ArrayList<String>();
		strRes.add("");
		
		for(ArrayList<Integer> groups:res){
			allSize+=groups.size();
		}
		if(allSize!=sizeOfGraph){
			System.out.println(" -------------- graph res dag topoloy broken ; no result");
			this.printAllEdges();
			return null;
		}
		
		
		for(ArrayList<Integer> groups:res){
			
			ArrayList<String> groupString = convertFromPossibleSameLevelNodesToString("",groups);
			
			int oriSize = strRes.size();
			for(int i=0;i<oriSize;++i){
				String s = strRes.get(i);
				String news;
				for(String fromnew:groupString){
					news = s+fromnew;
					strRes.add(news);
				}
			}
			
			for(int i=0;i<oriSize;++i){
				strRes.remove(0);
			}
			
		}
		
		
		return strRes;
	}
	
	/***
	 * one group is of id:      {1,2,3} so in this group; order doesn't matter; which means, permutation;
	 * result is 
	 * 123
	 * 132
	 * 213
	 * 231
	 * 312
	 * 321;
	 * 
	 * @param oneGroup
	 * @return
	 */
	private ArrayList<String> convertFromPossibleSameLevelNodesToString(String base,ArrayList<Integer> oneGroup){
		ArrayList<String> res = new ArrayList<String>();
		if(oneGroup.size()==0){
			res.add(base);
			return res;
		}
		
//		System.out.println(" group is : ");
//		for(int ii:oneGroup){
//			System.out.print("  "+ii);
//		}System.out.println(" ");
		
		
		
		int size = oneGroup.size();
		String newbase;
		for(int i=0;i<size;++i){
			newbase = base+nList[oneGroup.get(i)].name.charAt(0);
			
			ArrayList<Integer> left = new ArrayList<Integer>();
			for(int k=0;k<size;++k){
				if(k!=i) left.add(oneGroup.get(k));
			}
			ArrayList<String> partRes = convertFromPossibleSameLevelNodesToString(newbase,left);
			res.addAll(partRes);
		}
		
//		System.out.println(" result permuation string is : ");
//		for(String s:res){
//			System.out.print("  "+s);
//		}System.out.println(" ");
		return res;
	}
	
}
