package sortieren;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Test1 {
	
	private static Sortieren sort;
	
	@Before
	public void init() {
		sort = new Sortieren();
		sort.reset();
		sort.Krieger.ItemsFestlegen(sort.Schwert);
		sort.Krieger.ItemsFestlegen(sort.Schild);
		sort.Magier.ItemsFestlegen(sort.Stab);
		sort.Magier.ItemsFestlegen(sort.Ring);
		sort.Waldläufer.ItemsFestlegen(sort.Bogen);
		sort.Waldläufer.ItemsFestlegen(sort.Gift);
		
	}
	@Test
	public void testKrieger() {
		assertEquals(0, sort.Krieger.MengeRichtigerItems);
		sort.charakterDasItemGeben(sort.Krieger, "Bogen");
		assertEquals(0, sort.Krieger.MengeRichtigerItems);
		sort.charakterDasItemGeben(sort.Krieger, "Stab");
		assertEquals(0, sort.Krieger.MengeRichtigerItems);
		sort.charakterDasItemGeben(sort.Krieger, "Ring");
		assertEquals(0, sort.Krieger.MengeRichtigerItems);
		sort.charakterDasItemGeben(sort.Krieger, "Schwert");
		assertEquals(1, sort.Krieger.MengeRichtigerItems);
		sort.charakterDasItemGeben(sort.Krieger, "Schild");
		assertEquals(1, sort.Krieger.MengeRichtigerItems);
	}
	
	@Test
	public void testSortieren() {
		sort.charakterDasItemGeben(sort.Krieger, "Bogen");
		sort.charakterDasItemGeben(sort.Krieger, "Stab");
		sort.charakterDasItemGeben(sort.Krieger, "Ring");
		sort.charakterDasItemGeben(sort.Krieger, "Schwert");
		sort.Charaktersortieren("Krieger");
		assertEquals(1, sort.Krieger.MengeRichtigerItems);
		assertEquals(false, sort.Lager.IstLeer(0));
		assertEquals(false, sort.Lager.IstLeer(1));
		assertEquals(false, sort.Lager.IstLeer(2));
		assertEquals(true, sort.Lager.IstLeer(3));
		assertEquals(true, sort.Lager.IstLeer(4));
		
	}
	
	@Test
	public void testWaldläufer() {
		sort.charakterDasItemGeben(sort.Krieger, "Bogen");
		sort.charakterDasItemGeben(sort.Krieger, "Stab");
		sort.charakterDasItemGeben(sort.Krieger, "Ring");
		sort.charakterDasItemGeben(sort.Krieger, "Schwert");
		sort.charakterDasItemGeben(sort.Waldläufer, "Gift");
		sort.Charaktersortieren("Krieger");
		sort.Charaktersortieren("Waldläufer");
		assertEquals(2, sort.Waldläufer.MengeRichtigerItems);
		assertEquals(true, sort.Lager.IstLeer(0));
		assertEquals(false, sort.Lager.IstLeer(1));
		assertEquals(false, sort.Lager.IstLeer(2));
		assertEquals(true, sort.Lager.IstLeer(3));
		assertEquals(true, sort.Lager.IstLeer(4));
	}
	
	@Test
	public void testReset() {
		sort.charakterDasItemGeben(sort.Magier, "Stab");
		sort.reset();
		assertEquals(0, sort.Magier.MengeRichtigerItems);
		assertEquals(2, sort.Magier.AnzahlRichtigeItems);
	}
		

}
