package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

public class PanelLimit extends JPanel {

	private JLabel tytul;
	private SwitchBox wlacznik;
	private Font labelFont;
	private JLabel wlacznikLab;
	private JLabel trybLab;
	private JLabel autosumLab;
	private JLabel mansumLab;
	private JLabel kwotaLab;
	private JLabel czestotliwoscLab;
	private JFormattedTextField dataTxt;
	private JFormattedTextField kwotaTxt;
	private JRadioButton trybReczny;
	private JRadioButton trybAutomatyczny;
	private ButtonGroup trybyGrupa;
	private JRadioButton biezacyMiesiac;
	private JRadioButton biezacyRok;
	private ButtonGroup biezacyGrupa;
	private JPanel panelParametrow;
	private JPanel panelReczny;
	private JPanel panelAutomatyczny;
	private JPanel panelPodListeFaktur;
	private JButton zastosuj;
	private JButton podgladFaktury;
	private JButton odswiezListeFaktur;
	private TabelaFaktur listaFaktur;
	private JScrollPane listaFakturScroll;
	private JComboBox<String> limitNetBrut;
	private JComboBox<String> czestotliwoscCB;
	private DefaultTableModel modelListyFaktur;

	public PanelLimit() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Statyczne.getKolor());

		tytul = new JLabel("LIMIT");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);

		labelFont = new Font("TimesRoman", Font.CENTER_BASELINE, 20);

		wlacznikLab = new JLabel("Limit Wł/Wył");
		wlacznikLab.setFont(labelFont);
		wlacznikLab.setBounds(30, 100, 200, 30);
		trybLab = new JLabel("Tryb limitu");
		trybLab.setFont(labelFont);
		trybLab.setBounds(30, 150, 150, 30);
		czestotliwoscLab = new JLabel("Częstotliwość sprawdzania");
		czestotliwoscLab.setFont(labelFont);
		czestotliwoscLab.setBounds(30, 230, 300, 30);

		wlacznik = new SwitchBox("ON", "OFF");
		wlacznik.setBounds(400, 100, 130, 30);
		wlacznik.setSelected(true);

		trybAutomatyczny = new JRadioButton("Tryb automatyczny");
		trybAutomatyczny.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 15));
		trybAutomatyczny.setBounds(400, 150, 200, 30);
		trybAutomatyczny.setBackground(Statyczne.getKolor());
		trybReczny = new JRadioButton("Tryb ręczny");
		trybReczny.setFont(trybAutomatyczny.getFont());
		trybReczny.setBounds(400, 180, 150, 30);
		trybReczny.setBackground(Statyczne.getKolor());
		trybyGrupa = new ButtonGroup();
		trybyGrupa.add(trybReczny);
		trybyGrupa.add(trybAutomatyczny);
		trybAutomatyczny.setSelected(true);

		zastosuj = new JButton("ZASTOSUJ");
		zastosuj.setBounds(550, 620, 150, 30);
		
		czestotliwoscCB = new JComboBox<String>(Statyczne.getUstawienia().getDostepneCzestotliwosci());
		czestotliwoscCB.setFont(trybAutomatyczny.getFont());
		czestotliwoscCB.setBackground(Statyczne.getKolor());
		czestotliwoscCB.setBounds(400, 230, 150, 30);

		// ------- panel trybow limitu

		panelParametrow = new JPanel();
		panelParametrow.setLayout(null);
		panelParametrow.setBounds(0, 280, 740, 320);
		panelParametrow.setBackground(Statyczne.getKolor());
		panelParametrow.setVisible(true);

		kwotaLab = new JLabel("Kwota limitu");
		kwotaLab.setFont(labelFont);
		kwotaLab.setBounds(30, 80, 150, 30);
		
		kwotaTxt = new JFormattedTextField();
		kwotaTxt.setFont(trybAutomatyczny.getFont());
		kwotaTxt.setBounds(400, 80, 120, 30);
		kwotaTxt.setHorizontalAlignment(JTextField.CENTER);
		kwotaTxt.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				NumberFormat format = DecimalFormat.getInstance();
				format.setMinimumFractionDigits(2);
				format.setMaximumFractionDigits(2);
				format.setRoundingMode(RoundingMode.HALF_UP);
				InternationalFormatter formatter = new InternationalFormatter(format);
				formatter.setAllowsInvalid(false);
				formatter.setMinimum(0.0);
				formatter.setMaximum(10000000.00);
				return formatter;
			}
		});
		kwotaTxt.setText(Float.toString(10000.00f));

		limitNetBrut = new JComboBox<String>(Statyczne.getUstawienia().getRodzajeLimitu());
		limitNetBrut.setFont(trybAutomatyczny.getFont());
		limitNetBrut.setBounds(520, 80, 100, 30);
		limitNetBrut.setBackground(Statyczne.getKolor());

		// ------- tryb automatyczny

		autosumLab = new JLabel("Sprawdzaj limit sumując");
		autosumLab.setFont(labelFont);
		autosumLab.setBounds(20, 0, 300, 30);

		biezacyMiesiac = new JRadioButton("Bieżący miesiąc");
		biezacyMiesiac.setFont(trybAutomatyczny.getFont());
		biezacyMiesiac.setBounds(390, 0, 200, 30);
		biezacyMiesiac.setBackground(Statyczne.getKolor());
		biezacyRok = new JRadioButton("Bieżący rok");
		biezacyRok.setFont(trybAutomatyczny.getFont());
		biezacyRok.setBounds(390, 30, 150, 30);
		biezacyRok.setBackground(Statyczne.getKolor());
		biezacyGrupa = new ButtonGroup();
		biezacyGrupa.add(biezacyMiesiac);
		biezacyGrupa.add(biezacyRok);
		biezacyMiesiac.setSelected(true);

		// ------- tryb reczny

		mansumLab = new JLabel("Sprawdzaj limit od dnia");
		mansumLab.setFont(labelFont);
		mansumLab.setBounds(20, 0, 300, 30);

		try {
			dataTxt = new JFormattedTextField(new MaskFormatter("##-##-####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataTxt.setFont(trybAutomatyczny.getFont());
		dataTxt.setBounds(390, 0, 120, 30);
		dataTxt.setHorizontalAlignment(JTextField.CENTER);
		dataTxt.setText("07-12-2018");

		// ------- lista
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

		podgladFaktury = new JButton("PODGLAD FAKTURY");
		podgladFaktury.setBounds(475, 280, 225, 30);
		odswiezListeFaktur = new JButton("ODŚWIEŻ LISTĘ");
		odswiezListeFaktur.setBounds(40, 280, 225, 30);

		panelPodListeFaktur = new JPanel();
		panelPodListeFaktur.setLayout(new BorderLayout());
		panelPodListeFaktur.add(listaFakturScroll, BorderLayout.CENTER);
		panelPodListeFaktur.setBounds(30, 130, 680, 150);

		// ------- panele trybu

		panelAutomatyczny = new JPanel();
		panelAutomatyczny.setLayout(null);
		panelAutomatyczny.setBounds(10, 0, 720, 60);
		panelAutomatyczny.setBackground(Statyczne.getKolor());
		panelAutomatyczny.setVisible(true);

		panelAutomatyczny.add(biezacyMiesiac);
		panelAutomatyczny.add(biezacyRok);
		panelAutomatyczny.add(autosumLab);

		panelReczny = new JPanel();
		panelReczny.setLayout(null);
		panelReczny.setBounds(10, 0, 720, 60);
		panelReczny.setBackground(Statyczne.getKolor());
		panelReczny.setVisible(false);

		panelReczny.add(mansumLab);
		panelReczny.add(dataTxt);

		panelParametrow.add(panelAutomatyczny);
		panelParametrow.add(panelReczny);
		panelParametrow.add(kwotaLab);
		panelParametrow.add(kwotaTxt);
		panelParametrow.add(panelPodListeFaktur);
		panelParametrow.add(podgladFaktury);
		panelParametrow.add(odswiezListeFaktur);
		panelParametrow.add(limitNetBrut);

		// ------- listenery
		wlacznik.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (wlacznik.isSelected()) {
					trybLab.setVisible(false);					
					trybReczny.setVisible(false);
					trybAutomatyczny.setVisible(false);
					czestotliwoscLab.setVisible(false);
					czestotliwoscCB.setVisible(false);
					panelParametrow.setVisible(false);
				} else {
					trybLab.setVisible(true);
					trybReczny.setVisible(true);
					trybAutomatyczny.setVisible(true);
					czestotliwoscLab.setVisible(true);
					czestotliwoscCB.setVisible(true);
					panelParametrow.setVisible(true);
					zaladujUstawieniaOnOff();
				}
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
		zastosuj.addActionListener(l -> {
			zastosuj();
		});
		odswiezListeFaktur.addActionListener(l -> {
			odswiezListeFaktur();
		});

		trybAutomatyczny.addChangeListener(l -> {
			if (trybAutomatyczny.isSelected()) {
				panelReczny.setVisible(false);
				panelAutomatyczny.setVisible(true);
			} else {
				panelReczny.setVisible(true);
				panelAutomatyczny.setVisible(false);
			}
		});

		this.add(tytul);
		this.add(wlacznikLab);
		this.add(trybLab);
		this.add(czestotliwoscLab);
		this.add(czestotliwoscCB);
		this.add(wlacznik);
		this.add(trybReczny);
		this.add(trybAutomatyczny);
		this.add(panelParametrow);
		this.add(zastosuj);
	}

	public void zastosuj() {
		Statyczne.getUstawienia().setLimitOn(wlacznik.isSelected());
		if (wlacznik.isSelected()) {
			if (trybAutomatyczny.isSelected()) {
				Statyczne.getUstawienia().setTrybLimitu(1);
				if (biezacyMiesiac.isSelected()) {
					Statyczne.getUstawienia().setSprawdzajSumujac(1);
				} else if (biezacyRok.isSelected()) {
					Statyczne.getUstawienia().setSprawdzajSumujac(2);
				}
			} else if (trybReczny.isSelected()) {
				Statyczne.getUstawienia().setTrybLimitu(2);
				String[] dateParts = dataTxt.getText().split("-");
				try {
					int day = Integer.parseInt(dateParts[0]);
					int month = Integer.parseInt(dateParts[1]) - 1;
					int year = Integer.parseInt(dateParts[2]) - 1900;
					if ((day < 1) || (day > 31) || (month < 0) || (month > 11) || (year < 0) || (year > 1100)) {
						JOptionPane.showMessageDialog(this, "Niepoprawna data.", "Błąd", JOptionPane.ERROR_MESSAGE);
					} else {
						Date data = new Date(year, month, day);
						Statyczne.getUstawienia().setDataLimitu(data);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Niepoprawna data.", "Błąd", JOptionPane.ERROR_MESSAGE);
					dataTxt.setText("01-01-2018");
				}
			}
			Statyczne.getUstawienia().setCzestotliwosc((String) czestotliwoscCB.getSelectedItem());
			float kwotaLimitu = 0.0f;
			String kwotaStr = "";
			String[] kwotaParts = kwotaTxt.getText().split(" ");
			for (String s : kwotaParts) {
				kwotaStr += s;
			}
			String[] kwotaPartsCom = kwotaStr.split(",");
			kwotaLimitu = Float.parseFloat(kwotaPartsCom[0]) + Float.parseFloat(kwotaPartsCom[1]) / 100;
			Statyczne.getUstawienia().setLimit(kwotaLimitu);
			Statyczne.getUstawienia().setLimitRodzaj((String) limitNetBrut.getSelectedItem());
		}
		Statyczne.zapiszUstawienia();
		Statyczne.zapiszHistorie();
		Aplikacja.resetujIloscMinut();
	}

	public void odswiezListeFaktur() {
		Date data = new Date();
		if (panelParametrow.isVisible()) {
			if (trybAutomatyczny.isSelected()) {
				if (biezacyMiesiac.isSelected()) {
					int month = data.getMonth();
					int year = data.getYear();
					data = new Date(year, month, 1);
				} else if (biezacyRok.isSelected()) {
					int year = data.getYear();
					data = new Date(year, 0, 1);
				}
			} else if (trybReczny.isSelected()) {
				String[] dateParts = dataTxt.getText().split("-");
				try {
					int day = Integer.parseInt(dateParts[0]);
					int month = Integer.parseInt(dateParts[1]) - 1;
					int year = Integer.parseInt(dateParts[2]) - 1900;
					if ((day < 1) || (day > 31) || (month < 0) || (month > 11) || (year < 0) || (year > 1100)) {
						JOptionPane.showMessageDialog(this, "Niepoprawna data.", "Błąd", JOptionPane.ERROR_MESSAGE);
					} else {
						data = new Date(year, month, day);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Niepoprawna data.", "Błąd", JOptionPane.ERROR_MESSAGE);
					dataTxt.setText("01-01-2018");
				}
			}
			for (int i = modelListyFaktur.getRowCount(); i > 0; i--) {
				modelListyFaktur.removeRow(0);
			}			
			int j = 0;
			for (Fakturka k : Statyczne.getHistoria().getFaktury()) {
				if (data.getTime() < k.getDataWystawienia().getTime()) {
					Object[] element = new Object[8];
					element[0] = j + 1;
					element[1] = k.getNrFaktury();
					element[2] = SimpleDateFormat.getDateInstance(3).format(k.getDataWystawienia());
					element[3] = Float.toString(k.getCenaKoncowaNetto());
					element[4] = Float.toString(k.getCenaKoncowaBrutto());
					element[5] = Statyczne.getUstawienia().getWaluta();
					element[6] = k.isZamknieta();
					element[7] = k.isUwzgledniona();
					modelListyFaktur.addRow(element);
					j++;
				}
			}
		}
	}
	
	public void odswiez() {
		if (Statyczne.getUstawienia().isLimitOn()) {
			wlacznik.setSelected(true);
			trybLab.setVisible(true);
			trybReczny.setVisible(true);			
			trybAutomatyczny.setVisible(true);
			czestotliwoscLab.setVisible(true);
			czestotliwoscCB.setVisible(true);
			if (Statyczne.getUstawienia().getTrybLimitu() == 1) {
				panelAutomatyczny.setVisible(true);
				panelReczny.setVisible(false);
			} else if (Statyczne.getUstawienia().getTrybLimitu() == 2) {
				panelAutomatyczny.setVisible(false);
				panelReczny.setVisible(true);
			}
			panelParametrow.setVisible(true);
		} else {
			wlacznik.setSelected(false);
			trybLab.setVisible(false);
			trybReczny.setVisible(false);
			trybAutomatyczny.setVisible(false);
			czestotliwoscLab.setVisible(false);
			czestotliwoscCB.setVisible(false);
			if (Statyczne.getUstawienia().getTrybLimitu() == 1) {
				panelAutomatyczny.setVisible(true);
				panelReczny.setVisible(false);
			} else if (Statyczne.getUstawienia().getTrybLimitu() == 2) {
				panelAutomatyczny.setVisible(false);
				panelReczny.setVisible(true);
			}
			panelParametrow.setVisible(false);
		}
		zaladujUstawieniaOnOff();
	}
	
	public void zaladujUstawieniaOnOff() {
		switch (Statyczne.getUstawienia().getTrybLimitu()) {
		case 1: {
			trybAutomatyczny.setSelected(true);
			
			switch (Statyczne.getUstawienia().getSprawdzajSumujac()) {
			case 1: {
				biezacyMiesiac.setSelected(true);
				break;
			}
			case 2: {
				biezacyRok.setSelected(true);
				break;
			}
			}
			break;
		}
		case 2: {
			trybReczny.setSelected(true);
			String dd = Integer.toString(Statyczne.getUstawienia().getDataLimitu().getDate());
			String mm = Integer.toString(Statyczne.getUstawienia().getDataLimitu().getMonth() + 1);
			String yyyy = Integer.toString(Statyczne.getUstawienia().getDataLimitu().getYear() + 1900);
			if (dd.length() == 1) {
				dd = "0" + dd; 
			}
			if (mm.length() == 1) {
				mm = "0" + mm; 
			}
			dataTxt.setText(dd + "-" + mm + "-" + yyyy);
			break;
		}
		}
		czestotliwoscCB.setSelectedItem(Statyczne.getUstawienia().getCzestotliwosc());
		kwotaTxt.setValue(Statyczne.getUstawienia().getLimit());
		limitNetBrut.setSelectedItem(Statyczne.getUstawienia().getLimitRodzaj());
		odswiezListeFaktur();
	}
	
	public void przywrocUstawieniaDomyslne() {
		
	}
	
}
