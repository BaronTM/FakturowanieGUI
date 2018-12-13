package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class PanelNowejFaktury extends JPanel {

	private JLabel tytul;
	private JLabel klientLab;
	private JLabel wystawcaLab;
	private JLabel formaPlatnosciLab;
	private JTextArea etykietaWystawcy;
	private JTextArea etykietaKlienta;
	private JTextArea kwota;
	private JButton dodajProdukt;
	private JButton usunProdukt;
	private JButton edytujPozycje;
	private JButton zapiszFakture;
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
	private RamkaDodawaniaProduktu ramkaDodawaniaProduktu;
	private RamkaEdycjaPozycji ramkaEdycjaPozycji;
	private DefaultTableModel modelListyZakupow;
	private ArrayList<Pozycja> listaZakupow;
	private Wystawca wystawca;
	private Klient klient;
	private float kwNet;
	private float kwBrut;

	public PanelNowejFaktury() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Statyczne.getKolor());

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 740, 680);
		zaslona = new JPanel();
		zaslona.setLayout(null);
		zaslona.setBounds(0, 0, 740, 680);
		zaslona.setBackground(Statyczne.getKolor());
		zaslona.setVisible(false);
		zaslona.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		listaZakupow = new ArrayList<Pozycja>();

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
		etykietaWystawcy.setBackground(Statyczne.getKolor());

		etykietaKlienta = new JTextArea();
		etykietaKlienta.setFont(czcionkaEtykiet);
		etykietaKlienta.setEditable(false);
		etykietaKlienta.setBackground(Statyczne.getKolor());

		panelWystawcy = new JPanel();
		panelWystawcy.setLayout(new BorderLayout());
		panelWystawcy.add(wystawcaLab, BorderLayout.CENTER);
		panelWystawcy.add(etykietaWystawcy, BorderLayout.SOUTH);
		panelWystawcy.setBackground(Statyczne.getKolor());

		panelKlienta = new JPanel();
		panelKlienta.setLayout(new BorderLayout());
		panelKlienta.add(klientLab, BorderLayout.CENTER);
		panelKlienta.add(etykietaKlienta, BorderLayout.SOUTH);
		panelKlienta.setBackground(Statyczne.getKolor());

		panelPodEtykiety = new JPanel();
		panelPodEtykiety.setLayout(new BorderLayout());
		panelPodEtykiety.setBounds(40, 70, 660, 130);
		panelPodEtykiety.add(panelKlienta, BorderLayout.WEST);
		panelPodEtykiety.add(panelWystawcy, BorderLayout.EAST);
		panelPodEtykiety.setBackground(Statyczne.getKolor());

		dodajProdukt = new JButton("Dodaj produkt");
		dodajProdukt.setFont(new Font("TimesRoman", Font.BOLD, 15));
		dodajProdukt.setBounds(520, 260, 180, 30);

		usunProdukt = new JButton("Usun produkt");
		usunProdukt.setFont(dodajProdukt.getFont());
		usunProdukt.setBounds(370, 260, 150, 30);
		
		edytujPozycje = new JButton("Edytuj pozycję");
		edytujPozycje.setFont(dodajProdukt.getFont());
		edytujPozycje.setBounds(40, 510, 180, 30);

		wybierzKlienta = new JButton("Wybierz Klienta");
		wybierzKlienta.setFont(dodajProdukt.getFont());
		wybierzKlienta.setBounds(40, 210, 200, 30);

		wybierzWystawce = new JButton("Wybierz Wystawcę");
		wybierzWystawce.setFont(dodajProdukt.getFont());
		wybierzWystawce.setBounds(500, 210, 200, 30);

		uwzgledniona = new JCheckBox("Faktura uwzględniona w limicie");
		uwzgledniona.setFont(dodajProdukt.getFont());
		uwzgledniona.setBounds(50, 260, 300, 30);
		uwzgledniona.setSelected(true);
		uwzgledniona.setBackground(Statyczne.getKolor());

		modelListyZakupow = new DefaultTableModel(TabelaZakupow.getNazwyKolumn(), 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		lista = new TabelaZakupow(modelListyZakupow);
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		kwota = new JTextArea();
		kwota.setFont(new Font("TimesRoman", Font.BOLD, 15));
		kwota.setEditable(false);
		kwota.setBackground(Statyczne.getKolor());
		kwota.setText(String.format("Łączna kwonta netto: %.2f " + Statyczne.getUstawienia().getWaluta(), kwNet) + "\n" + 
		String.format("Łączna kwonta brutto: %.2f " + Statyczne.getUstawienia().getWaluta(), kwBrut));

		panelKwot = new JPanel();
		panelKwot.setLayout(new BorderLayout());
		panelKwot.add(kwota, BorderLayout.NORTH);
		panelKwot.setBackground(Statyczne.getKolor());

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
		formaPlatnosciCB.setBackground(Statyczne.getKolor());

		zapiszFakture = new JButton("Zapisz Fakture");
		zapiszFakture.setFont(new Font("TimesRoman", Font.BOLD, 15));
		zapiszFakture.setSize(180, 30);

		panelPrzyciskowDolnych = new JPanel();
		panelPrzyciskowDolnych.setLayout(new BorderLayout());
		panelPrzyciskowDolnych.add(zapiszFakture, BorderLayout.NORTH);
		panelPrzyciskowDolnych.setBackground(Statyczne.getKolor());

		panelPodsumowania = new JPanel();
		panelPodsumowania.setLayout(new BorderLayout());
		panelPodsumowania.add(panelKwot, BorderLayout.EAST);
		panelPodsumowania.add(panelPrzyciskowDolnych, BorderLayout.WEST);
		panelPodsumowania.setBounds(30, 570, 680, 70);
		panelPodsumowania.setBackground(Statyczne.getKolor());

		ramkaDodawaniaKlienta = new RamkaDodawaniaKlienta("Dodawanie Klienta");
		ramkaDodawaniaWystawcy = new RamkaDodawaniaWystawcy("Dodawanie Wystawcy");
		ramkaDodawaniaProduktu = new RamkaDodawaniaProduktu("Dodawanie Produktu");
		ramkaEdycjaPozycji = new RamkaEdycjaPozycji("Edycja Pozycji");
		
		// ------- Listenery
		wybierzKlienta.addActionListener(l -> {
			ramkaDodawaniaKlienta.setVisible(true);
			ramkaDodawaniaKlienta.odswiezListy();
			zaslona.setVisible(true);
		});
		wybierzWystawce.addActionListener(l -> {
			ramkaDodawaniaWystawcy.setVisible(true);
			ramkaDodawaniaWystawcy.odswiezListy();
			zaslona.setVisible(true);
		});
		dodajProdukt.addActionListener(l -> {
			ramkaDodawaniaProduktu.setVisible(true);
			ramkaDodawaniaProduktu.odswiezListy();
			zaslona.setVisible(true);
		});
		usunProdukt.addActionListener(l -> {
			int sel = lista.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Nie wybrano pozycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
			} else {
				if (JOptionPane.showOptionDialog(this, "Czy na pewno chcesz usunąć ten produkt?", "Usuwanie",
						JOptionPane.YES_NO_OPTION, 
					    JOptionPane.QUESTION_MESSAGE,
					    null, new Object[] {"Tak", "Nie"}, "Tak") == 0) {
					for (Pozycja k : listaZakupow) {
						if (k.getProdukt().getNazwa().equals(lista.getValueAt(sel, 1).toString())) {
							listaZakupow.remove(k);
							odswiezListeZakupow();
							break;
						}
					}
				}
			}
		});
		edytujPozycje.addActionListener(l -> {
			int sel = lista.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Nie wybrano pozycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
			} else {
				for (Pozycja k : listaZakupow) {
					if (k.getProdukt().getNazwa().equals(lista.getValueAt(sel, 1).toString())) {
						ramkaEdycjaPozycji.setVisible(true, k);
						zaslona.setVisible(true);
						break;
					}
				}		
			}
		});
		zapiszFakture.addActionListener(l -> {
			zapiszFakture();
		});
		

		this.add(layeredPane);
		layeredPane.add(tytul, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodEtykiety, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListe, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(dodajProdukt, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(usunProdukt, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(edytujPozycje, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(wybierzKlienta, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(wybierzWystawce, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodsumowania, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(uwzgledniona, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(formaPlatnosciCB, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(formaPlatnosciLab, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(zaslona, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ramkaDodawaniaKlienta, JLayeredPane.MODAL_LAYER);
		layeredPane.add(ramkaDodawaniaWystawcy, JLayeredPane.MODAL_LAYER);
		layeredPane.add(ramkaDodawaniaProduktu, JLayeredPane.MODAL_LAYER);
		layeredPane.add(ramkaEdycjaPozycji, JLayeredPane.MODAL_LAYER);
	}

	private void uzupelnijEtykieteWystawcy(Wystawca wystawca) {
		if (wystawca == null) {
			etykietaWystawcy.setText("");
		} else {
			etykietaWystawcy.setText(wystawca.toString());
		}
		this.wystawca = wystawca;
	}

	private void uzupelnijEtykieteKlienta(Klient klient) {
		if (klient == null) {
			etykietaKlienta.setText("");
		} else {
			etykietaKlienta.setText(klient.toString());
		}
		this.klient = klient;
	}
	
	private void zapiszFakture() {
		if ((klient == null) || (wystawca == null) || (modelListyZakupow.getRowCount() == 0)) {
			JOptionPane.showMessageDialog(this, "Nie wszytkie elementy zostały uzupełnione.", "Błąd", JOptionPane.ERROR_MESSAGE);
		} else {
			Fakturka nowa = new Fakturka();
			nowa.setWystawca(wystawca);
			nowa.setKlient(klient);
			nowa.setListaProduktow(listaZakupow);
			nowa.setDataWystawienia(new Date());
			nowa.setZamknieta(false);
			nowa.setFormaPlatnosci((String) formaPlatnosciCB.getSelectedItem());
			nowa.setNrFaktury();
			nowa.setUwzgledniona(uwzgledniona.isSelected());
			if (JOptionPane.showOptionDialog(this, "Czy chcesz zamknąć fakturę?\n"
					+ "Zamknięta faktura nie może być edytowana.", "Usuwanie",
					JOptionPane.YES_NO_OPTION, 
				    JOptionPane.QUESTION_MESSAGE,
				    null, new Object[] {"Tak", "Nie"}, "Tak") == 0) {
				nowa.setZamknieta(true);
			}
			nowa.obliczKwotyKoncowej();
			Statyczne.getHistoria().getFaktury().add(nowa);
			Statyczne.zapiszHistorie();
			Statyczne.zapiszUstawienia();
			wyczysc();
		}
	}
	
	
	public void wyczysc() {
		uzupelnijEtykieteKlienta(null);
		uzupelnijEtykieteWystawcy(Statyczne.getUstawienia().getDomyslnyWystawca());
		listaZakupow = new ArrayList<Pozycja>();
		odswiezListeZakupow();
		formaPlatnosciCB.setSelectedIndex(0);
		uwzgledniona.setSelected(true);
	}

	public void odswiezListeZakupow() {
		for (int k = modelListyZakupow.getRowCount(); k > 0; k--) {
			modelListyZakupow.removeRow(0);
		}
		kwNet = 0;
		kwBrut = 0;
		int i = 0;
		for (Pozycja p : listaZakupow) {
			p.oblicz();
			kwNet += p.getKwotaNettoPoz();
			kwBrut += p.getKwotaBruttoPoz();
			Object[] element = new Object[9];
			element[0] = i + 1;
			element[1] = p.getProdukt().getNazwa();
			element[2] = p.getProdukt().getCenaNetto();
			element[3] = p.getIlosc();
			element[4] = p.getProdukt().getJednostka();
			element[5] = String.format("%.0f%%", p.getVatPoz() * 100);
			element[6] = String.format("%.0f%%", p.getZnizka() * 100);
			element[7] = p.getKwotaNettoPoz();
			element[8] = p.getKwotaBruttoPoz();
			modelListyZakupow.addRow(element);
			i++;
		}
		kwota.setText(String.format("Łączna kwonta netto: %.2f " + Statyczne.getUstawienia().getWaluta(), kwNet) + "\n" + 
		String.format("Łączna kwonta brutto: %.2f " + Statyczne.getUstawienia().getWaluta(), kwBrut));
	}

	private class RamkaDodawaniaKlienta extends JInternalFrame {

		private JPanel panelDodawaniaKlienta;
		private JPanel panelPodListeKlientow;
		private DefaultTableModel modelListyKlientow;
		private JScrollPane listaScrollKlientow;
		private TabelaKlientowFakturka listaKlientow;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaKlienta(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(25, 152, 680, 375);

			panelDodawaniaKlienta = new JPanel();
			panelDodawaniaKlienta.setLayout(null);
			panelDodawaniaKlienta.setBounds(0, 0, 680, 375);

			modelListyKlientow = new DefaultTableModel(TabelaKlientowFakturka.getNazwyKolumn(), 0){
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			listaKlientow = new TabelaKlientowFakturka(modelListyKlientow);
			listaScrollKlientow = new JScrollPane(listaKlientow);
			listaScrollKlientow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			listaScrollKlientow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listaKlientow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
			dodaj.addActionListener(l -> {
				int sel = listaKlientow.getSelectedRow();
				if (sel == -1) {
					JOptionPane.showMessageDialog(this, "Nie wybrano klienta.", "Błąd", JOptionPane.ERROR_MESSAGE);
				} else {
					for (Klient k : Statyczne.getHistoria().getKlienci()) {
						if (k.nipToString().equals(listaKlientow.getValueAt(sel, 4).toString())) {
							uzupelnijEtykieteKlienta(k);
							this.setVisible(false);
							zaslona.setVisible(false);
							break;
						}
					}
				}
			});

			this.add(panelDodawaniaKlienta);
			this.setVisible(false);
		}

		public void odswiezListy() {
			for (int k = modelListyKlientow.getRowCount(); k > 0; k--) {
				modelListyKlientow.removeRow(0);
			}
			int i = 0;
			for (Klient p : Statyczne.getHistoria().getKlienci()) {
				Object[] element = new Object[6];
				element[0] = i + 1;
				element[1] = p.getNazwaFirmy();
				element[2] = p.getImie();
				element[3] = p.getNazwisko();
				element[4] = p.nipToString();
				element[5] = p.getAdres();
				modelListyKlientow.addRow(element);
				i++;
			}
		}
	}

	private class RamkaDodawaniaWystawcy extends JInternalFrame {

		private JPanel panelDodawaniaWystawcy;
		private JPanel panelPodListeWystawcow;
		private DefaultTableModel modelListyWystawcow;
		private JScrollPane listaScrollWystawcow;
		private TabelaWystawcowFakturka listaWystawcow;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaWystawcy(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(25, 152, 680, 375);

			panelDodawaniaWystawcy = new JPanel();
			panelDodawaniaWystawcy.setLayout(null);
			panelDodawaniaWystawcy.setBounds(0, 0, 680, 375);

			modelListyWystawcow = new DefaultTableModel(TabelaWystawcowFakturka.getNazwyKolumn(), 0){
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			listaWystawcow = new TabelaWystawcowFakturka(modelListyWystawcow);
			listaScrollWystawcow = new JScrollPane(listaWystawcow);
			listaScrollWystawcow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			listaScrollWystawcow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listaWystawcow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
			dodaj.addActionListener(l -> {
				int sel = listaWystawcow.getSelectedRow();
				if (sel == -1) {
					JOptionPane.showMessageDialog(this, "Nie wybrano wystawcy.", "Błąd", JOptionPane.ERROR_MESSAGE);
				} else {
					for (Wystawca k : Statyczne.getHistoria().getWystrawcy()) {
						if (k.nipToString().equals(listaWystawcow.getValueAt(sel, 4).toString())) {
							uzupelnijEtykieteWystawcy(k);
							Statyczne.getUstawienia().setDomyslnyWystawca(k);
							this.setVisible(false);
							zaslona.setVisible(false);
							break;
						}
					}
				}
			});

			this.add(panelDodawaniaWystawcy);
			this.setVisible(false);
		}

		public void odswiezListy() {
			for (int k = modelListyWystawcow.getRowCount(); k > 0; k--) {
				modelListyWystawcow.removeRow(0);
			}
			int i = 0;
			for (Wystawca p : Statyczne.getHistoria().getWystrawcy()) {
				Object[] element = new Object[6];
				element[0] = i + 1;
				element[1] = p.getNazwaFirmy();
				element[2] = p.getImie();
				element[3] = p.getNazwisko();
				element[4] = p.nipToString();
				element[5] = p.getAdres();
				modelListyWystawcow.addRow(element);
				i++;
			}
		}
	}

	private class RamkaDodawaniaProduktu extends JInternalFrame {

		private JPanel panelDodawaniaProduktow;
		private JPanel panelPodListeProduktow;
		private DefaultTableModel modelListyProduktow;
		private JScrollPane listaScrollProduktow;
		private TabelaProduktow listaProduktow;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaProduktu(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(130, 152, 480, 375);

			panelDodawaniaProduktow = new JPanel();
			panelDodawaniaProduktow.setLayout(null);
			panelDodawaniaProduktow.setBounds(0, 0, 480, 375);

			modelListyProduktow = new DefaultTableModel(TabelaProduktow.getNazwyKolumn(), 0){
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			listaProduktow = new TabelaProduktow(modelListyProduktow);
			listaScrollProduktow = new JScrollPane(listaProduktow);
			listaScrollProduktow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			listaScrollProduktow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listaProduktow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			panelPodListeProduktow = new JPanel();
			panelPodListeProduktow.setLayout(new BorderLayout());
			panelPodListeProduktow.add(listaScrollProduktow, BorderLayout.CENTER);
			panelPodListeProduktow.setBounds(0, 0, 470, 280);

			dodaj = new JButton("DODAJ");
			dodaj.setBounds(350, 300, 100, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 300, 100, 30);

			panelDodawaniaProduktow.add(panelPodListeProduktow);
			panelDodawaniaProduktow.add(dodaj);
			panelDodawaniaProduktow.add(anuluj);

			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
			});
			dodaj.addActionListener(l -> {
				int sel = listaProduktow.getSelectedRow();
				if (sel == -1) {
					JOptionPane.showMessageDialog(this, "Nie wybrano produktu.", "Błąd", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean zawiera = false;
					for (int i = 0; i < modelListyZakupow.getRowCount(); i++) {
						if (modelListyZakupow.getValueAt(i, 1).toString()
								.equals(listaProduktow.getValueAt(sel, 1).toString())) {
							zawiera = true;
						}
					}
					if (zawiera) {
						JOptionPane.showMessageDialog(this, "Ten produkt został już dodany.", "Błąd",
								JOptionPane.ERROR_MESSAGE);
					} else {
						for (Produkt p : Statyczne.getHistoria().getProdukty()) {
							if (p.getNazwa().equals(listaProduktow.getValueAt(sel, 1).toString())) {
								listaZakupow.add(new Pozycja(p));
								odswiezListeZakupow();
								this.setVisible(false);
								zaslona.setVisible(false);
								break;
							}
						}
					}
				}
			});
			this.add(panelDodawaniaProduktow);
			this.setVisible(false);
		}

		public void odswiezListy() {
			for (int k = modelListyProduktow.getRowCount(); k > 0; k--) {
				modelListyProduktow.removeRow(0);
			}
			int i = 0;
			for (Produkt p : Statyczne.getHistoria().getProdukty()) {
				Object[] element = new Object[5];
				element[0] = i + 1;
				element[1] = p.getNazwa();
				element[2] = p.getCenaNetto();
				element[3] = Statyczne.getUstawienia().getWaluta();
				element[4] = p.getJednostka();
				modelListyProduktow.addRow(element);
				i++;
			}
		}
	}
	
	private class RamkaEdycjaPozycji extends JInternalFrame {

		private JPanel panelEdycjiPozycji;
		private JButton dodaj;
		private JButton anuluj;
		private JLabel nazwaLab;
		private JLabel nazwaTxt;
		private JLabel cenaNettoLab;
		private JLabel cenaNettoTxt;
		private JLabel jednostkaLab;
		private JLabel jednostkaTxt;
		private JLabel iloscLab;
		private JLabel vatLab;
		private JLabel rabatLab;
		private Font fontLab;
		private Font fontTxt;
		private JSpinner iloscSpin;
		private JSpinner vatSpin;
		private JSpinner rabatSpin;
		private Pozycja poz;
		

		public RamkaEdycjaPozycji(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(170, 150, 400, 380);
			
			fontLab = new Font("TimesRoman", Font.BOLD, 20);
			fontTxt = new Font("TimesRoman", Font.ITALIC, 15);
			
			panelEdycjiPozycji = new JPanel();
			panelEdycjiPozycji.setLayout(null);
			panelEdycjiPozycji.setBounds(0, 0, 400, 380);

			nazwaLab = new JLabel("Nazwa:");
			nazwaLab.setFont(fontLab);
			nazwaLab.setBounds(15, 15, 100, 30);
			nazwaTxt = new JLabel("nazwa");
			nazwaTxt.setFont(fontTxt);
			nazwaTxt.setBounds(180, 15, 200, 30);
			nazwaTxt.setHorizontalAlignment(JLabel.CENTER);
			cenaNettoLab = new JLabel("Cena Netto:");
			cenaNettoLab.setFont(fontLab);
			cenaNettoLab.setBounds(15, 60, 150, 30);
			cenaNettoTxt = new JLabel("123456");
			cenaNettoTxt.setFont(fontTxt);
			cenaNettoTxt.setBounds(180, 60, 200, 30);
			cenaNettoTxt.setHorizontalAlignment(JLabel.CENTER);
			jednostkaLab = new JLabel("Jednostka:");
			jednostkaLab.setFont(fontLab);
			jednostkaLab.setBounds(15, 105, 150, 30);
			jednostkaTxt = new JLabel("SZT");
			jednostkaTxt.setFont(fontTxt);
			jednostkaTxt.setBounds(180, 105, 200, 30);
			jednostkaTxt.setHorizontalAlignment(JLabel.CENTER);
			iloscLab = new JLabel("Ilosc:");
			iloscLab.setFont(fontLab);
			iloscLab.setBounds(15, 150, 150, 30);
			vatLab = new JLabel("Vat:");
			vatLab.setFont(fontLab);
			vatLab.setBounds(15, 195, 150, 30);
			rabatLab = new JLabel("Rabat:");
			rabatLab.setFont(fontLab);
			rabatLab.setBounds(15, 240, 150, 30);
			
			iloscSpin = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
			iloscSpin.setBounds(230, 150, 100, 30);
			iloscSpin.setEditor(new JSpinner.NumberEditor(iloscSpin, "###"));
			((JSpinner.DefaultEditor) iloscSpin.getEditor()).getTextField().setEditable(false);
			((JSpinner.DefaultEditor) iloscSpin.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
			
			vatSpin = new JSpinner(new SpinnerNumberModel(Statyczne.getUstawienia().getVat(), 0, 1, 0.01));
			vatSpin.setBounds(230, 195, 100, 30);
			vatSpin.setEditor(new JSpinner.NumberEditor(vatSpin, "### %"));
			((JSpinner.DefaultEditor) vatSpin.getEditor()).getTextField().setEditable(false);
			((JSpinner.DefaultEditor) vatSpin.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
			
			rabatSpin = new JSpinner(new SpinnerNumberModel(0, 0, 1, 0.01));
			rabatSpin.setBounds(230, 240, 100, 30);
			rabatSpin.setEditor(new JSpinner.NumberEditor(rabatSpin, "### %"));
			((JSpinner.DefaultEditor) rabatSpin.getEditor()).getTextField().setEditable(false);
			((JSpinner.DefaultEditor) rabatSpin.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
			
			dodaj = new JButton("DODAJ ZMIANY");
			dodaj.setBounds(220, 300, 150, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 300, 100, 30);

			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
			});
			dodaj.addActionListener(l -> {
				poz.setIlosc((int) iloscSpin.getValue());
				poz.setVatPoz(Float.parseFloat(vatSpin.getValue().toString()));
				poz.setZnizka(Float.parseFloat(rabatSpin.getValue().toString()));
				odswiezListeZakupow();
				this.setVisible(false);
				zaslona.setVisible(false);
			});

			panelEdycjiPozycji.add(dodaj);
			panelEdycjiPozycji.add(anuluj);
			panelEdycjiPozycji.add(nazwaLab);
			panelEdycjiPozycji.add(nazwaTxt);
			panelEdycjiPozycji.add(cenaNettoLab);
			panelEdycjiPozycji.add(cenaNettoTxt);
			panelEdycjiPozycji.add(jednostkaLab);
			panelEdycjiPozycji.add(jednostkaTxt);
			panelEdycjiPozycji.add(iloscLab);
			panelEdycjiPozycji.add(iloscSpin);
			panelEdycjiPozycji.add(vatLab);
			panelEdycjiPozycji.add(vatSpin);
			panelEdycjiPozycji.add(rabatLab);
			panelEdycjiPozycji.add(rabatSpin);
			
			this.add(panelEdycjiPozycji);
			this.setVisible(false);
		}
		
		public void setVisible(boolean vis, Pozycja p) {
			this.setVisible(vis);
			this.poz = p;
			nazwaTxt.setText(poz.getProdukt().getNazwa());
			cenaNettoTxt.setText(Float.toString(poz.getProdukt().getCenaNetto()));
			jednostkaTxt.setText(poz.getProdukt().getJednostka());
			iloscSpin.setValue(poz.getIlosc());
			vatSpin.setValue(poz.getVatPoz());
			rabatSpin.setValue(poz.getZnizka());
		}
	}
}
