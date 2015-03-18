package sheetles6;

import java.util.LinkedList;
import java.util.List;

// op basis van informatie op
// http://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/specialtrees/
public class NKnoop<Persoon> {
	private Persoon ik;
	private Persoon partner;
	private List<NKnoop<Persoon>> kinderen;

	public NKnoop(Persoon ik) {
		this.ik = ik;
		this.kinderen = new LinkedList<NKnoop<Persoon>>();
	}

	public Persoon getPersoon() {
		return ik;
	}

	public void addKind(NKnoop<Persoon> kind) {
		this.kinderen.add(kind);
	}

	public Persoon getPartner() {
		return partner;
	}

	public void setPartner(Persoon partner) {
		this.partner = partner;
	}
	
	public boolean heeftKinderen(){
		if(this.kinderen.isEmpty()){
			return false;
		}
		return true;
	}
	
	public List<NKnoop<Persoon>> getKindlozen(){
		List<NKnoop<Persoon>> kinderlozen = new LinkedList<>(); 
		
		if(!heeftKinderen()){
			kinderlozen.add(this);
		}else{
			
			for(NKnoop<Persoon> kind : kinderen){
				kinderlozen.addAll(kind.getKindlozen());
			}
		}
		return kinderlozen;
	}
	
	public List<NKnoop<Persoon>> getOverledenen(){
		List<NKnoop<Persoon>> overledenen = new LinkedList<>();
		if(((sheetles6.Persoon)ik).isDood()){ // komt door NKnoop<Persoon> ? twee Persoon termen zijn niet gelijkwaardig o.i.d. 
			overledenen.add(this);
		}
		for(NKnoop<Persoon> kind : kinderen){
			overledenen.addAll(kind.getOverledenen());
		}
		
		return overledenen;
	}
	
	public NKnoop<Persoon> vindKnoop(final NKnoop<Persoon> doel) {
		// of ik ben het doel
		if (this == doel) {
			return this;
		}

		// of een van mijn kinderen is het doel
		NKnoop<Persoon> mogelijkDoel = null;

		for (NKnoop<Persoon> kind : kinderen) {
			if (mogelijkDoel == null) {
				mogelijkDoel = kind.vindKnoop(doel);
			}
		}

		return mogelijkDoel;
	}

	public NKnoop<Persoon> isOuderVan(final NKnoop<Persoon> doel){
		
		// of ik ben de ouder
		if(kinderen.contains(doel)){
			return this;
		}
		
		// of een van mijn kinderen is de ouder
		NKnoop<Persoon> mogelijkDoel = null;

		for (NKnoop<Persoon> kind : kinderen) {
			if (mogelijkDoel == null) {
				mogelijkDoel = kind.isOuderVan(doel);
			}
		}

		return mogelijkDoel;
	}
	
	public List<NKnoop<Persoon>> getNevenEnNichten(final NKnoop<Persoon> doel){
		// als ik de ouder ben van doel, ga op zoek naar neven en nichten
		List<NKnoop<Persoon>> nevenEnNichten = new LinkedList<>();
		
		if(this.equals(isOuderVan(doel))){
			// alle kinderen van mijn andere kinderen zijn neven/nichten van doel
			for(NKnoop<Persoon> kind : kinderen){
				
				// niet zijn/haar eigen kinderen
				if(kind!=doel){ 
					nevenEnNichten.addAll(kind.getMijzelEnKindskinderen());
					nevenEnNichten.remove(kind); // broer/zus weer weghalen
				}
			}
		}else{
			
			// anders is een van mijn kinderen ouder van het doel
			for(NKnoop<Persoon> kind : kinderen){
				nevenEnNichten.addAll(kind.getNevenEnNichten(doel));
			}
		}
		
		return nevenEnNichten;
	}
	
	
	// voor recursie is het noodzakelijk om ook zichzelf terug te geven
	public List<NKnoop<Persoon>> getMijzelEnKindskinderen(){
		List<NKnoop<Persoon>> kindskinderen = new LinkedList<>(); 
		
		// als ik geen kinderen heb, alleen mezelf
		if(kinderen.isEmpty()){
			kindskinderen.add(this);
		}else{
			// anders ook al mijn kinderen vragen
			kindskinderen.add(this);
			for(NKnoop<Persoon> kind : kinderen){
				kindskinderen.addAll(kind.getMijzelEnKindskinderen());
			}
		}
		
		return kindskinderen;
		
	}
}
