package sentenceGenerator;

public class Element implements Comparable<Element>{
		protected String name;
		protected int cost;
		
		public Element(String name, int cost) {
			this.name = name;
			this.cost = cost;
		}

		@Override
		public int compareTo(Element arg) {
			if(this.cost - arg.cost != 0) {
				return this.cost - arg.cost;
			}else {
				return this.name.compareTo(arg.name);
			}
		}
		
		public String toString() {
			return this.name;
		}
}
