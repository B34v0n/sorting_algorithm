package items;


public class Item  {
	
	
	private String Name = "";
	
	private String ArtDesItem = "";
	
	public Item(String Art, String benennen) {
		if (Art == "Sword") {
			final Schwert schwert = new Schwert(benennen);
			ArtDesItem = Art; 
		}
		else if (Art == "Shield") {
			final Schild schild = new Schild(benennen);
			ArtDesItem = Art;
		}
		else if (Art == "Staff") {
			final Stab stab = new Stab(benennen);
			ArtDesItem = Art;
		}
		else if (Art == "Ring") {
			final Ring ring = new Ring(benennen);
			ArtDesItem = Art;
		}
		else if (Art == "Bow") {
			final Bogen bogen = new Bogen(benennen);
			ArtDesItem = Art;
		}
		else if (Art == "Poison") {
			final Gift gift = new Gift(benennen);
			ArtDesItem = Art;
		}
		else if (Art == "Leer") {
			final LeeresItem leer = new LeeresItem();
			ArtDesItem = Art;
		}
		Name = benennen;
	}
	
	public String GibName() {
		return Name;
	}
	
	public String GibArt() {
		return ArtDesItem;
	}
	
}
