package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PanelKlientow extends JPanel{

	private JLabel tytul;
	private JLabel fakturyLab;
	private TabelaKlientow lista;
	private TabelaFaktur listaFaktur;
	private JScrollPane listaScroll;
	private JScrollPane listaFakturScroll;
	private JPanel panelPodListe;
	private JPanel panelPodListeFaktur;
	private JButton nowyKlient;
	private JInternalFrame ramkaDodawania;
	private JPanel panelDodawania;
	
	public PanelKlientow() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		
		tytul = new JLabel("KLIENCI");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		// ---------------------------- TESTOWE
		Object[][] data = {
				{"1", "NOVAKOWSKI Janusz Nowakowski", 145505.43, 214012.99, "PLN", 160, 
					100}
				};
		
		lista = new TabelaKlientow(data, TabelaKlientow.getNazwyKolumn());
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 70, 680, 250);

		fakturyLab = new JLabel("Faktury klienta");
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
		
		nowyKlient = new JButton("NOWY KLIENT");
		nowyKlient.setBounds(550, 330, 150, 30);
		
		//------- panel dodawania
		ramkaDodawania = new JInternalFrame("Dodawanie Nowego Klienta", false, true, false, false);
		panelDodawania = new JPanel();
		panelDodawania.setBounds(0, 0, 300, 300);
		ramkaDodawania.add(panelDodawania);
		ramkaDodawania.setBounds(30, 30, 300, 300);
		ramkaDodawania.setVisible(true);
		
		this.add(tytul);
		this.add(panelPodListe);
		this.add(fakturyLab);
		this.add(panelPodListeFaktur);
		this.add(nowyKlient);
		this.add(ramkaDodawania);
	}
	
}
