package sheetles6;

import java.util.List;

import sheetles6.Persoon.Geslacht;

public class Stamboom {
	private NKnoop<Persoon> eersteGeneratie;
	private NKnoop<Persoon> teVindenMoeder;
	private NKnoop<Persoon> teVindenNevenNichten;
	
	public Stamboom(){
		populate();
	}
	
	public static void main(String[] args){
		Stamboom b = new Stamboom();
		b.vindKinderlozen();
		b.vindOverledenen();
		b.vindMoeder();
		b.vindNevenEnNichten();
	}
	
	public void vindKinderlozen(){
		List<NKnoop<Persoon>> kindLozen = eersteGeneratie.getKindlozen();
		System.err.println("\nKinderlozen:");
		for(NKnoop<Persoon> knoop : kindLozen){
			if(knoop.getPartner()==null){
				System.err.println(knoop.getPersoon() + " heeft geen kinderen");
			}else{
				System.err.println(knoop.getPersoon() + " en " + knoop.getPartner() + " hebben geen kinderen");
			}
		}
	}
	
	public void vindMoeder(){
		// knoop is hatshepsutThutmose en moet ahmose opleveren
		
		// zoek de ouder knoop
		NKnoop<Persoon> ouders = eersteGeneratie.isOuderVan(teVindenMoeder);
		
		Persoon moeder = null;
		// moeder kan persoon zelf zijn, of de partner
		if(ouders.getPersoon().getGeslacht() == Geslacht.VROUWELIJK){
			moeder = ouders.getPersoon();
		}else{
			moeder = ouders.getPartner();
		}
		System.err.println("\nMoeder:");
		System.err.println(moeder + " is de moeder van " + teVindenMoeder.getPersoon());
	}
	
	public void vindOverledenen(){
		// alles behalve hatshepsut
		List<NKnoop<Persoon>> overledenen = eersteGeneratie.getOverledenen();
		System.err.println("\nOverledenen:");
		for(NKnoop<Persoon> knoop : overledenen){
			System.err.println(knoop.getPersoon() + " leeft niet meer");				
		}
	}
	
	public void vindNevenEnNichten(){
		// knoop is sapair, moet ahmose en hatshepsut opleveren
		List<NKnoop<Persoon>> nevenEnNichten = eersteGeneratie.getNevenEnNichten(teVindenNevenNichten);
		
		System.err.println("\nNeven en Nichten van "+teVindenNevenNichten.getPersoon()+":");
		
		for(NKnoop<Persoon> neefOfNicht : nevenEnNichten){
			if(neefOfNicht.getPersoon().getGeslacht() == Geslacht.MANNELIJK){
				System.err.println("neef: " + neefOfNicht.getPersoon());
			}else{
				System.err.println("nicht: " + neefOfNicht.getPersoon());
			}
		}
		
	}
	
	private void populate() {

		// op basis van (data en geslacht her en der verzonnen om alle gevallen te dekken)
		// http://www.crystalinks.com/dynasty18.html
		
		///////////////////
		// eerste generatie
		Persoon ahmoseI = new Persoon("Ahmose I","1550 BC","1525 BC",Persoon.Geslacht.MANNELIJK);
		Persoon nefertari = new Persoon("Nefertari","1552 BC","1527 BC",Persoon.Geslacht.VROUWELIJK);
		
		NKnoop<Persoon> ahmoseNefertari = new NKnoop<Persoon>(ahmoseI);
			ahmoseNefertari.setPartner(nefertari);
		
		///////////////////
		// tweede generatie
		Persoon ankh = new Persoon("Ankh","1560 BC","1535 BC",Persoon.Geslacht.MANNELIJK);
		Persoon sapair = new Persoon("Sapair","1560 BC","1535 BC",Persoon.Geslacht.VROUWELIJK);
		Persoon amenhotep = new Persoon("Amenhotep","1525 BC","1504 BC",Persoon.Geslacht.MANNELIJK);
		Persoon meritamon = new Persoon("Meritamon","1560 BC","1535 BC",Persoon.Geslacht.VROUWELIJK);
		

		
		ahmoseNefertari.addKind(new NKnoop<Persoon>(ankh)); //kindloos
		teVindenNevenNichten = new NKnoop<Persoon>(sapair);
		ahmoseNefertari.addKind(teVindenNevenNichten); //kindloos
		
		NKnoop<Persoon> amenhotepMeritamon = new NKnoop<Persoon>(amenhotep); // partner is vrouwelijk
			amenhotepMeritamon.setPartner(meritamon); // eww
		
		ahmoseNefertari.addKind(amenhotepMeritamon); 

		//////////////////
		// derde generatie
		Persoon ahmose = new Persoon("Ahmose","1503 BC", "1439 BC", Persoon.Geslacht.VROUWELIJK);
		Persoon thutmoseI = new Persoon("Thutmose I","1503 BC", "1493 BC", Persoon.Geslacht.MANNELIJK);
		
		NKnoop<Persoon> ahmoseThutmose = new NKnoop<Persoon>(ahmose); // partner is mannelijk
			ahmoseThutmose.setPartner(thutmoseI);
		
		amenhotepMeritamon.addKind(ahmoseThutmose);
		
		///////////////////
		// vierde generatie
		Persoon hatshepsut = new Persoon("Hatshepsut", "1479 BC", "", Persoon.Geslacht.VROUWELIJK); // leeft nog (enigszins op leeftijd)
		Persoon thutmoseII = new Persoon("Thutmose II", "1479 BC", "1425 BC", Persoon.Geslacht.MANNELIJK);
		
		NKnoop<Persoon> hatshepsutThutmose = new NKnoop<Persoon>(hatshepsut);
			hatshepsutThutmose.setPartner(thutmoseII);
		
		ahmoseThutmose.addKind(hatshepsutThutmose); // hatshepsut dus kinderloos
		
		eersteGeneratie = ahmoseNefertari;
		teVindenMoeder = hatshepsutThutmose; // moet ahmose opleveren
		
	}
}
