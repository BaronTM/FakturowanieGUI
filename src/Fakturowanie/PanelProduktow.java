package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

public class PanelProduktow extends JPanel {

	private JLabel tytul;
	private JLabel fakturyLab;
	private TabelaProduktow lista;
	private TabelaFaktur listaFaktur;
	private JScrollPane listaScroll;
	private JScrollPane listaFakturScroll;
	private JPanel panelPodListe;
	private JPanel panelPodListeFaktur;
	private JPanel zaslona;
	private JButton nowyProdukt;
	private JButton usunProdukt;
	private JButton podgladFaktury;
	private JLayeredPane layeredPane;
	private RamkaDodawaniaProduktow ramkaDodawania;
	private DefaultTableModel modelListyProduktow;
	private DefaultTableModel modelListyFaktur;

	public PanelProduktow() {
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

		tytul = new JLabel("PRODUKTY");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);

		modelListyProduktow = new DefaultTableModel(TabelaProduktow.getNazwyKolumn(), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		lista = new TabelaProduktow(modelListyProduktow);
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 70, 470, 250);

		fakturyLab = new JLabel("Faktury zawierające produkt");
		fakturyLab.setFont(new Font("TimesRoman", Font.BOLD, 20));
		fakturyLab.setBounds(30, 360, 350, 40);

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

		nowyProdukt = new JButton("NOWY PRODUKT");
		nowyProdukt.setBounds(520, 70, 180, 30);
		usunProdukt = new JButton("USUN PRODUKT");
		usunProdukt.setBounds(520, 100, 180, 30);
		podgladFaktury = new JButton("PODGLAD FAKTURY");
		podgladFaktury.setBounds(475, 370, 225, 30);

		// ------- panel dodawania
		ramkaDodawania = new RamkaDodawaniaProduktow("Dodawanie Nowego Produktu");

		// ------- Listenery
		nowyProdukt.addActionListener(l -> {
			ramkaDodawania.setVisible(true);
			zaslona.setVisible(true);
		});
		usunProdukt.addActionListener(l -> {
			int sel = lista.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Nie wybrano produktu.", "Błąd", JOptionPane.ERROR_MESSAGE);
			} else {
				if (JOptionPane.showOptionDialog(this, "Czy na pewno chcesz usunąć ten produkt?", "Usuwanie",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Tak", "Nie" },
						"Tak") == 0) {
					for (Produkt k : Statyczne.getHistoria().getProdukty()) {
						if (k.getNazwa().equals(lista.getValueAt(sel, 1).toString())) {
							Statyczne.getHistoria().getProdukty().remove(k);
							odswiezListy();
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
		layeredPane.add(nowyProdukt, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(usunProdukt, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(podgladFaktury, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(zaslona, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(ramkaDodawania, JLayeredPane.MODAL_LAYER);
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

	public void pokazFakture() {
		int sel = lista.getSelectedRow();
		if (sel != -1) {
			for (Produkt k : Statyczne.getHistoria().getProdukty()) {
				if (k.getNazwa().equals(lista.getValueAt(sel, 1).toString())) {
					for (int i = modelListyFaktur.getRowCount(); i > 0; i--) {
						modelListyFaktur.removeRow(0);
					}
					int j = 0;
					for (Fakturka p : Statyczne.getHistoria().getFaktury()) {
						for (Pozycja a : p.getListaProduktow()) {
							if (a.getProdukt().equals(k)) {
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
	}

	private class RamkaDodawaniaProduktow extends JInternalFrame {

		private JPanel panelDodawania;
		private JLabel nazwaLab;
		private JLabel cenaLab;
		private JLabel jednostkaLab;
		private JTextField nazwaTxt;
		private JFormattedTextField cenaTxt;
		private JComboBox<String> jednostkaCB;
		private JButton dodaj;
		private JButton anuluj;

		public RamkaDodawaniaProduktow(String nazwa) {
			super(nazwa, false, false, false, false);
			this.setBounds(120, 90, 425, 250);

			panelDodawania = new JPanel();
			panelDodawania.setLayout(null);
			panelDodawania.setBounds(0, 0, 425, 250);

			nazwaLab = new JLabel("Nazwa");
			nazwaLab.setFont(new Font("TimesRoman", Font.BOLD, 20));
			nazwaLab.setBounds(15, 15, 150, 30);
			cenaLab = new JLabel("Cena Netto");
			cenaLab.setFont(nazwaLab.getFont());
			cenaLab.setBounds(15, 60, 150, 30);
			jednostkaLab = new JLabel("Jednostka");
			jednostkaLab.setFont(nazwaLab.getFont());
			jednostkaLab.setBounds(15, 105, 150, 30);

			nazwaTxt = new JTextField();
			nazwaTxt.setFont(new Font("TimesRoman", Font.ITALIC, 15));
			nazwaTxt.setHorizontalAlignment(JTextField.CENTER);
			nazwaTxt.setBounds(200, 15, 200, 30);
			cenaTxt = new JFormattedTextField();
			cenaTxt.setFont(nazwaTxt.getFont());
			cenaTxt.setBounds(200, 60, 200, 30);
			cenaTxt.setHorizontalAlignment(JTextField.CENTER);
			cenaTxt.setFormatterFactory(new AbstractFormatterFactory() {
				@Override
				public AbstractFormatter getFormatter(JFormattedTextField tf) {
					NumberFormat format = DecimalFormat.getInstance();
					format.setMinimumFractionDigits(2);
					format.setMaximumFractionDigits(2);
					format.setRoundingMode(RoundingMode.HALF_UP);
					InternationalFormatter formatter = new InternationalFormatter(format);
					formatter.setAllowsInvalid(false);
					formatter.setMinimum(0.0);
					formatter.setMaximum(1000000.00);
					return formatter;
				}
			});
			cenaTxt.setText(Float.toString(0.00f));
			jednostkaCB = new JComboBox<String>(Produkt.getListaJednostek());
			jednostkaCB.setFont(nazwaTxt.getFont());
			jednostkaCB.setBounds(200, 105, 200, 30);
			jednostkaCB.setSelectedIndex(0);
			dodaj = new JButton("DODAJ");
			dodaj.setBounds(300, 170, 100, 30);
			anuluj = new JButton("ANULUJ");
			anuluj.setBounds(15, 170, 100, 30);

			panelDodawania.add(nazwaLab);
			panelDodawania.add(cenaLab);
			panelDodawania.add(jednostkaLab);
			panelDodawania.add(nazwaTxt);
			panelDodawania.add(cenaTxt);
			panelDodawania.add(jednostkaCB);
			panelDodawania.add(dodaj);
			panelDodawania.add(anuluj);

			// ------- Listenery
			anuluj.addActionListener(l -> {
				this.setVisible(false);
				zaslona.setVisible(false);
				nazwaTxt.setText("");
				cenaTxt.setText("0.00");
				jednostkaCB.setSelectedIndex(0);
			});
			dodaj.addActionListener(l -> {
				// float
				float cenaNowego = 0.0f;
				String cenaStr = "";
				String[] parts = cenaTxt.getText().split(" ");
				for (String s : parts) {
					cenaStr += s;
				}
				String[] partsCom = cenaStr.split(",");
				cenaNowego = Float.parseFloat(partsCom[0]) + Float.parseFloat(partsCom[1]) / 100;
				String nazwaNowego = (String) nazwaTxt.getText();
				String jednostkaNowego = (String) jednostkaCB.getSelectedItem();
				if ((cenaNowego < 0) || (nazwaNowego.equals("")) || jednostkaNowego.equals("")) {
					JOptionPane.showMessageDialog(this, "Wprowadź wymagane dane.", "Błąd wprowadzania",
							JOptionPane.ERROR_MESSAGE);
				} else {
					boolean istnieje = false;
					for (Produkt p : Statyczne.getHistoria().getProdukty()) {
						if (p.getNazwa().equals(nazwaNowego)) {
							istnieje = true;
						}
					}
					if (istnieje) {
						JOptionPane.showMessageDialog(this, "Produkt o takiej nazwie już istnieje.", "Błąd",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Statyczne.getHistoria().getProdukty()
								.add(new Produkt(nazwaNowego, cenaNowego, jednostkaNowego));
						nazwaTxt.setText("");
						cenaTxt.setText("0.00");
						jednostkaCB.setSelectedIndex(0);
						odswiezListy();
						Statyczne.zapiszHistorie();
						this.setVisible(false);
						zaslona.setVisible(false);
					}
				}
			});

			this.add(panelDodawania);
			this.setVisible(false);
		}

	}
}
