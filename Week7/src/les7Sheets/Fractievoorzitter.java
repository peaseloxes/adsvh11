package les7Sheets;

import java.util.Comparator;

public class Fractievoorzitter implements Comparable<Fractievoorzitter> {
	private String naam;
	private String haarkleur;
	private String taalgebruik;

	public Fractievoorzitter(String naam, String haarkleur, String taalgebruik) {
		this.naam = naam;
		this.haarkleur = haarkleur;
		this.taalgebruik = taalgebruik;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getHaarkleur() {
		return haarkleur;
	}

	public void setHaarkleur(String haarkleur) {
		this.haarkleur = haarkleur;
	}

	public String getTaalgebruik() {
		return taalgebruik;
	}

	public void setTaalgebruik(String taalgebruik) {
		this.taalgebruik = taalgebruik;
	}

	@Override
	public int compareTo(Fractievoorzitter other) {

		if (this.getOrderNumberForTaalgebruik() > other
				.getOrderNumberForTaalgebruik()) {
			System.err.println("this > other");
			return 1;
		} else if (this.getOrderNumberForTaalgebruik() <= other
				.getOrderNumberForTaalgebruik()) {
			System.err.println("this < other");
			return -1;
		} else {
			//nooit
			return 0;
		}

	}

	private int getOrderNumberForTaalgebruik() {
		String[] preferredOrder = new String[] { "ABN", "Haags", "Vloeken" };

		for (int i = 0; i < preferredOrder.length; i++) {
			if (preferredOrder[i].equals(this.taalgebruik)) {
				return i;
			}
		}

		return -1;
	}
	
	private int getOrderNumberForHaarkleur() {
		String[] preferredOrder = new String[] { "Grijs", "Zwart", "Donkerblond", "Wit", "Geen" };

		for (int i = 0; i < preferredOrder.length; i++) {
			if (preferredOrder[i].equals(this.haarkleur)) {
				return i;
			}
		}

		return -1;
	}

	public static Comparator<Fractievoorzitter> getHairComparator() {
		Comparator<Fractievoorzitter> c = new Comparator<Fractievoorzitter>() {

			@Override
			public int compare(Fractievoorzitter one, Fractievoorzitter other) {
				if (one.getOrderNumberForHaarkleur() > other
						.getOrderNumberForHaarkleur()) {
					return 1;
				} else if (one.getOrderNumberForHaarkleur() <= other
						.getOrderNumberForHaarkleur()) {
					return -1;
				} else {
					// nooit
					return 0;
				}
			}
		};

		return c;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Fractievoorzitter)){
			return false;
		}
		
		Fractievoorzitter other = (Fractievoorzitter) o;
		
		if(other.naam.equals(this.naam)){
			return true;
		}else{
			return false;
		}
		
	}
	// private int getList(List<? super Number> l){
	// return l.get(0).
	// }
}
