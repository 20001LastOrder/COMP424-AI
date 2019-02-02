package sentenceGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class GraphGenerator {
	public ArrayList<Element> words;
	private int level;
	public ArrayList<Element> currentLevelNodes;
	
	public GraphGenerator(int[] costs) {
		words = new ArrayList<Element>();
		words.add(new Element("the", costs[0]));
		words.add(new Element("cat", costs[1]));
		words.add(new Element("sat", costs[2]));
		words.add(new Element("on", costs[3]));
		words.add(new Element("mat", costs[4]));
		currentLevelNodes = new ArrayList<Element>();
		Collections.sort(words);
		level = -1;
	}
	
	public ArrayList<Element> getNextLevel(){
		level++;
		if(level == 0) {
			currentLevelNodes.add(new Element("", 0));
		}else {
			int length = currentLevelNodes.size();
			for(int i = 0; i < length; i++) {
				Element ce = currentLevelNodes.remove(0);
				for(Element ele : words) {
					Element e = new Element(ce.name +" "+ ele.name, ce.cost + ele.cost);
					currentLevelNodes.add(e);
				}
			}
		}
		return currentLevelNodes;
	}
	
	public ArrayList<Element> expand(Element e){
		ArrayList<Element> elements = new ArrayList<Element>();
		for(Element ele : words) {
			Element sub = new Element(e.name +" "+ ele.name, e.cost + ele.cost);
			elements.add(sub);
		}
		return elements;
	}
}
