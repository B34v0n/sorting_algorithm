package charakter;

import lagerplatz.*;
import sortieren.*;
import java.util.*;
import items.*;


public class Charakter extends Sortieren {
	
	public int InventarGr��e = 0;
	public String Name = "";
	public int AnzahlRichtigeItems = 0;
	public ArrayList<Item> richtigeItems = new ArrayList<Item>();
	public Item Leer = new Item("LeeresItem","");
	public Lagerplatz Inventar[] = new Lagerplatz[0];
	public int MengeRichtigerItems = 0;
	public int InventarF�lle = 0;
	public int WelcherPlatz = 0;
	
	
	
	public Charakter(int Gr��e, String benennen){
		InventarGr��e = Gr��e;
		Name = benennen;
		Lagerplatz temp[]= new Lagerplatz[InventarGr��e];
		Inventar = temp;
		super.InTextfeldSchreiben("Inventar von " + Name + " hat " + InventarGr��e + " Pl�tze: \n");
		
		//eigene For Schleife.
		int x = 0;
		while (x < InventarGr��e) {
			Inventar[x] = new Lagerplatz(Leer);
			//Inventar[x].anzeigen(x);
			x = x + 1;
		}
		
		}
	
	
	
	public void ItemsFestlegen(Item richtig) {								//welche Items sind die, die der Charakter haben m�chte
		richtigeItems.add(richtig);
		super.InTextfeldSchreiben(Name + " takes " + richtigeItems.get(AnzahlRichtigeItems).GibName() + "\n");
		AnzahlRichtigeItems += 1;
		/*AnzahlRichtigeItems = AnzahlRichtigeItems + 1; 						// erh�ht die Anzahl der richtigen Items
		Item temp[] = new Item[AnzahlRichtigeItems];						// ein tempor�res Array zum kopieren
		System.arraycopy(RichtigeItems, 0, temp, 0, AnzahlRichtigeItems-1);	// kopiert das Array in das tempor�re Array
		RichtigeItems = temp; 												// hat das Array um 1 erweitert
		RichtigeItems[AnzahlRichtigeItems-1] = richtig;						//legt das gegebene Item im n�chsten Arrayplatz ab
		
		System.out.println(Name + " nimmt " + RichtigeItems[AnzahlRichtigeItems-1].GibName()); 	//Eine Testzeile*/
	}
	
	public void Pl�tzeAnzeigen() {						//hier wird ausgegeben, welche Items die richtigen sind, wie gro� das Inventar ist und welche Items dort gelagert sind.
		super.InTextfeldSchreiben(Name + " takes: ");
		for (int x = 0; x < AnzahlRichtigeItems; x++) {
			super.InTextfeldSchreiben(" " + richtigeItems.get(x).GibName() );
		}
		super.InTextfeldSchreiben("\n");
		super.InTextfeldSchreiben("Inventory of " + Name + " has " + Inventar.length + " Spaces. \n");
		
		for (int y = 0; y < InventarGr��e; y++) {
			Inventar[y].anzeigen(y);
		}
		
		
	}
	

	public boolean Einlagern(Item AI) {				//Hier wird das gegeben Item AI an den n�chsten freien Inventarplatz gelegt.
		WelcherPlatz = 0;
		boolean hatgeklappt = false;
		for (int a = 0; a<InventarGr��e;a++) {
			if (Inventar[a].IstLeer() == true) {
				Inventar[a].belegen(AI);
				WelcherPlatz = a + 1;
				a = a + InventarGr��e;
				hatgeklappt = true;
			}
		}
		InventarF�lle += 1;
		return hatgeklappt;
	}
	
	public int eingelagerterPlatz() {
		return WelcherPlatz;
	}
		
		
	public void Leeren(int x) {					//Der Inventarplatz an Stelle x wird geleert, also entnommen.
		Inventar[x].Leeren();
		InventarF�lle -= 1;
	}
	
	
	public int Vergleichen(int Stelle) {			// Hier wird geschaut, ob das ausgew�hlte Item zum Charakter passt. 
		//int x = Stelle + 1;
		int WasLiegtHier = 0; //0=nichts, 1=was falsches, 2=was richtiges
		if (Inventar[Stelle].IstLeer() == false) {
	
			if (Abgleich(Inventar[Stelle].GibItem()) == true) {
				WasLiegtHier = 2;
				//System.out.println("Das Item an Stelle " + x + " richtig." + WasLiegtHier); //Testprint
				return WasLiegtHier;
			}
			else {
				WasLiegtHier = 1;
				//System.out.println("Das Item an Stelle " + x + " falsch." + WasLiegtHier);
				return WasLiegtHier;
			}
		}
		else
			//System.out.println("Der Inventarplatz an Stelle " + x + " ist leer." + WasLiegtHier);
			return WasLiegtHier;
	}
	
	
	
	public boolean Abgleich(Item TestItem) {					//Hier wird das gegebene TestItem mit den richtigen Items abgeglichen.
		final HashSet<String> Richtig = new HashSet<String>();	//mit einem Set wird f�r jedes richtige Item ein Vergleich mit dem TestItem durchgef�hrt.
		boolean Test;
		for (int x = 0; x < AnzahlRichtigeItems; x++) {
			if (Test(TestItem, x) == true) {
				Richtig.add("true");							//wenn das TestItem mit dem Item �bereinstimmt, wird ein true in das Set gegeben,
			}
			else {
				Richtig.add("false");							//sonst ein false.
			}
		}
		if (Richtig.contains("true")){							//wenn im Anschluss ein true vorhanden ist, also das TestItem mit einem richtigen Item �bereinstimmt,
			Test = true;										//wird ein true zur�ckgegeben
			return Test;
		}
		else {
			Test = false;										//sonst ein false.
			return Test;
		}
	}
	
	public boolean Test(Item TestItem, int Itemsplatz) {		//Hier wird das Item an Platz X verglichen, ob es richtig ist mit Platz Y. (Aus �bersichtlichkeit ausgelagert)
		if (TestItem.GibArt() == richtigeItems.get(Itemsplatz).GibArt()) {
			return true;
	}
		else 
			return false;
	}
	
	public Item GibItem(int s) {								//Hier wird das Item an Stelle s im Inventar ausgegeben.
		return Inventar[s].GibItem();
	}
	
	
}
