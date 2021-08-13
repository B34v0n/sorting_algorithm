package lagerplatz;

import items.*;
import sortieren.*;

public class Lagerplatz extends Sortieren {
	
	private Item LeeresItem = new Item("Leer", "");
	
	private Item HierGelagertesItem;
	
	private boolean leer = true;
	
	private boolean richtig = false;
	
	
	public Lagerplatz(Item X) {
		HierGelagertesItem = X;
	}
	
	
	public void belegen(Item I) {
		HierGelagertesItem = I;
		leer = false;
		/*System.out.println("Hier liegt " + HierGelagertesItem.GibName());
		System.out.println(leer);*/
	}
	
	
	public void Leeren() {
		HierGelagertesItem = LeeresItem;
		leer = true;
	}
	
	
	public void anzeigen(int Stelle) {
		Stelle += 1;
		if (leer == true)
			super.InTextfeldSchreiben("The place number " + Stelle + " is free. \n");
		else
			super.InTextfeldSchreiben("The place number " + Stelle + " is not free. Here is " + HierGelagertesItem.GibName() + ". \n");
	}
	
	public boolean IstLeer() {
		return leer;
	}
	
	public boolean IstRichtig() {
		return richtig;
	}
	
	public String GibItemArt() {
		return HierGelagertesItem.GibArt();
	}
	
	public Item GibItem() {
		return HierGelagertesItem;
	}
	
}
