package sortieren;

import java.util.Scanner;

import javax.swing.JOptionPane;

import bank.Bank;
import charakter.Charakter;
import items.Item;


public class Sortieren {

	//Hier werden die Bank, die Charaktere und die Itemarten erstellt.
	public static Oberfläche Of = new Oberfläche();
	public static int Lagergröße = 10;
	public static int Inventargröße = 4;
	public static Bank Lager = new Bank(Lagergröße);
	public static Charakter Warrior = new Charakter(Inventargröße, "Warrior");
	public static Charakter Mage = new Charakter(Inventargröße, "Mage");
	public static Charakter Ranger = new Charakter(Inventargröße, "Ranger");
	public static Item Sword = new Item("Sword", "Sword");
	public static Item Shield = new Item("Shield","Shield");
	public static Item Staff = new Item("Staff","Staff");
	public static Item Ring = new Item("Ring","Ring");
	public static Item Bow = new Item("Bow","Bow");
	public static Item Poison = new Item("Poison","Poison");
	
	
	
	
	public static void main (String[] args) {
				
		// Hier wird festgelegt, welcher Charakter welches Item nimmt.
		Warrior.ItemsFestlegen(Sword);
		Warrior.ItemsFestlegen(Shield);
		Mage.ItemsFestlegen(Staff);
		Mage.ItemsFestlegen(Ring);
		Ranger.ItemsFestlegen(Bow);
		Ranger.ItemsFestlegen(Poison);
		
		
		/*
		Scannen(Warrior);
		Scannen(Mage);
		Scannen(Ranger);
		*/
		
		//TestSzenarien();
		
		
		
		
		
	}


	public static void sortierenKern() {
		InTextfeldSchreiben(" \n The Sorting starts: \n");
		InTextfeldSchreiben("Inventory right now: \n");
		anzeigen();
		InTextfeldSchreiben("\n");
		sortieren(Warrior);
		//System.out.println("Warrior sortiert 1:");
		//anzeigen();
		sortieren(Mage);
		//System.out.println("Mage sortiert 1:");
		//anzeigen();
		sortieren(Ranger);
		//System.out.println("Ranger sortiert:");
		//anzeigen();
		sortieren(Mage);
		//System.out.println("Mage sortiert 2:");
		//anzeigen();
		sortieren(Warrior);
		InTextfeldSchreiben("\n");
		InTextfeldSchreiben("Finally: \n");
	
		anzeigen();
	}
	
	
	public static void anzeigen () {
		Warrior.PlätzeAnzeigen();
		Mage.PlätzeAnzeigen();
		Ranger.PlätzeAnzeigen();
		Lager.bankAnzeigen();
	}
	
	public static void sortieren (Charakter Char) {
		//Das ist die einlagernde Seite:
		boolean aktion = false;
		int i = 0;
		while (i < Char.InventarGröße) { 						//solange i kleiner als das Inventar ist:
			if (Char.Vergleichen(i) == 1) { 					//liegt an Inventarstelle i ein falsches Item? ja, dann:
				aktion = true;
				if (Lager.IstBankleer() == true) {				//wenn in der Bank ein freier Platz ist:
					i = legeItemAnNächstenFreienBankplatz(Char, i);
					//Of.BankBild(null, i);
				}
				else if (Lager.IstBankleer() == false) {						//Wenn in der Bank KEIN freier Platz ist:
					nehmeNächstesBankItem(Char);
	
				

				}
			}
			else //liegt an Inventarstelle i kein falsches Item:
				i++;	//gehe zur nächsten Inventarstelle
		}
		
		//Das ist die entnehmende Seite:
		//nehmeNächstesBankItem(Char);
		for (int v = 0; v < Lager.Größe; v++) {
			if (Lager.IstLeer(v) == false) {
				Item Vergleichsitem = Lager.GibItem(v);
				if (Char.Abgleich(Vergleichsitem) == true) {
					for (int w = 0; w < Char.InventarGröße; w++) {
						InTextfeldSchreiben(Char.Name + " takes the Item " + Vergleichsitem.GibName() + "\n");
						aktion = true;
						Char.Einlagern(Vergleichsitem);
						Of.Itembild(Char.Name, Vergleichsitem.GibArt(), Char.eingelagerterPlatz());
						Lager.Leeren(v);
						Of.BankBildlöschen(v+1);
						w = w + Char.InventarGröße;
					}
				}
			}
		}
		
		if (aktion == false) {
			InTextfeldSchreiben(Char.Name + " doesn't do anything. \n");
		}
	}


	private static void nehmeNächstesBankItem(Charakter Char) {
		for (int y = 0; y < Lager.Größe; y++) {						//das Lager durchsuchen
			if (Lager.IstLeer(y) == false) { 						//wenn die Stelle y frei ist,
				Item Vergleichsitem = Lager.GibItem(y);				//Das Item an Stelle y nehmen und
				if (Char.Abgleich(Vergleichsitem) == true) {		//abgleichen. wenn es ein richtiges Item ist,
					InTextfeldSchreiben(Char.Name + " takes the Item " + Vergleichsitem.GibName() + "\n");
					Char.Einlagern(Vergleichsitem);					//Das Item wird am nächsten freien Inventarplatz eingelagert
					Of.Itembild(Char.Name, Vergleichsitem.GibArt(), Char.eingelagerterPlatz());
					Lager.Leeren(y);								//und der Bankplatz geleert.
					Of.BankBildlöschen(y+1);
					y = y + Lager.Größe;							//um aus der Schleife zu kommen.
				
					//Hier wird i NICHT erhöht, sodass für die Inventarstelle i (die jetzt frei ist) wieder abgeglichen werden kann.

				}
				
			} 


		}
	}


	private static int legeItemAnNächstenFreienBankplatz(Charakter Char, int i) {
		int z = 0;										//z anlegen um das Lager zu durchsuchen.
		while (z < Lager.Größe) { 					//Wird das Lager mit z durchsucht:
			if (Lager.IstLeer(z) == true) {			//Wenn der Lagerplatz an z Leer ist, dann:
				InTextfeldSchreiben(Char.Name + " puts " + Char.GibItem(i).GibName() + " at the " + (z+1) + " Place in the Bank. \n");
				Of.BankBild(Char.GibItem(i).GibArt(), z+1);
				Lager.belegen(Char.GibItem(i), z);	//wird das Item an Platz i an Platz z gelegt. 
				Char.Leeren(i);						//und die Stelle i im Inventar geleert. 
				Of.Itembildlöschen(Char.Name, i+1);
				z = z + Lager.Größe;				//um aus der Lagerdurchsuchung rauszukommen, da der Abgleich Zu Ende ist.
				i++;								//i wird um eins erhöht, um auf den nächsten Inventarplatz zuzugreifen.
			}
			else {									//Stelle z ist nicht frei. daher:
				z++;								//an der nächsten Stelle suchen.
			}
		}
		return i;
	}
	
	public static void Scannen(Charakter Char) {
		Scanner scanner = new Scanner(System.in);

		
		
		System.out.println("Wieviele Items, bis zu " + Char.InventarGröße + ", soll der " + Char.Name + " nehmen?");
		int g = 0;
		int j = 0;
		int K = 0;
		while (j<1) {
			try {
				while (g < 1) {
					K = scanner.nextInt();
					if (K > Char.InventarGröße) {
						System.out.println("That's too many Items!");
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
			if (K1.equals("Sword")) {
				if (Warrior.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Sword);
					d++;
					Warrior.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Shield")) {
				if (Warrior.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Shield);
					d++;
					Warrior.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Bow")) {
				if (Ranger.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Bow);
					d++;
					Ranger.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Poison")) {
				if (Ranger.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Poison);
					d++;
					Ranger.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Staff")) {
				if (Mage.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Staff);
					d++;
					Mage.MengeRichtigerItems += 1;
				}
			}
			else if (K1.equals("Ring")) {
				if (Mage.MengeRichtigerItems >= Warrior.InventarGröße) {
					System.out.println("There are already too many of these Items.");
				}
				else {
					Char.Einlagern(Ring);
					d++;
					Mage.MengeRichtigerItems += 1;
				}
			}
			else {
				System.out.println("Dieses Item wird nicht unterstützt. Bitte eines aus der oberen Liste wählen");
			}
		}
	}
	
	
	public static void TestSzenarien() {

		System.out.println("");
		System.out.println("Anlegen der Items:");
		
		Item Sword1 = new Item ("Sword", "Sword1"); 
		Item Sword2 = new Item ("Sword", "Sword2");
		Item Shield1 = new Item ("Shield", "Shield1");
		Item Shield2 = new Item ("Shield", "Shield2");
		Item Bow1 = new Item("Bow", "Bow1");
		Item Bow2 = new Item("Bow", "Bow2");
		Item Poison1 = new Item("Poison", "Poison1");
		Item Poison2 = new Item("Poison", "Poison2");
		Item Staff1 = new Item("Staff", "Staff1");
		Item Staff2 = new Item("Staff", "Staff2");
		Item Ring1 = new Item("Ring", "Ring1");
		Item Ring2 = new Item("Ring", "Ring2");
		
		
		//Test-Szenario 1 - Jeder hat 4 Items, teilweise richtig, teilweise falsch
		
		Warrior.Einlagern(Sword1); 
		Warrior.Einlagern(Sword2);
		Warrior.Einlagern(Staff1);
		Warrior.Einlagern(Bow1);
		Mage.Einlagern(Shield1);
		Mage.Einlagern(Bow2);
		Mage.Einlagern(Poison1);
		Mage.Einlagern(Ring1);
		Ranger.Einlagern(Staff2);
		Ranger.Einlagern(Poison2);
		Ranger.Einlagern(Ring2);
		Ranger.Einlagern(Shield2);
		
		
		//Test-Szenario 2 - Jeder hat Items, die er haben will.
		/*
		Warrior.Einlagern(Sword1);
		Warrior.Einlagern(Shield1);
		Mage.Einlagern(Staff1);
		Mage.Einlagern(Ring1);
		Ranger.Einlagern(Bow1);
		Ranger.Einlagern(Poison1);
		*/
		 
		//Test-Szenario 3 - Alle haben etwas falsches und die Bank wird voll.
		/*
		Warrior.Einlagern(Poison1); 
		Warrior.Einlagern(Poison2);
		Warrior.Einlagern(Bow1);
		Warrior.Einlagern(Bow2);
		Mage.Einlagern(Sword1);
		Mage.Einlagern(Sword2);
		Mage.Einlagern(Shield1);
		Mage.Einlagern(Shield2);
		Ranger.Einlagern(Staff1);
		Ranger.Einlagern(Staff2);
		Ranger.Einlagern(Ring1);
		Ranger.Einlagern(Ring2);	
		*/
		
	}
	
	public static Charakter gibCharakter(String s) {
		if (s == "Ranger")
			return Ranger;
		else if (s == "Warrior")
			return Warrior;
		else if (s == "Mage")
			return Mage;
		else
			return null;
	}
	
	public static void charakterDasItemGeben(Charakter Char, String K1) {
		if (K1.equals("Sword")) {
			if (Warrior.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
				//System.out.println("There are already too many of these Items.");
			}
			else {
				if (Char.Einlagern(Sword) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Warrior.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
			}
		}
		else if (K1.equals("Shield")) {
			if (Warrior.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
			}
			else {
				if (Char.Einlagern(Shield) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Warrior.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
				
			}
		}
		else if (K1.equals("Bow")) {
			if (Ranger.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
			}
			else {
				if(Char.Einlagern(Bow) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Ranger.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
			}
		}
		else if (K1.equals("Poison")) {
			if (Ranger.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
			}
			else {
				if(Char.Einlagern(Poison) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Ranger.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
			}
		}
		else if (K1.equals("Staff")) {
			if (Mage.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
			}
			else {
				if(Char.Einlagern(Staff) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Mage.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
			}
		}
		else if (K1.equals("Ring")) {
			if (Mage.MengeRichtigerItems >= Warrior.InventarGröße) {
				JOptionPane.showMessageDialog(Of.f, "There are already too many of these Items.");
			}
			else {
				if(Char.Einlagern(Ring) == true) {
					Of.Itembild(Char.Name, K1, Char.eingelagerterPlatz());
					Mage.MengeRichtigerItems += 1;
					Of.Schreiben(Char.Name + " takes " + K1 + ". \n");
				}
				else
					JOptionPane.showMessageDialog(Of.f, "The Inventory of " + Char.Name + " is full.");
				
			}
		}
		else {
			JOptionPane.showMessageDialog(Of.f, "Please choose a supported Item.");
		}
	}
	
	public static void InTextfeldSchreiben(String s) {
		Of.Schreiben(s);
	}
	
	public static void Charaktersortieren(String s) {
		if (s == "Ranger")
			sortieren(Ranger);
		else if (s == "Warrior")
			sortieren(Warrior);
		if (s == "Mage")
			sortieren(Mage);
	}
	
	public static void reset() {
		for (int x = 0; x < Inventargröße; x++) {
			Mage.Leeren(x);
			Of.Itembildlöschen(Mage.Name, x+1);
			Warrior.Leeren(x);
			Of.Itembildlöschen(Warrior.Name, x+1);
			Ranger.Leeren(x);
			Of.Itembildlöschen(Ranger.Name, x+1);
		}
		for (int y = 0; y < Lagergröße; y++) {
			Of.BankBildlöschen(y+1);
		}
		Mage.MengeRichtigerItems = 0;
		Warrior.MengeRichtigerItems = 0;
		Ranger.MengeRichtigerItems = 0;
		Of.TA.setText(" ");
		anzeigen();
	}
	
}
