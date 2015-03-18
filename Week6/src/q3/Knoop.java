package q3;

public class Knoop {
	private Knoop linkerKnoop;
	private Knoop rechterKnoop;
	
	public Knoop(){
		
	}
	
	public Knoop(Knoop links, Knoop rechts){
		this.linkerKnoop = links;
		this.rechterKnoop = rechts;
	}
	
	public void voegtoe(Knoop k){
		if(linkerKnoop == null){
			linkerKnoop = k;
		}else if(rechterKnoop == null){
			rechterKnoop = k;
		}else{
			System.err.println("vol is vol");
		}
	}
	
	public int diepte(){
		//nul want diepte is positief of 0
		int links = 0; 
		int rechts = 0;
		
		if(linkerKnoop!=null){
			links = linkerKnoop.diepte();
		}
		if(rechterKnoop!=null){
			rechts = rechterKnoop.diepte();
		}
		
		if(links > rechts){
			return 1 + links;
		}else{
			return 1 + rechts;
		}
	}
}
