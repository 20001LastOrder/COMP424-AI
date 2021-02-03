public class CSP {
	
	public ConstrainChecker constrainCheck;
	public int numVariables;
	public int[] domain;
	public int[] solution;
	
	public CSP(ConstrainChecker constrainCheck, int numVariables, int[] domain) {
		this.constrainCheck = constrainCheck;
		this.numVariables = numVariables;
		this.domain = domain;
	}
	
	public int[] recursiveDomain(int[] setup) throws NoSolutionException,
													IllegalArgumentException{
		if(setup.length != numVariables) throw new IllegalArgumentException("bad initial set up");
		int[] assign = setup.clone();
		
		
		return assign;
	}
	
}
