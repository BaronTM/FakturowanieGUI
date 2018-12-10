package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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
	private JButton wybierzKlienta;
	private JButton wybierzWystawce;
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
	private JLayeredPane layeredPane;
	private JPanel zaslona;
	private RamkaDodawaniaKlienta ramkaDodawaniaKlienta;
	private RamkaDodawaniaWystawcy ramkaDodawaniaWystawcy;
	
	public PanelNowejFaktury() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.YELLOW);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 740, 680);
		zaslona = new JPanel();
		zaslona.setLayout(null);
		zaslona.setBounds(0, 0, 740, 680);
		zaslona.setBackground(Color.BLACK);
		zaslona.setVisible(false);
		
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
		dodajProdukt.setBounds(510, 260, 180, 30);
		
		wybierzKlienta = new JButton("Wybierz Klienta");
		wybierzKlienta.setFont(dodajProdukt.getFont());
		wybierzKlienta.setBounds(40, 210, 200, 30);
		
		wybierzWystawce = new JButton("Wybierz Wystawcę");
		wybierzWystawce.setFont(dodajProdukt.getFont());
		wybierzWystawce.setBounds(500, 210, 200, 30);
		
		uwzgledniona = new JCheckBox("Faktura uwzględniona w limicie");
		uwzgledniona.setFont(dodajProdukt.getFont());
		uwzgledniona.setBounds(50, 260, 300, 30);
			
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
		panelPodListe.setBounds(30, 300, 680, 200);
		
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
		
		ramkaDodawaniaKlienta = new RamkaDodawaniaKlienta("Dodawanie Klienta");
		ramkaDodawaniaWystawcy = new RamkaDodawaniaWystawcy("Dodawanie Wystawcy");
		// ------- Listenery
		wybierzKlienta.addActionListener(l -> {
			ramkaDodawaniaKlienta.setVisible(true);
			zaslona.setVisible(true);
		});
		wybierzWystawce.addActionListener(l -> {
			ramkaDodawaniaWystawcy.setVisible(true);
			zaslona.setVisible(true);
		});
		
		this.add(layeredPane);
		layeredPane.add(tytul, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodEtykiety, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListe, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(dodajProdukt, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(wybierzKlienta, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(wybierzWystawce, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodsumowania, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(uwzgledniona, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(formaPlatnosciCB, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(formaPlatnosciLab, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(zaslona, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ramkaDodawaniaKlienta, JLayeredPane.MODAL_LAYER);
		layeredPane.add(ramkaDodawaniaWystawcy, JLayeredPane.MODAL_LAYER);
	}
	
	private void uzupelnijEtykieteWystawcy(Wystawca wystawca) {
		etykietaWystawcy.setText(wystawca.toString());
	}
	
	private void uzupelnijEtykieteKlienta(Klient klient) {
		etykietaKlienta.setText(klient.toString());
	}

	private class RamkaDodawaniaKlienta extends JInternalFrame {

		private JPanel panelDodawaniaKlienta;
		private JPanel panelPodListeKlientow;
		private DefaultTableModel modelListyKlientow;
		private JScrollPane listaScrollKlientow;
		private TabelaKlientow listaKlientow;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaKlienta(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(28, 152, 684, 375);

			panelDodawaniaKlienta = new JPanel();
			panelDodawaniaKlienta.setLayout(null);
			panelDodawaniaKlienta.setBounds(0, 0, 680, 375);
			
			modelListyKlientow = new DefaultTableModel(TabelaKlientow.getNazwyKolumn(), 0);
			listaKlientow = new TabelaKlientow(modelListyKlientow);
			listaScrollKlientow = new JScrollPane(listaKlientow);
			listaScrollKlientow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			listaScrollKlientow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			panelPodListeKlientow = new JPanel();
			panelPodListeKlientow.setLayout(new BorderLayout());
			panelPodListeKlientow.add(listaScrollKlientow, BorderLayout.CENTER);
			panelPodListeKlientow.setBounds(0, 0, 680, 280);

			dodaj = new JButton("DODAJ");
			dodaj.setBounds(560, 300, 100, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 300, 100, 30);
			
			panelDodawaniaKlienta.add(panelPodListeKlientow);
			panelDodawaniaKlienta.add(dodaj);
			panelDodawaniaKlienta.add(anuluj);
			
			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
			});

			this.add(panelDodawaniaKlienta);
			this.setVisible(false);
		}
	}
	
	private class RamkaDodawaniaWystawcy extends JInternalFrame {

		private JPanel panelDodawaniaWystawcy;
		private JPanel panelPodListeWystawcow;
		private DefaultTableModel modelListyWystawcow;
		private JScrollPane listaScrollWystawcow;
		private TabelaKlientow listaWystawcow;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaWystawcy(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(28, 152, 684, 375);

			panelDodawaniaWystawcy = new JPanel();
			panelDodawaniaWystawcy.setLayout(null);
			panelDodawaniaWystawcy.setBounds(0, 0, 680, 375);
			
			modelListyWystawcow = new DefaultTableModel(TabelaWystawcow.getNazwyKolumn(), 0);
			listaWystawcow = new TabelaKlientow(modelListyWystawcow);
			listaScrollWystawcow = new JScrollPane(listaWystawcow);
			listaScrollWystawcow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			listaScrollWystawcow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			panelPodListeWystawcow = new JPanel();
			panelPodListeWystawcow.setLayout(new BorderLayout());
			panelPodListeWystawcow.add(listaScrollWystawcow, BorderLayout.CENTER);
			panelPodListeWystawcow.setBounds(0, 0, 680, 280);

			dodaj = new JButton("DODAJ");
			dodaj.setBounds(560, 300, 100, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 300, 100, 30);
			
			panelDodawaniaWystawcy.add(panelPodListeWystawcow);
			panelDodawaniaWystawcy.add(dodaj);
			panelDodawaniaWystawcy.add(anuluj);
			
			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
			});

			this.add(panelDodawaniaWystawcy);
			this.setVisible(false);
		}
	}
}
