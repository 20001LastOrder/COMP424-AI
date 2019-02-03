package sentenceGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class GraphSearcher {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static void main(String args[]) {
		int[] BFSCost = {2,2,2,2,2};
		GraphGenerator searchGraph = new GraphGenerator(BFSCost, false);
		String[] goals = {" the cat sat", " the cat sat on the mat"};
		bfs(searchGraph, goals, "bfs.txt");
		searchGraph = new GraphGenerator(BFSCost, true);
		ids(searchGraph, goals, "ids.txt");
		int[] uniformCOst = {2,2,2,1,2};
		searchGraph = new GraphGenerator(uniformCOst, false);
		uniform(searchGraph, goals, "uniform.txt");
	}
	
	public static void printToFile(String filename, String content){
		PrintStream system = System.out;
		try {
			PrintStream out = new PrintStream(new File(filename));
			System.setOut(out);
			System.out.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		System.setOut(system);
		System.out.println("Search FINISHED, file exported to " + filename);
	}
	
	public static void bfs(GraphGenerator g, String[] goals, String filename) {
		Element start = new Element("", 0);
		LinkedList<Element> queue = new LinkedList<Element>();
		queue.add(start);
		String result = "";
		boolean goalReached = false;
		int j = 0;
		while(true) {
			Element e = queue.removeFirst();
			for(String goal : goals){
				if(e.name.equals(goal)) {
					goalReached = true;
				}
			}
			if(goalReached) {
				result += j + e.name+" ("+e.parent+") GOAL!!\n";
				break;
			}else {
				result += j + e.name+" ("+e.parent + ")\n";
			}
			ArrayList<Element> subsequents = g.expand(e);
			for(Element ele : subsequents){
				ele.parent = j;
			}
			j++;
			queue.addAll(subsequents);
		}
		//print result to file
		printToFile(filename, result);
	}
	
	public static void uniform(GraphGenerator g, String[] goals, String filename) {
		Element start = new Element("", 0);
		PriorityQueue<Element> queue = new PriorityQueue<Element>();
		queue.add(start);
		String result = "";
		boolean goalReached = false;
		int j = 0;
		while(true) {
			Element e = queue.poll();
			for(String goal : goals){
				if(e.name.equals(goal)) {
					goalReached = true;
				}
			}
			if(goalReached) {
				result += j  + e.name +"  ["+e.cost+"] ("+e.parent+") GOAL!!\n";
				break;
			}else {
				result +=j + e.name+"  ["+e.cost+"] ("+e.parent+")\n";
			}
			ArrayList<Element> subsequents = g.expand(e);
			for(Element ele : subsequents){
				ele.parent = j;
			}
			j++;
			queue.addAll(subsequents);
		}
		printToFile(filename, result);
	}
	
	public static void ids(GraphGenerator g, String[] goals, String filename){
		int depth = 0;
		boolean goalReached = false;
		String result = "";
		outloop:
		while(true){
			int j = 0;
			Element start = new Element("", 0);
			Stack<IDSElement> stack = new Stack<IDSElement>();
			stack.push(new IDSElement(start, 0));
			result+="*****************Depth:"+depth+"********************\n";
			while(stack.size()>0){
				IDSElement e = stack.pop();
				if(e.depth < depth){
					ArrayList<Element> subsequents = g.expand(e.element);
					for(Element ele : subsequents){
						ele.parent = j;
						stack.push(new IDSElement(ele, e.depth+1));
					}
				}
				for(String goal : goals){
					if(e.element.name.equals(goal)) {
						goalReached = true;
					}
				}
				if(goalReached) {
					result += j + e.element.name+" ("+e.element.parent+") GOAL!!\n";
					break outloop;
				}else {
					result += j + e.element.name+" ("+e.element.parent + ")\n";
				}
				
				j++;
			}
			depth++;
		}
		printToFile(filename, result);
	}
	
	static class IDSElement{
		Element element;
		int depth;
		public IDSElement(Element element, int depth){
			this.element = element;
			this.depth = depth;
		}
	}
}
