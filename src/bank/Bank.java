package bank;

import lagerplatz.*;
import sortieren.Sortieren;
import java.util.HashSet;
import items.*;

public class Bank extends Sortieren{
	
	public int Größe = 0;
	Lagerplatz bank[]= new Lagerplatz[Größe] ;
	
	public Bank (int G){
	Größe = G;
	Lagerplatz temp[]= new Lagerplatz [Größe];
	bank = temp;
	
	super.InTextfeldSchreiben("Die Bank hat " + Größe + " Lagerplätze. \n");
	for (int x = 0; x < Größe; x++) {
		Item Leer = new Item("LeeresItem","");
		bank[x] = new Lagerplatz(Leer);
		//bank[x].anzeigen(x);
	}
	
	}
	
	public void bankAnzeigen() {												//Die Bankgröße und die Plätze anzeigen
		super.InTextfeldSchreiben("Die Bank hat " + Größe + " Lagerplätze. \n");
		for (int x = 0; x < Größe; x++) {
			bank[x].anzeigen(x);
		}
	}
	
	public void belegen(Item X, int a) {									//gegebenes Item X an Stelle a in die Bank legen
			bank[a].belegen(X);
	}
	
	public boolean IstLeer(int x) {											//gibt an, ob der Platz an stelle x leer ist.
		boolean leer = bank[x].IstLeer();
		return leer;
	}
	
	public Item GibItem(int x) {
		return bank[x].GibItem();
	}
	
	public void Leeren(int x) {
		bank[x].Leeren();
	}
	
	
	public boolean IstBankleer() {											//Kontrollieren, ob es einen freien Platz in der Bank gibt.
		final HashSet<String> Voll = new HashSet<String>();
		boolean Test;
		for (int x = 0; x < Größe; x++) {
			if (bank[x].IstLeer() == true) {
				Voll.add("true");
			}
			else {
				Voll.add("false");
			}
		}
		if (Voll.contains("true")){
			Test = true;
			return Test;
		}
		else {
			Test = false;
			return Test;
		}
	}
}
