package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMenu extends JPanel {
	
	private JButton butNowaFaktura;
	private JButton butPrzegladajFaktury;
	private JButton butKlienci;
	private JButton butProdukty;
	private JButton	butWystawcy;
	private JButton	butLimit;
	private JButton butUstawienia;
	private Font font;
	private ArrayList<JPanel> paneleGlowne;
	
	public PanelMenu() {
		super();
		this.setLayout(null);
		this.setBounds(0, 0, 260, 680);
		this.setBackground(Color.BLUE);
		font = new Font("TimesRoman", Font.BOLD, 25);
		
		butNowaFaktura = new JButton("Nowa Faktura");
		butPrzegladajFaktury = new JButton();
		butPrzegladajFaktury.setLayout(new BorderLayout());
		JLabel bpfn = new JLabel("Przeglądaj");
		JLabel bpfs = new JLabel("Faktury");
		butPrzegladajFaktury.add(BorderLayout.NORTH, bpfn);
		butPrzegladajFaktury.add(BorderLayout.SOUTH, bpfs);
		butKlienci = new JButton("Klienci");
		butProdukty = new JButton("Produkty");
		butWystawcy = new JButton("Wystawcy");
		butLimit = new JButton("Limit");
		butUstawienia = new JButton("Ustawienia");
		
		butNowaFaktura.setBounds(10, 10, this.getWidth()-20, 50);
		butPrzegladajFaktury.setBounds(10, 70, this.getWidth()-20, 70);
		butKlienci.setBounds(10, 150, this.getWidth()-20, 50);
		butProdukty.setBounds(10, 210, this.getWidth()-20, 50);
		butWystawcy.setBounds(10, 270, this.getWidth()-20, 50);
		butLimit.setBounds(10, 330, this.getWidth()-20, 50);
		butUstawienia.setBounds(10, 390, this.getWidth()-20, 50);
		
		butNowaFaktura.setFont(font);
		bpfn.setFont(font);
		bpfs.setFont(font);
		bpfn.setHorizontalAlignment(SwingConstants.CENTER);
		bpfs.setHorizontalAlignment(SwingConstants.CENTER);
		butKlienci.setFont(font);
		butProdukty.setFont(font);
		butWystawcy.setFont(font);
		butLimit.setFont(font);
		butUstawienia.setFont(font);
		
		paneleGlowne = new ArrayList<>();
		paneleGlowne.add(Aplikacja.getPanelFunkcyjny());
		paneleGlowne.add(Aplikacja.getPanelNowejFaktury());
		paneleGlowne.add(Aplikacja.getPanelPrzegladFaktur());
		paneleGlowne.add(Aplikacja.getPanelKlientow());
		paneleGlowne.add(Aplikacja.getPanelProduktow());
		paneleGlowne.add(Aplikacja.getPanelWystawcow());
		paneleGlowne.add(Aplikacja.getPanelLimit());
		paneleGlowne.add(Aplikacja.getPanelUstawien());

		
		//------- Listenery
		butNowaFaktura.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelNowejFaktury().setVisible(true);
		});
		butPrzegladajFaktury.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelPrzegladFaktur().setVisible(true);
		});
		butKlienci.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelKlientow().setVisible(true);
		});
		butProdukty.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelProduktow().setVisible(true);
		});
		butWystawcy.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelWystawcow().setVisible(true);
		});
		butLimit.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelLimit().setVisible(true);
		});
		butUstawienia.addActionListener(l -> {
			wylaczPanele();
			Aplikacja.getPanelUstawien().setVisible(true);
		});
		
	

		
		this.add(butNowaFaktura);
		this.add(butPrzegladajFaktury);
		this.add(butKlienci);
		this.add(butProdukty);
		this.add(butWystawcy);
		this.add(butLimit);
		this.add(butUstawienia);
		this.setVisible(true);
	}
	
	private void wylaczPanele() {
		Aplikacja.getPanelFunkcyjny().setVisible(false);
		Aplikacja.getPanelNowejFaktury().setVisible(false);
		Aplikacja.getPanelPrzegladFaktur().setVisible(false);
		Aplikacja.getPanelKlientow().setVisible(false);
		Aplikacja.getPanelProduktow().setVisible(false);
		Aplikacja.getPanelWystawcow().setVisible(false);
		Aplikacja.getPanelLimit().setVisible(false);
		Aplikacja.getPanelUstawien().setVisible(false);
	}
}
