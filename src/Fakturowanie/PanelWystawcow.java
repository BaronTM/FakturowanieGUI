package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;


public class PanelWystawcow extends JPanel{
	
	private JLabel tytul;
	private JLabel fakturyLab;
	private TabelaWystawcow lista;
	private TabelaFaktur listaFaktur;
	private JScrollPane listaScroll;
	private JScrollPane listaFakturScroll;
	private JPanel panelPodListe;
	private JPanel panelPodListeFaktur;
	private JPanel zaslona;
	private JButton nowyWystawca;
	private JLayeredPane layeredPane;
	private RamkaDodawaniaWystawcy ramkaDodawania;

	
	public PanelWystawcow() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 740, 680);
		zaslona = new JPanel();
		zaslona.setLayout(null);
		zaslona.setBounds(0, 0, 740, 680);
		zaslona.setBackground(Color.BLACK);
		zaslona.setVisible(false);
		
		tytul = new JLabel("WYSTAWCY");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		// ---------------------------- TESTOWE
		Object[][] data = {
				{"1", "MALYNOWSKY Roman Malinowski", 145505.43, 214012.99, "PLN", 160, 
					100}
				};
		
		lista = new TabelaWystawcow(data, TabelaKlientow.getNazwyKolumn());
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 70, 680, 250);

		fakturyLab = new JLabel("Faktury wystawcy");
		fakturyLab.setFont(new Font("TimesRoman", Font.BOLD, 20));
		fakturyLab.setBounds(30, 360, 200, 40);
		
		// ---------------------------- TESTOWE
		Object[][] data2 = {
				{"1", "9146/2018", "24.11.2018", 145505.43, 214012.99, "PLN", true, 
					false, "check"}
		};

		listaFaktur = new TabelaFaktur(data2, TabelaFaktur.getNazwyKolumn());
		listaFakturScroll = new JScrollPane(listaFaktur);
		listaFakturScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaFakturScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panelPodListeFaktur = new JPanel();
		panelPodListeFaktur.setLayout(new BorderLayout());
		panelPodListeFaktur.add(listaFakturScroll, BorderLayout.CENTER);
		panelPodListeFaktur.setBounds(30, 400, 680, 250);
		
		nowyWystawca = new JButton("NOWY WYSTAWCA");
		nowyWystawca.setBounds(520, 330, 180, 30);
		
		// ------- panel dodawania
		ramkaDodawania = new RamkaDodawaniaWystawcy("Dodawanie Nowego Wystawcy");
		
		// ------- Listenery
		nowyWystawca.addActionListener(l -> {
			ramkaDodawania.setVisible(true);
			zaslona.setVisible(true);
			
		});
		
		this.add(layeredPane);
		layeredPane.add(tytul, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListe, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(fakturyLab, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListeFaktur, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(nowyWystawca, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(zaslona, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ramkaDodawania, JLayeredPane.MODAL_LAYER);				
	}
	
	private class RamkaDodawaniaWystawcy extends JInternalFrame {

		private JPanel panelDodawania;
		private JLabel imieLab;
		private JLabel nazwiskoLab;
		private JLabel nazwaFirmyLab;
		private JLabel nipLab;
		private JLabel adresLab;
		private JTextField imieTxt;
		private JTextField nazwiskoTxt;
		private JTextField nazwaFirmyTxt;
		private JFormattedTextField nipTxt;
		private JTextField adresTxt;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaWystawcy(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(120, 90, 425, 375);

			panelDodawania = new JPanel();
			panelDodawania.setLayout(null);
			panelDodawania.setBounds(0, 0, 425, 375);

			imieLab = new JLabel("Imie");
			imieLab.setFont(new Font("TimesRoman", Font.BOLD, 20));
			imieLab.setBounds(15, 15, 150, 30);
			nazwiskoLab = new JLabel("Nazwisko");
			nazwiskoLab.setFont(imieLab.getFont());
			nazwiskoLab.setBounds(15, 60, 150, 30);
			nazwaFirmyLab = new JLabel("Nazwa Firmy");
			nazwaFirmyLab.setFont(imieLab.getFont());
			nazwaFirmyLab.setBounds(15, 105, 150, 30);
			nipLab = new JLabel("NIP");
			nipLab.setFont(imieLab.getFont());
			nipLab.setBounds(15, 150, 150, 30);
			adresLab = new JLabel("Adres");
			adresLab.setFont(imieLab.getFont());
			adresLab.setBounds(15, 195, 150, 30);

			imieTxt = new JTextField();
			imieTxt.setFont(new Font("TimesRoman", Font.ITALIC, 15));
			imieTxt.setHorizontalAlignment(JTextField.CENTER);
			imieTxt.setBounds(200, 15, 200, 30);
			nazwiskoTxt = new JTextField();
			nazwiskoTxt.setFont(imieTxt.getFont());
			nazwiskoTxt.setHorizontalAlignment(JTextField.CENTER);
			nazwiskoTxt.setBounds(200, 60, 200, 30);
			nazwaFirmyTxt = new JTextField();
			nazwaFirmyTxt.setFont(imieTxt.getFont());
			nazwaFirmyTxt.setHorizontalAlignment(JTextField.CENTER);
			nazwaFirmyTxt.setBounds(200, 105, 200, 30);
			try {
				nipTxt = new JFormattedTextField(new MaskFormatter("###-###-##-##"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nipTxt.setFont(imieTxt.getFont());
			nipTxt.setHorizontalAlignment(JTextField.CENTER);
			nipTxt.setBounds(200, 150, 200, 30);
			adresTxt = new JTextField();
			adresTxt.setFont(imieTxt.getFont());
			adresTxt.setHorizontalAlignment(JTextField.CENTER);
			adresTxt.setBounds(15, 240, 385, 30);

			dodaj = new JButton("DODAJ");
			dodaj.setBounds(300, 300, 100, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 300, 100, 30);

			panelDodawania.add(imieLab);
			panelDodawania.add(nazwiskoLab);
			panelDodawania.add(nazwaFirmyLab);
			panelDodawania.add(nipLab);
			panelDodawania.add(adresLab);
			panelDodawania.add(imieTxt);
			panelDodawania.add(nazwiskoTxt);
			panelDodawania.add(nazwaFirmyTxt);
			panelDodawania.add(nipTxt);
			panelDodawania.add(adresTxt);
			panelDodawania.add(dodaj);
			panelDodawania.add(anuluj);

			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
				imieTxt.setText("");
				nazwiskoTxt.setText("");
				nazwaFirmyTxt.setText("");
				nipTxt.setText("");
				adresTxt.setText("");
			});

			this.add(panelDodawania);
			this.setVisible(false);
		}

	}
}
