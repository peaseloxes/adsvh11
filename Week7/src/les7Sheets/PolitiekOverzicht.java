package les7Sheets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class PolitiekOverzicht {
	private Map<Fractievoorzitter, Partij> overzicht;
	private Map<Fractievoorzitter, String> dinners;
	
	private SortedSet<Fractievoorzitter> sortNatural;
	private SortedSet<Fractievoorzitter> sortHair;

	public PolitiekOverzicht() {
		overzicht = new HashMap<>();

		dinners = new LinkedHashMap<>();
		
		sortNatural = new TreeSet<>();
		sortHair = new TreeSet<>();
		
		populate();
	}

	public void printOverzicht() {
		for (Map.Entry<Fractievoorzitter, Partij> entry : overzicht.entrySet()) {
			System.out.println(entry.getKey().getNaam() + " -> "
					+ entry.getValue().getNaam());
		}
		
		System.out.println("\ndinner:");
		for (Map.Entry<Fractievoorzitter, String> entry : dinners.entrySet()) {
			System.out.println(entry.getKey().getNaam() + " -> "
					+ entry.getValue());
		}
		
		System.out.println("\nnatural {ABN, HAAGS, VLOEKEN} :");
		Iterator<Fractievoorzitter> iterator = sortNatural.iterator();
		while(iterator.hasNext()) {
			Fractievoorzitter fractievoorzitter = (Fractievoorzitter) iterator
					.next();
			System.out.println(fractievoorzitter.getNaam() + " -> " + fractievoorzitter.getTaalgebruik());
		}
		
		System.out.println("\nhair {Grijs, Zwart, donkerblond, wit, geen} :");
		iterator = sortHair.iterator();
		while(iterator.hasNext()) {
			Fractievoorzitter fractievoorzitter = (Fractievoorzitter) iterator.next();
			System.out.println(fractievoorzitter.getNaam() + " -> " + fractievoorzitter.getHaarkleur());
		}
		
	}

	private void populate() {
		// Bontes, L. (GrBvK)
		Fractievoorzitter bontes = new Fractievoorzitter("Bontes, L", "Zwart",
				"Vloeken");
		Partij grbvk = new Partij("GRBVK");
		overzicht.put(bontes, grbvk);

		// Haersma Buma, S. van (CDA)
		Fractievoorzitter buma = new Fractievoorzitter("Haersma Buma, S.",
				"Donkerblond", "ABN");
		Partij cda = new Partij("CDA");
		overzicht.put(buma, cda);

		// Klein, N.P.M. (50PLUS/Klein)
		Fractievoorzitter klein = new Fractievoorzitter("Klein, N.P.M.",
				"Zwart", "ABN");
		Partij plus = new Partij("50 plus klein");
		overzicht.put(klein, plus);

		// Krol, H.C.M. (50PLUS/Baay-Timmerman)
		Fractievoorzitter krol = new Fractievoorzitter("Krol, H.C.M.", "Grijs",
				"ABN");
		Partij plusT = new Partij("50 plus timmerman");
		overzicht.put(krol, plusT);

		// Kuzu, T. (GrKÖ)
		Fractievoorzitter kuzu = new Fractievoorzitter("Kuzu, T.", "Zwart",
				"Haags");
		Partij grko = new Partij("GrKÖ");
		overzicht.put(kuzu, grko);

		// Ojik, A. van (GL)
		Fractievoorzitter ojik = new Fractievoorzitter("Ojik, A. van", "Zwart",
				"Haags");
		Partij gl = new Partij("gl");
		overzicht.put(ojik, gl);

		// Pechtold, A. (D66)
		Fractievoorzitter pechthold = new Fractievoorzitter("Pechtold, A.",
				"Donkerblond", "Haags");
		Partij d66 = new Partij("d66");
		overzicht.put(pechthold, d66);

		// Roemer, E.G.M. (SP)
		Fractievoorzitter roemer = new Fractievoorzitter("Roemer, E.G.M.",
				"Zwart", "Brabants");
		Partij sp = new Partij("SP");
		overzicht.put(roemer, sp);

		// Samsom, D.M. (PvdA)
		Fractievoorzitter samsom = new Fractievoorzitter("Samsom, D.M.",
				"Geen", "ABN");
		Partij pvda = new Partij("PvdA");
		overzicht.put(samsom, pvda);

		// Slob, A. (CU)
		Fractievoorzitter slob = new Fractievoorzitter("Slob, A.", "Zwart",
				"ABN");
		Partij cu = new Partij("CU");
		overzicht.put(slob, cu);

		// Staaij, C.G. van der (SGP)
		Fractievoorzitter staaij = new Fractievoorzitter(
				"Staaij, C.G. van der", "Zwart", "ABN");
		Partij sgp = new Partij("SGP");
		overzicht.put(staaij, sgp);

		// Thieme, M.L. (PvdD)
		Fractievoorzitter thieme = new Fractievoorzitter("Thieme, M.L.",
				"Donkerblond", "ABN");
		Partij pvdd = new Partij("PvdD");
		overzicht.put(thieme, pvdd);

		// Vliet, R.A. van (Van Vliet)
		Fractievoorzitter vliet = new Fractievoorzitter("Vliet, R.A. van",
				"Donkerblond", "ABN");
		Partij vanvliet = new Partij("van vliet");
		overzicht.put(vliet, vanvliet);

		// Wilders, G. (PVV)
		Fractievoorzitter wilders = new Fractievoorzitter("Wilders, G.", "Wit",
				"ABN");
		Partij pvv = new Partij("PVV");
		overzicht.put(wilders, pvv);

		// Zijlstra, H. (VVD)
		Fractievoorzitter zijlstra = new Fractievoorzitter("Zijlstra, H.",
				"Wit", "ABN");
		Partij vvd = new Partij("VVD");
		overzicht.put(zijlstra, vvd);
		
		dinners.put(pechthold, "Zalm");
		dinners.put(roemer, "Kip");
		dinners.put(wilders, "Stampot");
		
		sortNatural.addAll(overzicht.keySet());
		
		sortHair = new TreeSet<>(Fractievoorzitter.getHairComparator());
		sortHair.addAll(overzicht.keySet());
		System.err.println(overzicht.keySet().size());
	}
}
