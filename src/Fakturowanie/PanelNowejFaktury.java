package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelNowejFaktury extends JPanel{
	
	private JLabel tytul;
	private JLabel klientLab;
	private JLabel wystawcaLab;
	private JLabel formaPlatnosciLab;
	private JTextArea etykietaWystawcy;
	private JTextArea etykietaKlienta;
	private JTextArea kwota;
	private JButton dodajProdukt;
	private JButton zapiszFakture;
	private JButton zamknijFakture;
	private JPanel panelPodEtykiety;
	private JPanel panelWystawcy;
	private JPanel panelKlienta;
	private JPanel panelKwot;
	private JPanel panelPodsumowania;
	private JPanel panelPrzyciskowDolnych;
	private JPanel panelPodListe;
	private JScrollPane listaScroll;
	private JCheckBox uwzgledniona;
	private JComboBox<String> formaPlatnosciCB;
	private Font czcionkaEtykiet;
	private TabelaZakupow lista;
	
	public PanelNowejFaktury() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.YELLOW);
		
		tytul = new JLabel("NOWA FAKTURA");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		czcionkaEtykiet = new Font("TimesRoman", Font.CENTER_BASELINE, 15);
		
		klientLab = new JLabel("KLIENT");
		klientLab.setFont(new Font("TimesRoman", Font.BOLD, 15));
		klientLab.setHorizontalAlignment(SwingConstants.CENTER);
		
		wystawcaLab = new JLabel("WYSTAWCA");
		wystawcaLab.setFont(new Font("TimesRoman", Font.BOLD, 15));
		wystawcaLab.setHorizontalAlignment(SwingConstants.CENTER);
		
		etykietaWystawcy = new JTextArea();
		etykietaWystawcy.setFont(czcionkaEtykiet);
		etykietaWystawcy.setEditable(false);
		
		etykietaKlienta = new JTextArea();
		etykietaKlienta.setFont(czcionkaEtykiet);
		etykietaKlienta.setEditable(false);
		
		panelWystawcy = new JPanel();
		panelWystawcy.setLayout(new BorderLayout());
		panelWystawcy.add(wystawcaLab, BorderLayout.CENTER);
		panelWystawcy.add(etykietaWystawcy, BorderLayout.SOUTH);
		
		panelKlienta = new JPanel();
		panelKlienta.setLayout(new BorderLayout());
		panelKlienta.add(klientLab, BorderLayout.CENTER);
		panelKlienta.add(etykietaKlienta, BorderLayout.SOUTH);
		
		panelPodEtykiety = new JPanel();
		panelPodEtykiety.setLayout(new BorderLayout());
		panelPodEtykiety.setBounds(40, 70, 660, 130);
		panelPodEtykiety.add(panelKlienta, BorderLayout.WEST);
		panelPodEtykiety.add(panelWystawcy, BorderLayout.EAST);
		
		dodajProdukt = new JButton("Dodaj produkt");
		dodajProdukt.setFont(new Font("TimesRoman", Font.BOLD, 15));
		dodajProdukt.setBounds(510, 210, 180, 30);
		
		uwzgledniona = new JCheckBox("Faktura uwzględniona w limicie");
		uwzgledniona.setFont(dodajProdukt.getFont());
		uwzgledniona.setBounds(50, 210, 300, 30);
			
		
		//----------------------- TESTOWE
		Wystawca wystawca = new Wystawca("Janusz", "Romanowski", "MojaFirma", 7174563234l, "ul. Srebrna 485, 36-741 Lublin");
		this.uzupelnijEtykieteWystawcy(wystawca);
		
		//----------------------- TESTOWE
		Klient klient = new Klient("Adam", "Kowalski", "JegoFirma", 5153367876l, "Złota 1c, 01-456 Warszawa");
		this.uzupelnijEtykieteKlienta(klient);
		
		//----------------------- TESTOWE
		Object[][] data = {
				{"1", "Kisiel paczka 100", 39.99, 1, "szt", 23, "30%", 
					39.99, 39.99*1.23, "Usun"}
				};
		lista = new TabelaZakupow(data, TabelaZakupow.getNazwyKolumn());
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		kwota = new JTextArea();
		kwota.setFont(new Font("TimesRoman", Font.BOLD, 15));
		kwota.setEditable(false);
		kwota.setText("Łączna kwonta netto: " + "\n" + "Łączna kwonta brutto: ");
		
		panelKwot = new JPanel();
		panelKwot.setLayout(new BorderLayout());
		panelKwot.add(kwota, BorderLayout.NORTH);
		
		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 250, 680, 250);
		
		formaPlatnosciLab = new JLabel("Forma Platnosci");
		formaPlatnosciLab.setFont(klientLab.getFont());
		formaPlatnosciLab.setBounds(420, 520, 150, 30);
		
		formaPlatnosciCB = new JComboBox<String>(Fakturka.getDostepneFormyPlatnosci());
		formaPlatnosciCB.setFont(formaPlatnosciLab.getFont());
		formaPlatnosciCB.setSelectedIndex(0);
		formaPlatnosciCB.setBounds(560, 520, 130, 30);
		
		zapiszFakture = new JButton("Zapisz Fakture");
		zapiszFakture.setFont(new Font("TimesRoman", Font.BOLD, 15));
		zapiszFakture.setSize(180, 30);
		zamknijFakture = new JButton("Zamknij Fakture");
		zamknijFakture.setFont(new Font("TimesRoman", Font.BOLD, 15));
		zamknijFakture.setSize(180, 30);
		
		panelPrzyciskowDolnych = new JPanel();
		panelPrzyciskowDolnych.setLayout(new BorderLayout());
		panelPrzyciskowDolnych.add(zapiszFakture, BorderLayout.NORTH);
		panelPrzyciskowDolnych.add(zamknijFakture, BorderLayout.SOUTH);
		
		panelPodsumowania = new JPanel();
		panelPodsumowania.setLayout(new BorderLayout());
		panelPodsumowania.add(panelKwot, BorderLayout.EAST);
		panelPodsumowania.add(panelPrzyciskowDolnych, BorderLayout.WEST);
		panelPodsumowania.setBounds(30, 570, 680, 70);
		
		this.add(tytul);
		this.add(panelPodEtykiety);
		this.add(panelPodListe);
		this.add(dodajProdukt);
		this.add(panelPodsumowania);
		this.add(uwzgledniona);
		this.add(formaPlatnosciCB);
		this.add(formaPlatnosciLab);
	}
	
	private void uzupelnijEtykieteWystawcy(Wystawca wystawca) {
		etykietaWystawcy.setText(wystawca.toString());
	}
	
	private void uzupelnijEtykieteKlienta(Klient klient) {
		etykietaKlienta.setText(klient.toString());
	}

}
