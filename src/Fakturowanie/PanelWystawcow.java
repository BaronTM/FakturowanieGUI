package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class PanelWystawcow extends JPanel {

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
	private JButton usunWystawce;
	private JButton podgladFaktury;
	private JLayeredPane layeredPane;
	private RamkaDodawaniaWystawcy ramkaDodawania;
	private DefaultTableModel modelListyWystawcow;
	private DefaultTableModel modelListyFaktur;

	public PanelWystawcow() {
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
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		tytul = new JLabel("WYSTAWCY");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);

		modelListyWystawcow = new DefaultTableModel(TabelaWystawcow.getNazwyKolumn(), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		lista = new TabelaWystawcow(modelListyWystawcow);
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 70, 680, 250);

		fakturyLab = new JLabel("Faktury wystawcy");
		fakturyLab.setFont(new Font("TimesRoman", Font.BOLD, 20));
		fakturyLab.setBounds(30, 360, 200, 40);

		modelListyFaktur = new DefaultTableModel(TabelaFaktur.getNazwyKolumn(), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		listaFaktur = new TabelaFaktur(modelListyFaktur);
		listaFakturScroll = new JScrollPane(listaFaktur);
		listaFakturScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaFakturScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listaFaktur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panelPodListeFaktur = new JPanel();
		panelPodListeFaktur.setLayout(new BorderLayout());
		panelPodListeFaktur.add(listaFakturScroll, BorderLayout.CENTER);
		panelPodListeFaktur.setBounds(30, 400, 680, 250);

		nowyWystawca = new JButton("NOWY WYSTAWCA");
		nowyWystawca.setBounds(520, 330, 180, 30);
		usunWystawce = new JButton("USUŃ WYSTAWCĘ");
		usunWystawce.setBounds(340, 330, 180, 30);
		podgladFaktury = new JButton("PODGLAD FAKTURY");
		podgladFaktury.setBounds(475, 370, 225, 30);

		// ------- panel dodawania
		ramkaDodawania = new RamkaDodawaniaWystawcy("Dodawanie Nowego Wystawcy");

		// ------- Listenery
		nowyWystawca.addActionListener(l -> {
			ramkaDodawania.setVisible(true);
			zaslona.setVisible(true);
		});
		usunWystawce.addActionListener(l -> {
			int sel = lista.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Nie wybrano wystawcy.", "Błąd", JOptionPane.ERROR_MESSAGE);
			} else {
				if (JOptionPane.showOptionDialog(this, "Czy na pewno chcesz usunąć tego wystawcę?", "Usuwanie",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Tak", "Nie" },
						"Tak") == 0) {
					for (Wystawca k : Statyczne.getHistoria().getWystrawcy()) {
						if (k.toString().equals(lista.getValueAt(sel, 1).toString())) {
							Statyczne.getHistoria().getWystrawcy().remove(k);
							odswiezListy();
							if (k.equals(Statyczne.getUstawienia().getDomyslnyWystawca())) {
								Statyczne.getUstawienia().setDomyslnyWystawca(null);
							}
							break;
						}
					}
				}
			}
		});
		lista.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				pokazFakture();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		podgladFaktury.addActionListener(l -> {
			int sel = listaFaktur.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Nie wybrano faktury.", "Błąd", JOptionPane.ERROR_MESSAGE);
			} else {
				for (Fakturka k : Statyczne.getHistoria().getFaktury()) {
					if (k.getNrFaktury().equals(listaFaktur.getValueAt(sel, 1).toString())) {
						Aplikacja.getPanelPodgladu().setVisible(k, true);
						break;
					}
				}
			}
		});

		this.add(layeredPane);
		layeredPane.add(tytul, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListe, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(fakturyLab, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodListeFaktur, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(nowyWystawca, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(usunWystawce, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(podgladFaktury, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(zaslona, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ramkaDodawania, JLayeredPane.MODAL_LAYER);
	}

	public void odswiezListy() {
		for (int k = modelListyWystawcow.getRowCount(); k > 0; k--) {
			modelListyWystawcow.removeRow(0);
		}
		int i = 0;
		for (Wystawca p : Statyczne.getHistoria().getWystrawcy()) {
			Object[] element = new Object[7];
			float sumaBr = 0;
			float sumaNet = 0;
			int iloscFaktur = 0;
			int iloscZamknietych = 0;
			element[0] = i + 1;
			element[1] = p.toString();
			for (Fakturka f : Statyczne.getHistoria().getFaktury()) {
				if (f.getWystawca().equals(p)) {
					iloscFaktur++;
					sumaBr += f.getCenaKoncowaBrutto();
					sumaNet += f.getCenaKoncowaNetto();
					if (f.isZamknieta()) {
						iloscZamknietych++;
					}
				}
			}
			sumaBr = Aplikacja.zaokraglij(sumaBr);
			sumaNet = Aplikacja.zaokraglij(sumaNet);
			element[2] = Float.toString(sumaNet);
			element[3] = Float.toString(sumaBr);
			element[4] = Statyczne.getUstawienia().getWaluta();
			element[5] = iloscFaktur;
			element[6] = iloscZamknietych;
			modelListyWystawcow.addRow(element);
			i++;
		}
	}

	public void pokazFakture() {
		int sel = lista.getSelectedRow();
		if (sel != -1) {
			for (Wystawca k : Statyczne.getHistoria().getWystrawcy()) {
				if (k.toString().equals(lista.getValueAt(sel, 1).toString())) {
					for (int i = modelListyFaktur.getRowCount(); i > 0; i--) {
						modelListyFaktur.removeRow(0);
					}
					int j = 0;
					for (Fakturka p : Statyczne.getHistoria().getFaktury()) {
						if (p.getWystawca().equals(k)) {
							Object[] element = new Object[8];
							element[0] = j + 1;
							element[1] = p.getNrFaktury();
							element[2] = SimpleDateFormat.getDateInstance(3).format(p.getDataWystawienia());
							element[3] = Float.toString(p.getCenaKoncowaNetto());
							element[4] = Float.toString(p.getCenaKoncowaBrutto());
							element[5] = Statyczne.getUstawienia().getWaluta();
							element[6] = p.isZamknieta();
							element[7] = p.isUwzgledniona();
							modelListyFaktur.addRow(element);
							j++;
						}
					}
				}
			}
		}
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
				nipTxt.setFocusLostBehavior(JFormattedTextField.PERSIST);
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
			dodaj.addActionListener(l -> {
				String imie = imieTxt.getText();
				String nazwisko = nazwiskoTxt.getText();
				String nazwaFirmy = nazwaFirmyTxt.getText();
				String[] nipParts = nipTxt.getText().split("-");
				String nipStr = nipParts[0] + nipParts[1] + nipParts[2] + nipParts[3];
				String adres = adresTxt.getText();
				if (nipStr.contains(" ")) {
					JOptionPane.showMessageDialog(this, "Nieprawidłowy nip.", "Błąd wprowadzania",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Long nip = 0 + Long.parseLong(nipStr);
					if ((nip < 1000000000) || (nazwaFirmy.equals("") && (imie.equals("") || nazwisko.equals("")))) {
						JOptionPane.showMessageDialog(this, "Wprowadź wymagane dane.", "Błąd wprowadzania",
								JOptionPane.ERROR_MESSAGE);
					} else {
						boolean istnieje = false;
						for (Wystawca k : Statyczne.getHistoria().getWystrawcy()) {
							if (Long.compare(k.getNip(), nip) == 0) {
								istnieje = true;
							}
						}
						if (istnieje) {
							JOptionPane.showMessageDialog(this, "Wystawca o takim NIPie juz istnieje.", "Błąd",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Statyczne.getHistoria().getWystrawcy()
									.add(new Wystawca(imie, nazwisko, nazwaFirmy, nip, adres));
							imieTxt.setText("");
							nazwiskoTxt.setText("");
							nazwaFirmyTxt.setText("");
							nipTxt.setText("");
							adresTxt.setText("");
							odswiezListy();
							Statyczne.zapiszHistorie();
							this.setVisible(false);
							zaslona.setVisible(false);
						}
					}
				}
			});

			this.add(panelDodawania);
			this.setVisible(false);
		}

	}
}
