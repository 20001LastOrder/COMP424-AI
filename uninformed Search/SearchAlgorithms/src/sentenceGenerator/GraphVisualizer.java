package sentenceGenerator;

import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/*
 * This class is used to visualize the graph with the rule specified in GraphGenerator
 */
public class GraphVisualizer {
	public static void main(String args[]) {
		//set the properties corresponding to the graph renderer
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		//set cost of each action of the graph
		int[] GraphCost = {2,2,2,1,2};
		GraphGenerator g = new GraphGenerator(GraphCost, false);
		visualizeGraph(g,2,true);

	}
	
	/**
	 * Graph visualize part
	 * @param g: the graph to be visualized 
	 * @param level: the depth of the graph should be visualized up to
	 * @param isGraph: specified the graph should be visualized as a search graph or search tree
	 */
	public static void visualizeGraph(GraphGenerator g, int level, boolean isGraph) {
		//create a new Graph
		Graph tree = new SingleGraph(" ");
		Element p = g.getNextLevel().get(0);
		
		//Add the initial state
		Node source = tree.addNode(p.name);
		source.addAttribute("xy", 0.0, 0.0);
		source.addAttribute("cost", p.cost);  //add the cost used to calculate the costs of subsequent state
		// initial y coordinate of nodes
		int y = 0;
		
		//used to store the parent node of the level up
		ArrayList<Node> parents = new ArrayList<Node>();
		parents.add(source);
		
		for(int i = 0; i < level; i++) {
			//update the y coordinate: do deep
			y-=6;
			//get the next level node of the graph
			ArrayList<Element> elements = g.getNextLevel();
			double increment = 128 / elements.size();	//increment of x coordinate
			ArrayList<Node> children = new ArrayList<Node>();	// add all node generated as children
			for(int j = 0; j < elements.size(); j++) {
				Element element = elements.get(j);
				Node n = tree.addNode(parents.get(j/5).getId()+element.name);	//generate node with specified name
				n.setAttribute("xy", -64+increment*j, y);	//update x coordinate to make the draw as a tree
				int edgeCost = element.cost-(int)parents.get(j/5).getAttribute("cost");
				n.addAttribute("cost", element.cost);
				Edge edge = tree.addEdge(Math.random()+"", parents.get(j/5), n);   //create an edge with random id (id is not used)
				
				//add labels to the graph as a search tree or graph
				if(isGraph) {
					n.addAttribute("ui.label", element.name);
					edge.addAttribute("ui.label", edgeCost+"");
				}else {
					n.addAttribute("ui.label", element.name+"\n ("+element.cost+")");

				}
				
				children.add(n);
			}
			
			//assign the children as parents for next level
			parents = children;
		}
		
		//add a Dummy node to scale the graph drawing area
		Node n1 = tree.addNode("DUMMY1");
		n1.addAttribute("xy",	65, 10);
		
		//add CSS attribute to visualize graph
		tree.addAttribute("ui.stylesheet", " node {\r\n" + 
				" fill-color: black;" +
				"text-size:15;" +
				"text-color: red;" + 
				"text-alignment: under;"+
				"   }"
				+ "edge{\r\n"+
				"text-size:20;" 
				+ "text-color: blue;"
				+ "}");
		
		//draw the graph without random placement of nodes
		tree.display(false);
		
	}
}
