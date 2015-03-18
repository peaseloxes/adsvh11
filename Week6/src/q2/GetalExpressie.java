package q2;


public class GetalExpressie implements Expressie {
	
	private double waarde;
	
	public GetalExpressie(double waarde){
		this.waarde = waarde;
	}
	
	@Override
	public double calc(double links, double rechts) {
		return waarde;
	}

}
