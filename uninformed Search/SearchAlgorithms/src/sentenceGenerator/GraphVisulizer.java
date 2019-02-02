package sentenceGenerator;

import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphVisulizer {
	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int[] GraphCost = {2,2,2,1,2};
		GraphGenerator g = new GraphGenerator(GraphCost);
		visulizeGraph(g,2,false);

	}
	
	public static void visulizeGraph(GraphGenerator g, int level, boolean isGraph) {
		Graph tree = new SingleGraph(" ");
		Element p = g.getNextLevel().get(0);
		Node source = tree.addNode(p.name);
		source.addAttribute("xy", 0.0, 0.0);
		source.addAttribute("cost", p.cost);
		int y = 0;
		ArrayList<Node> parents = new ArrayList<Node>();
		parents.add(source);
		for(int i = 0; i < level; i++) {
			y-=6;
			ArrayList<Element> elements = g.getNextLevel();
			double increment = 128 / elements.size();
			ArrayList<Node> children = new ArrayList<Node>();
			for(int j = 0; j < elements.size(); j++) {
				Element element = elements.get(j);
				Node n = tree.addNode(parents.get(j/5).getId()+element.name);
				n.setAttribute("xy", -64+increment*j, y);
				int edgeCost = element.cost-(int)parents.get(j/5).getAttribute("cost");
				n.addAttribute("cost", element.cost);
				Edge edge = tree.addEdge(Math.random()+"", parents.get(j/5), n);
				if(isGraph) {
					n.addAttribute("ui.label", element.name);
					edge.addAttribute("ui.label", edgeCost+"");
				}else {
					n.addAttribute("ui.label", element.name+"\n ("+element.cost+")");

				}
				
				children.add(n);
			}
			parents = children;
		}
		Node n1 = tree.addNode("DUMMY1");
		n1.addAttribute("xy",	65, 10);
		tree.addAttribute("ui.stylesheet", " node {\r\n" + 
				" fill-color: black;" +
				"text-size:15;" +
				"text-color: red;" + 
				"text-alignment: under;"+
				"   }"
				+ "edge{\r\n"
				+ "text-color: blue;"
				+ "}");
		tree.display(false);
		
	}
}
