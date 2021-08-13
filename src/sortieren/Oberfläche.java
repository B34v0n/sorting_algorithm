package sortieren;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import charakter.Charakter;



public class Oberfläche extends Sortieren{
	
	//static Sortieren s = new Sortieren();
	
	JFrame f = new JFrame();
	JTextArea TA = new JTextArea();
	Canvas C = new Canvas();
	final ImageIcon BankBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Bank.png"));
	final ImageIcon BogenBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Bogen.png"));
	final ImageIcon GiftBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Gift.png"));
	final ImageIcon InventarBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Inventarplatz.png"));
	final ImageIcon KriegerBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Krieger.png"));
	final ImageIcon MagierBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Magier.png"));
	final ImageIcon RingBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Ring.png"));
	final ImageIcon SchildBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Schild.png"));
	final ImageIcon SchwertBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Schwert.png"));
	final ImageIcon StabBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Stab.png"));
	final ImageIcon WaldläuferBild = new ImageIcon(Oberfläche.class.getResource("..\\Bilder\\Waldläufer.png"));
	final JLabel W1 = new JLabel(InventarBild);
	final JLabel W2 = new JLabel(InventarBild);
	final JLabel W3 = new JLabel(InventarBild);
	final JLabel W4 = new JLabel(InventarBild);
	final JLabel K1 = new JLabel(InventarBild);
	final JLabel K2 = new JLabel(InventarBild);
	final JLabel K3 = new JLabel(InventarBild);
	final JLabel K4 = new JLabel(InventarBild);
	final JLabel M1 = new JLabel(InventarBild);
	final JLabel M2 = new JLabel(InventarBild);
	final JLabel M3 = new JLabel(InventarBild);
	final JLabel M4 = new JLabel(InventarBild);
	final JLabel B1 = new JLabel(InventarBild);
	final JLabel B2 = new JLabel(InventarBild);
	final JLabel B3 = new JLabel(InventarBild);
	final JLabel B4 = new JLabel(InventarBild);
	final JLabel B5 = new JLabel(InventarBild);
	final JLabel B6 = new JLabel(InventarBild);
	final JLabel B7 = new JLabel(InventarBild);
	final JLabel B8 = new JLabel(InventarBild);
	final JLabel B9 = new JLabel(InventarBild);
	final JLabel B10 = new JLabel(InventarBild);

	public Oberfläche() {
		
		
		JButton b1 = new JButton(BankBild);
		b1.setBounds(20, 750, 100, 100);
		
		JButton b2 = new JButton(BogenBild);
		b2.setBounds(300, 100, 40, 40);
		
		JButton b3 = new JButton(GiftBild);
		b3.setBounds(400, 100, 40, 40);
		
		JButton b4 = new JButton(InventarBild);
		b4.setBounds(140, 10, 40, 40);
		
		PlätzeAnlegen();
		
		
		JButton b5 = new JButton(KriegerBild);
		b5.setBounds(820, 200, 200, 400);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){    
				CS("Krieger");
				JOptionPane.showMessageDialog(f,"Krieger sortiert!"); 
				} 
		});
		
		JButton b6 = new JButton(MagierBild);
		b6.setBounds(1370, 200, 200, 400);
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){    
				CS("Magier");
				JOptionPane.showMessageDialog(f,"Magier sortiert!"); 
				} 
		});
		
		JButton b7 = new JButton(RingBild);
		b7.setBounds(1400, 100, 40, 40);
		
		JButton b8 = new JButton(SchildBild);
		b8.setBounds(850, 100, 40, 40);
		
		JButton b9 = new JButton(SchwertBild);
		b9.setBounds(950, 100, 40, 40);
		
		JButton b10 = new JButton(StabBild);
		b10.setBounds(1500, 100, 40, 40);
		
		JButton b11 = new JButton(WaldläuferBild);
		b11.setBounds(270, 200, 200, 400);
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){    
				CS("Waldläufer");
				JOptionPane.showMessageDialog(f,"Waldläufer sortiert!"); 
				} 
		});
		
		JButton sort = new JButton("Sortieren!");
		sort.setBounds(870, 20, 100, 30);
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){    
				SK();
				JOptionPane.showMessageDialog(f,"Sortiert!"); 
				} 
		});
		
		
		
		//Das Ausgabe-Textfeld
		TA.setBounds(1100, 700, 750, 300);
		TA.setEditable(false);
		f.add(TA);
		JScrollPane scroll = new JScrollPane(TA);
		scroll.setBounds(1100, 700, 750, 300);
		f.add(scroll);
		
		
		
		final JRadioButton rb1, rb2, rb3;
		rb1 = new JRadioButton("Waldläufer");
		rb1.setBounds(330, 160, 200, 30);
		
		rb2 = new JRadioButton("Krieger");
		rb2.setBounds(880, 160, 200, 30);

		rb3 = new JRadioButton("Magier");
		rb3.setBounds(1430, 160, 200, 30);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb3); bg.add(rb2); bg.add(rb1);
		
		f.add(b1); f.add(b2); f.add(b3); f.add(b4); f.add(b5); f.add(b6); f.add(b7); f.add(b8); f.add(b9); f.add(b10); f.add(b11);
		f.add(sort);
		f.add(rb1); f.add(rb2); f.add(rb3);
		
		
		b9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Schwert");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Schwert");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Schwert");
				}
			}
		});
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Bogen");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Bogen");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Bogen");
				}
			}
		});

		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Gift");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Gift");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Gift");
				}
			}
		});

		b7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Ring");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Ring");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Ring");
				}
			}
		});
		
		b8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Schild");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Schild");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Schild");
				}
			}
		});

		b10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()) {
					CDIG("Waldläufer", "Stab");
				}
				else if(rb2.isSelected()) {
					CDIG("Krieger", "Stab");
				}
				else if(rb3.isSelected()) {
					CDIG("Magier", "Stab");
				}
			}
		});
		
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetit();
			}
		});
		
		f.setSize(1900, 1080);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}

	private void PlätzeAnlegen() {
		//WaldläuferInventarplätze
		W1.setBounds(200, 200, 40, 40);
		W2.setBounds(200, 320, 40, 40);
		W3.setBounds(200, 440, 40, 40);
		W4.setBounds(200, 560, 40, 40);
		f.add(W1); f.add(W2); f.add(W3); f.add(W4);
		//KriegerInventarplätze
		K1.setBounds(750, 200, 40, 40);
		K2.setBounds(750, 320, 40, 40);
		K3.setBounds(750, 440, 40, 40);
		K4.setBounds(750, 560, 40, 40);
		f.add(K1); f.add(K2); f.add(K3); f.add(K4);
		//MagierInventarplätze
		M1.setBounds(1300, 200, 40, 40);
		M2.setBounds(1300, 320, 40, 40);
		M3.setBounds(1300, 440, 40, 40);
		M4.setBounds(1300, 560, 40, 40);
		f.add(M1); f.add(M2); f.add(M3); f.add(M4);
		//Bankplätze
		B1.setBounds(200, 730, 40, 40);
		B2.setBounds(300, 730, 40, 40);
		B3.setBounds(400, 730, 40, 40);
		B4.setBounds(500, 730, 40, 40);
		B5.setBounds(600, 730, 40, 40);
		B6.setBounds(200, 830, 40, 40);
		B7.setBounds(300, 830, 40, 40);
		B8.setBounds(400, 830, 40, 40);
		B9.setBounds(500, 830, 40, 40);
		B10.setBounds(600, 830, 40, 40);
		f.add(B1); f.add(B2); f.add(B3); f.add(B4); f.add(B5); f.add(B6); f.add(B7); f.add(B8); f.add(B9); f.add(B10);
		
	}
	
	public void SK() {
		super.sortierenKern();
	}
	
	public void CS(String s) {
		super.Charaktersortieren(s);
	}
	
	public Charakter GC(String s) {
		return super.gibCharakter(s);
	}
	
	public void CDIG(String Chars, String Items) {
		super.charakterDasItemGeben(super.gibCharakter(Chars), Items);
		//Itembild(Chars, Items);
	}
	
	public void Schreiben(String s) {
		TA.append(s);
	}
	
	public void Itembild(String C, String I, int x) {
		if (C == "Waldläufer") {
			if (x == 1) {
				W1.setIcon(Itemabgleich(I));
			}
			else if (x == 2) {
				W2.setIcon(Itemabgleich(I));
			}
			else if (x == 3) {
				W3.setIcon(Itemabgleich(I));
			}
			else if (x == 4) {
				W4.setIcon(Itemabgleich(I));
			}
		}
		else if (C == "Krieger") {
			if (x == 1) {
				K1.setIcon(Itemabgleich(I));
			}
			else if (x == 2) {
				K2.setIcon(Itemabgleich(I));
			}
			else if (x == 3) {
				K3.setIcon(Itemabgleich(I));
			}
			else if (x == 4) {
				K4.setIcon(Itemabgleich(I));
			}
		}
		else if (C == "Magier") {
			if (x == 1) {
				M1.setIcon(Itemabgleich(I));
			}
			else if (x == 2) {
				M2.setIcon(Itemabgleich(I));
			}
			else if (x == 3) {
				M3.setIcon(Itemabgleich(I));
			}
			else if (x == 4) {
				M4.setIcon(Itemabgleich(I));
			}
		}
	}
	
	public void BankBild(String s, int x) {
		if (x == 1)
			B1.setIcon(Itemabgleich(s));
		else if (x == 2)
			B2.setIcon(Itemabgleich(s));
		else if (x == 3)
			B3.setIcon(Itemabgleich(s));
		else if (x == 4)
			B4.setIcon(Itemabgleich(s));
		else if (x == 5)
			B5.setIcon(Itemabgleich(s));
		else if (x == 6)
			B6.setIcon(Itemabgleich(s));
		else if (x == 7)
			B7.setIcon(Itemabgleich(s));
		else if (x == 8)
			B8.setIcon(Itemabgleich(s));
		else if (x == 9)
			B9.setIcon(Itemabgleich(s));
		else if (x == 10)
			B10.setIcon(Itemabgleich(s));
	}
	
	public ImageIcon Itemabgleich(String s) {
		if (s == "Schwert")
			return SchwertBild;
		else if (s == "Schild")
			return SchildBild;
		else if (s == "Bogen")
			return BogenBild;
		else if (s == "Stab")
			return StabBild;
		else if (s == "Ring")
			return RingBild;
		else if (s == "Gift")
			return GiftBild;
		else
			return InventarBild;
	}
	
	
	public void Itembildlöschen(String C, int x) {
		if (C == "Waldläufer") {
			if (x == 1) {
				W1.setIcon(InventarBild);
			}
			else if (x == 2) {
				W2.setIcon(InventarBild);
			}
			else if (x == 3) {
				W3.setIcon(InventarBild);
			}
			else if (x == 4) {
				W4.setIcon(InventarBild);
			}
		}
		else if (C == "Krieger") {
			if (x == 1) {
				K1.setIcon(InventarBild);
			}
			else if (x == 2) {
				K2.setIcon(InventarBild);
			}
			else if (x == 3) {
				K3.setIcon(InventarBild);
			}
			else if (x == 4) {
				K4.setIcon(InventarBild);
			}
		}
		else if (C == "Magier") {
			if (x == 1) {
				M1.setIcon(InventarBild);
			}
			else if (x == 2) {
				M2.setIcon(InventarBild);
			}
			else if (x == 3) {
				M3.setIcon(InventarBild);
			}
			else if (x == 4) {
				M4.setIcon(InventarBild);
			}
		}
	}
	
	
	public void BankBildlöschen(int x) {
		if (x == 1)
			B1.setIcon(InventarBild);
		else if (x == 2)
			B2.setIcon(InventarBild);
		else if (x == 3)
			B3.setIcon(InventarBild);
		else if (x == 4)
			B4.setIcon(InventarBild);
		else if (x == 5)
			B5.setIcon(InventarBild);
		else if (x == 6)
			B6.setIcon(InventarBild);
		else if (x == 7)
			B7.setIcon(InventarBild);
		else if (x == 8)
			B8.setIcon(InventarBild);
		else if (x == 9)
			B9.setIcon(InventarBild);
		else if (x == 10)
			B10.setIcon(InventarBild);
	}
	
	
	public void resetit() {
		super.reset();
	}
	
	
	
	
	
	/*
	public static void main(String[] args) {
		new Oberfläche();
	}*/
	
	
}
