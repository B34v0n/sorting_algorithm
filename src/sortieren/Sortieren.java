package sortieren;

import java.util.Scanner;

import javax.swing.JOptionPane;

import bank.Bank;
import charakter.Charakter;
import items.Item;


public class Sortieren {

	//Hier werden die Bank, die Charaktere und die Itemarten erstellt.
	public static Oberfl�che Of = new Oberfl�che();
	public static int Lagergr��e = 10;
	public static int Inventargr��e = 4;
	public static Bank Lager = new Bank(Lagergr��e);
	public static Charakter Krieger = new Charakter(Inventargr��e, "Krieger");
	public static Charakter Magier = new Charakter(Inventargr��e, "Magier");
	public static Charakter Waldl�ufer = new Charakter(Inventargr��e, "Waldl�ufer");
	public static Item Schwert = new Item("Schwert", "Schwert");
	public static Item Schild = new Item("Schild","Schild");
	public static Item Stab = new Item("Stab","Stab");
	public static Item Ring = new Item("Ring","Ring");
	public static Item Bogen = new Item("Bogen","Bogen");
	public static Item Gift = new Item("Gift","Gift");
	
	
	
	
	public static void main (String[] args) {
				
		// Hier wird festgelegt, welcher Charakter welches Item nimmt.
		Krieger.ItemsFestlegen(Schwert);
		Krieger.ItemsFestlegen(Schild);
		Magier.ItemsFestlegen(Stab);
		Magier.ItemsFestlegen(Ring);
		Waldl�ufer.ItemsFestlegen(Bogen);
		Waldl�ufer.ItemsFestlegen(Gift);
		
		
		/*
		Scannen(Krieger);
		Scannen(Magier);
		Scannen(Waldl�ufer);
		*/
		
		//TestSzenarien();
		
		
		
		
		
	}


	public static void sortierenKern() {
		InTextfeldSchreiben(" \n Das Sortieren startet! \n");
		InTextfeldSchreiben("Aktuelle Belegung: \n");
		anzeigen();
		InTextfeldSchreiben("\n");
		sortieren(Krieger);
		//System.out.println("Krieger sortiert 1:");
		//anzeigen();
		sortieren(Magier);
		//System.out.println("Magier sortiert 1:");
		//anzeigen();
		sortieren(Waldl�ufer);
		//System.out.println("Waldl�ufer sortiert:");
		//anzeigen();
		sortieren(Magier);
		//System.out.println("Magier sortiert 2:");
		//anzeigen();
		sortieren(Krieger);
		InTextfeldSchreiben("\n");
		InTextfeldSchreiben("Endergebnis: \n");
	
		anzeigen();
	}
	
	
	public static void anzeigen () {
		Krieger.Pl�tzeAnzeigen();
		Magier.Pl�tzeAnzeigen();
		Waldl�ufer.Pl�tzeAnzeigen();
		Lager.bankAnzeigen();
	}
	
	public static void sortieren (Charakter Char) {
		//Das ist die einlagernde Seite:
		boolean aktion = false;
		int i = 0;
		while (i < Char.InventarGr��e) { 						//solange i kleiner als das Inventar ist:
			if (Char.Vergleichen(i) == 1) { 					//liegt an Inventarstelle i ein falsches Item? ja, dann:
				aktion = true;
				if (Lager.IstBankleer() == true) {				//wenn in der Bank ein freier Platz ist:
					i = legeItemAnN�chstenFreienBankplatz(Char, i);
					//Of.BankBild(null, i);
				}
				else if (Lager.IstBankleer() == false) {						//Wenn in der Bank KEIN freier Platz ist:
					nehmeN�chstesBankItem(Char);
	
				

				}
			}
			else //liegt an Inventarstelle i kein falsches Item:
				i++;	//gehe zur n�chsten Inventarstelle
		}
		
		//Das ist die entnehmende Seite:
		//nehmeN�chstesBankItem(Char);
		for (int v = 0; v < Lager.Gr��e; v++) {
			if (Lager.IstLeer(v) == false) {
				Item Vergleichsitem = Lager.GibItem(v);
				if (Char.Abgleich(Vergleichsitem) == true) {
					for (int w = 0; w < Char.InventarGr��e; w++) {
						InTextfeldSchreiben(Char.Name + " nimmt das Item " + Vergleichsitem.GibName() + "\n");
						aktion = true;
						Char.Einlagern(Vergleichsitem);
						Of.Itembild(Char.Name, Vergleichsitem.GibArt(), Char.eingelagerterPlatz());
						Lager.Leeren(v);
						Of.BankBildl�schen(v+1);
						w = w + Char.InventarGr��e;
					}
				}
			}
		}
		
		if (aktion == false) {
			InTextfeldSchreiben(Char.Name + " unternimmt nichts. \n");
		}
	}


	private static void nehmeN�chstesBankItem(Charakter Char) {
		for (int y = 0; y < Lager.Gr��e; y++) {						//das Lager durchsuchen
			if (Lager.IstLeer(y) == false) { 						//wenn die Stelle y frei ist,
				Item Vergleichsitem = Lager.GibItem(y);				//Das Item an Stelle y nehmen und
				if (Char.Abgleich(Vergleichsitem) == true) {		//abgleichen. wenn es ein richtiges Item ist,
					InTextfeldSchreiben(Char.Name + " nimmt das Item " + Vergleichsitem.GibName() + "\n");
					Char.Einlagern(Vergleichsitem);					//Das Item wird am n�chsten freien Inventarplatz eingelagert
					Of.Itembild(Char.Name, Vergleichsitem.GibArt(), Char.eingelagerterPlatz());
					Lager.Leeren(y);								//und der Bankplatz geleert.
					Of.BankBildl�schen(y+1);
					y = y + Lager.Gr��e;							//um aus der Schleife zu kommen.
				
					//Hier wird i NICHT erh�ht, sodass f�r die Inventarstelle i (die jetzt frei ist) wieder abgeglichen werden kann.

				}
				
			} 


		}
	}


	private static int legeItemAnN�chstenFreienBankplatz(Charakter Char, int i) {
		int z = 0;										//z anlegen um das Lager zu durchsuchen.
		while (z < Lager.Gr��e) { 					//Wird das Lager mit z durchsucht:
			if (Lager.IstLeer(z) == true) {			//Wenn der Lagerplatz an z Leer ist, dann:
				InTextfeldSchreiben(Char.Name + " legt " + Char.GibItem(i).GibName() + " an die " + (z+1) + ".te Stelle in der Bank. \n");
				Of.BankBild(Char.GibItem(i).GibArt(), z+1);
				Lager.belegen(Char.GibItem(i), z);	//wird das Item an Platz i an Platz z gelegt. 
				Char.Leeren(i);						//und die Stelle i im Inventar geleert. 
				Of.Itembildl�schen(Char.Name, i+1);
				z = z + Lager.Gr��e;				//um aus der Lagerdurchsuchung rauszukommen, da der Abgleich Zu Ende ist.
				i++;								//i wird um eins erh�ht, um auf den n�chsten Inventarplatz zuzugreifen.
			}
			else {									//Stelle z ist nicht frei. daher:
				z++;								//an der n�chsten Stelle suchen.
			}
		}
		return i;
	}
	
	public static void Scannen(Charakter Char) {
		Scanner scanner = new Scanner(System.in);

		
		
		System.out.println("Wieviele Items, bis zu " + Char.InventarGr��e + ", soll der " + Char.Name + " nehmen?");
		int g = 0;
		int j = 0;
		int K = 0;
		while (j<1) {
			try {
				while (g < 1) {
					K = scanner.nextInt();
					if (K > Char.InventarGr��e) {
						System.out.println("Das sind zu viele Items!");
					}
					else 
						g++;
				}
				j++;
			}
			catch (Exception e) {
				System.out.println("Bitte eine Zahl eingeben!");
				scanner.nextLine();
			}
		}
		
		System.out.println("Welche Items soll der " + Char.Name + " halten?");
		int d = 1;
		while (d <= K) {
			String K1 = scanner.next();
			if (K1.equals("Schwert")) {
				if (Krieger.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Schwert);
					d++;
					Krieger.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Schild")) {
				if (Krieger.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Schild);
					d++;
					Krieger.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Bogen")) {
				if (Waldl�ufer.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Bogen);
					d++;
					Waldl�ufer.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Gift")) {
				if (Waldl�ufer.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Gift);
					d++;
					Waldl�ufer.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Stab")) {
				if (Magier.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Stab);
					d++;
					Magier.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Ring")) {
				if (Magier.MengeRichtigerItems >= Krieger.InventarGr��e) {
					System.out.println("Es gibt bereits zu viele Items dieser Art.");
				}
				else {
					Char.Einlagern(Ring);
					d++;
					Magier.MengeRichtigerItems += 1;
				}
			}
			else {
				System.out.println("Dieses Item wird nicht unterst�tzt. Bitte eines aus der oberen Liste w�hlen");
			}
		}
	}
	
	
	public static void TestSzenarien() {

		System.out.println("");
		System.out.println("Anlegen der Items:");
		
		Item Schwert1 = new Item ("Schwert", "Schwert1"); 
		Item Schwert2 = new Item ("Schwert", "Schwert2");
		Item Schild1 = new Item ("Schild", "Schild1");
		Item Schild2 = new Item ("Schild", "Schild2");
		Item Bogen1 = new Item("Bogen", "Bogen1");
		Item Bogen2 = new Item("Bogen", "Bogen2");
		Item Gift1 = new Item("Gift", "Gift1");
		Item Gift2 = new Item("Gift", "Gift2");
		Item Stab1 = new Item("Stab", "Stab1");
		Item Stab2 = new Item("Stab", "Stab2");
		Item Ring1 = new Item("Ring", "Ring1");
		Item Ring2 = new Item("Ring", "Ring2");
		
		
		//Test-Szenario 1 - Jeder hat 4 Items, teilweise richtig, teilweise falsch
		
		Krieger.Einlagern(Schwert1); 
		Krieger.Einlagern(Schwert2);
		Krieger.Einlagern(Stab1);
		Krieger.Einlagern(Bogen1);
		Magier.Einlagern(Schild1);
		Magier.Einlagern(Bogen2);
		Magier.Einlagern(Gift1);
		Magier.Einlagern(Ring1);
		Waldl�ufer.Einlagern(Stab2);
		Waldl�ufer.Einlagern(Gift2);
		Waldl�ufer.Einlagern(Ring2);
		Waldl�ufer.Einlagern(Schild2);
		
		
		//Test-Szenario 2 - Jeder hat Items, die er haben will.
		/*
		Krieger.Einlagern(Schwert1);
		Krieger.Einlagern(Schild1);
		Magier.Einlagern(Stab1);
		Magier.Einlagern(Ring1);
		Waldl�ufer.Einlagern(Bogen1);
		Waldl�ufer.Einlagern(Gift1);
		*/
		 
		//Test-Szenario 3 - Alle haben etwas falsches und die Bank wird voll.
		/*
		Krieger.Einlagern(Gift1); 
		Krieger.Einlagern(Gift2);
		Krieger.Einlagern(Bogen1);
		Krieger.Einlagern(Bogen2);
		Magier.Einlagern(Schwert1);
		Magier.Einlagern(Schwert2);
		Magier.Einlagern(Schild1);
		Magier.Einlagern(Schild2);
		Waldl�ufer.Einlagern(Stab1);
		Waldl�ufer.Einlagern(Stab2);
		Waldl�ufer.Einlagern(Ring1);
		Waldl�ufer.Einlagern(Ring2);	
		*/
		
	}
	
	public static Charakter gibCharakter(String s) {
		if (s == "Waldl�ufer")
			return Waldl�ufer;
		else if (s == "Krieger")
			return Krieger;
		else if (s == "Magier")
			return Magier;
		else
			return null;
	}
	
	public static void charakterDasItemGeben(Charakter Char, String K1) {
		if (K1.equals("Schwert")) {
			if (Krieger.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
				//System.out.println("Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if (Char.Einlagern(Schwert) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Krieger.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
			}
		}
		else if (K1.equals("Schild")) {
			if (Krieger.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if (Char.Einlagern(Schild) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Krieger.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
				
			}
		}
		else if (K1.equals("Bogen")) {
			if (Waldl�ufer.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if(Char.Einlagern(Bogen) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Waldl�ufer.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
			}
		}
		else if (K1.equals("Gift")) {
			if (Waldl�ufer.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if(Char.Einlagern(Gift) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Waldl�ufer.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
			}
		}
		else if (K1.equals("Stab")) {
			if (Magier.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if(Char.Einlagern(Stab) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Magier.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
			}
		}
		else if (K1.equals("Ring")) {
			if (Magier.MengeRichtigerItems >= Krieger.InventarGr��e) {
				JOptionPane.showMessageDialog(Of.f, "Es gibt bereits zu viele Items dieser Art.");
			}
			else {
				if(Char.Einlagern(Ring) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Magier.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " nimmt " + K1 + " auf. \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "Das Inventar von " + Char.Name + " ist voll.");
				
			}
		}
		else {
			JOptionPane.showMessageDialog(Of.f, "Dieses Item wird nicht unterst�tzt. Bitte eines aus der oberen Liste w�hlen");
		}
	}
	
	public static void InTextfeldSchreiben(String s) {
		Of.Schreiben(s);
	}
	
	public static void Charaktersortieren(String s) {
		if (s == "Waldl�ufer")
			sortieren(Waldl�ufer);
		else if (s == "Krieger")
			sortieren(Krieger);
		if (s == "Magier")
			sortieren(Magier);
	}
	
	public static void reset() {
		for (int x = 0; x < Inventargr��e; x++) {
			Magier.Leeren(x);
			Of.Itembildl�schen(Magier.Name, x+1);
			Krieger.Leeren(x);
			Of.Itembildl�schen(Krieger.Name, x+1);
			Waldl�ufer.Leeren(x);
			Of.Itembildl�schen(Waldl�ufer.Name, x+1);
		}
		for (int y = 0; y < Lagergr��e; y++) {
			Of.BankBildl�schen(y+1);
		}
		Magier.MengeRichtigerItems = 0;
		Krieger.MengeRichtigerItems = 0;
		Waldl�ufer.MengeRichtigerItems = 0;
		Of.TA.setText(" ");
		anzeigen();
	}
	
}
