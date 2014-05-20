package data;


/**
 * node element for DirectedGraph
 * @author gnibrE
 *
 */
public class GraphNode {	
	public String name;
	public int id;
	public GraphNode(String s){
		name = s;
	}
	
	public GraphNode(String s,int inId){
		name = s;
		id = inId;
	}
	
	public String toString(){
		return name;
	}
}
