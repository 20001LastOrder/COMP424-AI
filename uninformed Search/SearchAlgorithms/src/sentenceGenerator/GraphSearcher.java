package sentenceGenerator;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class GraphSearcher {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static void main(String args[]) {
		int[] BFSCost = {2,2,2,2,2};
		GraphGenerator searchGraph = new GraphGenerator(BFSCost);
		String[] goals = {" the cat sat", " the cat sat on mat"};
		//bfs(searchGraph, goals);
		int[] uniformCOst = {2,2,2,1,2};
		searchGraph = new GraphGenerator(uniformCOst);
		//uniform(searchGraph, goals);
	}
	
	public static void bfs(GraphGenerator g, String[] goals) {
		Element start = new Element("", 0);
		LinkedList<Element> queue = new LinkedList<Element>();
		queue.add(start);
		int goalCounter = 0; 
		int j = 0;
		while(goalCounter < goals.length) {
			Element e = queue.removeFirst();
			if(e.name.equals(goals[goalCounter])) {
				System.out.println(j + " " + e.name+" GOAL!!");
				goalCounter++;
			}else {
				System.out.println(j + " " + e.name);
			}
			j++;
			queue.addAll(g.expand(e));
		}
	}
	
	public static void uniform(GraphGenerator g, String[] goals) {
		Element start = new Element("", 0);
		PriorityQueue<Element> queue = new PriorityQueue<Element>();
		queue.add(start);
		int goalCounter = 0; 
		int j = 0;
		while(goalCounter < goals.length) {
			Element e = queue.poll();
			if(e.name.equals(goals[goalCounter])) {
				System.out.println(j + " " + e.name +"  "+e.cost+" GOAL!!");
				goalCounter++;
			}else {
				System.out.println(j + " " + e.name+"  "+e.cost);
			}
			j++;
			queue.addAll(g.expand(e));
		}
	}
}
