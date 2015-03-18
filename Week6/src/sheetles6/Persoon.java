package sheetles6;

public class Persoon {
	private String naam;
	private String geboorteJaar;
	private String sterfteJaar;
	private Geslacht geslacht;
	
	public Persoon(String naam, String geboorte, String sterfte, Geslacht geslacht){
		this.naam = naam;
		this.geboorteJaar = geboorte;
		this.sterfteJaar = sterfte;
		this.geslacht = geslacht;
	}
	
	@Override
	public String toString(){
		return naam + " (" + geboorteJaar + " - " + sterfteJaar + ")";
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getGeboorteJaar() {
		return geboorteJaar;
	}

	public void setGeboorteJaar(String geboorteJaar) {
		this.geboorteJaar = geboorteJaar;
	}

	public String getSterfteJaar() {
		return sterfteJaar;
	}

	public void setSterfteJaar(String sterfteJaar) {
		this.sterfteJaar = sterfteJaar;
	}

	public boolean isDood(){
		if("".equals(sterfteJaar)){
			return false;
		}else{
			return true;
		}
	}
	
	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public enum Geslacht{
		MANNELIJK,
		VROUWELIJK;
	}
}
